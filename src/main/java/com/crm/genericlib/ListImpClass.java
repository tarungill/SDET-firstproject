package com.crm.genericlib;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListImpClass implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		String failedtestName = result.getMethod().getMethodName();
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(BaseClassAnnotation.sDriver);
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		String systemDate = new Date().toString().replace(":", "_").replace(" ", "_");
		
	     try {
			FileUtils.copyFile(srcFile, new File("./ScreenShot/"+failedtestName+"_"+systemDate+".png"));
		} catch (IOException e) {
					}
	}

}
