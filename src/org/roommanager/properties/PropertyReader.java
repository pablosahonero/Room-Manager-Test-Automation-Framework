package org.roommanager.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	Properties properties = new Properties();
	InputStream propertyFile = null;
 
	public PropertyReader(){
		properties = new Properties();
		try {
			propertyFile = new FileInputStream("roommanager.properties");
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
	
	public String getLoginUrl(){
		return properties.getProperty("loginurl");
	}
	
	public String getUsername(){
		return properties.getProperty("username");
	}

	public String getUserPassword(){
		return properties.getProperty("password");
	}
	
	public String getChromeDriverPath(){
		return properties.getProperty("chromedriverpath");
	}
}

