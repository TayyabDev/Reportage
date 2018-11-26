package app.java.com.model.usecase;

import app.java.com.model.database.api.CreateCommand;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;

public class CreateTemplateWithQueryUseCase implements UseCase {

	private String query;

	private CreateTemplateResultInterface resultInterface;

	public CreateTemplateWithQueryUseCase(CreateTemplateResultInterface resultInterface,
			String query) {
		this.query = query;
		this.resultInterface = resultInterface;
	}

	@Override
	/*
	 * create a template table with al
	 */
	public void run() {
		int success = -1;

		try {
			success = new CreateCommand().runExecuteUpdate(query);


			if (success != -1) {
				// Look at templateResultInterface for communication back with the presenter
				resultInterface.onSuccessCreateTemplate();
			}

		} catch (Exception e) {
			resultInterface.onErrorCreateTemplate("failed when create " + e.getMessage());
		}

	}

}
