package app.java.com.view.ui.createReportViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.CreateReportPresenterImpl;
import app.java.com.presenter.interfaces.CreateReportPresenter;
import app.java.com.view.interfaces.CreateReportView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class Report implements CreateReportView {
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JButton standard;
	private JButton createSQL;
	private JButton existingReportFormat;
	private JButton newFormat;
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
				new Dashboard(frame, false, account);
			}
		});


		standard = UIHelpers.buttonGenerator("Get standard reports");
		standard.setBounds(400, 100, 250, 50);
		standard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomReport(frame, account);
			}
		});

		createSQL = UIHelpers.buttonGenerator("Create custom reports using SQL");
		createSQL.setBounds(400, 200, 250, 50);
		createSQL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get sql query
				String sqlQuery =
						JOptionPane.showInputDialog(frame, "Enter the query to create reports");
				if (sqlQuery != null) {
					presenter.createReport(sqlQuery);
				}
			}
		});

		existingReportFormat = UIHelpers.buttonGenerator("Using existing Format");
		existingReportFormat.setBounds(400, 300, 250, 50);
		existingReportFormat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// existing report view
				new ExistingReportFormat(frame, account);
			}
		});

		newFormat = UIHelpers.buttonGenerator("Add new format");
		newFormat.setBounds(400, 400, 250, 50);
		newFormat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// save the query
				new NewReportFormat(frame, account);
			}
		});
		panel.add(standard);
		panel.add(createSQL);
		panel.add(existingReportFormat);
		panel.add(newFormat);
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
