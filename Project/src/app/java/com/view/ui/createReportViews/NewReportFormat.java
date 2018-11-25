package app.java.com.view.ui.createReportViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JButton add;
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

		JLabel lblReportName = new JLabel("please enter the format name");
		lblReportName.setBounds(300, 50, 400, 20);

		JTextField reportNameTxt = new JTextField();
		reportNameTxt.setBounds(300, 100, 400, 20);

		JLabel lblQuery = new JLabel("please enter query here");
		lblQuery.setBounds(300, 150, 400, 20);

		JTextArea queryTxt = new JTextArea();
		queryTxt.setBounds(300, 200, 400, 200);


		add = UIHelpers.buttonGenerator("Add");
		add.setBounds(300, 450, 400, 30);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = reportNameTxt.getText();
				String query = queryTxt.getText();
				if (name == null) {
					showPopUpWithMessage("report name can not be empty", "error");
				} else if (query == null) {
					showPopUpWithMessage("query can not be empty", "error");
				} else {
					presenter.addNewFormat(name, query);
				}

			}
		});

		panel.add(lblReportName);
		panel.add(reportNameTxt);
		panel.add(lblQuery);
		panel.add(queryTxt);
		panel.add(add);

		this.frame.setContentPane(panel);
		this.frame.revalidate();

	}

	private void showPopUpWithMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void onErrorFormatAdded(String errorMessage) {
		showPopUpWithMessage(errorMessage, "error");

	}

	@Override
	public void onSuccessFormatAdded() {
		showPopUpWithMessage("successfully added", "success");
		new Report(frame, account);
	}

	@Override
	public void onErrorFormatAdded() {
		showPopUpWithMessage("there is something wrong with query", "error");
	}

}
