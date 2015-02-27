package datalayer.access;

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

import datalayer.objects.csvable.YFData;
import datalayer.objects.interfaces.ICapnpMsg;

/**
 * PLEASE DO NOT FUCK WITH THIS FILE
 */
public class CapnpObjConverterMaker
{
	public static String makeConverterCode(Class<? extends ICapnpMsg> cls)
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
		// out.println("import " + capnpMsgFileCls.getCanonicalName() + "." + cls.getSimpleName() + ".Reader;");
		out.println("import " + MessageBuilder.class.getCanonicalName() + ";");
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
			String clsFieldGetterName = "get" + field.getName();
			String msgClsFieldSetterName = "set" + field.getName();
			out.println("		b." + msgClsFieldSetterName + "(m." + clsFieldGetterName + "());");
		}
		out.println("		return mb;");
		out.println("	}");

		// Capnp Java Msg -> ICapnpMsg
		out.println("	public static " + cls.getSimpleName() + " convert(" + messageBuilderClsName + " mb)");
		out.println("	{");
		out.println("		" + cls.getSimpleName() + " m = new " + cls.getSimpleName() + "();");
		out.println("		Builder b = mb.initRoot(" + capnpMsgFileCls.getSimpleName() + "." + cls.getSimpleName() + "." + "factory);");
		for (Field field : cls.getDeclaredFields())
		{
			String clsFieldSetterName = "set" + field.getName();
			String msgClsFieldGetterName = "get" + field.getName();
			if (field.getType() == String.class) out.println("		m." + clsFieldSetterName + "(b." + msgClsFieldGetterName + "() == null ? null : b." + msgClsFieldGetterName
					+ "().toString());");
			else out.println("		m." + clsFieldSetterName + "(b." + msgClsFieldGetterName + "());");

		}
		out.println("		return m;");
		out.println("	}");

		out.println("}");

		return writer.toString();
	}

	public static void makeJavaFile(Class<? extends ICapnpMsg> cls, String code)
	{
		Path converterFilePath = Paths.get(CapnpConstants.CAPNP_JAVA_CONVERTER_DIR + cls.getSimpleName() + CapnpConstants.CAPNP_JAVA_CONVERTER_SUFFIX + ".java");
		try (BufferedWriter writer = Files.newBufferedWriter(converterFilePath, StandardCharsets.UTF_8))
		{
			writer.write(code);
			writer.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace(); // TODO -- Better Error Logging
		}
	}

	/**
	 * TEST STUFF
	 */
	public static void main(String[] args)
	{
		makeJavaFile(YFData.class, makeConverterCode(YFData.class));
	}
}
