package com.targetx.poc.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.targetx.poc.common.UIActions;
import com.targetx.poc.common.Utils;
import com.targetx.poc.pageobjects.HomePage;
import com.targetx.poc.pageobjects.LogInPage;


public class LogInLogOutTest {
	
	WebDriver driver;
	Utils objUtils;
	UIActions objUIActions;
	LogInPage objLogInPage;
	HomePage objHomePage;
	
	@BeforeMethod (groups = {"SmokeTest","RegressionTest"})
	public void setUp() throws Exception{		
		 driver = new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
	}
	
	@Test(groups = {"SmokeTest","RegressionTest"})
	public void testLogInLogOut() throws Exception {
		//objUIActions = new UIActions(driver);
		//objUIActions.accessSFLogInPage();
		driver.get("https://login.salesforce.com/");
		objLogInPage = new LogInPage(driver);
		objLogInPage.logInToSF("srm_integration_testing@targetx.com", "t8AxjtKu22MA5vV");
		objHomePage = new HomePage(driver);
		objHomePage.SFLogOut();	 
	     }
	
	
	@AfterMethod (groups = {"SmokeTest","RegressionTest"})
	public void tearDown()throws Exception {	
	     driver.quit();
	}
}
