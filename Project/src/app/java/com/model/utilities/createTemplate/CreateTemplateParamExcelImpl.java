package app.java.com.model.utilities.createTemplate;

import java.util.List;

import app.java.com.model.entities.template.BaseTemplate;
import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.ExcelFile;

public class CreateTemplateParamExcelImpl implements CreateTemplateParamInterface {

	public Template getTemplateNameColumns(String fileName, int sheetNum) {

		ExcelFile exc = new ExcelFile(fileName);
        
        // ask ui for specific sheet number 
        // for now by default the excel file only contain 1 sheet
        String temName = exc.getTemplateName(sheetNum);
        List<String> columnIds = exc.getSheetColumnIds(sheetNum);
        List<String> columnNames = exc.getSheetColumnNames(sheetNum);
        
        // using the sheet Name as the table name in the database
        String sheetName = exc.getSheetName(sheetNum);
        String tableName = "`" + sheetName.replace(' ', '_') + "`";
        
        Template result = new BaseTemplate(temName, columnIds, columnNames, tableName);
        
		return result;
	}

}
