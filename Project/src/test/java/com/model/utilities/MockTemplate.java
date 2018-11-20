package test.java.com.model.utilities;

import app.java.com.model.entities.template.Template;
import app.java.com.model.entities.template.TemplateOptions;

import java.util.List;

public class MockTemplate extends Template {
    @Override
    public String getTemplateName() {
        return ClientProfileMockFile.TEMPLATE_NAME;
    }

    @Override
    public List<String> getColumnNames() {
        return ClientProfileMockFile.columnNames;
    }

    @Override
    public List<String> getColumnIds() {
        return ClientProfileMockFile.columnIds;
    }

    @Override
    public String getTableName() {
        return ClientProfileMockFile.TABLE_NAME;
    }

	@Override
	public List<String> getColumnTypes() {
		return ClientProfileMockFile.columnTypes;
	}
}
