package com.stepdefinitions;

import com.FrameworkReusableMethods.ActionEngine;
import com.FrameworkUtils.Browser;
import com.FrameworkUtils.ConfigMngr;
import com.PageObjects.Facebook;
import com.framework.Base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class Stepdefinition1 extends Base {
Facebook fb= new Facebook();

	@Given("launch the browser")
	public void launch_Browser() {
		Browser.browserLaunch();
	}

	@Given("launch facebook")
	public void launch_Facebook() {
		ActionEngine.launchURL(ConfigMngr.getProperty("FacebookUrl"));
	}
	@And("login to facebook")
	public void login_to_FaceBook() {
		
		fb.login();
	}
	
	@Given("Reuse testdata from {string} and {string}")
	public void reUseTestData(String testCaseName, String rowName) {
		reuseTestData(testCaseName, rowName);
	}
	
	@Given("Reuse testdata from {string} , {string} and {string}")
	public void reuse_testdata_from_and(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
		reuseTestData(string, string2, string3);
	}
}
