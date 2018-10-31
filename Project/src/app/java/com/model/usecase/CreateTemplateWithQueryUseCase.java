package app.java.com.model.usecase;

import app.java.com.model.database.api.CreateCommand;
import app.java.com.model.interfaces.CreateTemplateResultInterface;

public class CreateTemplateWithQueryUseCase extends UseCase {

    private String query;

    private CreateTemplateResultInterface resultInterface;

	public CreateTemplateWithQueryUseCase(CreateTemplateResultInterface resultInterface, String query) {
        this.query = query;
        this.resultInterface = resultInterface;
    }

	@Override
	/*
	 * create a template table with al
	 */
	public void run() {
        boolean success = false;

        try {

            success = new CreateCommand().runExecuteUpdate(query);

        } catch (Exception e) {
            resultInterface.onErrorCreateTemplate("failed when create " + e.getMessage());
        }

        if (success) {
            // Look at templateResultInterface for communication back with the presenter
            resultInterface.onSuccessCreateTemplate("success");
        }
	}

}
