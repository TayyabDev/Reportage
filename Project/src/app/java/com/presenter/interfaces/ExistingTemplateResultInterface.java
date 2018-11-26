package app.java.com.presenter.interfaces;

import java.util.List;

public interface ExistingTemplateResultInterface {

	void onSuccessSelectTable(List<String> columns);

	void onErrorSelectTable(String message);
}
