package app.java.com.presenter;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.presenter.interfaces.ResolveConflictPresenter;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;
import app.java.com.view.interfaces.ResolveConflictsView;

import java.util.List;

public class ResolveConflictPresenterImpl implements ResolveConflictPresenter, ResolveConflictResultInterface {
    private ResolveConflictsView view;

    @Override
    public void attachView(ResolveConflictsView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void attemptFixConflict(String update) {
        // Use case with conflict mode
    }

    @Override
    public void attemptFixInvalid(String update) {
        // Use conflict with invalid mode
    }


    @Override
    public void processDuplicateRowConflicts(List<InsertException> exceptions) {
        // here run the usecase
    }


    @Override
    public void onSuccessConflictResolved() {
        view.onSuccessConflictFix();
    }

    @Override
    public void onSuccessInvalidResolved() {
        view.onSuccessInvalidFix();
    }

    @Override
    public void onErrorConflictFix(String message) {
        view.onErrorConflictFix(message);
    }

    @Override
    public void onErrorInvalidFix(String message) {
        view.onErrorInvalidFix(message);
    }

    @Override
    public void onSuccessProcessingDuplicateRows(List<InsertException> exceptions) {

    }
}
