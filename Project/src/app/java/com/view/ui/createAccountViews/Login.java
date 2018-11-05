package app.java.com.view.ui.createAccountViews;

import app.java.com.presenter.LoginPresenterImpl;
import app.java.com.presenter.interfaces.LoginPresenter;
import app.java.com.view.interfaces.LoginView;
import app.java.com.view.ui.UIHelpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Login implements LoginView {

    LoginPresenter presenter;
	static JFrame frame;
	JPanel panel;
	JLabel labelLogo;
	ImageIcon teqLogo;
	JLabel labelUsername;
	JLabel labelPassword;
	JTextField username ;
	JPasswordField password;
	
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
		
		
	}

	public static void main(String[] args) {
		
	        frame = new JFrame("TEQ Login");
	        frame.add(new Login().panel);
	        frame.setPreferredSize(new Dimension(800, 400));
	        frame.pack();
	        frame.setVisible(true);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			UIHelpers.setLook();


	}

    @Override
    public void onSuccessLogin() {
        Dashboard db = new Dashboard();
        db.main(null);
        frame.dispose();
    }

    @Override
    public void onErrorLogin() {
	    JOptionPane.showMessageDialog(frame, "Invalid username or password.");

    }

    @Override
    public boolean isFieldsValid(String username, String password) {
	    if (username.length() > 0 && password.length() > 0){
	        return true;
        } else {
	        return false;
        }

    }

    @Override
    public void invalidFields() {
	    JOptionPane.showMessageDialog(frame, "Please enter a valid username or password.");

    }
}
