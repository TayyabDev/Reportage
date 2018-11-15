package app.java.com.view.interfaces;

public interface  CreateAccountView {
    void onSuccessAccountCreated();


    void onErrorCreatingAccount(String errorMessage);

    boolean isFieldsValid(String name, String password);

    void invalidFields();
}
