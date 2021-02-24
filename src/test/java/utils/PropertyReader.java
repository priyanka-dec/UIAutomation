package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyReader {

	private static String userDir = System.getProperty("user.dir");
	private Properties prop;

	private PropertyReader(Properties prop) {
		this.prop = prop;
	}
	
	public static PropertyReader getInstance(String fileName) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(userDir+ "/src/main/resources/data/"+fileName));
		}
		catch(IOException ex) {
			ex.getStackTrace();
		}

		return new PropertyReader(prop);
	}
	
	public String getValue(String key) {
		return prop.getProperty(key);	

	}

}


