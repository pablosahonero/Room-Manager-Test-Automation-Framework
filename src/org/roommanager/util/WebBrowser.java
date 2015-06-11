package org.roommanager.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebBrowser {
	private static WebBrowser instance = new WebBrowser();
	private static WebDriver driver;
	
	private WebBrowser(){}
	
	public static WebBrowser getInstance(){
		driver = null;
		return instance;
	}
	
	public static WebDriver getGoogleChromeWebDriver(){
		if(driver == null){
			System.setProperty("webdriver.chrome.driver", PropertyReader.getChromeDriverPath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	public static WebDriver getFirefoxWebDriver(){
		if(driver == null){
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
}


