package app.java.com.view.interfaces;

public interface  CreateAccountView {
    void onSuccessAccountCreated();


    void onErrorCreatingAccount();

    boolean isFieldsValid(String name, String password);

    void invalidFields();
}
