package app.java.com.presenter;

import java.util.HashMap;
import java.util.List;

import app.java.com.model.usecase.FetchExistingReportUseCase;
import app.java.com.model.usecase.GenerateReportsUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.ExistingReportFormatPresenter;
import app.java.com.presenter.interfaces.ExistingReportFormatResultInterface;
import app.java.com.presenter.interfaces.FetchExistingReportResultInterface;
import app.java.com.view.interfaces.ExistingReportFormatView;

public class ExistingReportFormatImpl implements ExistingReportFormatPresenter,
		ExistingReportFormatResultInterface, FetchExistingReportResultInterface {


	private ExistingReportFormatView view;


	@Override
	public void onSuccessCreatingReport() {
		view.onSuccessCreateReport();

	}

	@Override
	public void onErrorCreatingReport(List<String> errorReportNames) {
		view.onErrorCreateReport(errorReportNames);

	}

	@Override
	public void fetchExistingReports() {
		UseCase fetchReport = new FetchExistingReportUseCase(this);
		fetchReport.run();

	}

	public void generateReport(HashMap<String, String> querys) {
		UseCase generateReport = new GenerateReportsUseCase(this, querys);
		generateReport.run();

	}

	@Override
	public void attachView(ExistingReportFormatView view) {
		this.view = view;

	}

	@Override
	public void unbindView() {
		this.view = null;

	}

	@Override
	public void onSuccessFetchExistingReport(HashMap<String, String> map) {
		if (map.isEmpty()) {
			view.errorFetchReportFormats(
					"There is no format exist. Please create some report formats.");
		}
		view.displayExistingReport(map);

	}

	@Override
	public void onErrorFetchExistingReport(String errorMessage) {
		view.errorFetchReportFormats();

	}

}
