package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.model.utilities.Account.Account;
import app.java.com.model.utilities.Account.AgencyAccount;
import app.java.com.model.utilities.Account.TeqAccount;
import app.java.com.presenter.interfaces.LoginResultInterface;

import java.util.ArrayList;
import java.util.List;

public class VerifyAccountUseCase extends UseCase{

    LoginResultInterface resultInterface;
    String username;
    String password;

    public VerifyAccountUseCase(LoginResultInterface resultInterface, String username, String password){
        this.resultInterface = resultInterface;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {

        ArrayList<String> attrs = new ArrayList<>();
        attrs.add("accountId");
        attrs.add("accountType");
        attrs.add("registered");

        List<String> constraints = new ArrayList<>();
        constraints.add("`userName` = '"+ this.username + "'");
        constraints.add("`password` = '"+this.password+"'");

        SelectCommand sel = new SelectCommand(attrs,
                "Account", constraints);

        List<List<String>> data= null;
        try{
             data = sel.selectHandle();

        } catch (SelectException e) {

        }
        if(data == null|| data.isEmpty() ){
            resultInterface.onErrorLogin();
        } else {
        	Account account;
        	boolean registered = data.get(0).get(2) == "1";
        	int accountId = Integer.parseInt(data.get(0).get(0));
        	if (data.get(0).get(1).compareTo("T") == 0) {
        		account = new TeqAccount(accountId, username, password, registered);
        	} else {
        		account = new AgencyAccount(accountId, username, password, registered);
        	}
        	System.out.println("in usecase");
            resultInterface.onSuccessLogin(account);
        }
    }
}
