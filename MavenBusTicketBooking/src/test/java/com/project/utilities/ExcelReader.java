package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {
	
	public static Object[][] ReadExcelDataToObjectArr(String FilePath,String FileName,String Sheetname) throws IOException {
		  //this will read operation and write it to our console the test data
			File file = new File(FilePath +"/"+ FileName);
			FileInputStream inputStream = new FileInputStream(file);
			//Create a workbook
			//for xlsx we use XSSFWorkbook
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(Sheetname);
			
			//get the number of rows 
			int rowCount = sheet.getLastRowNum();//nom empty row count
			Object[][] data = new Object[rowCount][1];
			Row headerRow = sheet.getRow(0); //first row which is header row - keys row for all the testdata sets
			Hashtable<String,String> record = null;
			
			//loop through the rows 
			for(int r = 1;r<rowCount+1;r++) {
				Row datarow = sheet.getRow(r);
				record = new Hashtable<String,String>();
				for(int c = 0;c<datarow.getLastCellNum();c++) {
					String key = headerRow.getCell(c).getStringCellValue();
					String val = datarow.getCell(c).getStringCellValue();
					 record.put(key, val);			 
				}
				//2 dimensional array
				data[r-1][0] = record;
			}
			return data;
		}
		

	
//	public static void ReadExcelData(String FilePath,String FileName,String Sheetname) throws IOException {
//	  //this will read operation and write it to our console the test data
//		File file = new File(FilePath +"/"+ FileName);
//		FileInputStream inputStream = new FileInputStream(file);
//		//Create a workbook
//		//for xlsx we use XSSFWorkbook
//		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//		Sheet sheet = workbook.getSheet(Sheetname);
//		//get the number of rows 
//		int rowCount = sheet.getLastRowNum();//nom empty row count
//		//loop through the rows 
//		for(int r = 0;r<rowCount+1;r++) {
//			Row row = sheet.getRow(r);
//			for(int c = 0;c<row.getLastCellNum();c++) {
//				 Cell cell = row.getCell(c);
//				 System.out.print(cell.getStringCellValue() + "\t");				 
//			}
//			System.out.print("\n");
//		}
//		
//		
//	}
//	
//	//main method
//	
//	public static void main(String args[]) throws IOException {
//		
//		String FilePath = System.getProperty("user.dir")+"/src/com/project/testdata";
//		String FileName = "CalorieTestData.xlsx";
//		String Sheetname = "CalorieTestData";
//		ExcelReader.ReadExcelData(FilePath, FileName, Sheetname);
//	}
//	

}
