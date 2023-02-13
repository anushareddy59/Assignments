package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	static Properties p;
	
	public static void initProperties() {
		try {
			p = new Properties();
			String curDir = System.getProperty("user.dir");
			p.load(new FileInputStream(new File(curDir + "\\src\\main\\resources\\input.properties")));
		} catch (Exception e) {
			System.out.println("Exception while initializing." + e.getMessage());
		}
	}
	
	public static String getProperty(String key) throws FileNotFoundException, IOException {
		return p.getProperty(key);
	}
}
