package app.java.com.view.interfaces;

import java.util.List;

public interface ViewUserDataView {

	void invalidQuery(String message);

	void displayData(List<List<String>> data);

}
