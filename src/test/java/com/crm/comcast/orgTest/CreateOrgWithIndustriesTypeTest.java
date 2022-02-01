package com.crm.comcast.orgTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrgWithIndustriesTypeTest {
	
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
			
			
			//test script data
			String orgname = elib.getDataFromExcel("organization", 5, 2)+"_"+jlib.getRandomNumber();
			String industriesType = elib.getDataFromExcel("organization", 5, 3)+"_"+jlib.getRandomNumber();
			
			//launch the browser
			if(browser.equals("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(browser.equals("firefox"))
			{
				driver=new FirefoxDriver();
			}
			
			wlib.waitForPageLoad(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			
			//login to app
			Login lp=new Login(driver);
			lp.loginToApp(username, password);
			
			//navigate to organization
			Home hp=new Home(driver);
			hp.getOrganizationLink().click();
			
			//navigate to create organization
			Organizations org=new Organizations(driver);
			org.getCreateorgImg().click();
			
			//create new organization
			CreatingNewOrganization cno=new CreatingNewOrganization(driver);
			cno.createOrg(orgname, industriesType);
			
			//verify orgname and industry
			OrganizationInformation orginfoPage=new OrganizationInformation(driver);
			String actsucmsg = orginfoPage.getSuccessfullMsg().getText();
			if(actsucmsg.equals(orgname))
			{
				System.out.println("orgname created successfully");
			}
			else
			{
				System.out.println("orgname is not created successfully");
			}
			
			
			
			
	}

}
