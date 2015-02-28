package datalayer.helpers;

public class CapnpObjHelper
{
	private static final CapnpObjHelper converter = new CapnpObjHelper();

	private CapnpObjHelper()
	{
	}

	public final CapnpObjHelper getHelper()
	{
		return CapnpObjHelper.converter;
	}

	/**
	 * TEST STUFF
	 */
	public static void main(String[] args) throws ClassNotFoundException
	{
	}

}
