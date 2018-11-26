package app.java.com.model.usecase;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.TrendReportResultInterface;
import java.util.ArrayList;
import java.util.List;

public class TrendReportsUseCase extends UseCase {
    private static final int CLIENT_PROFILE_TEMPLATE_ID = 6;
    private static final String CLIENT_DATA_FORM = "ClientDataForm";
    private TrendReportResultInterface reportResultInterface;

    public TrendReportsUseCase(TrendReportResultInterface reportResultInterface) {
        this.reportResultInterface = reportResultInterface;
    }

    @Override
    public void run() {
        // run the select command

        List<String> targets = new ArrayList<>();

        targets.add("year");
        targets.add("month");
        targets.add("numOfClients");

        List<String> constraints = new ArrayList<>();
        constraints.add("templateId = '" + CLIENT_PROFILE_TEMPLATE_ID + "'");

        List<List<String>> result = null;

        SelectCommand command = new SelectCommand(targets, CLIENT_DATA_FORM, constraints);

        try {
            result = command.selectHandle();
        } catch (SelectException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        if(result != null) {
            reportResultInterface.onSuccessFetchingData(result);
        } else {
            reportResultInterface.onErrorFetchingData("Error fetching data");
        }
    }
}
