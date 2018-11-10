package test.java.com.model.utilities;

import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import java.util.Arrays;
import java.util.List;

public class MockFile implements TemplateFileInterface {

    public static final List<String> COLUMN_IDS = Arrays.asList("ID1", "ID2", "ID3", "ID4");
    public static final List<String> COLUMN_NAMES = Arrays.asList("Column1", "Column2", "Column3", "Column4");
    public static final String TABLE_NAME = "MockTable";
    public static final String TEMPLATE_NAME = "MockFile";
    public static final List<String> MOCK_ROW = Arrays.asList("RowVal1", "RowVal2", "RowVal3", "RowVal4");
    public static final int NUM_ROWS = 5;
    private Template mockFileTemplate;

    public MockFile(Template mockTemplate) {
        this.mockFileTemplate = mockTemplate;
    }

    @Override
    public Template getFileAsTemplate() {
        return mockFileTemplate;
    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    public List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public int getNumColumns() {
        return COLUMN_NAMES.size();
    }

    @Override
    public List<String> getRow(int rowNum) {
        return MOCK_ROW;
    }

    @Override
    public int getNumRows() {
        return NUM_ROWS;
    }

    @Override
    public List<String> getColumnIds() {
        return COLUMN_IDS;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
