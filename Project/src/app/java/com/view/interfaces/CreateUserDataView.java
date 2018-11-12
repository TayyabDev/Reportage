package app.java.com.view.interfaces;

import java.util.List;

public interface CreateUserDataView {

	void invalidQuery(String message);

	void displayData(List<String> columns, List<List<String>> data);
	
	void fillDropdownWithTemplateNames(List<String> templateNames); 

}
