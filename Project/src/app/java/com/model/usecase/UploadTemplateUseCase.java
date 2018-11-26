package app.java.com.model.usecase;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.Exceptions.UpdateException;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.database.api.UpdateCommand;
import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.UploadTemplateResultInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UploadTemplateUseCase implements UseCase {

    private static final String CLIENT_DATA_FORM_TABLE = "ClientDataForm";
	private static final String CLIENT_DATA_FORM_ID = "clientDataFormId";
	private static final String CLIENT_PROFILE = "Client_Profile";
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

		List<InsertException> errorList = insertAllRows(fileInterface, clientFormId);

		// all rows inserted successfully if errorList is empty
		if (!errorList.isEmpty() || clientFormId == -1) {
			resultInterface.onErrorUploadingTemplate(errorList);
			return;
		}

		int numberOfClients = getNumberOfClients();

        List<String> targets = new ArrayList<>();
        targets.add("numOfClients");

        List<String> values = new ArrayList<>();
        values.add(String.valueOf(numberOfClients));

        List<String> constraints = new ArrayList<>();
        constraints.add("clientDataFormId = '" + String.valueOf(clientFormId) + "'");
        UpdateCommand updateCommand = new UpdateCommand(CLIENT_DATA_FORM_TABLE, targets, values, constraints);

        try {
            updateCommand.handle();
        } catch (UpdateException e) {
            resultInterface.onErrorUploadingTemplate(errorList);
            return;
        }

        resultInterface.onSuccessUploadingTemplate();
	}

	private int getNumberOfClients() {
        List<String> selectionColumns = new ArrayList<>();
        selectionColumns.add("count(*)");

        SelectCommand selectCommand = new SelectCommand(selectionColumns, CLIENT_PROFILE);

        List<List<String>> result = null;
        try {
            result = selectCommand.selectHandle();
        } catch (SelectException e) {
            return -1;
        }

        return Integer.valueOf(result.get(0).get(0));
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
			return -1;
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
			return -1;
		}

	}

	private int getUserId(int accountId) {
		// get the userId from the user table where account id is equal to accountId
		List<String> constraints = new ArrayList<>();

		constraints.add("accountId = '" + String.valueOf(accountId) + "'");

		SelectCommand command = new SelectCommand("User", constraints);
		List<List<String>> result = null;

		try {
			result = command.selectHandle();
		} catch (SelectException e) {
			return -1;
		}

		if (result != null) {
			return Integer.valueOf(result.get(0).get(0));
		}

		return -1;
	}

	private int getAgencyId(int userId) {

		List<String> target = Arrays.asList("agencyId");
		List<String> constraints = new ArrayList<>();
		constraints.add("officerId = '" + String.valueOf(userId) + "'");

		List<List<String>> result = null;

		SelectCommand agencyIdCommand = new SelectCommand(target, "Officer", constraints);

		try {
			result = agencyIdCommand.selectHandle();
		} catch (SelectException e) {
			return -1;
		}
		
		if (result != null) {
            return Integer.valueOf(result.get(0).get(0));
		}

		return -1;
	}
}
