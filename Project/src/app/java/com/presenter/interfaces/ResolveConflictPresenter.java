package app.java.com.presenter.interfaces;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.view.interfaces.ResolveConflictsView;


public interface ResolveConflictPresenter {

	void attachView(ResolveConflictsView view);

	void unbindView();

	void attemptFixConflict(List<String> correctedValues, String table);

	void processDuplicateRowConflicts(List<InsertException> exceptions);

	void fetchTemplateColumns(String template);


}
