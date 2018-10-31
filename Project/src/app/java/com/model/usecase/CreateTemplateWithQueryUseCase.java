package app.java.com.model.usecase;

import app.java.com.model.usecase.*;

public class CreateTemplateWithQueryUseCase extends app.java.com.model.usecase.BaseUsecase {

    private String query;

    private app.java.com.model.interfaces.CreateTemplateResultInterface resultInterface;

	public CreateTemplateWithQueryUseCase(app.java.com.model.interfaces.CreateTemplateResultInterface resultInterface, String query) {
        this.query = query;
        this.resultInterface = resultInterface;
    }

	@Override
	/*
	 * create a template table with al
	 */
	public boolean run() {

        boolean success = false;

        try {
            success =
        } catch (app.java.com.model.Exceptions.CreateException e) {
            templateResultInterface.onErrorCreateTemplate("failed when create " + e.getTable());
        }
        if (success) {
            // Look at templateResultInterface for communication back with the presenter
            templateResultInterface.onSuccessCreateTemplate("success");
        }

	}

}
