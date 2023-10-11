package com.Runners;  

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.FrameworkReusableMethods.ActionEngine;
import com.FrameworkUtils.Browser;
import com.FrameworkUtils.ConfigMngr;
import com.PageObjects.Facebook;
import com.framework.Base;

public class RunWithMainMethod extends Base{
	
	static Facebook fb=new Facebook();
	
	public static Logger log4j = Logger.getLogger(RunWithMainMethod.class.getName());
	
	public static void waitFor(int t) {
		try {
			Thread.sleep(t*1000);
		} catch (Exception e) {
			log4j.info(e.getMessage());
		}
	}
	@Test
	public static void main(String[] args) throws Throwable {
	reuseTestData("FACEBOOK", "SURYA");
	Browser.browserLaunch();
	ActionEngine.launchURL(ConfigMngr.getProperty("FacebookUrl"));
	ActionEngine.implicitywait(Integer.valueOf(ConfigMngr.getProperty("TimeOut10")));
	fb.login();
	log4j.info("ITS WORKING\n");

	}
}
