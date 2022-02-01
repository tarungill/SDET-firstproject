package com.crm.comcast.orgTest;

import org.testng.annotations.Test;

import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;
import com.crm.genericlib.BaseClassAnnotation;

public class Organization extends BaseClassAnnotation{
	
	
	@Test(groups="smoketest")
	public void createOrgTest() throws Throwable
	{
		//read test data from excel
				String orgName = elib.getDataFromExcel("organization", 1, 2)+ "_" +jlib.getRandomNumber();
				
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
				
				
				
			}
		
	}


