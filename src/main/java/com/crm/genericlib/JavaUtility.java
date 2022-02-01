package com.crm.genericlib;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * this method will generate a random number and returns it to the caller
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran=new Random();
		int random = ran.nextInt();
		return random;
		
	}
	
	/**
	 * this method will return date in specified formate
	 * @return
	 */
	public String getCurrentDate()
	{
		Date date=new Date();
		String d = date.toString();
		String[] dte = d.split(" ");
		String yyyy = dte[5];
		String dd = dte[2];
		String mm = dte[1];
		String today = yyyy+"-"+mm+"-"+dd;
		return today;
		
	}
	/**
	 * this method will be used to pass virtual key to os
	 * @throws Throwable
	 */
	public void pressVirtualEnterKey() throws Throwable
	{
		Robot rc=new Robot();
		rc.keyPress(KeyEvent.VK_ENTER);
		rc.keyRelease(KeyEvent.VK_ENTER);
	}
}
