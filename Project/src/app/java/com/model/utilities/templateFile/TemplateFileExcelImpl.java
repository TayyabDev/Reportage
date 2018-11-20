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
import app.java.com.model.entities.template.TemplateOptions;

public class TemplateFileExcelImpl implements TemplateFileInterface {
	
	private Sheet sheet;
	private Workbook workbook;
	private String sheetName;
	private int sheetNum;
	private String fileName;
	
	/**
	 * Constructor for TemplateFileExcelImpl for multiple sheets.
	 * 
	 * @param fileName the fileName to parse.
	 */
	public TemplateFileExcelImpl(String fileName) {
		this.workbook = parceExcelFile(fileName);
		this.fileName = fileName;
	}
	
	/**
	 * The main constructor for TemplateFileExcelImpl.
	 * 
	 * @param fileName the fileName to parse.
	 * @param sheetNum the sheet number to parse.
	 */
	public TemplateFileExcelImpl(String fileName, int sheetNum) {
		this.sheet = parseExcelFile(fileName, sheetNum);
		this.sheetName = sheet.getSheetName();
		this.sheetNum = sheetNum;
		this.fileName = fileName;
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
			this.workbook = workbook;
			currentSheet = workbook.getSheetAt(sheetNum);
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return currentSheet;
	}
	
	/**
	 * Parses the Excel File into a Workbook.
	 * @param fileName the name of the file to parse.
	 * @return returns a Workbook of the Excel File.
	 */
	private Workbook parceExcelFile(String fileName) {
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(fileName));
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	private TemplateOptions parseTemplateOptions(List<String> columnIds, List<String> columnNames) {
		
		List<String> columnTypes = new ArrayList<String>();
		List<List<String>> columnOptions = new ArrayList<List<String>>();
		List<Boolean> columnRequired = new ArrayList<Boolean>();
		
		Sheet optionsSheet = workbook.getSheetAt(1);
		Row optionColumnNames = optionsSheet.getRow(1);
		int columnNum = 0;
		for (int i = 0; i < columnNames.size(); i++) {
			for (int j = 0; j < getSheetNumColumns(1); j++) {
				if (optionColumnNames.getCell(i).getStringCellValue().equals(columnNames.get(i))) {
					columnNum = j;
					break;
				}
			}
			for (int k = 2; k < getSheetNumRows(1); k++) {
				columnOptions.get(columnNum).add(optionsSheet.getRow(k).getCell(columnNum).getStringCellValue());
			}
			columnTypes.add(optionsSheet.getRow(1).getCell(columnNum).getCellType().toString());
			columnRequired.add(optionsSheet.getRow(1).getCell(columnNum).getCellStyle().getFontIndexAsInt() != 0);
		}
		
		
		TemplateOptions templateOptions = new TemplateOptions(columnIds, columnTypes, columnOptions);
		return templateOptions;
	}
	
	@Override
	public Template getFileAsTemplate() {
        
		String temName = this.getTemplateName();
        List<String> columnIds = this.getColumnIds();
        List<String> columnNames = this.getColumnNames();
        String tableName = "`" + this.getTableName().replace(' ', '_') + "`";
        Template result = null;

		if (sheetNum > 1) {
	        List<String> columnTypes = this.getColumnTypes(sheetNum, 1);
			result = new BaseTemplate(temName, columnNames, columnIds, tableName, columnTypes);
		} else {
			result = new BaseTemplate(temName, columnNames, columnIds, tableName);

		}
		return result;
	}

	@Override
	public String getTemplateName() {
		try {
			String temName = sheet.getRow(0).getCell(0).getStringCellValue();
			int start = temName.indexOf("\n", 0);
			start = temName.indexOf("\n", start+1);
			int end = temName.indexOf("\n", start+1);
			end = temName.indexOf("\n", end);
			temName = temName.substring(start, end);
			temName = temName.replace("\n", "");
			return temName;
		} catch (Exception e) {
			return "Untitled Template";
		}
	}

	@Override
	public String getTableName() {
		return '`' + this.sheetName.replace(' ', '_') + '`';
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
			if (cell.getCellType() == CellType.STRING) {
				result.add(cell.getStringCellValue().replace('\'', '_'));
			} 
			else if (cell.getCellType() == CellType.NUMERIC) {
				result.add(Double.toString(cell.getNumericCellValue()).replace('\'', '_'));
			}
			else if (cell.getCellType() == CellType.BOOLEAN) {
				result.add(Boolean.toString(cell.getBooleanCellValue()).replace('\'', '_'));
			}
		}
		return result;
	}

	@Override
	public int getNumColumns() {
		return sheet.getRow(1).getPhysicalNumberOfCells();
	}

	@Override
	public List<String> getRow(int rowNum) {
		Row row = sheet.getRow(rowNum);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < getNumColumns(); i++) {
			try {
				if (row.getCell(i).getCellType() == CellType.STRING) {
					result.add(row.getCell(i).getStringCellValue());
				} 
				else if (row.getCell(i).getCellType() == CellType.NUMERIC) {
					result.add(Double.toString(row.getCell(i).getNumericCellValue()));
				}
				else if (row.getCell(i).getCellType() == CellType.BOOLEAN) {
					result.add(Boolean.toString(row.getCell(i).getBooleanCellValue()));
				}
			} catch (NullPointerException e) {
				result.add(null);
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
	        if (row == null || row.getCell(0).getStringCellValue().isEmpty()) {
	            doneReading = true;
            }
            rowIndex++;
	    }
		return rowIndex - 4;
	}
	
	public int getNumSheets() {
		return workbook.getNumberOfSheets();
	}
	
	public int getSheetNumRows(int sheetNum) {
		Sheet sheet = workbook.getSheetAt(sheetNum);
	    int rowIndex = 3;
	    boolean doneReading = false;
	    while(!doneReading) {
	        Row row = sheet.getRow(rowIndex);
	        if (row == null || row.getCell(0).getStringCellValue().isEmpty()) {
	            doneReading = true;
            }
            rowIndex++;
	    }
		return rowIndex - 4;
	}
	
	public int getSheetNumColumns(int sheetNum) {
		return workbook.getSheetAt(sheetNum).getRow(1).getPhysicalNumberOfCells();
	}
	
	public List<String> getColumnTypes(int sheetNum, int optionsSheetNum) {
		TemplateFileExcelImpl templateSheet = new TemplateFileExcelImpl(fileName, sheetNum);
		TemplateFileExcelImpl optionsSheet = new TemplateFileExcelImpl(fileName, optionsSheetNum);
		System.out.println(optionsSheet.getFileAsTemplate().toString());
		List<String> columnNames = templateSheet.getColumnNames();
		List<String> optionColumnNames = optionsSheet.getColumnIds();
		List<String> optionFirstEntry = optionsSheet.getRow(2);
		List<String> columnTypes = new ArrayList<String>();
		
		for (int columnIndex = 0; columnIndex < templateSheet.getNumColumns(); columnIndex++) {
			String columnName = columnNames.get(columnIndex);
			System.out.println(optionsSheet.getNumColumns());
			for (int optionIndex = 0; optionIndex < optionsSheet.getNumColumns(); optionIndex++) {
				System.out.println(String.format("if %s equals %s", optionColumnNames.get(optionIndex),columnName));
				if (optionColumnNames.get(optionIndex).equals(columnName)) {
					System.out.println(optionFirstEntry.get(optionIndex));
					String option = optionFirstEntry.get(optionIndex);
					try {
						Double.valueOf(option);
						columnTypes.add("INT");
						System.out.println("INT");
					} catch (NullPointerException | NumberFormatException e) {
						columnTypes.add("VARCHAR");
						System.out.println("VARCHAR");
					}
				}
			}
		}
		return columnTypes;
	}
	
}
