package com.FrameworkUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigMngr {
	
	private static Map<String, Properties> configs = new HashMap<String, Properties>();
	
	
	private static String configFilePath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"config.properties";
	
	public static String getProperty(String propertyName)  {
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(configFilePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prop.getProperty(propertyName).toString().trim();
	}

}
