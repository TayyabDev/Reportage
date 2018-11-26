package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;

public interface ResolveConflictResultInterface {

	void onSuccessFixingConflict(String message);

	void onErrorFixingConflict(String message);

	void onSuccessProcessingDuplicateRows(List<InsertException> exceptions);

	void onSuccessSelectTable(List<String> columns);

	void onErrorSelectTable(String message);
}
