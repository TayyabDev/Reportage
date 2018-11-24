package app.java.com.presenter.interfaces;

import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.interfaces.ExistingTemplateView;

public interface ExistingTemplatePresenter {

    void fetchTemplateNames();

    void fetchTemplateColumns(String template);

    void attachView(ExistingTemplateView view);

    void unbindView();

}
