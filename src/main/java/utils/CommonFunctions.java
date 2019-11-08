package utils;

import java.util.UUID;

public class CommonFunctions {

	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static String randomAlphaNumericString(int count) {

	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
	
	
	//UUID or GUID(Universal or Global Unique IDentifier)
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
