package app.java.com.presenter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.usecase.FetchAttributeNamesUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;
import app.java.com.presenter.interfaces.GenerateCustomReportPresenter;
import app.java.com.presenter.interfaces.GenerateCustomReportResultInterface;
import app.java.com.view.interfaces.GenerateCustomReportView;

public class GenerateCustomReportPresenterImpl implements FetchAttributeNamesResultInterface,
							GenerateCustomReportPresenter, GenerateCustomReportResultInterface{

	private GenerateCustomReportView view;
	
	public GenerateCustomReportPresenterImpl() {
		this.view = null;
	}
	@Override
	public void onSuccessGenerateCustomReport() {
		
		
	}

	@Override
	public void onErrorGenerateCustomReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fetchAttributes() {
		UseCase fetchAttributesUseCase = new FetchAttributeNamesUseCase(this);
		fetchAttributesUseCase.run();
	}

	@Override
	public void generateCustomReport(List<String> attributes, Date begin, Date end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attachView(GenerateCustomReportView view) {
		this.view = view;
		
	}

	@Override
	public void unbindView() {
		this.view = null;
	}

	@Override
	public void onSuccessFetchAttributeNames(HashMap<String, List<String>> attributes) {
		this.view.testDisplayAttributes(attributes);
	}
	@Override
	public String onErrorFetchAttributeNames(String failedMessage) {
		return failedMessage;
		
	}
	

}
