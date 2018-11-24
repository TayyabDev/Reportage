package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.ExistingTemplateResultInterface;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;

import java.util.List;

public class FetchTemplateColumnsUseCase extends UseCase {

    private ExistingTemplateResultInterface existingTemplateResultInterface;
    final int EXISTING_TEMPLATE = 0;

    private ResolveConflictResultInterface resolveConflictResultInterface;
    final int RESOLVE_CONFLICT = 1;

    private String template;
    private int mode;



    public FetchTemplateColumnsUseCase(ExistingTemplateResultInterface resultInterface, String template){
        this.existingTemplateResultInterface = resultInterface;
        this.template = '`' + template.replace(' ', '_') + '`';
        this.mode = EXISTING_TEMPLATE;
    }

    public FetchTemplateColumnsUseCase(ResolveConflictResultInterface resultInterface, String template){
        this.resolveConflictResultInterface = resultInterface;
        this.template = template;
        this.mode = RESOLVE_CONFLICT;
    }

    @Override
    public void run() {

        SelectCommand sel = new SelectCommand(template);
            try {
                List<String> columns = sel.getColumnIds();
                System.out.println(columns);

                if(mode == EXISTING_TEMPLATE){
                    existingTemplateResultInterface.onSuccessSelectTable(columns);
                } else if(mode == RESOLVE_CONFLICT){
                    resolveConflictResultInterface.onSuccessSelectTable(columns);
                }

            } catch (SelectException e) {
                if(mode == EXISTING_TEMPLATE){
                    existingTemplateResultInterface.onErrorSelectTable(e.getMessage());
                } else if(mode == RESOLVE_CONFLICT){
                    resolveConflictResultInterface.onErrorSelectTable(e.getMessage());
                }
            }
        }
}
