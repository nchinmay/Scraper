package findata.validation;

import datalayer.objects.findata.YFData;

public class YFDataValidation {
	public static int validate(YFData ydata) {
		if (ydata.getSymbol() == null || ydata.getSymbol().isEmpty())
			return Validation.CATASTROPHIC_ERROR;
		return Validation.SUCCESS;
	}
}
