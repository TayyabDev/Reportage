package app.java.com.view.ui.register;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.interfaces.RegisterNewUserResultInterface;
import app.java.com.view.interfaces.RegisterNewUserView;
import app.java.com.view.ui.UIHelpers;

public abstract class RegisterNewUser implements RegisterNewUserView {
	
	public void showPopUpWithMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }
	
	public JPanel initPanel() {
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.decode("#f1f8e9"));
 		panel.setLayout(null);
 		return panel;
	}
	
	public JFrame setFrame(JFrame frame) {
		frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        UIHelpers.setLook();
        return frame;
	}
	
	@Override
	public void onSuccessRegisterNewUser() {
		showPopUpWithMessage("Successfully registered.", "Information");
	}
	
	@Override
	public void onErrorRegisterNewUser() {
		showPopUpWithMessage("Error registering new User.", "Alert");
		
	}

}