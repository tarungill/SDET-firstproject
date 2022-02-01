package com.crm.genericlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;

public class BaseClassAnnotation {
	
	
	public ExcelUtility elib=new ExcelUtility();
	public FileUtility flib=new FileUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sDriver=null;
	
	//@Parameters("browser")
	@BeforeClass(groups= {"smoketest","regressiontest"})
	public void configBC() throws Throwable {
		System.out.println("==========launch the browser==============");
		String browser = flib.readDataFromPropertyFile("browser");
		String url = flib.readDataFromPropertyFile("url");
		//launch the browser
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		driver.get(url);
		sDriver=driver;
		}
	
	@BeforeMethod(groups= {"smoketest","regressiontest"})
	public void configBM() throws Throwable {
		String username = flib.readDataFromPropertyFile("username");
		String password = flib.readDataFromPropertyFile("password");
		Login lp=new Login(driver);
		lp.loginToApp(username, password);
	
	}
	
	@AfterMethod(groups= {"smoketest","regressiontest"})
	public void configAM() {
		//logout
		Home hp=new Home(driver);
		hp.logout();
		
	}
	@AfterClass(groups= {"smoketest","regressiontest"})
	public void configAC() {
		//close the browser
		driver.quit();
		
	}

}
