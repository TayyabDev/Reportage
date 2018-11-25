package app.java.com.model.usecase;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UploadTemplateUseCase extends UseCase {

	private static final String CLIENT_DATA_FORM_ID = "clientDataFormId";
    private static final int TEQ_AGENCY_ID = 1;
	private TemplateFileInterface fileInterface;
	private String templateName;
	private UploadTemplateResultInterface resultInterface;
	private Date dateSelected;
	private Account account;

	public UploadTemplateUseCase(UploadTemplateResultInterface resultInterface, Date date,
			String templateName, TemplateFileInterface file, Account account) {
		this.resultInterface = resultInterface;
		this.dateSelected = date;
		this.fileInterface = file;
		this.templateName = templateName;
		this.account = account;
	}

	@Override
	public void run() {

		// Get the clientFormId after uploading it to the database
		int clientFormId = insertClientDataForm(templateName);
		System.out.println(clientFormId + " is the formId");

		// Now add this to the insert command since the template has a clientIdForm column

		// Verify template matches the chosen template's format
		List<InsertException> errorList = insertAllRows(fileInterface, clientFormId);

		// all rows inserted successfully if errorList is empty
		if (!errorList.isEmpty() || clientFormId == -1) {
			resultInterface.onErrorUploadingTemplate(errorList);
		} else {
			resultInterface.onSuccessUploadingTemplate();
		}
	}

	/*
	 * inserting all the data rows in the TemplateFileInterface into the database
	 */
	private List<InsertException> insertAllRows(TemplateFileInterface exc, int clientDataFormId) {
		// Upload the template into the database using insert command
		List<String> columnIds = exc.getColumnIds();
		columnIds.add(0, CLIENT_DATA_FORM_ID);
		List<String> row = null;
		// get the tableName in the database
		String templateName = exc.getTableName();
		int numOfRow = exc.getNumRows();
		List<InsertException> errorList = new ArrayList<>();
		System.out.println("no error in insertAllRows so far");
		for (int i = 0; i < numOfRow; i++) {
			row = exc.getRow(i + 3);
			row.add(0, String.valueOf(clientDataFormId));
			InsertCommand insert = new InsertCommand(templateName, columnIds, row);
			try {
				insert.handle();
			} catch (InsertException e) {
				errorList.add(e);
			}
		}
		return errorList;
	}

	@SuppressWarnings("deprecation")
	private int insertClientDataForm(String templateSelected) {

		// Get the templateId using the templateSelected

		List<String> target = new ArrayList<>();
		target.add("templateId");

		templateSelected = templateSelected.replace(' ', '_');
		templateSelected = '`' + templateSelected + '`';

		List<String> constraints = new ArrayList<>();
		String constraintTemplateName = "`tableName`= \'" + templateSelected + "\'";

		constraints.add(constraintTemplateName);

		SelectCommand selectTemplateId = new SelectCommand(target, "Template", constraints);
		List<List<String>> result = null;
		try {
			result = selectTemplateId.selectHandle();
		} catch (SelectException e) {
			e.printStackTrace();
		}

		String templateId = result.get(0).get(0);

		int month = dateSelected.getMonth() + 1; // normalize

		int year = dateSelected.getYear() + 1900; // normalize


		int userId = 0;
        int agencyId = 0;

        if(AccountTypeFinder.isTeqAccount(account)) {
            agencyId = TEQ_AGENCY_ID;
        } else {
            userId = getUserId(account.getAccountId());
            agencyId = getAgencyId(userId);
        }



		// Let's insert the information into the ClientDataForm table now
		// we only need to upload the templateId, month, and year
		String clientDataFormTableName = "ClientDataForm";
		List<String> attributes = new ArrayList<>();
		attributes.add("templateId");
		attributes.add("agencyId");
		attributes.add("month");
		attributes.add("year");

		List<String> values = new ArrayList<>();
		values.add(templateId);
		values.add(String.valueOf(agencyId));
		values.add(String.valueOf(month));
		values.add(String.valueOf(year));

		InsertCommand insertCommand =
				new InsertCommand(clientDataFormTableName, attributes, values);

		try {
			return insertCommand.insertHandle();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	private int getUserId(int accountId) {
		System.out.println(accountId + " is the accountId");
		// get the userId from the user table where account id is equal to accountId
		List<String> constraints = new ArrayList<>();
		constraints.add("accountId = '" + String.valueOf(accountId) + "'");

		SelectCommand command = new SelectCommand("User", constraints);
		List<List<String>> result = null;

		try {
			result = command.selectHandle();
			System.out.println(result);
		} catch (SelectException e) {
			e.printStackTrace();
		}

		int userId = 0;
		if (result != null) {
			userId = Integer.valueOf(result.get(0).get(0));
		}

		System.out.println(String.valueOf(userId) + " is the userId");

		return userId;
	}

	private int getAgencyId(int userId) {

		// then we need to get the agencyId from the Officer table
		List<String> target = Arrays.asList("agencyId");
		List<String> constraints = new ArrayList<>();
		constraints = new ArrayList<>();
		constraints.add("officerId = '" + String.valueOf(userId) + "'");

		List<List<String>> result = null;

		SelectCommand agencyIdCommand = new SelectCommand(target, "Officer", constraints);
		try {
			result = agencyIdCommand.selectHandle();
		} catch (SelectException e) {
			e.printStackTrace();
		}

		int agencyId = 0;
		System.out.println(result + " is the result for agency");
		if (result != null) {
			agencyId = Integer.valueOf(result.get(0).get(0));
		}

		System.out.println(String.valueOf(agencyId) + " is the agency");
		return agencyId;
	}
}
