package com.crm.genericlib;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReTryImpClass implements IRetryAnalyzer {

	public boolean retry(ITestResult arg0) {
		int counter=0;
		int retrylimit=4;
		
		if(counter<retrylimit) {
			counter++;
			return true;
		}
		return false;
	}
	

}
