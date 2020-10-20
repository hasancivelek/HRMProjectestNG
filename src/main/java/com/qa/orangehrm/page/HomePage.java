package com.qa.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.ElementUtil;

public class HomePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.xpath("//h1[contains(text(),'Dashboard')]");
	By accountName = By.id("welcome");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	//method
	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		return elementUtil.doGetText(header);
	}
	public String getHomeUrl() {
		return elementUtil.doGetPageUrl();
	}
	
	public String getLoggedInUserAccountName() {
		return elementUtil.doGetText(accountName);
	}
	
}
