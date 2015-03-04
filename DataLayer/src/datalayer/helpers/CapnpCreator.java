package datalayer.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datalayer.objects.findata.YFHistData;
import datalayer.objects.interfaces.ICapnpMsg;

public class CapnpCreator
{
	// TODO - Also Create Converters
	private static Set<Class<? extends ICapnpMsg>> I_CAPNP_MSG_CLASS_SET = new HashSet<>();

	static
	{
		I_CAPNP_MSG_CLASS_SET.add(YFHistData.class);
	}

	public static void main(String[] args) throws Exception
	{
		for (Class<? extends ICapnpMsg> cls : I_CAPNP_MSG_CLASS_SET)
		{
			makeCapnpSchema(cls);
			makeCapnpMsgFile(cls);
			CapnpObjConverterMaker.makeJavaFile(cls);
		}
	}

	private static void makeCapnpMsgFile(Class<? extends ICapnpMsg> msg) throws Exception
	{
		Path compilerPath = Paths.get(CapnpConstants.CAPNP_COMPILER_DIR + CapnpConstants.CAPNP_COMPILER);
		if (!Files.exists(compilerPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler - " + CapnpConstants.CAPNP_COMPILER);
		}

		Path pluginPath = Paths.get(CapnpConstants.CAPNP_COMPILER_DIR + CapnpConstants.CAPNP_COMPILER_JAVA_PLUGIN);
		if (!Files.exists(pluginPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler java plugin - " + CapnpConstants.CAPNP_COMPILER_JAVA_PLUGIN);
		}

		String msgClassName = msg.getSimpleName();
		String schemaFileName = msgClassName.toLowerCase() + CapnpConstants.CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(CapnpConstants.CAPNP_SCHEMA_DIR + schemaFileName);

		Process proc = null;
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(compilerPath.toString(), "compile", "-o" + CapnpConstants.CAPNP_COMPILER_DIR_RELATIVE_PATH + CapnpConstants.CAPNP_COMPILER_JAVA_PLUGIN,
				schemaFilePath.toString());
		processBuilder.directory(new File(Paths.get(CapnpConstants.CAPNP_JAVA_FILE_DIR).toString()));
		proc = processBuilder.start();
		if (proc == null || 0 != proc.waitFor())
		{
			System.out.println("Failed to create java file for " + schemaFileName);
			printStream(proc.getInputStream());
			printStream(proc.getErrorStream());
		}
	}

	private static void makeCapnpSchema(Class<? extends ICapnpMsg> msg) throws Exception
	{
		String msgClassName = msg.getSimpleName();
		String schemaFileName = msgClassName.toLowerCase() + CapnpConstants.CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(CapnpConstants.CAPNP_SCHEMA_DIR + schemaFileName);

		Map<String, CapnpFileFieldInfo> capnpFileFieldMap = null;
		if (Files.exists(schemaFilePath))
		{
			capnpFileFieldMap = getCapnpFileFieldMap(schemaFilePath, msg);
		}

		// Make New Capnp File
		try (BufferedWriter writer = Files.newBufferedWriter(schemaFilePath, StandardCharsets.UTF_8))
		{
			writer.write(getIdFromCompiler() + ";");
			writer.newLine();
			writer.write("using Java = import \"" + CapnpConstants.CAPNP_SCHEMA_DIR_RELATIVE_PATH + "java.capnp\";");
			writer.newLine();
			writer.write("$Java.package(\"" + CapnpConstants.CAPNP_JAVA_FILE_PACKAGE + "\");");
			writer.newLine();
			writer.write("$Java.outerClassname(\"" + msgClassName + CapnpConstants.CAPNP_JAVA_FILE_SUFFIX + "\");");
			writer.newLine();
			writer.newLine();
			writer.write("struct " + msgClassName + "\n{");
			writer.newLine();
			addFields(writer, msg, capnpFileFieldMap);
			writer.write("}");
			writer.flush();
		}
	}

	private static String getIdFromCompiler() throws IOException
	{
		String capnpDirPath = CapnpConstants.CAPNP_COMPILER_DIR;
		Path compilerPath = Paths.get(capnpDirPath + CapnpConstants.CAPNP_COMPILER);
		Process proc = Runtime.getRuntime().exec(compilerPath.toString() + " id");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream())))
		{
			return br.readLine();
		}
	}

	private static String getTypeForCapnp(Field field)
	{
		if (field.getType() == int.class || field.getType() == Integer.class) return "UInt32";
		if (field.getType() == long.class || field.getType() == Long.class) return "UInt64";
		if (field.getType() == float.class || field.getType() == Float.class) return "UInt32";
		if (field.getType() == double.class || field.getType() == Double.class) return "Float64";
		if (field.getType() == boolean.class || field.getType() == Boolean.class) return "Bool";
		if (field.getType() == String.class) return "Text";
		return null;
	}

	private static void printStream(InputStream stream) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream)))
		{
			String line = br.readLine();
			while (line != null)
			{
				System.out.println(line);
				line = br.readLine();
			}
		}
	}

	private static void addFields(BufferedWriter writer, Class<? extends ICapnpMsg> msg, Map<String, CapnpFileFieldInfo> capnpFileFieldMap) throws Exception
	{
		Field[] fields = msg.getDeclaredFields();
		Map<Integer, String> linesToWrite = new TreeMap<Integer, String>();
		int fieldIndex = 0;

		if (capnpFileFieldMap != null) // old fields
		{
			for (CapnpFileFieldInfo capnpFieldInfo : capnpFileFieldMap.values())
			{
				linesToWrite.put(capnpFieldInfo.index, capnpFieldInfo.line);
				fieldIndex = Math.max(fieldIndex, capnpFieldInfo.index + 1);
			}
		}

		for (Field field : fields) // new fields
		{
			String type = getTypeForCapnp(field);
			if (type == null) throw new Exception("Failed to create .capnp file for " + msg.getSimpleName() + " due to Field " + field.getName());
			String firstChar = field.getName().substring(0, 1).toLowerCase();
			String fieldName = firstChar + field.getName().substring(1);
			CapnpFileFieldInfo capnpFieldInfo = capnpFileFieldMap == null ? null : capnpFileFieldMap.get(fieldName);
			if (capnpFieldInfo == null)
			{
				linesToWrite.put(Integer.valueOf(fieldIndex), "\t" + fieldName + " @" + fieldIndex++ + " :" + type + ";");
			}
		}

		for (String line : linesToWrite.values())
		{
			writer.write(line);
			writer.newLine();
		}
	}

	private static Map<String, CapnpFileFieldInfo> getCapnpFileFieldMap(Path schemaFilePath, Class<? extends ICapnpMsg> msg) throws IOException
	{
		Map<String, CapnpFileFieldInfo> fieldMap = null;
		try (BufferedReader reader = Files.newBufferedReader(schemaFilePath, StandardCharsets.UTF_8))
		{
			String regex = "^\t([a-z]{1}([a-zA-Z_0-9]*)) @([0-9]+) :.*;$";
			Pattern pattern = Pattern.compile(regex);
			fieldMap = new TreeMap<String, CapnpFileFieldInfo>();
			String line = reader.readLine();
			while (line != null)
			{
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches())
				{
					fieldMap.put(matcher.group(1), new CapnpFileFieldInfo(Integer.parseInt(matcher.group(3)), line));
				}
				line = reader.readLine();
			}
		}
		return fieldMap;
	}

	private static class CapnpFileFieldInfo
	{
		public final int index;
		public final String line;

		public CapnpFileFieldInfo(int index, String line)
		{
			this.index = index;
			this.line = line;
		}
	}
}
