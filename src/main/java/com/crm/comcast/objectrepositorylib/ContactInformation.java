package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformation {
	
	public ContactInformation(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement successfullMsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgnameInfo;
	
	

	public WebElement getOrgnameInfo() {
		return orgnameInfo;
	}



	public WebElement getSuccessfullMsg() {
		return successfullMsg;
	}
	
	


}
