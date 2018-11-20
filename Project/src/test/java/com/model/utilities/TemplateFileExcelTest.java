package test.java.com.model.utilities;

import java.util.Arrays;
import java.util.List;


import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateFileExcelTest {

	String file1 = "./src/test/java/com/model/utilities/testFile1.xlsx";
	String file2 = "./src/test/java/com/model/utilities/testFile2.xlsx";
	List<String> file1SheetNames = Arrays.asList("`Example_Template`", "`My Template`");
	List<String> file1TemplateNames = Arrays.asList("Example Template", "My Template");
	List<String> file1Sheet0ColumnIds = Arrays.asList("first_name", "last_name", "age");
	List<String> file1Sheet0ColumnNames = Arrays.asList("First Name", "Last Name", "Age");
	List<String> file1Sheet0Row1 = Arrays.asList("Jane", "Doe", "20.0");
	List<String> file1Sheet0Row2 = Arrays.asList("Joe", "Smith", "30.0");
	List<String> file1Sheet1ColumnIds = Arrays.asList("student_id", "student_name", "student_enrol_date");
	List<String> file1Sheet1ColumnNames = Arrays.asList("Student Id", "Student Name", "Student Enrollment Date");
	List<String> file1Sheet1Row1 = Arrays.asList("100.0", "Jane Doe", "01/02/2018");
	List<String> file1Sheet1Row2 = Arrays.asList("200.0", "Joe Smith", "09/01/2018");
	
	int file2NumSheets = 3;
	int file2Sheet0NumRows;
	int file2Sheet1NumRows;
	int file2Sheet2NumRows;
	int file2Sheet0NumColumns;
	int file2Sheet1NumColumns;
	int file2Sheet2NumColumns;
	List<String> file2Sheet2ColumnTypes = Arrays.asList("INT", "VARCHAR", "VARCHAR", "INT");
	
	@Test
	public void testGetName() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		String result = excelFile1.getTableName();
		assertEquals(file1SheetNames.get(0), result);
	}
	
	@Test
	public void testGetSheetColumnIds1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		List<String> result = excelFile1.getColumnIds();
		assertEquals(file1Sheet0ColumnIds, result);
	}

	@Test
	public void testGetSheetColumnIds2() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		List<String> result = excelFile1.getColumnIds();
		assertEquals(file1Sheet1ColumnIds, result);
	}
	
	@Test
	public void testGetSheetColumnNames1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		List<String> result = excelFile1.getColumnNames();
		assertEquals(file1Sheet0ColumnNames, result);
	}
	
	@Test
	public void testGetSheetColumnNames2() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		List<String> result = excelFile1.getColumnNames();
		assertEquals(file1Sheet1ColumnNames, result);
	}
	
	@Test
	public void testGetSheetNumColumns() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		int result = excelFile1.getNumColumns();
		assertEquals(3, result);
	}
	
	@Test
	public void testGetSheetRows1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		List<String> result = excelFile1.getRow(3);
		assertEquals(file1Sheet0Row1, result);
	}
	
	@Test
	public void testGetSheetRows2() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		List<String> result = excelFile1.getRow(4);
		assertEquals(file1Sheet0Row2, result);
	}
	
	@Test
	public void testGetSheetRows3() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		List<String> result = excelFile1.getRow(3);
		assertEquals(file1Sheet1Row1, result);
	}
	
	@Test
	public void testGetSheetRows4() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		List<String> result = excelFile1.getRow(4);
		assertEquals(file1Sheet1Row2, result);
	}
	
	@Test
	public void testGetSheetNumRows1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		int result = excelFile1.getNumRows();
		assertEquals(2, result);
	}
	
	@Test
	public void testGetSheetNumRows2() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		int result = excelFile1.getNumRows();
		assertEquals(2, result);
	}
	
	@Test
	public void testGetTemplateName0() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 0);
		String result = excelFile1.getTemplateName();
		assertEquals(file1TemplateNames.get(0), result);
	}
	
	@Test
	public void testGetTemplateName1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		String result = excelFile1.getTemplateName();
		assertEquals(file1TemplateNames.get(1), result);
	}
	
	@Test
	public void testGetSheetNumSheets() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getNumSheets();
		assertEquals(file2NumSheets, result);
	}
	
	@Test
	public void testGetSheetNumRows4() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumRows(0);
		assertEquals(file2Sheet0NumRows, result);
	}
	
	@Test
	public void testGetSheetNumRows5() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumRows(1);
		assertEquals(file2Sheet1NumRows, result);
	}

	@Test
	public void testGetSheetNumRows6() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumRows(2);
		assertEquals(file2Sheet2NumRows, result);
	}

	@Test
	public void testGetSheetNumColumns0() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumColumns(0);
		assertEquals(file2Sheet0NumColumns, result);
	}
	
	@Test
	public void testGetSheetNumColumns1() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumColumns(1);
		assertEquals(file2Sheet1NumColumns, result);
	}
	
	@Test
	public void testGetSheetNumColumns2() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		int result = excelFile2.getSheetNumColumns(2);
		assertEquals(file2Sheet2NumColumns, result);
	}
	
	@Test
	public void testGetColumnTypes() {
		TemplateFileExcelImpl excelFile2 = new TemplateFileExcelImpl(file2);
		List<String> result = excelFile2.getColumnTypes(2, 0);
		assertEquals(file2Sheet2ColumnTypes, result);

	}
}
