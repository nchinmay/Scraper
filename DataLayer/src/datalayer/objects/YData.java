package datalayer.objects;

import org.capnproto.MessageBuilder;
import org.capnproto.StructFactory;

import datalayer.objects.msg.YDataMsg;
import datalayer.objects.msg.YDataMsg.YData.Builder;
import datalayer.objects.msg.YDataMsg.YData.Reader;

public class YData
{
	// TODO - explore this path
	private final StructFactory<Builder, Reader> factory = YDataMsg.YData.factory;
	private final MessageBuilder messageBuilder;
	private final Builder builder;

	public YData()
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
