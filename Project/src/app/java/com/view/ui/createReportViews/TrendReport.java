package app.java.com.view.ui.createReportViews;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.TrendReportPresenterImpl;
import app.java.com.presenter.interfaces.TrendReportPresenter;
import app.java.com.view.interfaces.TrendReportView;
import app.java.com.view.ui.UIHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.List;

public class TrendReport implements TrendReportView {

    private JFrame frame;
    private JPanel panel;
    private JPanel imagePanel;

    private String SPECIFIED_FILEPATH = "src/app/java/com/view/ui/createReportViews/";
    private String TREND_REPORT_CSV_FILENAME = "ImmigrationCoordinates.csv";
    private String FILE_PATH = SPECIFIED_FILEPATH + TREND_REPORT_CSV_FILENAME;
    private String PYTHON_FILENAME = "GenerateTrendReport.py";



    private TrendReportPresenter trendReportPresenter;

    public TrendReport(JFrame frame, TeqAccount account){
        this.frame = frame;

        panel = new JPanel(null);
        panel.setBackground(Color.decode("#f1f8e9"));


        trendReportPresenter = new TrendReportPresenterImpl(this);
        trendReportPresenter.fetchData();

        JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Report(frame, account);
            }
        });


        JButton generate = UIHelpers.buttonGenerator("Generate Immigration Trend Report");
        generate.setBounds(350,200,250,40);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPython(); // will show the plot
            }
        });
        panel.add(generate);

        this.frame.setContentPane(panel);
        this.frame.revalidate();
    }


    @Override
    public void createCSVFile(List<List<String>> data) {
        // convert to hashmap
        // write to csv file
        String output = "";

        for(List<String> row : data){
            String year = row.get(0).split("-")[0];
            String month = row.get(1);
            String numClients = row.get(2);

            output += month + "/" + year +"," + numClients + "\n";
        }

        // get filepath
        String filepath = FILE_PATH;
        try {
            PrintWriter pw = new PrintWriter(new File(filepath));
            pw.write(output);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void runPython(){
        String command = "python " + SPECIFIED_FILEPATH + PYTHON_FILENAME;
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            System.out.println(in.readLine());

        } catch (IOException e) {
            command = "py " + SPECIFIED_FILEPATH + PYTHON_FILENAME;
            try {
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                System.out.println(in.readLine());
            } catch(IOException e2){
                e2.printStackTrace();
            }
        }
    }



    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

}
