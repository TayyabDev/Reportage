package app.java.com.presenter.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface ResolveConflictResultInterface {

    void onSuccessFixingConflict(String message);

    void onErrorFixingConflict(String message);

    void onSuccessProcessingDuplicateRows(List<InsertException> exceptions);
}
