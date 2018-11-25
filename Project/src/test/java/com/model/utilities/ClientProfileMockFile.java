package test.java.com.model.utilities;

import java.util.ArrayList;
import java.util.List;

import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;

public class ClientProfileMockFile implements TemplateFileInterface {

	static List<String> columnIds = null;
	static List<String> columnNames = null;
	static List<String> requiredIds = null;
	static List<String> mockRow = null;

	public static final String TABLE_NAME = "Client_Profile";
	public static final String TEMPLATE_NAME = "ClientProfileMockFile";
	public static final int NUM_ROWS = 1;
	private Template mockFileTemplate;

	static {
		columnIds = new ArrayList<>();
		columnIds.add("processing_details");
		columnIds.add("client_validation_type_id");
		columnIds.add("client_validation_id");
		columnIds.add("client_birth_dt");
		columnIds.add("phone_no");
		columnIds.add("email_txt_ind");
		columnIds.add("email_txt");
		columnIds.add("street_no");
		columnIds.add("street_nme");
		columnIds.add("street_type_id");
		columnIds.add("street_direction_id");
		columnIds.add("unit_txt");
		columnIds.add("city_txt");
		columnIds.add("province_id");
		columnIds.add("postal_txt");
		columnIds.add("official_language_id");
		columnIds.add("consent_ind");

		columnNames = new ArrayList<>();
		columnNames.add("Processing Details");
		columnNames.add("Unique Identifier");
		columnNames.add("Unique Identifier Value");
		columnNames.add("Date of Birth (YYYY-MM-DD)");
		columnNames.add("Phone Number");
		columnNames.add("Does the Client Have an Email Address");
		columnNames.add("Email Address");
		columnNames.add("Street Number");
		columnNames.add("Street Name");
		columnNames.add("Street Type");
		columnNames.add("Street Direction");
		columnNames.add("Unit/Suite/Apt");
		columnNames.add("City");
		columnNames.add("Province");
		columnNames.add("Postal Code");
		columnNames.add("Official Language of Preference");
		columnNames.add("Consent for Future Research/Consultation");


		mockRow = new ArrayList<>();
		mockRow.add("RowVal1");
		mockRow.add("RowVal2");
		mockRow.add("RowVal3");
		mockRow.add("RowVal4");
		mockRow.add("RowVal5");
		mockRow.add("RowVal6");
		mockRow.add("RowVal7");
		mockRow.add("RowVal8");
		mockRow.add("RowVal9");
		mockRow.add("RowVal10");
		mockRow.add("RowVal11");
		mockRow.add("RowVal12");
		mockRow.add("RowVal13");
		mockRow.add("RowVal14");
		mockRow.add("RowVal15");
		mockRow.add("RowVal16");
		mockRow.add("RowVal17");

		requiredIds = new ArrayList<>();
		requiredIds.add("client_validation_type_id");
		requiredIds.add("client_validation_id");
		requiredIds.add("client_birth_dt");
		requiredIds.add("postal_txt");

	}

	public ClientProfileMockFile(Template mockTemplate) {
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
		return columnNames;
	}

	@Override
	public int getNumColumns() {
		return columnNames.size();
	}

	@Override
	public List<String> getRow(int rowNum) {
		return mockRow;
	}

	@Override
	public int getNumRows() {
		return NUM_ROWS;
	}

	@Override
	public List<String> getColumnIds() {
		return columnIds;
	}

	public List<String> getRequiredIds() {
		return requiredIds;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

}
