package app.java.com.model.utilities.templateFile;

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

import app.java.com.model.entities.template.BaseTemplate;
import app.java.com.model.entities.template.Template;

public class TemplateFileExcelImpl implements TemplateFileInterface {
	
	private Sheet sheet;
	private String sheetName;
	
	/**
	 * The main constructor for ExcelFile.
	 * 
	 * @param fileName the fileName to parse.
	 * @param sheetNum the sheet number to parse.
	 */
	public TemplateFileExcelImpl (String fileName, int sheetNum) {
		this.sheet = parseExcelFile(fileName, sheetNum);
		this.sheetName = sheet.getSheetName();
	}
	
	/**
	 * Parses the Excel sheet into a Sheet.
	 * @param fileName the name of the file to parse.
	 * @return returns a Sheet of the Excel sheet
	 */
	private Sheet parseExcelFile(String fileName, int sheetNum) {
		Sheet currentSheet = null;
		try {
			Workbook workbook = WorkbookFactory.create(new File(fileName));
			currentSheet = workbook.getSheetAt(sheetNum);
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return currentSheet;
	}
	
	@Override
	public Template getFileAsTemplate() {
        
		String temName = this.getTemplateName();
        List<String> columnIds = this.getColumnIds();
        List<String> columnNames = this.getColumnNames();
        String tableName = "`" + this.getTableName().replace(' ', '_') + "`";
        
        Template result = new BaseTemplate(temName, columnNames, columnIds , tableName);
        
		return result;
	}

	@Override
	public String getTemplateName() {
		String temName = sheet.getRow(0).getCell(0).getStringCellValue();
		int start = temName.indexOf("\n", 0);
		start = temName.indexOf("\n", start+1);
		int end = temName.indexOf("\n", start+1);
		end = temName.indexOf("\n", end);
		temName = temName.substring(start, end);
		temName = temName.replace("\n", "");
		return temName;
	}

	@Override
	public String getTableName() {
		return this.sheetName;
	}

	@Override
	public List<String> getColumnIds() {
		// get second row of the sheet
		Row columnRow = sheet.getRow(1);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue());
		}
		return result;
	}

	@Override
	public List<String> getColumnNames() {
		// get third row of the sheet
		Row columnRow = sheet.getRow(2);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue().replace('\'', '_'));
		}
		return result;
	}

	@Override
	public int getNumColumns() {
		return sheet.getRow(2).getPhysicalNumberOfCells();
	}

	@Override
	public List<String> getRow(int rowNum) {
		Row row = sheet.getRow(rowNum);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < getNumColumns(); i++) {
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

	@Override
	public int getNumRows() {

	    int rowIndex = 3;
	    boolean doneReading = false;
	    while(!doneReading) {
	        Row row = sheet.getRow(rowIndex);
	        if(row == null || row.getCell(0).getStringCellValue().isEmpty()) {
	            doneReading = true;
            }

            rowIndex++;
	    }

		return rowIndex - 4;
	}

}
