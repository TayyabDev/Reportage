package test.java.com.model.utilities;

import app.java.com.model.entities.template.Template;
import java.util.List;

public class MockTemplate extends Template {
    @Override
    public String getTemplateName() {
        return MockFile.TEMPLATE_NAME;
    }

    @Override
    public List<String> getColumnNames() {
        return MockFile.COLUMN_NAMES;
    }

    @Override
    public List<String> getColumnIds() {
        return MockFile.COLUMN_IDS;
    }

    @Override
    public String getTableName() {
        return MockFile.TABLE_NAME;
    }
}
