package datalayer.access;

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
import java.util.Set;

import runutil.RunHelper;
import datalayer.objects.YData;
import datalayer.objects.interfaces.ICapnpMsg;

public class CapnpCreator
{
	public static final String CAPNP_FILE_EXT = ".capnp";
	public static final String CAPNP_JAVA_FILE_DIR = RunHelper.getCurrentWorkingDirectory() + "src/datalayer/objects/msg" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER_DIR = RunHelper.getCurrentWorkingDirectory() + "capnp" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR = CAPNP_COMPILER_DIR + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER_DIR_RELATIVE_PATH = "../../../../capnp/" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR_RELATIVE_PATH = CAPNP_COMPILER_DIR_RELATIVE_PATH + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER = "capnp.exe";
	public static final String CAPNP_COMPILER_JAVA_PLUGIN = "capnpc-java.exe";
	public static final String CAPNP_JAVA_FILE_PACKAGE = "datalayer.objects.msg";

	private static Set<Class<? extends ICapnpMsg>> I_CAPNP_MSG_CLASS_SET = new HashSet<>();

	static
	{
		I_CAPNP_MSG_CLASS_SET.add(YData.class);
	}

	public static void main(String[] args) throws Exception
	{
		for (Class<? extends ICapnpMsg> cls : I_CAPNP_MSG_CLASS_SET)
		{
			makeCapnpSchema(cls);
			makeCapnpMsgFile(cls);
		}
	}

	private static void makeCapnpMsgFile(Class<? extends ICapnpMsg> msg) throws Exception
	{
		Path compilerPath = Paths.get(CAPNP_COMPILER_DIR + CAPNP_COMPILER);
		if (!Files.exists(compilerPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler - " + CAPNP_COMPILER);
		}

		Path pluginPath = Paths.get(CAPNP_COMPILER_DIR + CAPNP_COMPILER_JAVA_PLUGIN);
		if (!Files.exists(pluginPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler java plugin - " + CAPNP_COMPILER_JAVA_PLUGIN);
		}

		String msgClassName = msg.getSimpleName();
		String schemaFileName = msgClassName.toLowerCase() + CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(CAPNP_SCHEMA_DIR + schemaFileName);

		Process proc = null;
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(compilerPath.toString(), "compile", "-ojava", schemaFilePath.toString());
		processBuilder.directory(new File(Paths.get(CAPNP_JAVA_FILE_DIR).toString()));
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
		String schemaFileName = msgClassName.toLowerCase() + CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(CAPNP_SCHEMA_DIR + schemaFileName);

		if (!Files.exists(schemaFilePath))
		{
			// Make New Capnp File
			try (BufferedWriter writer = Files.newBufferedWriter(schemaFilePath, StandardCharsets.UTF_8))
			{
				writer.write(getIdFromCompiler() + ";");
				writer.newLine();
				writer.write("using Java = import \"" + CAPNP_SCHEMA_DIR_RELATIVE_PATH + "java.capnp\";");
				writer.newLine();
				writer.write("$Java.package(\"" + CAPNP_JAVA_FILE_PACKAGE + "\");");
				writer.newLine();
				writer.write("$Java.outerClassname(\"" + msgClassName + "Msg\");");
				writer.newLine();
				writer.newLine();
				writer.write("struct " + msgClassName + "\n{");
				writer.newLine();
				addFields(writer, msg);
				writer.write("}");
				writer.flush();
			}
		}
		else
		{
			// TODO - Update Existing Capnp File
		}
	}

	private static String getIdFromCompiler() throws IOException
	{
		String capnpDirPath = CAPNP_COMPILER_DIR;
		Path compilerPath = Paths.get(capnpDirPath + CAPNP_COMPILER);
		Process proc = Runtime.getRuntime().exec(compilerPath.toString() + " id");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream())))
		{
			return br.readLine();
		}
	}

	private static void addFields(BufferedWriter writer, Class<? extends ICapnpMsg> msg) throws Exception
	{
		Field[] fields = msg.getDeclaredFields();
		int fieldIndex = 0;
		for (Field field : fields)
		{
			String type = getTypeForCapnp(field);
			if (type == null) throw new Exception("Failed to create .capnp file for " + msg.getSimpleName() + " due to Field " + field.getName());
			writer.write("\t" + field.getName().toLowerCase() + " @" + fieldIndex++ + " :" + type + ";");
			writer.newLine();
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
}
