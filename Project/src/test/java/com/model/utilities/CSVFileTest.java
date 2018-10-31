package test.java.com.model.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import app.java.com.model.utilities.CSVFile;


public class CSVFileTest {

	String file1 = "./src/test/java/com/model/utilities/exampleTemplate.csv";
	String file2 = "./src/test/java/com/model/utilities/myTemplate.csv";
	String file1Name = "exampleTemplate";
	String file2Name = "myTemplate";
	String template1Name = "Example Template";
	String template2Name = "My Template";
	List<String> file1ColumnIds = Arrays.asList("`first_name`", "`last_name`", "`age`");
	List<String> file1ColumnNames = Arrays.asList("First Name", "Last Name", "Age");
	List<String> file1Row1 = Arrays.asList("Jane", "Doe", "20");
	List<String> file1Row2 = Arrays.asList("Joe", "Smith", "30");
	List<String> file2ColumnIds = Arrays.asList("`student_id`", "`student_name`", "`student_enrol_date`");
	List<String> file2ColumnNames = Arrays.asList("Student Id", "Student Name", "Student Enrollment Date");
	List<String> file2Row1 = Arrays.asList("100", "Jane Doe", "01/02/2018");
	List<String> file2Row2 = Arrays.asList("200", "Joe Smith", "09/01/2018");
	
	@Test
	public void testGetName1() {
		CSVFile csvFile1 = new CSVFile(file1);
		String result = csvFile1.getName();
		assertEquals(file1Name, result);
	}
	
	@Test
	public void testGetName2() {
		CSVFile csvFile2 = new CSVFile(file2);
		String result = csvFile2.getName();
		assertEquals(file2Name, result);
	}
	
	@Test
	public void testGetColumnIds1() {
		CSVFile csvFile1 = new CSVFile(file1);
		List<String> result = csvFile1.getColumnIds();
		assertEquals(file1ColumnIds, result);
	}

	@Test
	public void testGetColumnIds2() {
		CSVFile csvFile2 = new CSVFile(file2);
		List<String> result = csvFile2.getColumnIds();
		assertEquals(file2ColumnIds, result);
	}
	
	@Test
	public void testGetColumnNames1() {
		CSVFile csvFile1 = new CSVFile(file1);
		List<String> result = csvFile1.getColumnNames();
		assertEquals(file1ColumnNames, result);
	}
	
	@Test
	public void testGetColumnNames2() {
		CSVFile csvFile2 = new CSVFile(file2);
		List<String> result = csvFile2.getColumnNames();
		assertEquals(file2ColumnNames, result);
	}
	
	@Test
	public void testGetNumColumns() {
		CSVFile csvFile1 = new CSVFile(file1);
		int result = csvFile1.getNumColumns();
		assertEquals(3, result);
	}
	
	@Test
	public void testGetRows1() {
		CSVFile csvFile1 = new CSVFile(file1);
		List<String> result = csvFile1.getRow(3);
		assertEquals(file1Row1, result);
	}
	
	@Test
	public void testGetRows2() {
		CSVFile csvFile1 = new CSVFile(file1);
		List<String> result = csvFile1.getRow(4);
		assertEquals(file1Row2, result);
	}
	
	@Test
	public void testGetRows3() {
		CSVFile csvFile2 = new CSVFile(file2);
		List<String> result = csvFile2.getRow(3);
		assertEquals(file2Row1, result);
	}
	
	@Test
	public void testGetRows4() {
		CSVFile csvFile2 = new CSVFile(file2);
		List<String> result = csvFile2.getRow(4);
		assertEquals(file2Row2, result);
	}
	
	@Test
	public void testGetNumRows1() {
		CSVFile csvFile1 = new CSVFile(file1);
		int result = csvFile1.getNumRows();
		assertEquals(2, result);
	}
	
	@Test
	public void testGetNumRows2() {
		CSVFile csvFile1 = new CSVFile(file1);
		int result = csvFile1.getNumRows();
		assertEquals(2, result);
	}
	
	@Test
	public void testGetTemplateName1() {
		CSVFile csvFile1 = new CSVFile(file1);
		String result = csvFile1.getTemplateName();
		assertEquals(template1Name, result);
	}
	
	@Test
	public void testGetTemplateName2() {
		CSVFile csvFile2 = new CSVFile(file2);
		String result = csvFile2.getTemplateName();
		assertEquals(template2Name, result);
	}

}
