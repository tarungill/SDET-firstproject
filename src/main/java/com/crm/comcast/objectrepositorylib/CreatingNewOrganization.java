package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganization {
	
	
	public CreatingNewOrganization(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement indType;
	
	public void createOrg(String orgName)
	{
		
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industriesType)
	{
		
		orgNameEdt.sendKeys(orgName);
		Select s=new Select(indType);
		indType.click();
		s.selectByVisibleText("Energy");
		saveBtn.click();
	}


}
