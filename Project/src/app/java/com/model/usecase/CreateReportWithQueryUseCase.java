package app.java.com.model.usecase;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.CreateCommand;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.CreateReportResultInterface;

public class CreateReportWithQueryUseCase extends UseCase{
    private String query;
    private CreateReportResultInterface resultInterface;

	public CreateReportWithQueryUseCase(CreateReportResultInterface resultInterface, String query) {
        this.query = query;
        this.resultInterface = resultInterface;
    }
	@Override
	public void run() {
        ResultSet rs = null;
        ResultSetMetaData rsmd;
        Command cmd = new SelectCommand();
        String report = "";
        int column = 0;

        try {
            rs = cmd.RunExecuteQuery(query);
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount() + 1;
            while (rs.next()) {
            	while (column <= columnsNumber) {
            		report += rs.getString(column + 1);
            		report += ",";
            		column++;
            	}
            	report = report.substring(0, report.length()-1);
            	report += "\n";
            	column = 0;
            }
            rs.close();
            
        } catch (Exception e) {
            resultInterface.onErrorCreateReport("failed when ran " + e.getMessage());
        }

        if (rs != null) {
            resultInterface.onSuccessCreateReport("success");
            resultInterface.report(report);
        }
	}

}
