package com.TestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.TestBase.TestBase;

public class OpenAndCloseBrowser extends TestBase{

	@BeforeSuite
	public void openbrowsermethod() throws Throwable
	{
		OpenBrowser();
	}
	
	@AfterSuite
	public void closebrowsermethod()
	{
		CloseBrowser();
	}
}
