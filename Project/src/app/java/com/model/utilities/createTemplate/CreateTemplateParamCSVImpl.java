package app.java.com.model.utilities.createTemplate;

import java.util.List;

import app.java.com.model.entities.template.BaseTemplate;
import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.CSVFile;

public class CreateTemplateParamCSVImpl implements CreateTemplateParamInterface {

	@Override
	public Template getTemplateNameColumns(String fileName, int sheetNum) {
		if (sheetNum != -1) {
			Exception e = new Exception("CSV files do not have sheet numbers.");
			e.printStackTrace();
			return null;
		}
		
		CSVFile csv = new CSVFile(fileName);
		
		// csv files only have 1 sheet, so no need to ask for sheet
		String temName = csv.getTemplateName();
		List<String> columnIds = csv.getColumnIds();
		List<String> columnNames = csv.getColumnNames();
		String tableName = "`" + csv.getName().replace(' ', '_') + "`";
		
        Template result = new BaseTemplate(temName, columnIds, columnNames, tableName);

		return result;
	}

}
