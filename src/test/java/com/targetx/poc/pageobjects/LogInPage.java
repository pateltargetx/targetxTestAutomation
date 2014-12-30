package com.targetx.poc.pageobjects;

import org.openqa.selenium.*;

public class LogInPage {
	 
	 WebDriver driver;
	 
	 public LogInPage(WebDriver driver){
		 super();
	        this.driver = driver;
	 
	    }
	
		    //user name textbox
	
	 public void setUserName(String strUserName){
		 driver.findElement(By.id("username")).sendKeys(strUserName);
	 }
	 
	     
	 
	    //password textbox
	 
	 public void setPassword(String strPassword){
		 driver.findElement(By.id("password")).sendKeys(strPassword);
	 }
	 
	     
	 
	    //login button
	 
	 public void clickLogIn(){
		 
	        driver.findElement(By.id("Login")).click();
	      
	    }
	    
	  public void logInToSF(String strUserName,String strPasword){
		  
	        //Fill user name
	 
	        this.setUserName(strUserName);
	 
	        //Fill password
	 
	        this.setPassword(strPasword);
	 
	        //Click Login button
	 
	        this.clickLogIn();        
	 
	  }       
	 
	 
	   

}
