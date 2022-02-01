package com.contact.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisplayedLastViewedContact {
	WebDriver driver=null;
	
	@BeforeClass
	public void openBrowser()
	{
		driver=new ChromeDriver();
	}
	
	@Test(priority=1)
	public void testnavApp() throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/commondata1.property");
		Properties p=new Properties();
		p.load(fis);
		String url = p.getProperty("url");
		driver.get(url);
		
	}
	
	@Test(priority=2)
	public void testloginApp() throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/commondata1.property");
		Properties p=new Properties();
		p.load(fis);
		
		String username = p.getProperty("username");
		String pwd = p.getProperty("password");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		
	}
	
	@Test(priority=3)
	public void testclickonContact()
	{
		WebElement contact = driver.findElement(By.xpath("(//a[text()='Contacts'])[1]"));
		Actions a=new Actions(driver);
		a.moveToElement(contact).click().perform();
	}
	
	@Test(priority=4)
	public void testchecklastallViewed()
	{
		WebElement lastviewed = driver.findElement(By.xpath("//img[@title='Last Viewed']"));
		Actions a=new Actions(driver);
		a.moveToElement(lastviewed).click().perform();
		List<WebElement> alllastviewed = driver.findElements(By.xpath("//td[contains(@class,'trackerList small')]"));
		int count = alllastviewed.size();
		for(int i=0;i<count;i++)
		{
			String text = alllastviewed.get(i).getText();
			System.out.println(text);
		}
	}

}
