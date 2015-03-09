package findata.toolset;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.FastMath;

import datalayer.objects.findata.YFHistData;

public class FinTools
{
	public static final int TRADING_DAYS_IN_YEAR = 252;
	public static final int TRADING_MONTHS_IN_YEAR = 12;

	public static double[] getIntervalReturns(Stream<YFHistData> sortedHistData)
	{
		double[] returns = null;
		if (sortedHistData != null)
		{
			ArrayList<YFHistData> histDataList = sortedHistData.collect(Collectors.toCollection(ArrayList::new));
			returns = new double[histDataList.size() - 1];
			for (int i = 1; i < histDataList.size(); ++i)
			{
				double prevClose = histDataList.get(i - 1).getAdjClose();
				double todayClose = histDataList.get(i).getAdjClose();
				double ret = prevClose == 0 ? 0 : todayClose / prevClose - 1;
				returns[i - 1] = ret;
			}
		}
		return returns;
	}

	public static double getSharpeRatio(double[] intervalReturns)
	{
		return getSharpeRatio(intervalReturns, 0);
	}

	// TODO - different trading intervals
	public static double getSharpeRatio(double[] intervalReturns, double riskFreeRate)
	{
		if (intervalReturns != null)
		{
			DescriptiveStatistics ds = new DescriptiveStatistics(intervalReturns);
			return FastMath.sqrt(TRADING_DAYS_IN_YEAR) * (ds.getMean() - (riskFreeRate / TRADING_DAYS_IN_YEAR)) / ds.getStandardDeviation();
		}
		return 0;
	}
}
