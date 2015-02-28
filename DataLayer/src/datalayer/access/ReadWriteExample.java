package datalayer.access;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		Path testFilePath = null;
		try
		{
			YFData m = new YFData();
			m.setSymbol("AAPL");
			testFilePath = Paths.get(RunHelper.getTodayRunDataDirectory() + m.getSymbol() + ".data");
			SerializePacked.writeToUnbuffered((new FileOutputStream(testFilePath.toString())).getChannel(), YFDataConverter.convert(m));
			MessageReader r = SerializePacked.readFromUnbuffered((new FileInputStream(testFilePath.toString())).getChannel());
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
