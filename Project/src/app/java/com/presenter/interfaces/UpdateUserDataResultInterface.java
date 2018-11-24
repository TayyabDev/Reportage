package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.usecase.UpdateUserDataUseCase.DataChanges;

public interface UpdateUserDataResultInterface {

	void onSuccessSelectTable(List<String> columns, List<List<String>> data);
	
	void onErrorSelectTable(String message);
	
	void onShowDataChanges(DataChanges changes);
	
	void onSuccessUpdate(String message);

	void onErrorUpdate(String message);

	void onProceedChanges(DataChanges rowsToUpdate);


}
