package app.java.com.presenter;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.usecase.FetchAttributeNamesUseCase;
import app.java.com.model.usecase.GenerateCustomReportUseCase;
import app.java.com.model.usecase.UseCase;
import app.java.com.presenter.interfaces.CustomReportPresenter;
import app.java.com.presenter.interfaces.CustomReportResultInterface;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;
import app.java.com.view.interfaces.CustomReportView;

import java.util.*;

public class CustomReportPresenterImpl implements CustomReportPresenter, CustomReportResultInterface, FetchAttributeNamesResultInterface {

    private CustomReportView view;

    @Override
    public void createReport(List<String> attributes, String date1, String date2) {
        if(view.isFieldsValid(date1, date2)){
            Calendar begin, end;
            if(date1.equals("") || date2.equals("")){
                 begin = null;
                 end = null;
            } else {
                int beginYear = Integer.parseInt(date1.split("/")[1]);
                int beginMonth = Integer.parseInt(date1.split("/")[0]);
                 begin = new GregorianCalendar(beginYear, beginMonth, 1);

                int endYear = Integer.parseInt(date2.split("/")[1]);
                int endMonth = Integer.parseInt(date2.split("/")[0]);
                 end = new GregorianCalendar(endYear, endMonth, 31);
            }

            HashMap<String, List<String>> templateRealNameMap = new HashMap<>();
            for(String curr : attributes){
                String template = curr.split("--")[0].strip();
                String attr = curr.split("--")[1].strip();
                if(!templateRealNameMap.containsKey(template)){
                    templateRealNameMap.put(template, new ArrayList<>(Arrays.asList(attr)));
                } else {
                    templateRealNameMap.get(template).add(attr);
                }
            }

            UseCase gen = new GenerateCustomReportUseCase(this, templateRealNameMap, begin, end);
            gen.run();
        } else {
            view.invalidFields();
        }
    }

    @Override
    public void attachView(CustomReportView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void fetchTemplatesAndAttributes() {
        UseCase fetch = new FetchAttributeNamesUseCase(this);
        fetch.run();

    }

    @Override
    public void onSuccessCreatingReport() {
        view.onSuccessReportCreated();
    }

    @Override
    public void onErrorCreatingReport() {
        view.onErrorCreatingReport();
    }

    @Override
    public void sendReport(HashMap<String, List<List<String>>> data) {
        this.view.sendReport(data);
    }



    @Override
    public void onSuccessFetchingAttributes(List<String> attributes) throws SelectException {
        this.view.fetchAttributes(attributes);
    }

    @Override
    public String onErrorFetchingAttributes(String errorMessage) {

        this.view.errorFetchingAttributes();
        return errorMessage;
    }
}
