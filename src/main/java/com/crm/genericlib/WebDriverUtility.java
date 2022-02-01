package com.crm.genericlib;

import java.awt.event.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * this class contains generic methods for all webdriver actions
 * @author Tarun
 *
 */
public class WebDriverUtility {
	
	/**
	 * this method will maximise the window
	 * @param driver
	 */
	public void maximiseWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * this method will wait for page to be loaded
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/**
	 * this method will wait unti the element becomes clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClick(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForVisibleElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='dvHeaderText']")));
	}
	
	public void waitForElementToVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitAndClick(WebElement element) throws Throwable
	{
		int count=0;
		while(count<20)
		{
			try
			{
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element,String value)
	{
		
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * 
	 * @param text
	 * @param element
	 */
	public void select(String text,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param driver
	 */
	public void doubleClick(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	public void mouseOver(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
		
	}
	
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement dest)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(src, dest);
	}
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void dismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
		}
	
	public void getScreenShot(WebDriver driver, String screenshotName) throws Throwable
	{
		TakesScreenshot tsc=(TakesScreenshot)driver;
		File src=tsc.getScreenshotAs(OutputType.FILE);
		File dst=new File("./screenshot/"+screenshotName+".PNG");
		Files.copy(src, dst);
		
	}
	
	/**
	 * switch to window depending on partial title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())//keep on running window until the condition
		{
			String winId = it.next();
			driver.switchTo().window(winId);
			String currentWintitle = driver.getTitle();
			if(currentWintitle.contains(partialWinTitle))
			{
				break;
			}
		}
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToFrame(WebDriver driver,String idOrName)
	{
		driver.switchTo().frame(idOrName);
	}
	
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	public void switchToFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void scrollToElement(WebDriver driver)
	{
		JavascriptExecutor j=(JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,500)","");
	}

	public void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor j=(JavascriptExecutor)driver;
		j.executeScript("argument[0].scrollIntoView()","element");
	}
	
	public void enterKey() throws Throwable
	{
		Robot rb=new Robot();
		
	}
}
