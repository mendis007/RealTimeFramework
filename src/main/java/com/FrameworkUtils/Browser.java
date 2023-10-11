package com.FrameworkUtils;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.framework.Base;

public class Browser extends Base{
	@BeforeSuite
	public static void browserLaunch()  {
//		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		String runMode = ConfigMngr.getProperty("Headless");
						log4j.info(runMode);
						if(runMode.equalsIgnoreCase("true")) {
						options.addArguments("--headless");	
						}
						driver = new ChromeDriver(options);
						driver.manage().window().maximize();
						driver.manage().deleteAllCookies();
	}
	@AfterSuite
	public static void browserquit() {
						driver.quit();
	}
	
	
	
}
