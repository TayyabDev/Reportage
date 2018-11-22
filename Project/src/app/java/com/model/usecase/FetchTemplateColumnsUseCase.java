package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.ExistingTemplateResultInterface;

import java.util.ArrayList;
import java.util.List;

public class FetchTemplateColumnsUseCase extends UseCase {

    private ExistingTemplateResultInterface resultInterface;
    private String template;


    public FetchTemplateColumnsUseCase(ExistingTemplateResultInterface resultInterface, String template){
        this.resultInterface = resultInterface;
        this.template = '`' + template.replace(' ', '_') + '`';


    }

    @Override
    public void run() {

        SelectCommand sel = new SelectCommand(template);
        try {
            List<String> columns = sel.getColumnIds();
            System.out.println(columns);
            resultInterface.onSuccessSelectTable(columns);
        } catch (SelectException e) {
            resultInterface.onErrorSelectTable(e.getMessage());
        }
    }
}
