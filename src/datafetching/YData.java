package datafetching;

public class YData {
	private String symbol;

	private String yearLow;
	private String yearHigh;
	private String fiftydayMovingAverage;
	private String changeFromFiftydayMovingAverage;
	private String twoHundreddayMovingAverage;
	private String changeFromTwoHundreddayMovingAverage;
	private String averageDailyVolume;

	private String pERatio;
	private String pEGRatio;
	private String eBITDA;
	private String marketCapitalization;

	private String earningsShare;
	private String ePSEstimateCurrentYear;
	private String ePSEstimateNextYear;
	private String ePSEstimateNextQuarter;
	private String priceEPSEstimateCurrentYear;
	private String priceEPSEstimateNextYear;
	private String oneyrTargetPrice;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getFiftydayMovingAverage() {
		return fiftydayMovingAverage;
	}

	public void setFiftydayMovingAverage(String fiftydayMovingAverage) {
		this.fiftydayMovingAverage = fiftydayMovingAverage;
	}

	public String getChangeFromFiftydayMovingAverage() {
		return changeFromFiftydayMovingAverage;
	}

	public void setChangeFromFiftydayMovingAverage(
			String changeFromFiftydayMovingAverage) {
		this.changeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
	}

	public String getTwoHundreddayMovingAverage() {
		return twoHundreddayMovingAverage;
	}

	public void setTwoHundreddayMovingAverage(String twoHundreddayMovingAverage) {
		this.twoHundreddayMovingAverage = twoHundreddayMovingAverage;
	}

	public String getChangeFromTwoHundreddayMovingAverage() {
		return changeFromTwoHundreddayMovingAverage;
	}

	public void setChangeFromTwoHundreddayMovingAverage(
			String changeFromTwoHundreddayMovingAverage) {
		this.changeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
	}

	public String getAverageDailyVolume() {
		return averageDailyVolume;
	}

	public void setAverageDailyVolume(String averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}

	public String getpERatio() {
		return pERatio;
	}

	public void setpERatio(String pERatio) {
		this.pERatio = pERatio;
	}

	public String getpEGRatio() {
		return pEGRatio;
	}

	public void setpEGRatio(String pEGRatio) {
		this.pEGRatio = pEGRatio;
	}

	public String geteBITDA() {
		return eBITDA;
	}

	public void seteBITDA(String eBITDA) {
		this.eBITDA = eBITDA;
	}

	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	public String getEarningsShare() {
		return earningsShare;
	}

	public void setEarningsShare(String earningsShare) {
		this.earningsShare = earningsShare;
	}

	public String getePSEstimateCurrentYear() {
		return ePSEstimateCurrentYear;
	}

	public void setePSEstimateCurrentYear(String ePSEstimateCurrentYear) {
		this.ePSEstimateCurrentYear = ePSEstimateCurrentYear;
	}

	public String getePSEstimateNextYear() {
		return ePSEstimateNextYear;
	}

	public void setePSEstimateNextYear(String ePSEstimateNextYear) {
		this.ePSEstimateNextYear = ePSEstimateNextYear;
	}

	public String getePSEstimateNextQuarter() {
		return ePSEstimateNextQuarter;
	}

	public void setePSEstimateNextQuarter(String ePSEstimateNextQuarter) {
		this.ePSEstimateNextQuarter = ePSEstimateNextQuarter;
	}

	public String getPriceEPSEstimateCurrentYear() {
		return priceEPSEstimateCurrentYear;
	}

	public void setPriceEPSEstimateCurrentYear(
			String priceEPSEstimateCurrentYear) {
		this.priceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}

	public String getPriceEPSEstimateNextYear() {
		return priceEPSEstimateNextYear;
	}

	public void setPriceEPSEstimateNextYear(String priceEPSEstimateNextYear) {
		this.priceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}

	public String getOneyrTargetPrice() {
		return oneyrTargetPrice;
	}

	public void setOneyrTargetPrice(String oneyrTargetPrice) {
		this.oneyrTargetPrice = oneyrTargetPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YData [symbol=");
		builder.append(symbol);
		builder.append(", yearLow=");
		builder.append(yearLow);
		builder.append(", yearHigh=");
		builder.append(yearHigh);
		builder.append(", fiftydayMovingAverage=");
		builder.append(fiftydayMovingAverage);
		builder.append(", changeFromFiftydayMovingAverage=");
		builder.append(changeFromFiftydayMovingAverage);
		builder.append(", twoHundreddayMovingAverage=");
		builder.append(twoHundreddayMovingAverage);
		builder.append(", changeFromTwoHundreddayMovingAverage=");
		builder.append(changeFromTwoHundreddayMovingAverage);
		builder.append(", averageDailyVolume=");
		builder.append(averageDailyVolume);
		builder.append(", pERatio=");
		builder.append(pERatio);
		builder.append(", pEGRatio=");
		builder.append(pEGRatio);
		builder.append(", eBITDA=");
		builder.append(eBITDA);
		builder.append(", marketCapitalization=");
		builder.append(marketCapitalization);
		builder.append(", earningsShare=");
		builder.append(earningsShare);
		builder.append(", ePSEstimateCurrentYear=");
		builder.append(ePSEstimateCurrentYear);
		builder.append(", ePSEstimateNextYear=");
		builder.append(ePSEstimateNextYear);
		builder.append(", ePSEstimateNextQuarter=");
		builder.append(ePSEstimateNextQuarter);
		builder.append(", priceEPSEstimateCurrentYear=");
		builder.append(priceEPSEstimateCurrentYear);
		builder.append(", priceEPSEstimateNextYear=");
		builder.append(priceEPSEstimateNextYear);
		builder.append(", oneyrTargetPrice=");
		builder.append(oneyrTargetPrice);
		builder.append("]");
		return builder.toString();
	}
}
