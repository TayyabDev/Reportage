package app.java.com.view.interfaces;

import app.java.com.model.utilities.Account.AgencyAccount;
import app.java.com.model.utilities.Account.TeqAccount;

public interface LoginView {

    void onSuccessLogin(TeqAccount account);
    void onSuccessLogin(AgencyAccount account);
    void onErrorLogin();

    boolean isFieldsValid(String name, String password);
    void invalidFields();

}
