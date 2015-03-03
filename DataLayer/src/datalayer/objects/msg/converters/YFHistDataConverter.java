package datalayer.objects.msg.converters;

import datalayer.objects.interfaces.ICapnpMsg;
import datalayer.objects.findata.YFHistData;
import datalayer.objects.msg.YFHistDataMsg;
import datalayer.objects.msg.YFHistDataMsg.YFHistData.Builder;
import datalayer.objects.msg.YFHistDataMsg.YFHistData.Reader;
import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;

public class YFHistDataConverter
{
	public static MessageBuilder convert(ICapnpMsg msg)
	{
		YFHistData m = (YFHistData) msg;
		MessageBuilder mb = new MessageBuilder();
		Builder b = mb.initRoot(YFHistDataMsg.YFHistData.factory);
		if(m.getSymbol() != null) b.setSymbol(m.getSymbol());
		b.setDate(m.getDate());
		b.setOpen(m.getOpen());
		b.setHigh(m.getHigh());
		b.setLow(m.getLow());
		b.setClose(m.getClose());
		b.setAdv(m.getAdv());
		b.setAdjClose(m.getAdjClose());
		return mb;
	}
	public static YFHistData convert(MessageReader mr)
	{
		YFHistData m = new YFHistData();
		Reader b = mr.getRoot(YFHistDataMsg.YFHistData.factory);
		m.setSymbol(b.getSymbol() == null ? null : b.getSymbol().toString());
		m.setDate(b.getDate());
		m.setOpen(b.getOpen());
		m.setHigh(b.getHigh());
		m.setLow(b.getLow());
		m.setClose(b.getClose());
		m.setAdv(b.getAdv());
		m.setAdjClose(b.getAdjClose());
		return m;
	}
}
