package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	public File file;
	public FileInputStream fis;
	private static PropertyReader propertyManager = null;
	private static final String propertiesFilePath = "./src/test/resources/properties/";
	private static final String intEnvConfig = "int-env.properties";
	private static final String testEnvConfig = "test-env.properties";
	//private static final String inputFileName = "input.properties";

	private Properties prop = new Properties();

	private PropertyReader() {
		if(System.getProperty("env").equalsIgnoreCase("int")) {
			System.out.println("Running on int environment");
			loadPropertiesFile(propertiesFilePath, intEnvConfig);
		}
		else {
			System.out.println("Running on test environment");
			loadPropertiesFile(propertiesFilePath, testEnvConfig);
		}
			
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
