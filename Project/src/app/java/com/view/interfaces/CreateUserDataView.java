package app.java.com.view.interfaces;

import java.util.List;

public interface CreateUserDataView {

	void invalidQuery(String message);

	void displayData(List<List<String>> data);

}
