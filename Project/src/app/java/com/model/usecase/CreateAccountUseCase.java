package app.java.com.model.usecase;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.presenter.interfaces.CreateAccountResultInterface;

import java.util.ArrayList;

public class CreateAccountUseCase extends  UseCase {

    private String name;
    private String password;
    private CreateAccountResultInterface resultInterface;

    public CreateAccountUseCase(CreateAccountResultInterface resultInterface, String name, String password){
        this.resultInterface = resultInterface;
        this.name = name;
        this.password = password;
    }

    @Override
    public void run() {
        boolean success = false;

        ArrayList<String> attrs = new ArrayList<>();
        attrs.add("userName");
        attrs.add("password");

        ArrayList<String> userData = new ArrayList<>();
        userData.add(this.name);
        userData.add(this.password);

        InsertCommand ic = new InsertCommand("Account", attrs, userData);

        try {
            success = ic.handle();
        } catch (InsertException e) {
            resultInterface.onErrorCreateAccount("failed when create " + e.getMessage());
        }

        if(success){
            // NOTE: Need to change this to get pass in account id instead of success
            resultInterface.onSuccessCreateAccount("success");
        }


    }
}
