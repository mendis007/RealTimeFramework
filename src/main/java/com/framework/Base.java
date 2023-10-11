package com.framework;

import java.util.HashMap;
import java.util.logging.Logger;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.FrameworkUtils.ExcelLib;


public class Base {
	
	public static RemoteWebDriver driver;
	public static Logger log4j = Logger.getLogger(Base.class.getName());
	public static HashMap<String, String> testData=new HashMap<String, String>();
	
	
	public static void reuseTestData(String testCaseName, String rowName) {
		try {
			ExcelLib excel = new ExcelLib();
			if (Base.testData != null)
				Base.testData.clear();
			try {
				Base.testData = excel.getDataFromMultipleRows(testCaseName, "Sheet1", rowName);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			log4j.info(e.getMessage());
		}
	}
	
	public static void reuseTestData(String testCaseName,String sheetName,String rowName) {
		try {
			ExcelLib excel = new ExcelLib();
			if (Base.testData != null)
				Base.testData.clear();
			try {
				Base.testData = excel.getDataFromMultipleRows(testCaseName,sheetName, rowName);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			log4j.info(e.getMessage());
		}
	}


}
