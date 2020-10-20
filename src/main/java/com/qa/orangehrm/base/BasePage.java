package com.qa.orangehrm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//WebDriver driver;
	
	Properties prop;
	
	
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_driver(String browserName) {
		
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;
		
		System.out.println("Browser name is "+ browserName);
		optionsManager=new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			
			//driver = new ChromeDriver();
//			if(prop.getProperty("headless").equals("yes")) {
//				ChromeOptions co = new ChromeOptions();
//				co.addArguments("--headless");
//				driver=new ChromeDriver(co);
//			}
//			else {
//				driver=new ChromeDriver();
//			}
		}
		
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
//			//driver = new FirefoxDriver();
//			if(prop.getProperty("headless").equals("yes")) {
//				FirefoxOptions fo = new FirefoxOptions();
//				fo.addArguments("--headless");
//				driver=new FirefoxDriver(fo);
//			}
//			else {
//				driver=new FirefoxDriver();
//			}
		}
		
		else if(browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else if(browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			//driver = new OperaDriver();
			tlDriver.set(new OperaDriver());
		}
		else {
			System.out.println("Browser name "+ browserName + " is not found. Please pass the correct browser...!");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
	
		return getDriver();
	}
	public Properties init_properties() {
		prop=new Properties();
		String path="/Users/mrs.z./Desktop/HasanNew/HRMOrange/src/main/java/com/qa/orangehrm/config/config.properties";
		
		try {
			FileInputStream ip=new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
System.out.println("some issue config properties .. Please correct your config..");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
		
	}
	

}
