package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.page.HomePage;
import com.qa.orangehrm.page.LoginPage;
import com.qa.orangehrm.util.AppConstants;
import com.qa.orangehrm.util.Credentials;

public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeMethod
	public void setUp() {
		basePage= new BasePage();
		prop=basePage.init_properties();
		String browserName=prop.getProperty("browser");
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage=new LoginPage(driver);
		userCred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage=loginPage.doLogin(userCred);
				
	}
	@Test(priority=1 , description="veriyf home page title" , enabled=true)
	public void verifyHomePageTitle() {
		String title=homePage.getHomePageTitle();
		System.out.println("Home page title is : " + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	@Test(priority=2 , description="verify home page header", enabled=true)
	public void verifyHomePageHeader() {
		String header= homePage.getHomePageHeader();
		System.out.println("Home page header is : "+ header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	@Test(priority=3 , description="verify account name" , enabled = true)
	public void verifyLoggedInUserAccount() {
		String accountName=homePage.getLoggedInUserAccountName();
		System.out.println("Account name is : "+ accountName);
		//Assert.assertTrue(accountName.contains("Welcome"));
		Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
	}
	@Test(priority=4 , description="verify URL", enabled= true)
	public void verifyAuthentication() {
		String url=homePage.getHomeUrl();
		System.out.println("URL is " + url);
		Assert.assertTrue(url.endsWith("https://opensource-demo.orangehrmlive.com/index.php/dashboard"));
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
