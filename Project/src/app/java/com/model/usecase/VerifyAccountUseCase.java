package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
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
        attrs.add("userName");
        attrs.add("password");

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
            resultInterface.onSuccessLogin();
        }
    }
}
