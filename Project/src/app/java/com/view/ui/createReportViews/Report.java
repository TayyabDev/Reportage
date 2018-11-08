package app.java.com.view.ui.createReportViews;

import app.java.com.presenter.*;
import app.java.com.presenter.interfaces.CreateAccountPresenter;
import app.java.com.presenter.interfaces.CreateReportPresenter;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;
import app.java.com.view.interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report implements CreateReportView{
    private static JFrame frame;
    private JPanel panel = new JPanel();
    private JButton standard;
    private JButton createSQL;
    private CreateReportPresenter presenter;
  

    public Report(JFrame frame){
        this.frame = frame;
        panel = new JPanel();
        panel.setBackground(Color.decode("#f1f8e9"));
        panel.setLayout(null);
        
    	presenter = new CreateReportPresenterImpl();
    	presenter.attachView(this);

        JButton back = UIHelpers.generateBackButton(50,50,50,50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard d = new Dashboard(frame, false);
            }
        });
        
        
        standard = UIHelpers.buttonGenerator("Get standard reports");
        standard.setBounds(400,100, 250,50);

		createSQL = UIHelpers.buttonGenerator("Create custom reports using SQL");
        createSQL.setBounds(400,200, 250,50);
        createSQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get sql query
                String sqlQuery = JOptionPane.showInputDialog(frame, "Enter the query to create reports");
                if(sqlQuery != null ){
					presenter.createReport(sqlQuery);
				}
        }});

        
        panel.add(standard);
        panel.add(createSQL);
        this.frame.setContentPane(panel);
        this.frame.revalidate();

    }

	@Override
	public void onSuccessReportCreated() {
		JOptionPane.showMessageDialog(frame, "Successful query.");
	}

	@Override
	public void onErrorReportCreated() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, "There was an error running the query.");
	}

	@Override
	public boolean isFieldsValid(String query) {
		boolean valid = false;
		if (query.startsWith("SELECT")) {
			valid = true;
		}
		return valid;
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame, "Error. Please make sure your input is a SELECT query");

	}

	@Override
	public void sendReport(String report) throws FileNotFoundException {
		System.out.print(report);
		String timeStamp, fileName;
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		fileName = "report-";
		fileName += timeStamp;
		fileName += ".csv";
		PrintWriter pw = new PrintWriter(new File(fileName));
        StringBuilder sb = new StringBuilder();
        sb.append(report);
        pw.write(sb.toString());
        pw.close();
	}
}
