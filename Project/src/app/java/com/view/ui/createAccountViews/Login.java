package app.java.com.view.ui.createAccountViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.LoginPresenterImpl;
import app.java.com.presenter.interfaces.LoginPresenter;
import app.java.com.view.interfaces.LoginView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.register.RegisterOfficer;
import app.java.com.view.ui.register.RegisterTeqStaff;

public class Login implements LoginView {

	private LoginPresenter presenter;
	private static JFrame frame;
	private JPanel panel;
	private JLabel labelLogo;
	private ImageIcon teqLogo;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField username;
	private JPasswordField password;

	public Login() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(gb);
		panel.setBackground(Color.decode("#f1f8e9"));

		presenter = new LoginPresenterImpl(this);

		// create the logo
		teqLogo = new ImageIcon(getClass().getResource("Logo.png"));
		labelLogo = new JLabel(teqLogo);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gb.setConstraints(labelLogo, c);
		panel.add(labelLogo);

		labelUsername = new JLabel("Username:");
		labelPassword = new JLabel("Password:");

		username = new JTextField();
		password = new JPasswordField();
		gb.setConstraints(username, c);
		gb.setConstraints(password, c);

		panel.add(labelUsername);
		panel.add(username);
		panel.add(labelPassword);
		panel.add(password);

		c.fill = GridBagConstraints.CENTER;
		JButton login = UIHelpers.buttonGenerator("Login");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presenter.verifyAccount(username.getText(), String.valueOf(password.getPassword()));
			}
		});

		gb.setConstraints(login, c);
		panel.add(login);
		frame.getRootPane().setDefaultButton(login);
	}

	public static void main(String[] args) {

		frame = new JFrame("TEQ Login");
		frame.add(new Login().panel);
		frame.setPreferredSize(new Dimension(800, 400));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		UIHelpers.setLook();


	}

	@Override
	public void onSuccessLogin(TeqAccount account) {
		// check if need to register
		if (!account.isRegisterd()) {
			// RegisterNewUserView
			new RegisterTeqStaff(new JFrame("Registration"), account);
		} else {
			new Dashboard(new JFrame("TEQ Dashboard"), true, account);
		}
		frame.dispose();
		presenter.unbindView();
	}

	@Override
	public void onSuccessLogin(AgencyAccount account) {
		// check if need to register
		if (!account.isRegisterd()) {
			// RegisterNewUserView
			new RegisterOfficer(new JFrame("Registration"), account);
		} else {
			// need to have another view for agency
			new AgencyDashboard(new JFrame("Agency Dashboard"), true, account);
		}
		frame.dispose();
		presenter.unbindView();
	}

	@Override
	public void onErrorLogin() {
		JOptionPane.showMessageDialog(frame, "Invalid username or password.");

	}

	@Override
	public boolean isFieldsValid(String username, String password) {
		return (username.length() > 0 && password.length() > 0);
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame, "Please enter a valid username or password.");

	}
}
