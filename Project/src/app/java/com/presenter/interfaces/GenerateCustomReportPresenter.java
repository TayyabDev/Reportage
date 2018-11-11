package app.java.com.presenter.interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import app.java.com.view.interfaces.GenerateCustomReportView;

public interface GenerateCustomReportPresenter {
	
	void fetchAttributes();
	
	void generateCustomReport(HashMap<String, List<String>> attributes, Calendar begin, Calendar end);
	
	void attachView(GenerateCustomReportView customReport);

    void unbindView();
}
