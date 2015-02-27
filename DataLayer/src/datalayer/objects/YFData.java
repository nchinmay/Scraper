package datalayer.objects;

import org.capnproto.MessageBuilder;
import org.capnproto.StructFactory;

import datalayer.objects.msg.YFDataMsg;
import datalayer.objects.msg.YFDataMsg.YFData.Builder;
import datalayer.objects.msg.YFDataMsg.YFData.Reader;

public class YFData
{
	// TODO - explore this path
	private final StructFactory<Builder, Reader> factory = YFDataMsg.YFData.factory;
	private final MessageBuilder messageBuilder;
	private final Builder builder;

	public YFData()
	{
		this.messageBuilder = new MessageBuilder();
		this.builder = this.messageBuilder.initRoot(factory);
	}

	public StructFactory<Builder, Reader> getFactory()
	{
		return factory;
	}

	public MessageBuilder getMessageBuilder()
	{
		return messageBuilder;
	}

	public Builder getBuilder()
	{
		return builder;
	}
}
