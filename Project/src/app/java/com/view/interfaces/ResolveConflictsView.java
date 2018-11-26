package app.java.com.view.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface ResolveConflictsView {

    void getErrors(List<InsertException> errors );

    boolean isFieldsValid(String update);

    void onSuccessConflictFix();

    void onErrorConflictFix(String error);

    void updateExceptions(List<InsertException> exceptions);

    void supplyTemplateColumns(List<String> columns);

    void errorSupplyingColumns(String message);

}
