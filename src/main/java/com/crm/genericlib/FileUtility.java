package com.crm.genericlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * this class provides generic method to read data from property file
 * @author Tarun
 *
 */

public class FileUtility {
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws Throwable
	 */

	public String readDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commondata1.property");
		Properties pload=new Properties();
		pload.load(fis);
		String value = pload.getProperty(key);
		return value;
		
		

	}

}
