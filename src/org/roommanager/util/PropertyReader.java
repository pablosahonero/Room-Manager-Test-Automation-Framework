package org.roommanager.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private static Properties properties = new Properties();
	private static InputStream propertyFile = null;
	
	private static Properties getProperties()
	{
		if(propertyFile == null){
			try {
				propertyFile = new FileInputStream("resources\\roommanager.properties");
				properties.load(propertyFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					propertyFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}
	
	public static String getLoginUrl(){
		return getProperties().getProperty("loginurl");
	}
	
	public static String getUsername(){
		return getProperties().getProperty("username");
	}

	public static String getUserPassword(){
		return getProperties().getProperty("password");
	}
	
	public static String getChromeDriverPath(){
		return getProperties().getProperty("chromedriverpath");
	}
}

