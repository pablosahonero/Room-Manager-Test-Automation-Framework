package org.roommanager.pages.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.roommanager.properties.PropertyReader;

public class WebBrowser {
	private static WebBrowser instance = new WebBrowser();
	private static WebDriver driver;
	
	private WebBrowser(){}
	
	public static WebBrowser getInstance(){
		   return instance;
	}
	
	public static WebDriver getGoogleChromeWebDriver(){
		PropertyReader properties = new PropertyReader();
		System.setProperty("webdriver.chrome.driver", properties.getChromeDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}


