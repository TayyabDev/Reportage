package com.test.model.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.app.model.utilities.ExcelFile;


public class ExcelFileTest {

	String file1 = "./src/com/test/model/utilities/testFile1.xlsx";
	List<String> file1SheetNames = Arrays.asList("Example Template", "My Template");
	List<String> file1Sheet0ColumnIds = Arrays.asList("first_name", "last_name", "age");
	List<String> file1Sheet0ColumnNames = Arrays.asList("First Name", "Last Name", "Age");
	List<String> file1Sheet0Row1 = Arrays.asList("Jane", "Doe", "20.0");
	List<String> file1Sheet0Row2 = Arrays.asList("Joe", "Smith", "30.0");
	List<String> file1Sheet1ColumnIds = Arrays.asList("student_id", "student_name", "student_enrol_date");
	List<String> file1Sheet1ColumnNames = Arrays.asList("Student Id", "Student Name", "Student Enrollment Date");
	List<String> file1Sheet1Row1 = Arrays.asList("100.0", "Jane Doe", "01/02/2018");
	List<String> file1Sheet1Row2 = Arrays.asList("200.0", "Joe Smith", "09/01/2018");
	
	@Test
	public void testGetSheetName() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		String result = excelFile1.getSheetName(0);
		assertEquals(file1SheetNames.get(0), result);
	}
	
	@Test
	public void testGetSheetNames() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetNames();
		assertEquals(file1SheetNames, result);
	}
	
	@Test
	public void testGetNumSheets() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		int result = excelFile1.getNumSheets();
		assertEquals(2, result);
	}
	
	@Test
	public void testGetSheetColumnIds1() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetColumnIds(0);
		assertEquals(file1Sheet0ColumnIds, result);
	}

	@Test
	public void testGetSheetColumnIds2() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetColumnIds(1);
		assertEquals(file1Sheet1ColumnIds, result);
	}
	
	@Test
	public void testGetSheetColumnNames1() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetColumnNames(0);
		assertEquals(file1Sheet0ColumnNames, result);
	}
	
	@Test
	public void testGetSheetColumnNames2() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetColumnNames(1);
		assertEquals(file1Sheet1ColumnNames, result);
	}
	
	@Test
	public void testGetSheetNumColumns() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		int result = excelFile1.getSheetNumColumns(0);
		assertEquals(3, result);
	}
	
	@Test
	public void testGetSheetRows1() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetRow(0, 3);
		assertEquals(file1Sheet0Row1, result);
	}
	
	@Test
	public void testGetSheetRows2() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetRow(0, 4);
		assertEquals(file1Sheet0Row2, result);
	}
	
	@Test
	public void testGetSheetRows3() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetRow(1, 3);
		assertEquals(file1Sheet1Row1, result);
	}
	
	@Test
	public void testGetSheetRows4() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		List<String> result = excelFile1.getSheetRow(1, 4);
		assertEquals(file1Sheet1Row2, result);
	}
	
	@Test
	public void testGetSheetNumRows1() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		int result = excelFile1.getSheetNumRows(0);
		assertEquals(2, result);
	}
	
	@Test
	public void testGetSheetNumRows2() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		int result = excelFile1.getSheetNumRows(1);
		assertEquals(2, result);
	}
	
	@Test
	public void testGetTemplateName0() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		String result = excelFile1.getTemplateName(0);
		assertEquals(file1SheetNames.get(0), result);
	}
	
	@Test
	public void testGetTemplateName1() {
		ExcelFile excelFile1 = new ExcelFile(file1);
		String result = excelFile1.getTemplateName(1);
		assertEquals(file1SheetNames.get(1), result);
	}

}
