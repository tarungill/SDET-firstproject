package com.contact.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DuplicateContact {
	WebDriver driver=null;
	@BeforeClass
	public void openBrowser()
	{
		driver=new ChromeDriver();
	}
	@Test(priority=1)
	public void testnavApp() throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commondata1.property");
		Properties p=new Properties();
		p.load(fis);
		String url = p.getProperty("url");
		driver.get(url);
		}
	@Test(priority=2)
	public void testloginApp() throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commondata1.property");
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
	public void testclickonContact() throws InterruptedException
	{
		WebElement contact = driver.findElement(By.xpath("//a[text()='Contacts']"));
		Actions a=new Actions(driver);
		a.moveToElement(contact).click().perform();
		Thread.sleep(3000);
	}
	
	@Test(priority=4)
	public void testselectContact()
	{
		
	 driver.findElement(By.xpath("(//a[@title='Contacts'])[1]")).click();
		
	}
	
	@Test(priority=5) 
	public void testselectDuplicate() throws InterruptedException
	{
	  WebElement dcont = driver.findElement(By.name("Duplicate"));
	  Actions a1=new Actions(driver);
	  a1.moveToElement(dcont).click().perform();
	  Thread.sleep(3000);
	  driver.findElement(By.name("button")).click();
	}
	@Test(priority=6)
	public void testcheckContact() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		List<WebElement> allcontacts = driver.findElements(By.xpath("//a[text()='Mary']"));
		int count = allcontacts.size();
		HashSet<String>hs=new HashSet<String>();
		
		for(int i=0;i<count;i++)
		{
			String text = allcontacts.get(i).getText();
			if(hs.add(text)==false)
			{
				System.out.println(text);
				System.out.println("same contacts are present in contact list so test is passed");
				break;
			}
			else if(count==0)
			{
				System.out.println("same contacts are not present in contact list so test is failed");
			}
		}
		 
	} 
		 
		}
		
	
	



