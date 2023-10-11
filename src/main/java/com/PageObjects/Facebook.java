package com.PageObjects;

import org.openqa.selenium.By;

import com.FrameworkUtils.ConfigMngr;
import com.framework.Base;

public class Facebook extends Base  {
	
	private static By loginID = By.xpath("//input[@id='email']");
	private static By password = By.xpath("//input[@id='pass']");
	private static By login = By.xpath("//button[@name='login']");
	public void login() {
		// TODO Auto-generated method stub
		try {
//			driver.findElement(loginID).sendKeys(ConfigMngr.getProperty("FbUser"));
//			driver.findElement(password).sendKeys(ConfigMngr.getProperty("FbPassword"));
			driver.findElement(loginID).sendKeys(testData.get("USER"));
			driver.findElement(password).sendKeys(testData.get("PASS"));
			driver.findElement(login).click();
		} catch (Exception e) {
			log4j.info(e.getMessage());
		}
	}

}
