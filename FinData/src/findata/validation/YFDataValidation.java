package findata.validation;

import org.jsoup.helper.StringUtil;

import datalayer.objects.findata.YFData;

public class YFDataValidation
{
	public static int validate(YFData ydata)
	{
		if (StringUtil.isBlank(ydata.getSymbol())) return Validation.CATASTROPHIC_ERROR;
		return Validation.SUCCESS;
	}
}
