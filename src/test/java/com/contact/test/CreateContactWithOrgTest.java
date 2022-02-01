package com.contact.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.comcast.objectrepositorylib.Contact;
import com.crm.comcast.objectrepositorylib.ContactInformation;
import com.crm.comcast.objectrepositorylib.CreateNewContact;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;
import com.crm.genericlib.ExcelUtility;
import com.crm.genericlib.FileUtility;
import com.crm.genericlib.JavaUtility;
import com.crm.genericlib.WebDriverUtility;

public class CreateContactWithOrgTest {

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
		String contact = elib.getDataFromExcel("contact", 5, 2)+ "_" +jlib.getRandomNumber();
		String orgname = elib.getDataFromExcel("contact", 5, 3)+ "_" +jlib.getRandomNumber();
		
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
		
		//navigate to org page
		Home hp=new Home(driver);
		hp.getOrganizationLink().click();
		
		//navigate to create new organization
		Organizations org=new Organizations(driver);
		org.getCreateorgImg().click();
		
		
		//create new organization
		CreatingNewOrganization crtorg=new CreatingNewOrganization(driver);
		crtorg.createOrg(orgname);
		
		//wait for header
				OrganizationInformation oi=new OrganizationInformation(driver);
				wlib.waitForElementToVisible(driver, oi.getSuccessfullMsg());
				
		
		//navigate to contact page
		Home hp1=new Home(driver);
		hp1.getContactsLink().click();
		
		
		//navigate to create new contact page
		Contact cp=new Contact(driver);
		cp.getCreatecontactImg().click();
		
		//create new contact with orgname page
		CreateNewContact cnp=new CreateNewContact(driver);
		cnp.createContact(contact, orgname);
		
		
		//verify the details
		ContactInformation cinfo=new ContactInformation(driver);
		String actcntmsg = cinfo.getSuccessfullMsg().getText();
		if(actcntmsg.contains(contact))
		{
			System.out.println(contact+"contact is created successful so test is passed");
		}
		else
		{
			System.out.println(contact+"contact is not created successfully so test is failed");
		}
		String actorginfo = cinfo.getOrgnameInfo().getText();
		System.out.println(actorginfo);
		if(actorginfo.trim().equals(orgname)){
			System.out.println(orgname+"is verified in contact page so test is passed");
			
		}
		else
		{
			System.out.println(orgname+"is not verified in contact page so test is failed");
		}


		
		//logout
		hp.logout();
		//closing the browser
		driver.quit();
		
}
}
