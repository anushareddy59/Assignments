package org.tekarch.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesLoader {

	static Properties p = new Properties();

	public static void initProperties() {
		try {
			String curDir = System.getProperty("user.dir");
			p.load(new FileInputStream(new File(curDir + "\\src\\test\\resources\\input.properties")));
		} catch (Exception e) {
			System.out.println("Exception while initializing." + e.getMessage());
		}
	}
	
	public static String getProperty(String key)  {
		return p.getProperty(key);
	}

	public Properties loadFile(String inputType) {
		Properties p = null;
		try {
			String fileName = null;
			if(inputType.equals("inputProperties")) {
				fileName = Constants.INPUT_PROPERTIES;
			}
			p = new Properties();
			p.load(new FileInputStream(new File(fileName)));
		} catch (Exception e) {
			System.out.println("Exception while initializing." + e.getMessage());
		}
		return p;
	}
}


