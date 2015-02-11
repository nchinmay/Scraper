package datafetching;

public class YData implements ICSVAble {
	private String Symbol;
	private String Name;

	private String YearLow;
	private String YearHigh;
	private String FiftydayMovingAverage;
	private String ChangeFromFiftydayMovingAverage;
	private String TwoHundreddayMovingAverage;
	private String ChangeFromTwoHundreddayMovingAverage;
	private String AverageDailyVolume;

	private String PERatio;
	private String PEGRatio;
	private String EBITDA;
	private String MarketCapitalization;

	private String EarningsShare;
	private String EPSEstimateCurrentYear;
	private String EPSEstimateNextYear;
	private String EPSEstimateNextQuarter;
	private String DividendShare;
	private String DividendYield;
	private String ExDividendDate;
	private String PriceEPSEstimateCurrentYear;
	private String PriceEPSEstimateNextYear;
	private String OneyrTargetPrice;

	@Override
	public String getCSVHeader() {
		return "Symbol, Name, YearLow, YearHigh, FiftydayMovingAverage, ChangeFromFiftydayMovingAverage, TwoHundreddayMovingAverage, ChangeFromTwoHundreddayMovingAverage, AverageDailyVolume, PERatio, PEGRatio, EBITDA, MarketCapitalization, EarningsShare, EPSEstimateCurrentYear, EPSEstimateNextYear, EPSEstimateNextQuarter, DividendShare, DividendYield, ExDividendDate, PriceEPSEstimateCurrentYear, PriceEPSEstimateNextYear, OneyrTargetPrice";
	}

	@Override
	public String getRow() {
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

	@Override
	public String getRowKey() {
		return this.Symbol;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getYearLow() {
		return YearLow;
	}

	public void setYearLow(String yearLow) {
		YearLow = yearLow;
	}

	public String getYearHigh() {
		return YearHigh;
	}

	public void setYearHigh(String yearHigh) {
		YearHigh = yearHigh;
	}

	public String getFiftydayMovingAverage() {
		return FiftydayMovingAverage;
	}

	public void setFiftydayMovingAverage(String fiftydayMovingAverage) {
		FiftydayMovingAverage = fiftydayMovingAverage;
	}

	public String getChangeFromFiftydayMovingAverage() {
		return ChangeFromFiftydayMovingAverage;
	}

	public void setChangeFromFiftydayMovingAverage(
			String changeFromFiftydayMovingAverage) {
		ChangeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
	}

	public String getTwoHundreddayMovingAverage() {
		return TwoHundreddayMovingAverage;
	}

	public void setTwoHundreddayMovingAverage(String twoHundreddayMovingAverage) {
		TwoHundreddayMovingAverage = twoHundreddayMovingAverage;
	}

	public String getChangeFromTwoHundreddayMovingAverage() {
		return ChangeFromTwoHundreddayMovingAverage;
	}

	public void setChangeFromTwoHundreddayMovingAverage(
			String changeFromTwoHundreddayMovingAverage) {
		ChangeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
	}

	public String getAverageDailyVolume() {
		return AverageDailyVolume;
	}

	public void setAverageDailyVolume(String averageDailyVolume) {
		AverageDailyVolume = averageDailyVolume;
	}

	public String getPERatio() {
		return PERatio;
	}

	public void setPERatio(String pERatio) {
		PERatio = pERatio;
	}

	public String getPEGRatio() {
		return PEGRatio;
	}

	public void setPEGRatio(String pEGRatio) {
		PEGRatio = pEGRatio;
	}

	public String getEBITDA() {
		return EBITDA;
	}

	public void setEBITDA(String eBITDA) {
		EBITDA = eBITDA;
	}

	public String getMarketCapitalization() {
		return MarketCapitalization;
	}

	public void setMarketCapitalization(String marketCapitalization) {
		MarketCapitalization = marketCapitalization;
	}

	public String getEarningsShare() {
		return EarningsShare;
	}

	public void setEarningsShare(String earningsShare) {
		EarningsShare = earningsShare;
	}

	public String getEPSEstimateCurrentYear() {
		return EPSEstimateCurrentYear;
	}

	public void setEPSEstimateCurrentYear(String ePSEstimateCurrentYear) {
		EPSEstimateCurrentYear = ePSEstimateCurrentYear;
	}

	public String getEPSEstimateNextYear() {
		return EPSEstimateNextYear;
	}

	public void setEPSEstimateNextYear(String ePSEstimateNextYear) {
		EPSEstimateNextYear = ePSEstimateNextYear;
	}

	public String getEPSEstimateNextQuarter() {
		return EPSEstimateNextQuarter;
	}

	public void setEPSEstimateNextQuarter(String ePSEstimateNextQuarter) {
		EPSEstimateNextQuarter = ePSEstimateNextQuarter;
	}

	public String getDividendShare() {
		return DividendShare;
	}

	public void setDividendShare(String dividendShare) {
		DividendShare = dividendShare;
	}

	public String getDividendYield() {
		return DividendYield;
	}

	public void setDividendYield(String dividendYield) {
		DividendYield = dividendYield;
	}

	public String getExDividendDate() {
		return ExDividendDate;
	}

	public void setExDividendDate(String exDividendDate) {
		ExDividendDate = exDividendDate;
	}

	public String getPriceEPSEstimateCurrentYear() {
		return PriceEPSEstimateCurrentYear;
	}

	public void setPriceEPSEstimateCurrentYear(
			String priceEPSEstimateCurrentYear) {
		PriceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}

	public String getPriceEPSEstimateNextYear() {
		return PriceEPSEstimateNextYear;
	}

	public void setPriceEPSEstimateNextYear(String priceEPSEstimateNextYear) {
		PriceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}

	public String getOneyrTargetPrice() {
		return OneyrTargetPrice;
	}

	public void setOneyrTargetPrice(String oneyrTargetPrice) {
		OneyrTargetPrice = oneyrTargetPrice;
	}

	@Override
	public String toString() {
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