package validation;

import org.jsoup.helper.StringUtil;

import datalayer.objects.YFData;
import datalayer.objects.msg.YFDataMsg.YFData.Builder;

public class YFValidation
{
	public static int validate(YFData ydata)
	{
		Builder data = ydata.getBuilder();
		if (StringUtil.isBlank(data.getSymbol().toString())) return Validation.CATASTROPHIC_ERROR;
		return Validation.SUCCESS;
	}
}
