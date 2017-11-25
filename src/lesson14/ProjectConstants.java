package lesson14;

public class ProjectConstants {
	
	//see web.xml
	private static final String AUTH_DATA_SOURCE_PARAM_NAME = "DataSource Name";
	private static String authDataSourceName;
	
	private static final String SESSION_ATTR_NAME_LOGIN_MESSAGE = "LoginMessage";
	private static final String SESSION_ATTR_NAME_USER = "AuthUser";
	private static final String SESSION_ATTR_NAME_DATA_SOURCE = "dsName";
	
	private static final String REQUEST_ATTR_NAME_INVALID_PARAMTER = "BadParam";

	public static String getAuthDataSourceName() {
		return authDataSourceName;
	}

	public static synchronized void setAuthDataSourceName(String authDataSourceName) {
		ProjectConstants.authDataSourceName = authDataSourceName;
	}
	
	public static String getAuthDataSourceParamName() {
		return AUTH_DATA_SOURCE_PARAM_NAME ;
	}
	
	public static String getSessionAttributeNameLoginMessage () {
		return SESSION_ATTR_NAME_LOGIN_MESSAGE;
	}
	
	public static String getSessionAttributeNameAuthUser () {
		return SESSION_ATTR_NAME_USER;
	}
	public static String getSessionAttributeNameDataSource () {
		return SESSION_ATTR_NAME_DATA_SOURCE;
	}
	
	public static String getRequestAttributeNameBadParam () {
		return REQUEST_ATTR_NAME_INVALID_PARAMTER;
	}

}
