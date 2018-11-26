package app.java.com.view.ui.createReportViews;

import app.java.com.model.entities.account.Account;
import app.java.com.presenter.TrendReportPresenterImpl;
import app.java.com.presenter.interfaces.TrendReportPresenter;
import app.java.com.view.interfaces.TrendReportView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Login;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrendReport implements TrendReportView {

    private JFrame frame;
    private JPanel panel;

    private JLabel imgLabel;
    private ImageIcon imgTrend;
    private TrendReportPresenter trendReportPresenter;

    public TrendReport(JFrame frame, Account account){
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(null);

        JButton generate = UIHelpers.buttonGenerator("Generate trend report");
        generate.setBounds(400,5,200,40);

        displayImage();
        panel.add(generate);

        frame.setContentPane(panel);
        frame.revalidate();

        trendReportPresenter = new TrendReportPresenterImpl(this);
        trendReportPresenter.fetchData();
    }


    @Override
    public void displayImage() {
        imgTrend= new ImageIcon(getClass().getResource("ImmigrationTrend.png"));
        imgLabel = new JLabel(imgTrend);
        imgLabel.setBounds(0,60,1000, 540);

        panel.add(imgLabel);
        imgLabel.revalidate();
        frame.revalidate();
    }

    @Override
    public void createCSVFile(List<List<String>> data) {

    }

    @Override
    public void displayError(String message) {

    }

    public static void main(String [] args) {
        JFrame frame = new JFrame("TEQ Login");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        UIHelpers.setLook();

        TrendReport tr = new TrendReport(frame, null);
    }
}
