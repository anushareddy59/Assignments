package org.tekarch.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesLoader {

	static Properties p = new Properties();



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


