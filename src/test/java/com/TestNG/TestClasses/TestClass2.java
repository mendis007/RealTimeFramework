package com.TestNG.TestClasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FrameworkReusableMethods.ActionEngine;
import com.FrameworkUtils.Browser;
import com.FrameworkUtils.ConfigMngr;

public class TestClass2 {
	
	@BeforeClass
	public void bfrcls() {
		Browser.browserLaunch();
	}
	
	@Test
	public void runTest2() {
		ActionEngine.launchURL(ConfigMngr.getProperty("Amazon"));
	}
	
	@AfterClass
	public void teardown() {
		Browser.browserquit();
		}
		
}
