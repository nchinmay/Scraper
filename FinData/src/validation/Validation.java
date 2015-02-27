package validation;

public class Validation
{
	// TODO - Create some good validation and error handling system
	public static final int SUCCESS = 0;
	public static final int BASIC_ERROR = -1;
	public static final int CATASTROPHIC_ERROR = -10;

	public static boolean isSuccess(int validationCode)
	{
		return validationCode == SUCCESS;
	}

	public static boolean isError(int validationCode)
	{
		return !isSuccess(validationCode);
	}

	public static boolean isBasicError(int validationCode)
	{
		return validationCode == BASIC_ERROR;
	}

	public static boolean isCatastrophicError(int validationCode)
	{
		return validationCode == CATASTROPHIC_ERROR;
	}
}
