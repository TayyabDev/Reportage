package app.java.com.model.utilities.templateFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.java.com.model.entities.template.BaseTemplate;
import app.java.com.model.entities.template.Template;

public class TemplateFileCsvImpl implements TemplateFileInterface {
	
	private List<List<String>> fileList;
	private String fileName;
	
	/**
	 * The main constructor for TemplateFileCsvImpl.
	 * 
	 * @param fileName the fileName to parse.
	 */
	public TemplateFileCsvImpl(String fileName) {
		this.fileList = parseCSVFile(fileName);	
		this.fileName = new File(fileName).getName().replaceFirst("[.][^.]+$", "");
	}
	
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
	
	@Override
	public Template getTemplateNameColumns() {
				
		// csv files only have 1 sheet, so no need to ask for sheet
		String temName = this.getTemplateName();
		List<String> columnIds = this.getColumnIds();
		List<String> columnNames = this.getColumnNames();
		String tableName = "`" + this.getTableName().replace(' ', '_') + "`";
		
        Template result = new BaseTemplate(temName, columnIds, columnNames, tableName);

		return result;
	}

	@Override
	public String getTemplateName() {
		List<String> row = this.fileList.get(0);
		String result = row.get(0);		
		return result;
	}

	@Override
	public String getTableName() {
		return this.fileName;
	}

	@Override
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

	@Override
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

	@Override
	public int getNumColumns() {
		List<String> row = this.fileList.get(1);
		int result = row.size();
		return result;
	}

	@Override
	public List<String> getRow(int rowNum) {
		List<String> result = this.fileList.get(rowNum);
		return result;
	}

	@Override
	public int getNumRows() {
		int result = this.fileList.size();
		result -= 3;
		return result;
	}

}
