package com.TestBase;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

public class TestBase {
	public static Properties prop;
	public static FileInputStream fip;
	public static String Browser;
	public static WebDriver driver;
	public static Logger l;
	public static SoftAssert st;
	public static int tc = 0;
	public static boolean Test_fail = false;
	public static Connection con = null;
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;

	public static void OpenBrowser() throws Throwable {

		st = new SoftAssert();
		l = Logger.getLogger("devpinoyLogger");
		fip = new FileInputStream(".\\src\\com\\Config\\Repository.Properties");
		prop = new Properties();
		prop.load(fip);
		Browser = prop.getProperty("browsertype");
		l.info("Browser Choosen is " + Browser);

		if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			l.info("Chrome browser launched..");
		} else if (Browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			l.info("IE browser launched..");
		} else if (Browser.equalsIgnoreCase("mozilla")) {
			System.setProperty("webdriver.firefox.marionette", "geckodriver.exe");
			driver = new FirefoxDriver();
			l.info("Firefox browser launched..");
		}

		driver.get("http://www.imdb.com/");
		l.info("URL Launched..");
		driver.manage().window().maximize();
		l.info("Browser Maximized.");
		driver.manage().deleteAllCookies();
		l.info("Deleted all cookies");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		l.info("Implicit Wait of 10 Seconds is applied.");
	}

	public static void CloseBrowser() {
		driver.quit();
		l.info("Driver Instance is Killed and Browser is Closed.");
	}
}
