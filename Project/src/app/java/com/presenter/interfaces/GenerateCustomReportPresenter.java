package app.java.com.presenter.interfaces;

import java.util.Date;
import java.util.List;

import app.java.com.view.interfaces.GenerateCustomReportView;

public interface GenerateCustomReportPresenter {
	
	void fetchAttributes();
	
	void generateCustomReport(List<String> attributes, Date begin, Date end);
	
	void attachView(GenerateCustomReportView customReport);

    void unbindView();
}
