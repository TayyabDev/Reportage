package app.java.com.model.usecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.UpdateException;
import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.UpdateCommand;
import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.user.Officer;
import app.java.com.model.entities.user.User;
import app.java.com.model.entities.user.UserTypeFinder;
import app.java.com.presenter.interfaces.RegisterNewUserResultInterface;

public class RegisterNewUserUseCase extends UseCase {

	private RegisterNewUserResultInterface resultInterface;
	private User user;
	private Account account;
	
	// user table
	private final String userTable = "User";
	private final String firstNameCol = "firstName";
	private final String lastNameCol = "lastName";
	private final String dobCol = "dateOfBirth";
	private final String userTypeCol = "userType";
	private final String accountIdCol = "accountId";
	private List<String> userTableAttr = new ArrayList<>();
	
	// teqstaff table
	private final String teqStaffTable = "TEQStaff";
	private final String teqStaffIdCol = "teqStaffId";
	
	// agency table 
	private final String agencyTable = "Agency";
	private final String nameCol = "name";
	
	// Officer table
	private final String officerTable = "Officer";
	private final String officerIdCol = "officerId";
	private final String agencyIdCol = "agencyId";
	
	// account table
	private final String accountTable = "Account";
	private final String registeredCol = "registered";

	
	public RegisterNewUserUseCase(RegisterNewUserResultInterface resultInterface, User user, Account account) {
		this.resultInterface = resultInterface;
		this.user = user;
		this.account = account;
	}
	
	@Override
	/*
	 * if TEQ staff
	 * insert the user to User table, TEQStaff table
	 * if officer
	 * insert into user table, Agency table, officer table
	 */
	public void run() {
		String type = getType();
		if (type != null) {
			try {
				int userId = insertUser();
				// update the user object
				user.setUserId(userId);
				if (UserTypeFinder.isTeqStaff(user)) {
					insertTEQStaff();
					
				} else {
					int agencyId = insertAgency();
					System.out.print("insert agency");
					insertOfficer(agencyId);
					System.out.print("insert officer");
				}
				// if everything goes well, set the account to be registered
				registerAccount();
				account.setRegisterd(true);
				System.out.println("no error register");
				this.resultInterface.onSuccessRegisterNewUser("successfully registered");
			} catch (InsertException e) {
				e.printStackTrace();
				this.resultInterface.onErrorRegisterNewUser(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				this.resultInterface.onErrorRegisterNewUser(e.getMessage());
			}
		} else {
			System.out.println("should not be here.");
		}	
	}

	private String getType() {
		String accountType = account.getAccountType();
		String userType = user.getUserType();
		if (accountType.compareTo(userType) == 0) {
			return accountType;
		} else {
			return null;
		}
	}
	
	private void initUserTableAttr() {
		this.userTableAttr.add(firstNameCol);
		this.userTableAttr.add(lastNameCol);
		this.userTableAttr.add(dobCol);
		this.userTableAttr.add(userTypeCol);
		this.userTableAttr.add(accountIdCol);
	}
	
	private String formatDate(Date date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateStr = formatter.format(date);
		return mysqlDateStr;
		
	}
	private int insertUser() throws InsertException, DuplicateKeyException {
		initUserTableAttr();
		List<String> vals = new ArrayList<>();
		vals.add(user.getFirstName());
		vals.add(user.getLastName());
		vals.add(formatDate(user.getDob()));
		vals.add(user.getUserType());
		vals.add(((Integer)account.getAccountId()).toString());
		InsertCommand insertUserComm = new InsertCommand(userTable, userTableAttr, vals);
		int userId = insertUserComm.insertHandle();
		
		
		return userId;
	}
	
	private void insertTEQStaff() throws DuplicateKeyException, InsertException {
		List<String> insertTeqAttr = new ArrayList<>();
		List<String> insertTeqVal = new ArrayList<>();
		insertTeqAttr.add(teqStaffIdCol);
		insertTeqVal.add(((Integer)user.getUserId()).toString());
		InsertCommand insertTeqComm = new InsertCommand(teqStaffTable, insertTeqAttr, insertTeqVal);
		insertTeqComm.handle();
	}
	
	private void insertOfficer(int agencyId) throws DuplicateKeyException, InsertException {
		List<String> insertOfficerAttr = new ArrayList<>();
		
		insertOfficerAttr.add(officerIdCol);
		insertOfficerAttr.add(agencyIdCol);
		
		List<String> insertOfficerVal = new ArrayList<>();
		insertOfficerVal.add(((Integer)user.getUserId()).toString());
		insertOfficerVal.add(((Integer)agencyId).toString());
		
		InsertCommand insertOfficerComm = new InsertCommand(officerTable, insertOfficerAttr, insertOfficerVal);
		insertOfficerComm.handle();
	}

	private int insertAgency() throws DuplicateKeyException, InsertException {
		List<String> insertAgencyAttr = new ArrayList<>();
		List<String> insertAgencyVal = new ArrayList<>();
		insertAgencyAttr.add(nameCol);
		insertAgencyVal.add(((Officer)user).getAgencyName());
		InsertCommand insertAgencyComm = new InsertCommand(agencyTable, insertAgencyAttr, insertAgencyVal);
		int agencyId = insertAgencyComm.insertHandle();
		return agencyId;
	}
	
	// update the account to be registered in database
	private void registerAccount() throws Exception  {
		Integer accountId = account.getAccountId();
		
		List<String> registerAccountAttrs = new ArrayList<>();
		registerAccountAttrs.add(registeredCol);
		List<String> registerAccountVals = new ArrayList<>();
		registerAccountVals.add("true");
		List<String> registerAccountCons = new ArrayList<>();
		registerAccountCons.add(accountIdCol + " = " + accountId.toString());
		
		Command registerAccount = new UpdateCommand(accountTable, registerAccountAttrs, registerAccountVals, registerAccountCons);
		registerAccount.handle();
	}

}
