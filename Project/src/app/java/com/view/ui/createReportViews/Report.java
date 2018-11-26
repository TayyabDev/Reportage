package app.java.com.view.ui.createReportViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.CreateReportPresenterImpl;
import app.java.com.presenter.interfaces.CreateReportPresenter;
import app.java.com.view.interfaces.CreateReportView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.TeqDashboard;

public class Report implements CreateReportView {
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JButton btnStandard;
	private JButton btnCreateSQL;
	private JButton btnExistingReportFormat;
	private JButton btnNewFormat;
	private JButton btnTrendReport;

	private CreateReportPresenter presenter;


	public Report(JFrame frame, TeqAccount account) {
		this.frame = frame;
		panel = new JPanel();
		panel.setBackground(Color.decode("#f1f8e9"));
		panel.setLayout(null);

		presenter = new CreateReportPresenterImpl();
		presenter.attachView(this);

		JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TeqDashboard(frame, false, account);
			}
		});

		JLabel lTitle = new JLabel("Report");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(425, 20, 150, 40);
		panel.add(lTitle);

		btnStandard = UIHelpers.buttonGenerator("Get Custom Reports");
		btnStandard.setBounds(400, 70, 250, 50);
		btnStandard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomReport(frame, account);
			}
		});

		btnCreateSQL = UIHelpers.buttonGenerator("Create Custom Reports using SQL");
		btnCreateSQL.setBounds(400, 170, 250, 50);
		btnCreateSQL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get sql query
				String sqlQuery =
					JOptionPane.showInputDialog(frame, "Enter the Query to Create Reports");
				if (sqlQuery != null) {
					presenter.createReport(sqlQuery);
				}
			}
		});

		btnExistingReportFormat = UIHelpers.buttonGenerator("Using Existing Format");
		btnExistingReportFormat.setBounds(400, 270, 250, 50);
		btnExistingReportFormat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// existing report view
				new ExistingReportFormat(frame, account);
			}
		});

		btnNewFormat = UIHelpers.buttonGenerator("Add New Format");
		btnNewFormat.setBounds(400, 370, 250, 50);
		btnNewFormat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// save the query
				new NewReportFormat(frame, account);
			}
		});

		btnTrendReport  = UIHelpers.buttonGenerator("View the Trend Reports");
        btnTrendReport.setBounds(400, 470, 250, 50);
        btnTrendReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrendReport(frame ,account);
            }
        });


        panel.add(btnStandard);
		panel.add(btnCreateSQL);
		panel.add(btnExistingReportFormat);
		panel.add(btnNewFormat);
		panel.add(btnTrendReport);
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
		if (query.startsWith("select")) {
			valid = true;
		}
		return valid;
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame,
				"Error. Please make sure your input is a SELECT query");

	}

	@Override
	public String sendReport(String report) throws FileNotFoundException {
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
		return fileName;
	}
}
