package practice;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.genericlib.BaseClassAnnotation;

public class SampleHomePageTest extends BaseClassAnnotation {
	
	@Test
	public void verifyHomePage() {
		
		String expectedTitle="Home";
		
		String actTitle=driver.getTitle();
		boolean status = actTitle.contains(expectedTitle);
		Assert.assertTrue(status,"Home page is not verified===>Fail");
		System.out.println("Home page is verified===>pass");
		
	}
	@Test
	public void verifyHomePageLogo() {
		boolean imgStatus = driver.findElement(By.xpath("//img[@alt='vtiger-crm-logo.gif']")).isDisplayed();
		Assert.assertTrue(imgStatus,"Home Page Logo is not verified===>Fail");
		System.out.println("Home page Logo is Verified====>pass");
		
	}

}
