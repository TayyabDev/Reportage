package app.java.com.view.interfaces;

import java.util.List;

public interface CreateUserDataView {

	void invalidQuery(String message);

	void fillDropdownWithTemplateNames(List<String> templateNames);

	void fillTableWithUserData(List<List<String>> data);
}
