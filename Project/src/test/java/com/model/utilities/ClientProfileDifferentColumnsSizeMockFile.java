package test.java.com.model.utilities;

import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import java.util.Arrays;
import java.util.List;

public class ClientProfileDifferentColumnsSizeMockFile implements TemplateFileInterface {

    public static final List<String> COLUMN_IDS = Arrays.asList("processing_details", "client_validation_type_id",
            "client_validation_id", "client_birth_dt", "phone_no", "email_txt_ind", "email_txt", "street_no", "street_nme",
            "street_type_id", "street_direction_id", "unit_txt", "city_txt", "province_id", "postal_txt", "official_language_id",
            "consent_ind", "one_more_column_id");

    public static final List<String> COLUMN_NAMES = Arrays.asList("Processing Details", "Unique Identifier",
            "Unique Identifier Value",
            "Date of Birth (YYYY-MM-DD)",
            "Phone Number",
            "Does the Client Have an Email Address",
            "Email Address",
            "Street Number",
            "Street Name",
            "Street Type",
            "Street Direction",
            "Unit/Suite/Apt",
            "City",
            "Province",
            "Postal Code",
            "Official Language of Preference",
            "Consent for Future Research/Consultation", "Filler Column Name");

    public static final String TABLE_NAME = "ClientProfile";
    public static final String TEMPLATE_NAME = "ClientProfileDifferentColumnsSizeMockFile";
    public static final List<String> MOCK_ROW = Arrays.asList("RowVal1", "RowVal2", "RowVal3", "RowVal4", "RowVal5",
            "RowVal6", "RowVal7", "RowVal8", "RowVal9", "RowVal10", "RowVal11", "RowVal12", "RowVal13", "RowVal14",
            "RowVal15", "RowVal16", "RowVal17", "RowVal18");
    public static final List<String> REQUIRED_IDS = Arrays.asList("client_validation_type_id", "client_validation_id",
    		"client_birth_dt", "postal_txt");
    public static final int NUM_ROWS = 5;
    private Template mockFileTemplate;

    public ClientProfileDifferentColumnsSizeMockFile(Template mockTemplate) {
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
    
    public List<String> getRequiredIds() {
    	return REQUIRED_IDS;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
