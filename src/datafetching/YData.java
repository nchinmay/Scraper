package datafetching;

public class YData implements ICSVAble
{
	private String Symbol;
	private String Name;

	private double YearLow;
	private double YearHigh;
	private double FiftydayMovingAverage;
	private double ChangeFromFiftydayMovingAverage;
	private double TwoHundreddayMovingAverage;
	private double ChangeFromTwoHundreddayMovingAverage;
	private long AverageDailyVolume;

	private double PERatio;
	private double PEGRatio;
	private double EBITDA;
	private double MarketCapitalization;

	private double EarningsShare;
	private double EPSEstimateCurrentYear;
	private double EPSEstimateNextYear;
	private double EPSEstimateNextQuarter;
	private double DividendShare;
	private double DividendYield;
	private String ExDividendDate;
	private double PriceEPSEstimateCurrentYear;
	private double PriceEPSEstimateNextYear;
	private double OneyrTargetPrice;

	public String getCSVHeader()
	{
		return "Symbol, Name, YearLow, YearHigh, FiftydayMovingAverage, ChangeFromFiftydayMovingAverage, TwoHundreddayMovingAverage, ChangeFromTwoHundreddayMovingAverage, AverageDailyVolume, PERatio, PEGRatio, EBITDA, MarketCapitalization, EarningsShare, EPSEstimateCurrentYear, EPSEstimateNextYear, EPSEstimateNextQuarter, DividendShare, DividendYield, ExDividendDate, PriceEPSEstimateCurrentYear, PriceEPSEstimateNextYear, OneyrTargetPrice";
	}

	public String getRow()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(Symbol);
		builder.append(", ");
		builder.append(Name);
		builder.append(", ");
		builder.append(YearLow);
		builder.append(", ");
		builder.append(YearHigh);
		builder.append(", ");
		builder.append(FiftydayMovingAverage);
		builder.append(", ");
		builder.append(ChangeFromFiftydayMovingAverage);
		builder.append(", ");
		builder.append(TwoHundreddayMovingAverage);
		builder.append(", ");
		builder.append(ChangeFromTwoHundreddayMovingAverage);
		builder.append(", ");
		builder.append(AverageDailyVolume);
		builder.append(", ");
		builder.append(PERatio);
		builder.append(", ");
		builder.append(PEGRatio);
		builder.append(", ");
		builder.append(EBITDA);
		builder.append(", ");
		builder.append(MarketCapitalization);
		builder.append(", ");
		builder.append(EarningsShare);
		builder.append(", ");
		builder.append(EPSEstimateCurrentYear);
		builder.append(", ");
		builder.append(EPSEstimateNextYear);
		builder.append(", ");
		builder.append(EPSEstimateNextQuarter);
		builder.append(", ");
		builder.append(DividendShare);
		builder.append(", ");
		builder.append(DividendYield);
		builder.append(", ");
		builder.append(ExDividendDate);
		builder.append(", ");
		builder.append(PriceEPSEstimateCurrentYear);
		builder.append(", ");
		builder.append(PriceEPSEstimateNextYear);
		builder.append(", ");
		builder.append(OneyrTargetPrice);
		return builder.toString();
	}

	public String getRowKey()
	{
		return this.Symbol;
	}

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

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("YData [Symbol=");
		builder.append(Symbol);
		builder.append(", Name=");
		builder.append(Name);
		builder.append(", YearLow=");
		builder.append(YearLow);
		builder.append(", YearHigh=");
		builder.append(YearHigh);
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