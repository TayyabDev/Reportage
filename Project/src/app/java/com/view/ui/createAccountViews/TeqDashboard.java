package app.java.com.view.ui.createAccountViews;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createReportViews.Report;
import app.java.com.view.ui.createTemplateViews.CreateTemplate;
import app.java.com.view.ui.viewUserDataViews.UserData;
import javax.swing.*;

public class TeqDashboard extends Dashboard {

	public TeqDashboard(JFrame frame, boolean init, TeqAccount account) {
		super(frame, init, account);
		this.frame = frame;

		JButton btnTemplate = UIHelpers.buttonGenerator("Templates");
		btnTemplate.addActionListener(e -> new CreateTemplate(frame, account));

		JButton btnAccount = UIHelpers.buttonGenerator("Accounts");
		btnAccount.addActionListener(e -> new CreateAccount(frame, account));

		JButton btnReport = UIHelpers.buttonGenerator("Reports");
		btnReport.addActionListener(e -> new Report(frame, account));

		JButton btnUserData = UIHelpers.buttonGenerator("User Data");
		btnUserData.addActionListener(e -> new UserData(frame, account));

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
