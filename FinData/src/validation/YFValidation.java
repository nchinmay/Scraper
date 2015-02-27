package validation;

import org.jsoup.helper.StringUtil;

import datalayer.objects.csvable.YFData;

public class YFValidation
{
	public static int validate(YFData ydata)
	{
		if (StringUtil.isBlank(ydata.getSymbol())) return Validation.CATASTROPHIC_ERROR;
		return Validation.SUCCESS;
	}
}
