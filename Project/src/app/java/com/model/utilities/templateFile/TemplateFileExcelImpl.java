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
	private Workbook workbook;
	private String sheetName;
	private int numSheets;
	private List<String> sheetNames = new ArrayList<>();
	private Integer firstRequiredIdIndex;
	private List<String> requiredIds;
	private List<String> requiredNames;
	private List<String> columnIds;
	private List<String> columnNames;

	/**
	 * Constructor for TemplateFileExcelImpl for multiple sheets.
	 * 
	 * @param fileName the fileName to parse.
	 */
	public TemplateFileExcelImpl(String fileName) {
		extractNumSheets(fileName);
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
		this.setRequiredIdsNames();
	}

	private void extractNumSheets(String fileName) {
		try {
			Workbook workbook = WorkbookFactory.create(new File(fileName));
			this.workbook = workbook;
			this.numSheets = workbook.getNumberOfSheets();

			for (int sheet = 0; sheet < numSheets; sheet++) {
				sheetNames.add(workbook.getSheetName(sheet));
			}

			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses the Excel sheet into a Sheet.
	 * 
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

	private List<String> wrapItemWithQuote(List<String> originalStrList, String quote) {
		List<String> resultList = new ArrayList<>();
		for (String s : originalStrList) {
			String resStr = quote + s + quote;
			resultList.add(resStr);
		}
		return resultList;
	}

	@Override
	public Template getFileAsTemplate() {

		String temName = this.getTemplateName();
		List<String> columnIds = wrapItemWithQuote(this.getColumnIds(), "`");
		List<String> columnNames = wrapItemWithQuote(this.getColumnNames(), "'");
		String tableName = this.getTableName();
		List<String> requiredIds = wrapItemWithQuote(this.getRequiredIds(), "`");
		List<String> requiredColumnNames = wrapItemWithQuote(this.getRequiredColumnNames(), "'");
		Template result = new BaseTemplate(temName, columnNames, columnIds, tableName, requiredIds,
				requiredColumnNames);
		return result;
	}

	@Override
	public String getTemplateName() {
		try {
			String temName = sheet.getRow(0).getCell(0).getStringCellValue();
			int start = temName.indexOf("\n", 0);
			start = temName.indexOf("\n", start + 1);
			int end = temName.indexOf("\n", start + 1);
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
		if (this.columnIds == null) {
			this.setColumnIds();
		}
		return this.columnIds;
	}

	private void setColumnIds() {
		// get second row of the sheet
		Row columnRow = sheet.getRow(1);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			result.add(cell.getStringCellValue());
		}
		this.columnIds = result;

	}

	private void setColumnNames() {
		// get third row of the sheet
		Row columnRow = sheet.getRow(2);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (Cell cell : columnRow) {
			if (cell.getCellType() == CellType.STRING) {
				result.add(cell.getStringCellValue().replace('\'', '_'));
			} else if (cell.getCellType() == CellType.NUMERIC) {
				result.add(Double.toString(cell.getNumericCellValue()).replace('\'', '_'));
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				result.add(Boolean.toString(cell.getBooleanCellValue()).replace('\'', '_'));
			}
		}
		this.columnNames = result;
	}

	@Override
	public List<String> getColumnNames() {
		if (this.columnNames == null) {
			this.setColumnNames();
		}
		return this.columnNames;
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
				} else if (row.getCell(i).getCellType() == CellType.NUMERIC) {
					result.add(Double.toString(row.getCell(i).getNumericCellValue()));
				} else if (row.getCell(i).getCellType() == CellType.BOOLEAN) {
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
		while (!doneReading) {
			Row row = sheet.getRow(rowIndex);
			if (row == null || row.getCell(firstRequiredIdIndex).getStringCellValue().isEmpty()) {
				doneReading = true;
			}
			rowIndex++;
		}
		return rowIndex - 4;
	}

	public int getFirstRequiedIdIndex() {
		// first required id index
		String firstRequiredId = this.getRequiredIds().get(0);
		Row idRow = sheet.getRow(1);
		int firstRequiredIdIndex = 0;
		for (int i = 0; i < this.getNumColumns(); i++) {
			if (idRow.getCell(i).toString().compareTo(firstRequiredId) == 0) {
				firstRequiredIdIndex = i;
			}
		}
		return firstRequiredIdIndex;
	}

	public int getNumSheets() {
		return numSheets;
	}

	public List<String> getSheetNames() {
		return sheetNames;
	}

	public void setRequiredIdsNames() {
		List<String> idResult = new ArrayList<String>();
		List<String> nameResult = new ArrayList<String>();
		List<String> columnIds = getColumnIds();
		Row row = sheet.getRow(2);
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			int colour = row.getCell(i).getCellStyle().getFontIndexAsInt();
			if (colour == 5) {
				idResult.add(columnIds.get(i));
				nameResult.add(columnNames.get(i));
				if (this.firstRequiredIdIndex == null) {
					this.firstRequiredIdIndex = i;
				}
			}
		}
		this.requiredIds = idResult;
		this.requiredNames = nameResult;
	}

	public List<String> getRequiredIds() {
		if (this.requiredIds == null) {
			this.setRequiredIdsNames();
		}
		return this.requiredIds;
	}

	public List<String> getRequiredColumnNames() {
		if (this.requiredNames == null) {
			this.setRequiredIdsNames();
		}
		return this.requiredNames;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
		this.sheet = this.workbook.getSheet(sheetName);
		this.setRequiredIdsNames();
	}
}
