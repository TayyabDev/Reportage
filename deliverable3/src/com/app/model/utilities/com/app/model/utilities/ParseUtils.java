package com.app.model.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



class ParseUtils {

	/**
	 * This function returns a List of String with the column names.
	 * @param fileName the name of the Excel file to extract template and columns.
	 * @param sheetNum the sheet number to extract from, starting at 0.
	 * @return returns the template name.
	 */
	public static String getTemplateNameExcel(String fileName, int sheetNum) {

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(fileName));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		// get the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		
		// add template name by getting indexes 
		String sheetName = sheet.getRow(0).getCell(0).getStringCellValue();
		int start = sheetName.indexOf("\n", 0);
		start = sheetName.indexOf("\n", start+1);
		int end = sheetName.indexOf("\n", start+1);
		end = sheetName.indexOf("\n", end);
		sheetName = sheetName.substring(start, end);
		sheetName = sheetName.replace("\n", "");
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheetName;
	}
	
	/**
	 * This function returns a List of String with the column ids.
	 * @param fileName the name of the Excel file to extract columns.
	 * @param sheetNum the sheet number to extract from, starting at 0.
	 * @return returns a List of column ids.
	 */
	public static List<String> getColumnsIdsExcel(String fileName, int sheetNum) {

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(fileName));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		// get second row of the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row columnRow = sheet.getRow(1);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue());
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This function returns a List of String with the column names.
	 * @param fileName the name of the Excel file to extract template and columns.
	 * @param sheetNum the sheet number to extract from, starting at 0.
	 * @return returns a List of the template name and column names.
	 */
	public static List<String> getColumnsNamesExcel(String fileName, int sheetNum) {

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(fileName));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		// get third row of the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row columnRow = sheet.getRow(2);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue());
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException  {
		String fileName = "C:\\Users\\leo\\Desktop\\New_iCARE_Template_Comb_with_Examples.xlsx";
		System.out.println(getTemplateNameExcel(fileName, 2));
		System.out.println(getColumnsIdsExcel(fileName, 2).toString());
		System.out.println(getColumnsNamesExcel(fileName, 2).toString());
	}
}
