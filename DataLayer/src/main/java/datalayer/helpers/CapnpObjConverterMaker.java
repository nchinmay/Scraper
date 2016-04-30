package datalayer.helpers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;

import datalayer.objects.findata.YFData;
import datalayer.objects.interfaces.ICapnpMsg;

/**
 * PLEASE DO NOT FUCK WITH THIS FILE
 */
public class CapnpObjConverterMaker
{
	public static void makeJavaFile(Class<? extends ICapnpMsg> cls)
	{
		Path converterFilePath = Paths.get(CapnpConstants.CAPNP_JAVA_CONVERTER_DIR + cls.getSimpleName() + CapnpConstants.CAPNP_JAVA_CONVERTER_SUFFIX + ".java");
		try (BufferedWriter writer = Files.newBufferedWriter(converterFilePath, StandardCharsets.UTF_8))
		{
			writer.write(makeConverterCode(cls));
			writer.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace(); // TODO -- Better Error Logging
		}
	}

	private static String makeConverterCode(Class<? extends ICapnpMsg> cls)
	{
		Class<?> capnpMsgFileCls = null;
		try
		{
			capnpMsgFileCls = Class.forName(CapnpConstants.CAPNP_JAVA_FILE_PACKAGE + "." + cls.getSimpleName() + CapnpConstants.CAPNP_JAVA_FILE_SUFFIX);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace(); // TODO -- Better Error Logging
			return null;
		}

		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);

		out.println("package " + CapnpConstants.CAPNP_JAVA_CONVERTER_PACKAGE + ";");
		out.println();
		out.println("import " + ICapnpMsg.class.getCanonicalName() + ";");
		out.println("import " + cls.getCanonicalName() + ";");
		out.println("import " + capnpMsgFileCls.getCanonicalName() + ";");
		out.println("import " + capnpMsgFileCls.getCanonicalName() + "." + cls.getSimpleName() + ".Builder;");
		out.println("import " + capnpMsgFileCls.getCanonicalName() + "." + cls.getSimpleName() + ".Reader;");
		out.println("import " + MessageBuilder.class.getCanonicalName() + ";");
		out.println("import " + MessageReader.class.getCanonicalName() + ";");
		out.println();
		out.println("public class " + cls.getSimpleName() + CapnpConstants.CAPNP_JAVA_CONVERTER_SUFFIX);
		out.println("{");

		// Functions
		// ICapnpMsg -> Capnp Java Msg
		String messageBuilderClsName = MessageBuilder.class.getSimpleName();
		out.println("	public static " + messageBuilderClsName + " convert(" + ICapnpMsg.class.getSimpleName() + " msg)");
		out.println("	{");
		out.println("		" + cls.getSimpleName() + " m = (" + cls.getSimpleName() + ") msg;");
		out.println("		" + messageBuilderClsName + " mb = new " + messageBuilderClsName + "();");
		out.println("		Builder b = mb.initRoot(" + capnpMsgFileCls.getSimpleName() + "." + cls.getSimpleName() + "." + "factory);");
		for (Field field : cls.getDeclaredFields())
		{
			String fieldNameForFunction = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			String clsFieldGetterName = "get" + fieldNameForFunction;
			String msgClsFieldSetterName = "set" + fieldNameForFunction;
			if (field.getType() == String.class) out.println("		if(m." + clsFieldGetterName + "() != null) b." + msgClsFieldSetterName + "(m." + clsFieldGetterName + "());");
			else out.println("		b." + msgClsFieldSetterName + "(m." + clsFieldGetterName + "());");
		}
		out.println("		return mb;");
		out.println("	}");

		// Capnp Java Msg -> ICapnpMsg
		String messageReaderClsName = MessageReader.class.getSimpleName();
		out.println("	public static " + cls.getSimpleName() + " convert(" + messageReaderClsName + " mr)");
		out.println("	{");
		out.println("		" + cls.getSimpleName() + " m = new " + cls.getSimpleName() + "();");
		out.println("		Reader b = mr.getRoot(" + capnpMsgFileCls.getSimpleName() + "." + cls.getSimpleName() + "." + "factory);");
		for (Field field : cls.getDeclaredFields())
		{
			String fieldNameForFunction = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			String clsFieldSetterName = "set" + fieldNameForFunction;
			String msgClsFieldGetterName = "get" + fieldNameForFunction;
			if (field.getType() == String.class) out.println("		m." + clsFieldSetterName + "(b." + msgClsFieldGetterName + "() == null ? null : b." + msgClsFieldGetterName
					+ "().toString());");
			else out.println("		m." + clsFieldSetterName + "(b." + msgClsFieldGetterName + "());");

		}
		out.println("		return m;");
		out.println("	}");

		out.println("}");

		return writer.toString();
	}

	/**
	 * TEST STUFF
	 */
	public static void main(String[] args)
	{
		makeJavaFile(YFData.class);
	}
}
