package app.java.com.presenter;

import app.java.com.model.usecase.CreateReportWithQueryUseCase;
import app.java.com.presenter.interfaces.CreateReportPresenter;
import app.java.com.presenter.interfaces.CreateReportResultInterface;
import app.java.com.view.interfaces.CreateReportView;

public class CreateReportPresenterImpl implements CreateReportPresenter, CreateReportResultInterface{

	private CreateReportView view;

	@Override
	public void onSuccessCreateReport(String message) {
		view.onSuccessReportCreated();
		
	}

	@Override
	public void onErrorCreateReport(String message) {
		view.onErrorReportCreated();
		
	}

	@Override
	public void createReport(String query) {
        if(view.isFieldsValid(query)){
            CreateReportWithQueryUseCase useCase = new CreateReportWithQueryUseCase(this, query);
            useCase.run();
        } else {
            // should make new method telling user fields are invalid
            view.invalidFields();
        }
		
	}

	@Override
	public void attachView(CreateReportView view) {
		this.view = view;
		
	}

	@Override
	public void unbindView() {
		this.view = null;
		
	}

	@Override
	public void report(String report) {
		view.sendReport(report);
	}
	
	
	
}
