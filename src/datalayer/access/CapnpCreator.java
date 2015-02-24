package datalayer.access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import runutil.RunHelper;
import datalayer.objects.IMsg;
import datalayer.objects.YData;

public class CapnpCreator
{
	public static final String CAPNP_FILE_EXT = ".capnp";
	public static final String CAPNP_COMPILER_DIR = RunHelper.getCurrentWorkingDirectory() + "capnp" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR = CAPNP_COMPILER_DIR + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER = "capnp.exe";
	public static final String CAPNP_COMPILER_JAVA_PLUGIN = "capnpc-java.exe";
	public static final String CAPNP_JAVA_FILE_PACKAGE = "datalayer.objects.msg";
	public static final String CAPNP_JAVA_FILE_PATH = RunHelper.getCurrentWorkingDirectory() + "src/datalayer/objects" + RunHelper.PATH_DELIM;

	public static void main(String[] args) throws Exception
	{
		// Stuff in progress
		makeCapnpSchema(YData.class);
		makeCapnpMsgFile(YData.class);
	}

	public static void makeCapnpMsgFile(Class<? extends IMsg> msg) throws Exception
	{
		String capnpPath = CAPNP_COMPILER_DIR;
		Path compilerPath = Paths.get(capnpPath + CAPNP_COMPILER);
		if (!Files.exists(compilerPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler - " + CAPNP_COMPILER);
		}

		Path pluginPath = Paths.get(capnpPath + CAPNP_COMPILER_JAVA_PLUGIN);
		if (!Files.exists(pluginPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler java plugin - " + CAPNP_COMPILER_JAVA_PLUGIN);
		}

		String msgClassName = msg.getSimpleName();
		String schemaFileName = msgClassName.toLowerCase() + CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(CAPNP_SCHEMA_DIR + schemaFileName);

		// TODO -- FIX THIS
		System.out.println(compilerPath.toString() + " compile -ojava " + schemaFilePath.toString());
		Runtime.getRuntime().exec(compilerPath.toString() + " compile -ojava " + schemaFilePath.toString());
	}

	public static void makeCapnpSchema(Class<? extends IMsg> msg) throws Exception
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
				writer.write("using Java = import \"java.capnp\";");
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

	public static String getIdFromCompiler() throws IOException
	{
		String capnpDirPath = CAPNP_COMPILER_DIR;
		Path compilerPath = Paths.get(capnpDirPath + CAPNP_COMPILER);
		Process proc = Runtime.getRuntime().exec(compilerPath.toString() + " id");
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		return br.readLine();
	}

	private static void addFields(BufferedWriter writer, Class<? extends IMsg> msg) throws Exception
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
}
