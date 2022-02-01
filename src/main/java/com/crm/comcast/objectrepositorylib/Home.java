package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	WebDriver driver;//global driver variable
	
	public Home(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsLink;
	
	public WebElement getContactsLink() {
		return contactsLink;
	}

	@FindBy(linkText="Products")
	private WebElement productLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrationImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getAdministrationImg() {
		return administrationImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(administrationImg).perform();
		signOutLink.click();
	}
	

}
