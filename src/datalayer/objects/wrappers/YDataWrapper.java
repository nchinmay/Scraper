package datalayer.objects.wrappers;

import org.capnproto.MessageBuilder;
import org.capnproto.StructFactory;
import org.capnproto.StructReader;

import datalayer.objects.msg.YDataMsg.YData.Builder;

public class YDataWrapper
{
	// TODO - explore this path
	private final StructFactory<Builder, StructReader> factory;
	private final MessageBuilder messageBuilder;
	private final Builder builder;

	public YDataWrapper(final StructFactory<Builder, StructReader> factory)
	{
		this.factory = factory;
		this.messageBuilder = new MessageBuilder();
		this.builder = this.messageBuilder.initRoot(factory);
	}

	public StructFactory<Builder, StructReader> getFactory()
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
