package app.java.com.view.ui.createAccountViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createReportViews.Report;
import app.java.com.view.ui.createTemplateViews.Template;
import app.java.com.view.ui.viewUserDataViews.UserData;

public class TeqDashboard extends Dashboard {

	public TeqDashboard(JFrame frame, boolean init, TeqAccount account) {
		super(frame, init, account);
		this.frame = frame;

		JButton btnTemplate = UIHelpers.buttonGenerator("Templates");
		btnTemplate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Template(frame, account);
			}
		});

		JButton btnAccount = UIHelpers.buttonGenerator("Accounts");
		btnAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateAccount(frame, account);
			}
		});

		JButton btnReport = UIHelpers.buttonGenerator("Reports");
		btnReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Report(frame, account);
			}
		});

		JButton btnUserData = UIHelpers.buttonGenerator("User Data");
		btnUserData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserData(frame, account);
			}
		});

		gb.setConstraints(btnTemplate, c);
		gb.setConstraints(btnAccount, c);
		gb.setConstraints(btnReport, c);
		gb.setConstraints(btnUserData, c);

		panel.add(btnTemplate);
		panel.add(btnAccount);
		panel.add(btnReport);
		panel.add(btnUserData);

		revalidatePanel(init);
	}

}
