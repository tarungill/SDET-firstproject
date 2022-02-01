package com.crm.comcast.orgTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;
import com.crm.genericlib.ExcelUtility;
import com.crm.genericlib.FileUtility;
import com.crm.genericlib.JavaUtility;
import com.crm.genericlib.WebDriverUtility;

public class CreateOrgTest {
	public static void main(String[] args) throws Throwable {
		
		//create object
		ExcelUtility elib=new ExcelUtility();
		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		WebDriver driver=null;
		
		//read common data
		String browser = flib.readDataFromPropertyFile("browser");
		String url = flib.readDataFromPropertyFile("url");
		String username = flib.readDataFromPropertyFile("username");
		String password = flib.readDataFromPropertyFile("password");
		
		//read test data from excel
		String orgName = elib.getDataFromExcel("organization", 1, 2)+ "_" +jlib.getRandomNumber();
		
		//launch the browser
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		//login to app
		driver.get(url);
		Login lp=new Login(driver);
		lp.loginToApp(username, password);
		
		//navigate to org
		Home hp=new Home(driver);
		hp.getOrganizationLink().click();
		
		
		//create new  organization
		Organizations crtorg=new Organizations(driver);
		crtorg.getCreateorgImg().click();
		
		//navigate to create org page
	    CreatingNewOrganization cnop=new CreatingNewOrganization(driver);
	    cnop.createOrg(orgName);
				
		
		
		//verify
		OrganizationInformation orginfo=new OrganizationInformation(driver);
		wlib.waitForVisibleElement(driver, orginfo.getSuccessfullMsg());
		String actsuchmsg = orginfo.getSuccessfullMsg().getText();
		if(actsuchmsg.contains(orgName))
		{
			System.out.println("org is created successful so test is passed");
		}
		else
		{
			System.out.println("org is not created successfully so test is failed");
		}
		
		//logout
		hp.logout();
		
		//close the browser
		driver.quit();
		
		
	}
	
}
