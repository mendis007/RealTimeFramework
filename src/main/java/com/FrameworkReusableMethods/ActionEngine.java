package com.FrameworkReusableMethods;

import java.util.concurrent.TimeUnit;

import com.framework.Base;

public class ActionEngine extends Base{
	
	public static void launchURL(String url) {
		driver.get(url);
	}
	
	public static void implicitywait(int t){
		try {
			driver.manage().timeouts().implicitlyWait(t,TimeUnit.SECONDS);

		} catch (Exception e) {
			log4j.info(e.getMessage());
		}
	}
	
	

}


