package datalayer.objects.msg.converters;

import datalayer.objects.interfaces.ICapnpMsg;
import datalayer.objects.findata.YFFundamentalData;
import datalayer.objects.msg.YFFundamentalDataMsg;
import datalayer.objects.msg.YFFundamentalDataMsg.YFFundamentalData.Builder;
import datalayer.objects.msg.YFFundamentalDataMsg.YFFundamentalData.Reader;
import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;

public class YFFundamentalDataConverter
{
	public static MessageBuilder convert(ICapnpMsg msg)
	{
		YFFundamentalData m = (YFFundamentalData) msg;
		MessageBuilder mb = new MessageBuilder();
		Builder b = mb.initRoot(YFFundamentalDataMsg.YFFundamentalData.factory);
		if(m.getSymbol() != null) b.setSymbol(m.getSymbol());
		if(m.getName() != null) b.setName(m.getName());
		if(m.getStockExchange() != null) b.setStockExchange(m.getStockExchange());
		b.setYearLow(m.getYearLow());
		b.setYearHigh(m.getYearHigh());
		b.setChangeFromYearLow(m.getChangeFromYearLow());
		b.setChangeFromYearHigh(m.getChangeFromYearHigh());
		b.setPercentChangeFromYearLow(m.getPercentChangeFromYearLow());
		b.setPercentChangeFromYearHigh(m.getPercentChangeFromYearHigh());
		b.setFiftydayMovingAverage(m.getFiftydayMovingAverage());
		b.setChangeFromFiftydayMovingAverage(m.getChangeFromFiftydayMovingAverage());
		b.setTwoHundreddayMovingAverage(m.getTwoHundreddayMovingAverage());
		b.setChangeFromTwoHundreddayMovingAverage(m.getChangeFromTwoHundreddayMovingAverage());
		b.setAverageDailyVolume(m.getAverageDailyVolume());
		b.setPERatio(m.getPERatio());
		b.setPEGRatio(m.getPEGRatio());
		b.setPriceSales(m.getPriceSales());
		b.setPriceBook(m.getPriceBook());
		b.setEBITDA(m.getEBITDA());
		b.setMarketCapitalization(m.getMarketCapitalization());
		b.setEarningsShare(m.getEarningsShare());
		b.setEPSEstimateCurrentYear(m.getEPSEstimateCurrentYear());
		b.setEPSEstimateNextYear(m.getEPSEstimateNextYear());
		b.setEPSEstimateNextQuarter(m.getEPSEstimateNextQuarter());
		b.setDividendShare(m.getDividendShare());
		b.setDividendYield(m.getDividendYield());
		if(m.getExDividendDate() != null) b.setExDividendDate(m.getExDividendDate());
		if(m.getDividendPayDate() != null) b.setDividendPayDate(m.getDividendPayDate());
		b.setPriceEPSEstimateCurrentYear(m.getPriceEPSEstimateCurrentYear());
		b.setPriceEPSEstimateNextYear(m.getPriceEPSEstimateNextYear());
		b.setOneyrTargetPrice(m.getOneyrTargetPrice());
		return mb;
	}
	public static YFFundamentalData convert(MessageReader mr)
	{
		YFFundamentalData m = new YFFundamentalData();
		Reader b = mr.getRoot(YFFundamentalDataMsg.YFFundamentalData.factory);
		m.setSymbol(b.getSymbol() == null ? null : b.getSymbol().toString());
		m.setName(b.getName() == null ? null : b.getName().toString());
		m.setStockExchange(b.getStockExchange() == null ? null : b.getStockExchange().toString());
		m.setYearLow(b.getYearLow());
		m.setYearHigh(b.getYearHigh());
		m.setChangeFromYearLow(b.getChangeFromYearLow());
		m.setChangeFromYearHigh(b.getChangeFromYearHigh());
		m.setPercentChangeFromYearLow(b.getPercentChangeFromYearLow());
		m.setPercentChangeFromYearHigh(b.getPercentChangeFromYearHigh());
		m.setFiftydayMovingAverage(b.getFiftydayMovingAverage());
		m.setChangeFromFiftydayMovingAverage(b.getChangeFromFiftydayMovingAverage());
		m.setTwoHundreddayMovingAverage(b.getTwoHundreddayMovingAverage());
		m.setChangeFromTwoHundreddayMovingAverage(b.getChangeFromTwoHundreddayMovingAverage());
		m.setAverageDailyVolume(b.getAverageDailyVolume());
		m.setPERatio(b.getPERatio());
		m.setPEGRatio(b.getPEGRatio());
		m.setPriceSales(b.getPriceSales());
		m.setPriceBook(b.getPriceBook());
		m.setEBITDA(b.getEBITDA());
		m.setMarketCapitalization(b.getMarketCapitalization());
		m.setEarningsShare(b.getEarningsShare());
		m.setEPSEstimateCurrentYear(b.getEPSEstimateCurrentYear());
		m.setEPSEstimateNextYear(b.getEPSEstimateNextYear());
		m.setEPSEstimateNextQuarter(b.getEPSEstimateNextQuarter());
		m.setDividendShare(b.getDividendShare());
		m.setDividendYield(b.getDividendYield());
		m.setExDividendDate(b.getExDividendDate() == null ? null : b.getExDividendDate().toString());
		m.setDividendPayDate(b.getDividendPayDate() == null ? null : b.getDividendPayDate().toString());
		m.setPriceEPSEstimateCurrentYear(b.getPriceEPSEstimateCurrentYear());
		m.setPriceEPSEstimateNextYear(b.getPriceEPSEstimateNextYear());
		m.setOneyrTargetPrice(b.getOneyrTargetPrice());
		return m;
	}
}
