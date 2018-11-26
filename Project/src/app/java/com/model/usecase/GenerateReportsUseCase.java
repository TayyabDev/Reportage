package app.java.com.model.usecase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.database.api.Command;
import app.java.com.presenter.interfaces.ExistingReportFormatResultInterface;

public class GenerateReportsUseCase implements UseCase {

	private ExistingReportFormatResultInterface resultInterface;
	private HashMap<String, String> querys;

	public GenerateReportsUseCase(ExistingReportFormatResultInterface resultInterface,
			HashMap<String, String> querys) {
		this.resultInterface = resultInterface;
		this.querys = querys;
	}

	@Override
	public void run() {
		List<String> errors = new ArrayList<String>();
		for (String name : querys.keySet()) {
			try {
				String report = Command.runExecuteQuery(querys.get(name));
				createReport(name, report);
			} catch (Exception e) {
				errors.add(name);
			}
		}
		if (errors.isEmpty()) {
			resultInterface.onSuccessCreatingReport();
		} else {
			resultInterface.onErrorCreatingReport(errors);
		}

	}

	public void createReport(String reportName, String report) throws FileNotFoundException {
		String timeStamp, fileName;
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		fileName = reportName;
		fileName += timeStamp;
		fileName += ".csv";
		String userHomeFolder = System.getProperty("user.home");
		File textFile = new File(userHomeFolder, fileName);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(report);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
