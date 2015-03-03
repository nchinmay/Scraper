package datalayer.objects.findata;

import datalayer.objects.interfaces.ICapnpMsg;

public class YFHistData implements ICapnpMsg, Comparable<YFHistData>
{
	private String symbol;
	private int date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long adv;
	private double adjClose;

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public int getDate()
	{
		return date;
	}

	public void setDate(int date)
	{
		this.date = date;
	}

	public double getOpen()
	{
		return open;
	}

	public void setOpen(double open)
	{
		this.open = open;
	}

	public double getHigh()
	{
		return high;
	}

	public void setHigh(double high)
	{
		this.high = high;
	}

	public double getLow()
	{
		return low;
	}

	public void setLow(double low)
	{
		this.low = low;
	}

	public double getClose()
	{
		return close;
	}

	public void setClose(double close)
	{
		this.close = close;
	}

	public long getAdv()
	{
		return adv;
	}

	public void setAdv(long adv)
	{
		this.adv = adv;
	}

	public double getAdjClose()
	{
		return adjClose;
	}

	public void setAdjClose(double adjClose)
	{
		this.adjClose = adjClose;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("YFHistData [symbol=");
		builder.append(symbol);
		builder.append(", date=");
		builder.append(date);
		builder.append(", open=");
		builder.append(open);
		builder.append(", high=");
		builder.append(high);
		builder.append(", low=");
		builder.append(low);
		builder.append(", close=");
		builder.append(close);
		builder.append(", adv=");
		builder.append(adv);
		builder.append(", adjClose=");
		builder.append(adjClose);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(YFHistData o)
	{
		return this.date - o.date;
	}

}
