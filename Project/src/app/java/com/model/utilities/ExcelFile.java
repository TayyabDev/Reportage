package app.java.com.model.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFile {
	
	private Workbook workbook;
	
	/**
	 * The main constructor for ExcelFile.
	 * 
	 * RUNS OUT OF MEMORY IF THE EXCEL FILE HAS TOO MANY SHEETS!
	 * PLEASE LIMIT EXCEL FILE TO 1 SHEET!
	 * @param fileName the fileName to parse.
	 */
	public ExcelFile(String fileName) {
		this.workbook = parseExcelFile(fileName);	
	}
	
	/**
	 * Parses the Excel file into a Workbook.
	 * @param fileName the name of the file to parse.
	 * @return returns a Workbook of all the Excel file.
	 */
	private Workbook parseExcelFile(String fileName) {
		try {
			workbook = WorkbookFactory.create(new File(fileName));
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	/**
	 * Gets the name of the sheet at sheet index sheetNum.
	 * @param sheetNum the index of the sheet to get the name.
	 * @return returns the name of the sheet.
	 */
	public String getSheetName(int sheetNum) {
		// get the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		return sheet.getSheetName();
	}
	
	/**
	 * Gets a list of column ids from the sheet index sheetNum.
	 * @param sheetNum the index of sheet to get the column ids of.
	 * @return returns a List of Strings of the column ids of sheet sheetNum.
	 */
	public List<String> getSheetColumnIds(int sheetNum) {
		// get second row of the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row columnRow = sheet.getRow(1);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add("`"+ cell.getStringCellValue() + "`");
		}
		return result;
	}
	
	/**
	 * Get a list of column names from the sheet index sheetNum.
	 * @param sheetNum the index of sheet to get the column ids of.
	 * @return returns a List of String of the column names of sheet sheetNum.
	 */
	public List<String> getSheetColumnNames(int sheetNum) {
		// get third row of the sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row columnRow = sheet.getRow(2);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue().replace('\'', '_'));
		}
		return result;
	}
	
	/**
	 * Get the number of columns from sheet index sheetNum.
	 * @param sheetNum the index of sheet to get the number of columns from.
	 * @return returns an int of the number of columns in sheet sheetNum.
	 */
	public int getSheetNumColumns(int sheetNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);
		return sheet.getRow(2).getPhysicalNumberOfCells();
	}
	
	/**
	 * Get a list of the sheet names of the Excel file.
	 * @return returns a List of String of the names of the sheets.
	 */
	public List<String> getSheetNames() {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < getNumSheets(); i++) {
			result.add(workbook.getSheetName(i));
		}
		return result;
	}
	
	/**
	 * Get the number of sheets in the Excel file.
	 * @return returns an int of the number of sheets in the Excel file.
	 */
	public int getNumSheets() {
		return workbook.getNumberOfSheets();
	}
	
	/**
	 * Get the data of row rowNum at the sheet sheetNum.
	 * @param sheetNum the index of the sheet to get row of.
	 * @param rowNum the index of the row to get data of (first entry is row 3).
	 * @return returns a List of Strings of all the data of rowNum of sheetNum.
	 */
	public List<String> getSheetRow(int sheetNum, int rowNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);
		Row row = sheet.getRow(rowNum);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < getSheetNumColumns(sheetNum); i++) {
			if (row.getCell(i).getCellType() == CellType.STRING) {
				result.add(row.getCell(i).getStringCellValue());
			} 
			else if (row.getCell(i).getCellType() == CellType.NUMERIC) {
				result.add(Double.toString(row.getCell(i).getNumericCellValue()));
			}
			else if (row.getCell(i).getCellType() == CellType.BOOLEAN) {
				result.add(Boolean.toString(row.getCell(i).getBooleanCellValue()));
			}
		}
		return result;
	}
	
	/**
	 * Get the number of rows of sheet sheetNum, only includes data.
	 * @param sheetNum the index of the sheet to get number of rows of.
	 * @return returns an int of the number of rows of sheetNum.
	 */
	public int getSheetNumRows(int sheetNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);
		int result = sheet.getPhysicalNumberOfRows();
		result -= 3;
		return result;
	}
	
	public String getTemplateName(int sheetNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);
		// add template name by getting indexes 
		String temName = sheet.getRow(0).getCell(0).getStringCellValue();
		int start = temName.indexOf("\n", 0);
		start = temName.indexOf("\n", start+1);
		int end = temName.indexOf("\n", start+1);
		end = temName.indexOf("\n", end);
		temName = temName.substring(start, end);
		temName = temName.replace("\n", "");
		return temName;
	}

}
