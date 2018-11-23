package app.java.com.presenter;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.usecase.ProcessDuplicateRowsUseCase;
import app.java.com.model.usecase.UseCase;
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
    public void processDuplicateRowConflicts(List<InsertException> exceptions) {
        // here run the usecase

        UseCase processDuplicateRowConflicts = new ProcessDuplicateRowsUseCase(this, exceptions);
        processDuplicateRowConflicts.run();

    }

    @Override
    public void onSuccessFixingConflict(String message) {
        this.view.onSuccessConflictFix();
    }

    @Override
    public void onErrorFixingConflict(String message) {
        this.view.onErrorConflictFix(message);
    }

    @Override
    public void onSuccessProcessingDuplicateRows(List<InsertException> exceptions) {
        // results sent here - pass to the view
        this.view.updateExceptions(exceptions);
        this.view.getErrors(exceptions);
    }
}
