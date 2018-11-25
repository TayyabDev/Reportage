package app.java.com.presenter;

import java.util.List;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.usecase.FetchTemplateColumnsUseCase;
import app.java.com.model.usecase.ProcessDuplicateRowsUseCase;
import app.java.com.model.usecase.ResolveConflictsUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.ResolveConflictPresenter;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;
import app.java.com.view.interfaces.ResolveConflictsView;

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
    public void attemptFixConflict(List<String> correctedValues, String table) {
        // Use case with conflict mode
        UseCase resolveConflictUseCase = new ResolveConflictsUseCase(this, correctedValues, table);
        resolveConflictUseCase.run();
    }

    @Override
    public void processDuplicateRowConflicts(List<InsertException> exceptions) {
        // here run the usecase

        UseCase processDuplicateRowConflicts = new ProcessDuplicateRowsUseCase(this, exceptions);
        processDuplicateRowConflicts.run();
    }

    @Override
    public void fetchTemplateColumns(String template) {
        FetchTemplateColumnsUseCase useCase = new FetchTemplateColumnsUseCase(this,template);
        useCase.run();

    }

    @Override
    public void onSuccessFixingConflict(String message) {
        this.view.onSuccessConflictFix();  // user fix did work, no need to ask the user again
    }

    @Override
    public void onErrorFixingConflict(String message) {
        this.view.onErrorConflictFix(message); // user fix did not work, ask the user again
    }

    @Override
    public void onSuccessProcessingDuplicateRows(List<InsertException> exceptions) {
        // results sent here - pass to the view
        this.view.updateExceptions(exceptions);
        this.view.getErrors(exceptions);
    }

    @Override
    public void onSuccessSelectTable(List<String> columns) {
        this.view.supplyTemplateColumns(columns);
    }

    @Override
    public void onErrorSelectTable(String message) {
        this.view.errorSupplyingColumns(message);
    }
}
