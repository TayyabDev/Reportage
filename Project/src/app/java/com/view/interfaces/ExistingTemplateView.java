package app.java.com.view.interfaces;

import java.util.List;

public interface ExistingTemplateView {

    void fillDropDownWithTemplates(List<String> templateNames);

    void onErrorFetchingTemplates();

    void displayData(List<String> columns);

    void onErrorSelectTemplate(String message);

}
