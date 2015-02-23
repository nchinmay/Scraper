package datalayer.access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public static final String CAPNP_COMPILER_DIR = RunHelper.PATH_DELIM + "capnp" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR = CAPNP_COMPILER_DIR + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER = "capnp.exe";
	public static final String CAPNP_COMPILER_JAVA_PLUGIN = "capnpc-java.exe";

	public static void main(String[] args) throws IOException
	{
		String capnpPath = RunHelper.getCurrentWorkingDirectory() + CAPNP_COMPILER_DIR;

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

		// Stuff in progress
		makeCapnpSchema(YData.class);
	}

	public static void makeCapnpSchema(Class<? extends IMsg> msg) throws IOException
	{
		String schemaFileName = msg.getSimpleName().toLowerCase() + CAPNP_FILE_EXT;
		Path schemaFilePath = Paths.get(RunHelper.getCurrentWorkingDirectory() + CAPNP_SCHEMA_DIR + schemaFileName);

		if (!Files.exists(schemaFilePath))
		{
			// Make New Capnp File
			try (BufferedWriter writer = Files.newBufferedWriter(schemaFilePath, StandardCharsets.UTF_8))
			{
				writer.write(getIdFromCompiler() + ";");
				writer.newLine();
				writer.write("using Java = import \"java.capnp\";");
				writer.newLine();
				writer.write("$Java.package(\"" + msg.getPackage().toString().replaceAll("package", "").trim() + "\");");
				writer.newLine();
				writer.write("$Java.outerClassname(\"" + msg.getSimpleName() + "CP\");");
				writer.newLine();
				writer.newLine();

				writer.flush();
			}
		}
		else
		{
			// Update Existing Capnp File
		}
	}

	public static String getIdFromCompiler() throws IOException
	{
		String capnpDirPath = RunHelper.getCurrentWorkingDirectory() + CAPNP_COMPILER_DIR;
		Path compilerPath = Paths.get(capnpDirPath + CAPNP_COMPILER);
		if (!Files.exists(compilerPath))
		{
			throw new FileNotFoundException("Failed to find capnp compiler - " + CAPNP_COMPILER);
		}
		Process proc = Runtime.getRuntime().exec(compilerPath.toString() + " id");
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		return br.readLine();
	}
}
