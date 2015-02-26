package datalayer.access;

import java.util.HashMap;
import java.util.Map;

import org.capnproto.MessageBuilder;

import datalayer.objects.YData;
import datalayer.objects.interfaces.ICapnpMsg;

public class CapnpObjHelper
{
	private static final CapnpObjHelper converter = new CapnpObjHelper();

	private final Map<Class<? extends ICapnpMsg>, Class<? extends org.capnproto.MessageBuilder>> javaObjClsToCapnpMsgBuilderClsMap;

	private CapnpObjHelper()
	{
		this.javaObjClsToCapnpMsgBuilderClsMap = new HashMap<>();
	}

	public final CapnpObjHelper getHelper()
	{
		return CapnpObjHelper.converter;
	}

	@SuppressWarnings("unchecked")
	public void convertToCapnpMsg(ICapnpMsg msg)
	{
		Class<? extends ICapnpMsg> capnpMsgCls = msg.getClass();
		Class<? extends MessageBuilder> msgBuilderCls = this.javaObjClsToCapnpMsgBuilderClsMap.get(capnpMsgCls);
		if (msgBuilderCls == null)
		{
			String clsName = capnpMsgCls.getSimpleName();
			String builderClsName = CapnpConstants.CAPNP_JAVA_FILE_PACKAGE + "." + clsName + CapnpConstants.CAPNP_JAVA_FILE_SUFFIX;
			try
			{
				msgBuilderCls = (Class<? extends MessageBuilder>) Class.forName(builderClsName);
				this.javaObjClsToCapnpMsgBuilderClsMap.put(capnpMsgCls, msgBuilderCls);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				return;
			}
			// TODO - Helper in progress
			// TODO - Put Converter in map
			System.out.println(msgBuilderCls);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException
	{
		CapnpObjHelper.converter.convertToCapnpMsg(new YData());
	}

}
