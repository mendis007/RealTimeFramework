package com.FrameworkUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib {

	
	private static String path = System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"MyData.xlsx";
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell=null;
	
	
	public ExcelLib() {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 */
	public ExcelLib(String filePath) {

		try {
			path = filePath;
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount(String sheetName) throws Throwable{
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}
	
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == CellType.STRING) {
				
				return cell.getStringCellValue();
			}
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" +cal.get(Calendar.DAY_OF_MONTH) + "/" +cellText;
				}

				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}
	
	public HashMap<String, String> getDataFromMultipleRows(String testCase, String sheetName, String rowNameBasedOnFeatureFile) throws Throwable {

		int testCaseStartRowNum = 0;
		int testCaseEndRowNum = 0;

		HashMap<String, String> map = null;
		
		// iterate through all rows from the sheet Test Data
		for (int rNum = 1; rNum <= getRowCount(sheetName); rNum++) {
			// to identify testCase starting row number  
			try {
				String[] rowNameWithTestCase =  getCellData(sheetName, 0, rNum).split("_");
				if(rowNameWithTestCase[1].equalsIgnoreCase("Start")) {
					String testCaseNameinSheet = rowNameWithTestCase[0].replaceAll("[^A-Za-z0-9]","");
					
					if (testCase.equalsIgnoreCase(testCaseNameinSheet)) {
						testCaseStartRowNum = rNum;
					
						break;
					}
				}
			}catch(Exception e) {

			}
		}

		for (int i = testCaseStartRowNum; i <= getRowCount(sheetName); i++) {
			// to identify testCase starting row number  
			try {
				String[] rowNameWithTestCase =  getCellData(sheetName, 0, i).split("_");
				if(rowNameWithTestCase[1].equalsIgnoreCase("End")) {
					String testCaseNameinSheet = rowNameWithTestCase[0].replaceAll("[^A-Za-z0-9]","");
					
					if (testCase.equalsIgnoreCase(testCaseNameinSheet)) {
						testCaseEndRowNum = i;
						
						break;
					}
				}
			}catch(Exception e) {

			}
		}


		int testCasedataStartRowNum =testCaseStartRowNum;
		boolean flag = false;
		for (int rNum = testCaseStartRowNum; rNum <= testCaseEndRowNum; rNum++) {

			String envName =  getCellData(sheetName, 0, rNum);
			if(rowNameBasedOnFeatureFile.equalsIgnoreCase(envName) || envName.equalsIgnoreCase("NA")) {
				testCasedataStartRowNum = rNum;
				
				flag = true;
				break;	
			}	
		}
		if(flag==false) {
			return null;	
		}

	



		int colStartRowNum = testCaseStartRowNum + 1;
		int cols = 1;
		// Get the total number of columns for which test data is present
		while (!getCellData(sheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}
	
		// rows
		int rowStartRowNum = testCasedataStartRowNum;
		int rows = 0;
		/*
		 * // Get the total number of rows for which test data is present while
		 * (!getCellData(sheetName, 1, (rowStartRowNum + rows)).equals("")) { rows++; }
		 */
		
		Object[][] data = new Object[rows][1];
		map = new HashMap<String, String>();
		for (int rNum = rowStartRowNum; rNum <= (rows + rowStartRowNum); rNum++) {
			for (int cNum = 1; cNum < cols; cNum++) {
				map.put(getCellData(sheetName, cNum, colStartRowNum), getCellData(sheetName, cNum, rNum));

			}
			// data[rNum - rowStartRowNum][0] = table;
		}
		return map;
	}
	
}
