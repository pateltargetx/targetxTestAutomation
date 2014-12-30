package com.targetx.poc.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;


public class Utils {
	
	WebDriver driver;
	
	public Utils(WebDriver driver){
		super();		 
        this.driver = driver;
 
    }
	
	boolean present;
	
	public enum testBrowser
	{
	    firefox, ie, chrome, phantomjs; 
	}
		
	public void setUpEnv() throws Exception {
		File file = new File("config.properties");
		Properties myProps = new Properties();
        FileInputStream MyInputStream = new FileInputStream(file);
        myProps.load(MyInputStream);        
        String getBrowser = myProps.getProperty("testBrowser");
        MyInputStream.close();
		        switch (testBrowser.valueOf(getBrowser))
		        {
		        case firefox:
		        	
		        	driver = new FirefoxDriver();
		        /*	ProfilesIni allProfiles = new ProfilesIni();
		        	FirefoxBinary binary = new FirefoxBinary(new File("D:/Program Files/Mozilla Firefox/firefox.exe"));
		    		FirefoxProfile profile = allProfiles.getProfile("seleniumFF");		 
		    		profile.setAcceptUntrustedCertificates(false);
		    		driver = new FirefoxDriver(binary,profile);*/
		    		break;
		        case ie:
		        	driver = new InternetExplorerDriver();
		        	break;
		        case chrome:
		        	System.setProperty("webdriver.chrome.driver", "C:/EBworkspace/EBTestAutomation/lib/chromedriver.exe"); 
		        	driver = new ChromeDriver();		        	
		            break;
		        case phantomjs:
		        	System.setProperty("phantomjs.binary.path", "C:\\Users\\bhavin.patel\\workspace\\targetxTestAutomation\\lib\\phantomjs.exe");
		    		driver = new PhantomJSDriver(new DesiredCapabilities());
		        }
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.get("https://login.salesforce.com/");
    	}
		
	public void closeBrowser(){
		driver.quit();
	}
	public void refreshPage(){
		driver.navigate().refresh();
	}
	public void clearBrowserCache() {
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		//driver.navigate().to("file:///c:/tmp/ClearCacheFirefox.html");
		}
	public void goBack(){
		driver.navigate().back();
	}
	public void gobacktoMainPage(){
		driver.switchTo().defaultContent();
	}
	public void executejs(String script)throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
	public void selectFilterByValue(String valToBeSelected){
        List <WebElement> options = driver.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (valToBeSelected.equalsIgnoreCase(option.getText())){
				option.click();
				driver.findElement(By.cssSelector(".button.submit")).click();
			}
		    }
	}
	public void selectDropdownValue(String dropbox, String valToBeSelected){
		WebElement select = driver.findElement(By.id(dropbox));
		List <WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (valToBeSelected.equalsIgnoreCase(option.getText())){
				option.click();
				break;
				}
		    }
	}
	public void selectDropdownValueByName(String dropbox, String valToBeSelected){
		WebElement select = driver.findElement(By.name(dropbox));
		List <WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (valToBeSelected.equalsIgnoreCase(option.getText())){
				option.click();
				break;
				}
		    }
	}
	public boolean validateDropdownValue(String dropbox, String valToBeVerified){
		WebElement select = driver.findElement(By.cssSelector(dropbox));
		List <WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if((option.getText().equalsIgnoreCase(valToBeVerified)))
				return true;
		    	break;
		}
		return false;
	}
	public boolean validateDropdownValues(String dropbox, String xpath, String valToBeVerified){
		int listCount = driver.findElements(By.xpath(dropbox)).size();
        ArrayList<String> optionList = new ArrayList<String>();
        for (int i = 1; i <= listCount; i++) {
            String option = driver.findElement(By.xpath("xpath["+i+"]")).getAttribute("value");
            optionList.add(option);
				return true;
		}
		return false;
	}
	
	public void selectRadioButton(String RadioButton)
	{
		driver.findElement(By.id(RadioButton)).click();
	}
	public void selectCheckBox(String CheckBox)
	{
		driver.findElement(By.id(CheckBox)).click();
	}
	public void clearCheckBox(String CheckBox)
	{
		driver.findElement(By.id(CheckBox)).click();
	}


	public void takeScreenShot(String fileName) throws Exception {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetDir = new File("C:/Program Files/Jenkins/jobs/targetxTestAutomation/workspace/Errors");	
		srcFile.renameTo(new File(fileName));
		FileUtils.copyFileToDirectory(new File(fileName),targetDir);
		}
	public boolean verifyElementPresent(String cssLocator) {
		try{
			if(driver.findElement(By.cssSelector(cssLocator)) != null)
				return true;
			else
	            return false;
		}
		catch(NoSuchElementException ignored)
		{
		}
		return false;
		
   }
	
	public void validatePrefills(String Selector, String Expected){
	String Actual = driver.findElement(By.cssSelector(Selector)).getAttribute("value");
	Assert.assertEquals(Actual, Expected, "Prefill value does not match the expected value.");
	}
	public void validateLinkLocation(String Selector, String Expected){
		String Actual = driver.findElement(By.linkText(Selector)).getAttribute("href");
		Assert.assertEquals(Actual, Expected, "Link location is not correct.");
		}
	
	public boolean verifyElementDisplayed(String xPath)
	{
		try{
			if(driver.findElement(By.xpath(xPath)) != null)
				return true;
			else
	            return false;
		}
		catch(NoSuchElementException ignored)
		{
		}
		return false;
	}
	public boolean verifyTextDisplayed(String value){
		if (driver.getPageSource().contains(value))
			{ 
				return true ; 
			}	
		return false;
	}
	 
	public boolean verifyLinkPresent(String linkText){
		try
		{
			driver.findElement(By.linkText(linkText));
			return true ;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	public void clickOnLink(String linkText) throws Exception {
		driver.findElement(By.linkText(linkText)).sendKeys(Keys.ENTER);
	}
	
					
	
			

}
