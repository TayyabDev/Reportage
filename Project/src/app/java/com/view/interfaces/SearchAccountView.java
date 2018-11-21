package app.java.com.view.interfaces;


public interface SearchAccountView {
    void onSuccessSearchAccount();

    void onErrorSearchAccount();

    boolean isFieldsValid(String query);

    void invalidFields();

	void displayData(String report);
	
}
