package datalayer.objects;

import datalayer.objects.interfaces.ICSVMsg;
import datalayer.objects.interfaces.ICapnpMsg;

public class YData implements ICSVMsg, ICapnpMsg
{
	private String Symbol;
	private String Name;
	private String StockExchange;

	private double YearLow;
	private double YearHigh;
	private double ChangeFromYearLow;
	private double ChangeFromYearHigh;
	private double PercentChangeFromYearLow;
	private double PercentChangeFromYearHigh;
	private double FiftydayMovingAverage;
	private double ChangeFromFiftydayMovingAverage;
	private double TwoHundreddayMovingAverage;
	private double ChangeFromTwoHundreddayMovingAverage;

	private long AverageDailyVolume;

	private double PERatio;
	private double PEGRatio;
	private double PriceSales;
	private double PriceBook;
	private double EBITDA;
	private double MarketCapitalization;

	private double EarningsShare;
	private double EPSEstimateCurrentYear;
	private double EPSEstimateNextYear;
	private double EPSEstimateNextQuarter;
	private double DividendShare;
	private double DividendYield;
	private String ExDividendDate;
	private String DividendPayDate;
	private double PriceEPSEstimateCurrentYear;
	private double PriceEPSEstimateNextYear;
	private double OneyrTargetPrice;

	public String getSymbol()
	{
		return Symbol;
	}

	public void setSymbol(String symbol)
	{
		Symbol = symbol;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public String getStockExchange()
	{
		return StockExchange;
	}

	public void setStockExchange(String stockExchange)
	{
		StockExchange = stockExchange;
	}

	public double getYearLow()
	{
		return YearLow;
	}

	public void setYearLow(double yearLow)
	{
		YearLow = yearLow;
	}

	public double getYearHigh()
	{
		return YearHigh;
	}

	public void setYearHigh(double yearHigh)
	{
		YearHigh = yearHigh;
	}

	public double getChangeFromYearLow()
	{
		return ChangeFromYearLow;
	}

	public void setChangeFromYearLow(double changeFromYearLow)
	{
		ChangeFromYearLow = changeFromYearLow;
	}

	public double getPercentChangeFromYearLow()
	{
		return PercentChangeFromYearLow;
	}

	public void setPercentChangeFromYearLow(double percentChangeFromYearLow)
	{
		PercentChangeFromYearLow = percentChangeFromYearLow;
	}

	public double getChangeFromYearHigh()
	{
		return ChangeFromYearHigh;
	}

	public void setChangeFromYearHigh(double changeFromYearHigh)
	{
		ChangeFromYearHigh = changeFromYearHigh;
	}

	public double getPercentChangeFromYearHigh()
	{
		return PercentChangeFromYearHigh;
	}

	public void setPercentChangeFromYearHigh(double percentChangeFromYearHigh)
	{
		PercentChangeFromYearHigh = percentChangeFromYearHigh;
	}

	public double getFiftydayMovingAverage()
	{
		return FiftydayMovingAverage;
	}

	public void setFiftydayMovingAverage(double fiftydayMovingAverage)
	{
		FiftydayMovingAverage = fiftydayMovingAverage;
	}

	public double getChangeFromFiftydayMovingAverage()
	{
		return ChangeFromFiftydayMovingAverage;
	}

	public void setChangeFromFiftydayMovingAverage(double changeFromFiftydayMovingAverage)
	{
		ChangeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
	}

	public double getTwoHundreddayMovingAverage()
	{
		return TwoHundreddayMovingAverage;
	}

	public void setTwoHundreddayMovingAverage(double twoHundreddayMovingAverage)
	{
		TwoHundreddayMovingAverage = twoHundreddayMovingAverage;
	}

	public double getChangeFromTwoHundreddayMovingAverage()
	{
		return ChangeFromTwoHundreddayMovingAverage;
	}

	public void setChangeFromTwoHundreddayMovingAverage(double changeFromTwoHundreddayMovingAverage)
	{
		ChangeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
	}

	public long getAverageDailyVolume()
	{
		return AverageDailyVolume;
	}

	public void setAverageDailyVolume(long averageDailyVolume)
	{
		AverageDailyVolume = averageDailyVolume;
	}

	public double getPERatio()
	{
		return PERatio;
	}

	public void setPERatio(double pERatio)
	{
		PERatio = pERatio;
	}

	public double getPEGRatio()
	{
		return PEGRatio;
	}

	public void setPEGRatio(double pEGRatio)
	{
		PEGRatio = pEGRatio;
	}

	public double getPriceSales()
	{
		return PriceSales;
	}

	public void setPriceSales(double priceSales)
	{
		PriceSales = priceSales;
	}

	public double getPriceBook()
	{
		return PriceBook;
	}

	public void setPriceBook(double priceBook)
	{
		PriceBook = priceBook;
	}

	public double getEBITDA()
	{
		return EBITDA;
	}

	public void setEBITDA(double eBITDA)
	{
		EBITDA = eBITDA;
	}

	public double getMarketCapitalization()
	{
		return MarketCapitalization;
	}

	public void setMarketCapitalization(double marketCapitalization)
	{
		MarketCapitalization = marketCapitalization;
	}

	public double getEarningsShare()
	{
		return EarningsShare;
	}

	public void setEarningsShare(double earningsShare)
	{
		EarningsShare = earningsShare;
	}

	public double getEPSEstimateCurrentYear()
	{
		return EPSEstimateCurrentYear;
	}

	public void setEPSEstimateCurrentYear(double ePSEstimateCurrentYear)
	{
		EPSEstimateCurrentYear = ePSEstimateCurrentYear;
	}

	public double getEPSEstimateNextYear()
	{
		return EPSEstimateNextYear;
	}

	public void setEPSEstimateNextYear(double ePSEstimateNextYear)
	{
		EPSEstimateNextYear = ePSEstimateNextYear;
	}

	public double getEPSEstimateNextQuarter()
	{
		return EPSEstimateNextQuarter;
	}

	public void setEPSEstimateNextQuarter(double ePSEstimateNextQuarter)
	{
		EPSEstimateNextQuarter = ePSEstimateNextQuarter;
	}

	public double getDividendShare()
	{
		return DividendShare;
	}

	public void setDividendShare(double dividendShare)
	{
		DividendShare = dividendShare;
	}

	public double getDividendYield()
	{
		return DividendYield;
	}

	public void setDividendYield(double dividendYield)
	{
		DividendYield = dividendYield;
	}

	public String getExDividendDate()
	{
		return ExDividendDate;
	}

	public void setExDividendDate(String exDividendDate)
	{
		ExDividendDate = exDividendDate;
	}

	public String getDividendPayDate()
	{
		return DividendPayDate;
	}

	public void setDividendPayDate(String dividendPayDate)
	{
		DividendPayDate = dividendPayDate;
	}

	public double getPriceEPSEstimateCurrentYear()
	{
		return PriceEPSEstimateCurrentYear;
	}

	public void setPriceEPSEstimateCurrentYear(double priceEPSEstimateCurrentYear)
	{
		PriceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}

	public double getPriceEPSEstimateNextYear()
	{
		return PriceEPSEstimateNextYear;
	}

	public void setPriceEPSEstimateNextYear(double priceEPSEstimateNextYear)
	{
		PriceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}

	public double getOneyrTargetPrice()
	{
		return OneyrTargetPrice;
	}

	public void setOneyrTargetPrice(double oneyrTargetPrice)
	{
		OneyrTargetPrice = oneyrTargetPrice;
	}

	public String getRowKey()
	{
		return Symbol;
	}

	public String getCSVHeader()
	{
		return "Symbol,Name,StockExchange,YearLow,YearHigh,ChangeFromYearLow,ChangeFromYearHigh,PercentChangeFromYearLow,PercentChangeFromYearHigh,FiftydayMovingAverage,ChangeFromFiftydayMovingAverage,TwoHundreddayMovingAverage,ChangeFromTwoHundreddayMovingAverage,AverageDailyVolume,PERatio,PEGRatio,PriceSales,PriceBook,EBITDA,MarketCapitalization,EarningsShare,EPSEstimateCurrentYear,EPSEstimateNextYear,EPSEstimateNextQuarter,DividendShare,DividendYield,ExDividendDate,DividendPayDate,PriceEPSEstimateCurrentYear,PriceEPSEstimateNextYear,OneyrTargetPrice";
	}

	public String getRow()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(Symbol);
		builder.append(",");
		builder.append(Name);
		builder.append(",");
		builder.append(StockExchange);
		builder.append(",");
		builder.append(YearLow);
		builder.append(",");
		builder.append(YearHigh);
		builder.append(",");
		builder.append(ChangeFromYearLow);
		builder.append(",");
		builder.append(ChangeFromYearHigh);
		builder.append(",");
		builder.append(PercentChangeFromYearLow);
		builder.append(",");
		builder.append(PercentChangeFromYearHigh);
		builder.append(",");
		builder.append(FiftydayMovingAverage);
		builder.append(",");
		builder.append(ChangeFromFiftydayMovingAverage);
		builder.append(",");
		builder.append(TwoHundreddayMovingAverage);
		builder.append(",");
		builder.append(ChangeFromTwoHundreddayMovingAverage);
		builder.append(",");
		builder.append(AverageDailyVolume);
		builder.append(",");
		builder.append(PERatio);
		builder.append(",");
		builder.append(PEGRatio);
		builder.append(",");
		builder.append(PriceSales);
		builder.append(",");
		builder.append(PriceBook);
		builder.append(",");
		builder.append(EBITDA);
		builder.append(",");
		builder.append(MarketCapitalization);
		builder.append(",");
		builder.append(EarningsShare);
		builder.append(",");
		builder.append(EPSEstimateCurrentYear);
		builder.append(",");
		builder.append(EPSEstimateNextYear);
		builder.append(",");
		builder.append(EPSEstimateNextQuarter);
		builder.append(",");
		builder.append(DividendShare);
		builder.append(",");
		builder.append(DividendYield);
		builder.append(",");
		builder.append(ExDividendDate);
		builder.append(",");
		builder.append(DividendPayDate);
		builder.append(",");
		builder.append(PriceEPSEstimateCurrentYear);
		builder.append(",");
		builder.append(PriceEPSEstimateNextYear);
		builder.append(",");
		builder.append(OneyrTargetPrice);
		return builder.toString();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("YData [Symbol=");
		builder.append(Symbol);
		builder.append(", Name=");
		builder.append(Name);
		builder.append(", StockExchange=");
		builder.append(StockExchange);
		builder.append(", YearLow=");
		builder.append(YearLow);
		builder.append(", YearHigh=");
		builder.append(YearHigh);
		builder.append(", ChangeFromYearLow=");
		builder.append(ChangeFromYearLow);
		builder.append(", ChangeFromYearHigh=");
		builder.append(ChangeFromYearHigh);
		builder.append(", PercentChangeFromYearLow=");
		builder.append(PercentChangeFromYearLow);
		builder.append(", PercentChangeFromYearHigh=");
		builder.append(PercentChangeFromYearHigh);
		builder.append(", FiftydayMovingAverage=");
		builder.append(FiftydayMovingAverage);
		builder.append(", ChangeFromFiftydayMovingAverage=");
		builder.append(ChangeFromFiftydayMovingAverage);
		builder.append(", TwoHundreddayMovingAverage=");
		builder.append(TwoHundreddayMovingAverage);
		builder.append(", ChangeFromTwoHundreddayMovingAverage=");
		builder.append(ChangeFromTwoHundreddayMovingAverage);
		builder.append(", AverageDailyVolume=");
		builder.append(AverageDailyVolume);
		builder.append(", PERatio=");
		builder.append(PERatio);
		builder.append(", PEGRatio=");
		builder.append(PEGRatio);
		builder.append(", PriceSales=");
		builder.append(PriceSales);
		builder.append(", PriceBook=");
		builder.append(PriceBook);
		builder.append(", EBITDA=");
		builder.append(EBITDA);
		builder.append(", MarketCapitalization=");
		builder.append(MarketCapitalization);
		builder.append(", EarningsShare=");
		builder.append(EarningsShare);
		builder.append(", EPSEstimateCurrentYear=");
		builder.append(EPSEstimateCurrentYear);
		builder.append(", EPSEstimateNextYear=");
		builder.append(EPSEstimateNextYear);
		builder.append(", EPSEstimateNextQuarter=");
		builder.append(EPSEstimateNextQuarter);
		builder.append(", DividendShare=");
		builder.append(DividendShare);
		builder.append(", DividendYield=");
		builder.append(DividendYield);
		builder.append(", ExDividendDate=");
		builder.append(ExDividendDate);
		builder.append(", DividendPayDate=");
		builder.append(DividendPayDate);
		builder.append(", PriceEPSEstimateCurrentYear=");
		builder.append(PriceEPSEstimateCurrentYear);
		builder.append(", PriceEPSEstimateNextYear=");
		builder.append(PriceEPSEstimateNextYear);
		builder.append(", OneyrTargetPrice=");
		builder.append(OneyrTargetPrice);
		builder.append("]");
		return builder.toString();
	}

}