package com.contact.test;


import org.testng.annotations.Test;

import com.crm.comcast.objectrepositorylib.Contact;
import com.crm.comcast.objectrepositorylib.ContactInformation;
import com.crm.comcast.objectrepositorylib.CreateNewContact;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;
import com.crm.genericlib.BaseClassAnnotation;


public class ContactTest extends BaseClassAnnotation {
	

	       @Test(groups="smoketest")
			public void createContactTest() throws Throwable {
			//read test data from excel
			String lastName = elib.getDataFromExcel("contact", 1, 2)+ "_" +jlib.getRandomNumber();
			
			
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

			
				}


	    @Test(groups="regressiontest")
		public void createcontactwithOrgTest() throws Throwable {
		//read test data from excel
		String contact = elib.getDataFromExcel("contact", 5, 2)+ "_" +jlib.getRandomNumber();
		String orgname = elib.getDataFromExcel("contact", 5, 3)+ "_" +jlib.getRandomNumber();
	
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


		
	    }
}
      
