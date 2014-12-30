package com.targetx.poc.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.targetx.poc.common.*;

public class UIActions {
	
	WebDriver driver;
	
	Utils objUtils;
	
	 public UIActions(WebDriver driver){
		 super();
	        this.driver = driver;
	 
	    }
	
	public void accessSFLogInPage()throws Exception {
	/*	File file = new File("config.properties");
		Properties myProps = new Properties();
        FileInputStream MyInputStream = new FileInputStream(file);
        myProps.load(MyInputStream);        
        String testOrg = myProps.getProperty("testOrg");
        MyInputStream.close();*/
        driver.get("https://login.salesforce.com/");
        objUtils = new Utils(driver);
        objUtils.clearBrowserCache();
        for(int i=1; i<=5; i++)
		  {
			if (objUtils.verifyElementPresent("#Login") == false || objUtils.verifyElementPresent("#username") == false)
			{
				objUtils.refreshPage();
				if (i==5)
				{
					objUtils.takeScreenShot("LogInPage-Failed.png");
					Assert.assertTrue(false, "SF LogInPage is not loading");
				}
			}
		  }
		  
		}

}
