package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	public File file;
	public FileInputStream fis;
	private static PropertyReader propertyManager = null;
	private static final String propertiesFilePath = "./src/test/resources/properties/";
	private static final String configFileName = "config.properties";
	//private static final String inputFileName = "input.properties";

	private Properties prop = new Properties();

	private PropertyReader() {
		loadPropertiesFile(propertiesFilePath, configFileName);
		//loadPropertiesFile(propertiesFilePath, inputFileName);

	}

	public static PropertyReader getInstance() {
		if(propertyManager ==null)
			propertyManager = new PropertyReader();
		return propertyManager;
	}

	public void loadPropertiesFile(String propertiesFilePath, String configFileName) {

		try {
			file = new File(propertiesFilePath + configFileName);
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

	public String getUsername() {
		return prop.getProperty("userName");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

}
