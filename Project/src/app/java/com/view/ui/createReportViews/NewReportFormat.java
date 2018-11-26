package app.java.com.view.ui.createReportViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.AddNewReportFormatImpl;
import app.java.com.presenter.interfaces.AddNewReportFormatPresenter;
import app.java.com.view.interfaces.AddNewReportFormatView;
import app.java.com.view.ui.UIHelpers;

public class NewReportFormat implements AddNewReportFormatView {

	private JFrame frame;
	private JPanel panel = new JPanel();
	private JButton btnAdd;
	private AddNewReportFormatPresenter presenter;
	private TeqAccount account;

	public NewReportFormat(JFrame frame, TeqAccount account) {
		this.frame = frame;
		this.account = account;
		panel = new JPanel();
		panel.setBackground(Color.decode("#f1f8e9"));
		panel.setLayout(null);

		presenter = new AddNewReportFormatImpl();
		presenter.attachView(this);

		JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Report(frame, account);
			}
		});

		JLabel lTitle = new JLabel("Create New Report Format");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(200, 20, 600, 40);
		panel.add(lTitle);
		
		JLabel lblReportName = new JLabel("Please Enter the Format Name:");
		lblReportName.setBounds(300, 100, 400, 20);

		JTextField reportNameTxt = new JTextField();
		reportNameTxt.setBounds(300, 150, 400, 20);

		JLabel lblQuery = new JLabel("Please Enter Query Here:");
		lblQuery.setBounds(300, 200, 400, 20);

		JTextArea queryTxt = new JTextArea();
		queryTxt.setBounds(300, 500, 400, 200);
		
		JScrollPane queryScrollPane = new JScrollPane(queryTxt);
		queryScrollPane.setBounds(300, 250, 400, 200);


		btnAdd = UIHelpers.buttonGenerator("Add");
		btnAdd.setBounds(300, 475, 400, 30);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = reportNameTxt.getText();
				String query = queryTxt.getText();
				if (name == null) {
					showPopUpWithMessage("Report Name can not be Empty", "Error");
				} else if (query == null) {
					showPopUpWithMessage("Query can not be Empty", "Error");
				} else {
					presenter.addNewFormat(name, query);
				}

			}
		});

		panel.add(lblReportName);
		panel.add(reportNameTxt);
		panel.add(lblQuery);
		panel.add(queryScrollPane);
		panel.add(btnAdd);

		this.frame.setContentPane(panel);
		this.frame.revalidate();

	}

	private void showPopUpWithMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void onErrorFormatAdded(String errorMessage) {
		showPopUpWithMessage(errorMessage, "Error");

	}

	@Override
	public void onSuccessFormatAdded() {
		showPopUpWithMessage("Successfully Added", "Success");
		new Report(frame, account);
	}

	@Override
	public void onErrorFormatAdded() {
		showPopUpWithMessage("Query is Invalid", "Error");
	}

}
