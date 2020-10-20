package com.qa.orangehrm.tests;

import java.util.Properties;

import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.page.HomePage;
import com.qa.orangehrm.page.LoginPage;
import com.qa.orangehrm.util.AppConstants;
import com.qa.orangehrm.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic -101 : create login features")
@Feature("US - 501 : create tests login system on HRM using valid or invalid credentials")

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger log=Logger.getLogger(LoginPageTest.class);
	
	@BeforeMethod
	@Parameters(value= {"browser"})
	
	public void setUp(String browser) {
		String browserName=null;
		basePage=new BasePage();
		prop=basePage.init_properties();
		if(browser.equals(null)) {
			browserName=prop.getProperty("browser");
		}else {
			browserName=browser;
		}
	
		driver =basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage=new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority=1, description="verify page title method" ,enabled=true)
	@Description("verify login page title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitle() {
		log.info("starting title method -------------->verify login page");
		String title =loginPage.getPageTitle();
		System.out.println("Login page title is : " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		log.info("ending title method ------------> verify login page");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@Test(priority=2, description="verify forget password link method" ,enabled=true)
	@Description("verify login page forget password link")
	@Severity(SeverityLevel.NORMAL)
	public void verifyForgetPassLink() {
		log.info("starting Forget passlink -------------->login page");
		Assert.assertTrue(loginPage.checkForgetPassLink());
		log.info("ending Forget passlink ------------> login page");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@Test(priority=3,description="verify valid login credentials" ,enabled=true)
	@Description("verify login page valid credentials")
	@Severity(SeverityLevel.CRITICAL)
	public void loginTest() {
		log.info("startig login test  -------------->login page");
		HomePage homePage=loginPage.doLogin(userCred);
		String accountName=homePage.getLoggedInUserAccountName();
		System.out.println("Logged account name is " +accountName);
		Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
		//Assert.assertTrue(accountName.contains("Welcome"));
		log.info("ending login method ------------> login page");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData(){
		
		Object data[][]= {{"Refia","321test"},
				 {"Hasan" , "test123"},
				 {"Richard","ret123"},
				 {"Furkan","ewr123"}
				  
		};
	
		return data;
		}
	@Test(priority=4 , dataProvider="getLoginInvalidData",enabled=false)
	@Description("verify login page invalid credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void Login_InvalidTestCase(String username ,String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
