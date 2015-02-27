package datalayer.access;

import runutil.RunHelper;

public class CapnpConstants
{
	public static final String CAPNP_FILE_EXT = ".capnp";
	public static final String CAPNP_JAVA_FILE_SUFFIX = "Msg";
	public static final String CAPNP_JAVA_FILE_DIR = RunHelper.getCurrentWorkingDirectory() + "/src/datalayer/objects/msg" + RunHelper.PATH_DELIM;
	public static final String CAPNP_JAVA_CONVERTER_DIR = CAPNP_JAVA_FILE_DIR + "/converters" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER_DIR = RunHelper.getCurrentWorkingDirectory() + "/capnp" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR = CAPNP_COMPILER_DIR + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER_DIR_RELATIVE_PATH = "../../../../capnp" + RunHelper.PATH_DELIM;
	public static final String CAPNP_SCHEMA_DIR_RELATIVE_PATH = CAPNP_COMPILER_DIR_RELATIVE_PATH + "schema" + RunHelper.PATH_DELIM;
	public static final String CAPNP_COMPILER = "capnp.exe";
	public static final String CAPNP_COMPILER_JAVA_PLUGIN = "capnpc-java.exe";
	public static final String CAPNP_JAVA_FILE_PACKAGE = "datalayer.objects.msg";

	public static final String CAPNP_JAVA_CONVERTER_SUFFIX = "Converter";
	public static final String CAPNP_JAVA_CONVERTER_PACKAGE = "datalayer.objects.msg.converters";
}
