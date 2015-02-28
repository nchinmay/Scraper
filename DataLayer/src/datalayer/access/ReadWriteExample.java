package datalayer.access;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.capnproto.MessageReader;
import org.capnproto.SerializePacked;

import runutil.RunHelper;
import datalayer.objects.findata.YFData;
import datalayer.objects.msg.converters.YFDataConverter;

public class ReadWriteExample
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		try
		{
			YFData m = new YFData();
			m.setSymbol("AAPL");
			SerializePacked.writeToUnbuffered((new FileOutputStream(RunHelper.getTodayRunDataDirectory() + m.getSymbol() + ".data")).getChannel(), YFDataConverter.convert(m));
			MessageReader r = SerializePacked.readFromUnbuffered((new FileInputStream(RunHelper.getTodayRunDataDirectory() + m.getSymbol() + ".data")).getChannel());
			System.out.println(YFDataConverter.convert(r).toString());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
