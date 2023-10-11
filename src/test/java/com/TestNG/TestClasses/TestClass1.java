package com.TestNG.TestClasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.FrameworkReusableMethods.ActionEngine;
import com.FrameworkUtils.Browser;
import com.FrameworkUtils.ConfigMngr;
import com.PageObjects.Facebook;
import com.framework.Base;

public class TestClass1 extends Base{
		
	Facebook ffb = new  Facebook();
	@BeforeClass
	public void bfrcls() {
		Browser.browserLaunch();
	}
	
	@Test(enabled=false)
	public void runTest1() {
		reuseTestData("FACEBOOK", "SURYA");
		ActionEngine.launchURL(ConfigMngr.getProperty("FacebookUrl"));
		ActionEngine.implicitywait(Integer.valueOf(ConfigMngr.getProperty("TimeOut10")));
		ffb.login();
		log4j.info("ITS WORKING\n");
	}
	
	
	
	@AfterClass
	public void teardown() {
		Browser.browserquit();
	}
}
