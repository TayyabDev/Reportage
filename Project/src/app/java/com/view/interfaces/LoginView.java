package app.java.com.view.interfaces;

import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;

public interface LoginView {

    void onSuccessLogin(TeqAccount account);
    void onSuccessLogin(AgencyAccount account);
    void onErrorLogin();

    boolean isFieldsValid(String name, String password);
    void invalidFields();

}
