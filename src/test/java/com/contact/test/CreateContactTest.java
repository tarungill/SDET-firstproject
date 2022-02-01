package com.contact.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import com.crm.comcast.objectrepositorylib.Contact;
import com.crm.comcast.objectrepositorylib.ContactInformation;
import com.crm.comcast.objectrepositorylib.CreateNewContact;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.genericlib.ExcelUtility;
import com.crm.genericlib.FileUtility;
import com.crm.genericlib.JavaUtility;
import com.crm.genericlib.WebDriverUtility;

public class CreateContactTest {
	
	
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
		String lastName = elib.getDataFromExcel("contact", 1, 2)+ "_" +jlib.getRandomNumber();
		
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
		
		//navigate to contact page
		Home hp=new Home(driver);
		hp.getContactsLink().click();
		
		//navigate to create org page
		Contact cp=new Contact(driver);
		cp.getCreatecontactImg().click();
		
		//create contact page
		CreateNewContact cnp=new CreateNewContact(driver);
		cnp.createContact(lastName);
		
		//verify the contact details
		ContactInformation cinfo=new ContactInformation(driver);
		String actsuchmsg = cinfo.getSuccessfullMsg().getText();
		if(actsuchmsg.contains(lastName))
		{
			System.out.println("contact is created successful so test is passed");
		}
		else
		{
			System.out.println("contact is not created successfully so test is failed");
		}

		
		
		//logout
		hp.logout();
		
		
		//close the browser
		driver.quit();
		
		
		
	}
	

}
