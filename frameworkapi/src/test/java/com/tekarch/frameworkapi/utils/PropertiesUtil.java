package com.tekarch.frameworkapi.utils;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.tekarch.frameworkapi.constants.ConfigConstants;

public class PropertiesUtil {

	static Properties p;
	
	public static void initProperties() {
		try {
			p = new Properties();
			p.load(new FileInputStream(new File(ConfigConstants.PROPERTIES_FILE)));
		} catch (Exception e) {
			System.out.println("Exception while initializing." + e.getMessage());
		}
	}
	
	public static String getProperty(String key)  {
		return p.getProperty(key);
	}
}
