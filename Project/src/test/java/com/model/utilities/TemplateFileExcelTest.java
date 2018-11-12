package test.java.com.model.utilities;

import java.util.Arrays;
import java.util.List;


import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateFileExcelTest {

	String file1 = "./src/test/java/com/model/utilities/testFile1.xlsx";
	List<String> file1SheetNames = Arrays.asList("Example Template", "My Template");
	List<String> file1Sheet0ColumnIds = Arrays.asList("`first_name`", "`last_name`", "`age`");
	List<String> file1Sheet0ColumnNames = Arrays.asList("First Name", "Last Name", "Age");
	List<String> file1Sheet0Row1 = Arrays.asList("Jane", "Doe", "20.0");
	List<String> file1Sheet0Row2 = Arrays.asList("Joe", "Smith", "30.0");
	List<String> file1Sheet1ColumnIds = Arrays.asList("`student_id`", "`student_name`", "`student_enrol_date`");
	List<String> file1Sheet1ColumnNames = Arrays.asList("Student Id", "Student Name", "Student Enrollment Date");
	List<String> file1Sheet1Row1 = Arrays.asList("100.0", "Jane Doe", "01/02/2018");
	List<String> file1Sheet1Row2 = Arrays.asList("200.0", "Joe Smith", "09/01/2018");
	
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
		assertEquals(file1SheetNames.get(0), result);
	}
	
	@Test
	public void testGetTemplateName1() {
		TemplateFileInterface excelFile1 = new TemplateFileExcelImpl (file1, 1);
		String result = excelFile1.getTemplateName();
		assertEquals(file1SheetNames.get(1), result);
	}

}
