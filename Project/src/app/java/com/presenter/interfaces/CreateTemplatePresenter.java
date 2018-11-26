package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.interfaces.CreateTemplateView;

public interface CreateTemplatePresenter {

	void createTemplateWithFile(String filePath, TeqAccount account);

	void sheetSelected(String sheetName);

	void PKSelected(List<String> pks);

	void createTemplateWithQuery(String query);

	void attachView(CreateTemplateView view);

	void unbindView();

}
