package app.java.com.view.ui.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.model.entities.user.TEQStaffWorker;
import app.java.com.model.entities.user.User;
import app.java.com.presenter.RegisterNewUserImpl;
import app.java.com.presenter.interfaces.RegisterNewUserPresenter;
import app.java.com.view.interfaces.RegisterNewUserView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class RegisterTeqStaff extends RegisterNewUser implements RegisterNewUserView {

	private JPanel panel;
	private JButton register;
	private JButton cancel;
	@SuppressWarnings("unused")
	private JFrame frame;
	private RegisterNewUserPresenter presenter;
	private Account account;

	public RegisterTeqStaff(JFrame frame, TeqAccount account) {
		this.frame = frame;
		this.account = account;

		presenter = new RegisterNewUserImpl(this);

		panel = super.initPanel();

		JLabel lblEnterInfo = new JLabel("Please enter the following information");
		lblEnterInfo.setBounds(300, 50, 400, 20);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(300, 100, 400, 20);
		JTextField firstNameTxt = new JTextField();
		firstNameTxt.setBounds(300, 150, 400, 20);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(300, 200, 400, 20);
		JTextField lastNameTxt = new JTextField();
		lastNameTxt.setBounds(300, 250, 400, 20);

		JLabel lblDOB = new JLabel("Date of Birth");
		lblDOB.setBounds(300, 300, 400, 20);
		JDateChooser chooser = new JDateChooser();
		chooser.setBounds(300, 350, 400, 20);

		register = UIHelpers.buttonGenerator("register");
		register.setBounds(325, 400, 150, 50);

		cancel = UIHelpers.buttonGenerator("Cancel");
		cancel.setBounds(500, 400, 150, 50);

		panel.add(lblEnterInfo);
		panel.add(lblFirstName);
		panel.add(firstNameTxt);
		panel.add(lblLastName);
		panel.add(lastNameTxt);
		panel.add(lblDOB);
		panel.add(chooser);
		panel.add(register);
		panel.add(cancel);

		frame.add(panel);
		super.setFrame(frame);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the info client input to presenter
				String firstName = firstNameTxt.getText();
				String lastName = lastNameTxt.getText();
				Date dob = chooser.getDate();
				if (firstName == "" || firstName == null) {
					showPopUpWithMessage("First Name can not be empty", "Error");
					return;
				} else if (lastName == "" || lastName == null) {
					showPopUpWithMessage("Last Name can not be empty", "Error");
					return;
				} else if (dob == null) {
					showPopUpWithMessage("No Date Selected", "Error");
					return;
				} else {
					// construct user
					// send information client input to presenter
					User teqStaff = new TEQStaffWorker(firstName, lastName, dob);
					presenter.registerNewUser(teqStaff, account);

				}

			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPopUpWithMessage("Not registered.", "Information");
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}

	@Override
	public void onSuccessRegisterNewUser() {
		showPopUpWithMessage("Successfully registered.", "Information");
		new Dashboard(new JFrame("TEQ Dashboard"), true, (TeqAccount) account);
	}

	@Override
	public void onErrorRegisterNewUser() {
		showPopUpWithMessage("Error registering new User.", "Alert");

	}

}
