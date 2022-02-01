package com.crm.genericlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



/**
 *this class contains method that will be read the data from excel file 
 * @author Tarun
 *
 */

public class ExcelUtility {
	
	/**
	 * this method is used to read the data from excel based on arguments
	 * @param sheetname
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetname,int rowNum,int cellNum) throws Throwable {
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\testscript1.xlsx");
	  Workbook wb = WorkbookFactory.create(fis);
	 Sheet sh = wb.getSheet(sheetname);
	 Row row = sh.getRow(rowNum);
	 String data = row.getCell(cellNum).getStringCellValue();
	 wb.close();
	 return data;
	 }
	
	/**
	 * this method used to get the last used row number on specified sheet
	 * @param sheetname
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetname) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\testscript1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		wb.close();
		return sh.getLastRowNum();
	}
	
	/**
	 * this method is used to write the data from excel file
	 * @param sheetname
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws Throwable
	 */
	public void setDataExcel(String sheetname,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\testscript1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(cellNum);
		cel.setCellValue(data);
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\testscript1.xlsx");
		wb.write(fos);
		wb.close();
		
		
	}

}
