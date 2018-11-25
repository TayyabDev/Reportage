package app.java.com.presenter;

import app.java.com.model.usecase.AddNewReportFormatUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.AddNewReportFormatPresenter;
import app.java.com.presenter.interfaces.AddNewReportFormatResultInterface;
import app.java.com.view.interfaces.AddNewReportFormatView;

public class AddNewReportFormatImpl
		implements AddNewReportFormatPresenter, AddNewReportFormatResultInterface {

	private AddNewReportFormatView view;

	@Override
	public void addNewFormat(String reportName, String sql) {
		// create AddNewReportFormat usecase and run
		UseCase addNewFormat = new AddNewReportFormatUseCase(this, reportName, sql);
		addNewFormat.run();

	}

	@Override
	public void attachView(AddNewReportFormatView view) {
		this.view = view;

	}

	@Override
	public void unbindView() {
		this.view = null;

	}

	@Override
	public void onSuccessAddReportFormat(String csvString) {
		view.onSuccessFormatAdded();

	}

	@Override
	public void onErrorAddReportFormat(String message) {
		view.onErrorFormatAdded(message);
	}

	@Override
	public void onErrorAddReportFormat() {
		view.onErrorFormatAdded();
	}

}
