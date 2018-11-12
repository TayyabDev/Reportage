package test.java.com.model.utilities;

import app.java.com.model.entities.template.Template;
import java.util.List;

public class MockTemplate extends Template {
    @Override
    public String getTemplateName() {
        return ClientProfileMockFile.TEMPLATE_NAME;
    }

    @Override
    public List<String> getColumnNames() {
        return ClientProfileMockFile.COLUMN_NAMES;
    }

    @Override
    public List<String> getColumnIds() {
        return ClientProfileMockFile.COLUMN_IDS;
    }

    @Override
    public String getTableName() {
        return ClientProfileMockFile.TABLE_NAME;
    }
}
