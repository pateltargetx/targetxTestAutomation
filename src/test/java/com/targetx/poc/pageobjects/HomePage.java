package com.targetx.poc.pageobjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;
	 public HomePage(WebDriver driver){
		 super();
	        this.driver = driver;
	 
	    }
	 
	 
	 public void SFLogOut(){
	 
	   driver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[4]")).click();
		 
	    }

}
