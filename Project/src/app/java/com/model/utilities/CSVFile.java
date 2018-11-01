package app.java.com.model.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFile {
	
	private List<List<String>> fileList;
	private String fileName;
	
	/**
	 * The main constructor for CSVFile.
	 * 
	 * @param fileName the fileName to parse.
	 */
	public CSVFile(String fileName) {
		this.fileList = parseCSVFile(fileName);	
		this.fileName = new File(fileName).getName().replaceFirst("[.][^.]+$", "");
	}
	
	/**
	 * Parses the CSV file into a Workbook.
	 * @param fileName the name of the file to parse.
	 * @return returns a Workbook of all the CSV file.
	 */
	private List<List<String>> parseCSVFile(String fileName) {
	    List<List<String>> result = new ArrayList<>();
	    try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String line = "";
	        while ((line = br.readLine()) != null) {
	        	result.add(Arrays.asList(line.split(",")));
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return result;
	}
	
	/**
	 * Gets the name of the row at row index rowNum.
	 * @return returns the name of the row.
	 */
	public String getName() {
		return this.fileName;
	}
	
	/**
	 * Gets a list of column ids from the row index rowNum.
	 * @return returns a List of Strings of the column ids of row rowNum.
	 */
	public List<String> getColumnIds() {
		// get second row of the file
		List<String> row = this.fileList.get(1);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (String cell: row) {
			result.add("`"+ cell + "`");
		}
		return result;
	}
	
	/**
	 * Get a list of column names from the row index rowNum.
	 * @return returns a List of String of the column names of row rowNum.
	 */
	public List<String> getColumnNames() {
		// get third row of the row
		List<String> row = this.fileList.get(2);
		List<String> result = new ArrayList<String>();

		// add column names to list
		for (String cell : row) {
			result.add(cell.replace('\'', '_'));
		}
		return result;
	}
	
	/**
	 * Get the number of columns from file.
	 * @return returns an int of the number of columns in file.
	 */
	public int getNumColumns() {
		List<String> row = this.fileList.get(1);
		int result = row.size();
		return result;
	}
	
	/**
	 * Get the data of row rowNum at the row rowNum.
	 * @param rowNum the index of the row to get data of (first entry is row 3).
	 * @return returns a List of Strings of all the data of rowNum of rowNum.
	 */
	public List<String> getRow(int rowNum) {
		List<String> result = this.fileList.get(rowNum);
		return result;
	}
	
	/**
	 * Get the number of rows of row rowNum, only includes data.
	 * @return returns an int of the number of rows of rowNum.
	 */
	public int getNumRows() {
		int result = this.fileList.size();
		result -= 3;
		return result;
	}
	
	public String getTemplateName() {
		List<String> row = this.fileList.get(0);
		String result = row.get(0);		
		return result;
	}

}
