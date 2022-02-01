package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericlib.WebDriverUtility;

public class CreateNewContact extends WebDriverUtility {
	
	
    WebDriver driver=null;
	public CreateNewContact(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement orglookupImg;

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}
	
	public WebElement getOrglookupImg() {
		return orglookupImg;
	}
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public void createContact(String contactlastname)
	{
		lastnameEdt.sendKeys(contactlastname);
		saveBtn.click();
	}
	
	public void createContact(String contactlastname, String orgname)
	{
		lastnameEdt.sendKeys(contactlastname);
		orglookupImg.click();
		switchToWindow(driver,"Accounts&action");
		Organizations op=new Organizations(driver);
		op.getSearchEdt().sendKeys(orgname);
		op.getSearchEdt().click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		switchToWindow(driver,"Contacts&action");
		saveBtn.click();
	}

	


}
