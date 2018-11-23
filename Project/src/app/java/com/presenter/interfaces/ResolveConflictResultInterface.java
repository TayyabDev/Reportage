package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface ResolveConflictResultInterface {

    void onSuccessConflictResolved();

    void onSuccessInvalidResolved();

    void onErrorConflictFix(String message);

    void onErrorInvalidFix(String message);

    void onSuccessProcessingDuplicateRows(List<InsertException> exceptions);
}
