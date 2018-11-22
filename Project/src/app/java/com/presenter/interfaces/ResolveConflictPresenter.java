package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.ResolveConflictsView;



public interface ResolveConflictPresenter {

    void attachView(ResolveConflictsView view);
    void unbindView();

    void attemptFixConflict(String update);
    void attemptFixInvalid(String update);


    void fillListWithErrors();
}
