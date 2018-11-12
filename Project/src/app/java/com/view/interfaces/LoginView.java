package app.java.com.view.interfaces;

public interface LoginView {

    void onSuccessLogin();
    void onErrorLogin();

    boolean isFieldsValid(String name, String password);
    void invalidFields();

}
