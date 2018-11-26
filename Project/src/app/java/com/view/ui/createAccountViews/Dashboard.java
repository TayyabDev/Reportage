package app.java.com.view.ui.createAccountViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.Template;

public class Dashboard {
	
	protected JFrame frame;
	protected JPanel panel = new JPanel();
	protected GridBagLayout gb;
	protected GridBagConstraints c;
	private JLabel labelLogo;
	private ImageIcon teqLogo;

	public Dashboard(JFrame frame, boolean init, Account account) {
		this.frame = frame;

		gb = new GridBagLayout();
		c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(gb);
		panel.setBackground(Color.decode("#f1f8e9"));

		// create the logo
		teqLogo = new ImageIcon(getClass().getResource("Logo.png"));
		labelLogo = new JLabel(teqLogo);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gb.setConstraints(labelLogo, c);
		panel.add(labelLogo);

		c.gridwidth = 1;
		c.gridheight = 1;
		// create the buttons
		c.weightx = 1.0;
		c.weighty = 2.0;
	
	}
	
	public void revalidatePanel(boolean init) {
		
		if (init) {
			this.frame.add(panel);
			this.frame.setPreferredSize(new Dimension(1000, 600));
			this.frame.pack();
			this.frame.setVisible(true);
			this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.frame.setLocationRelativeTo(null);
			this.frame.setResizable(false);
			UIHelpers.setLook();
		} else {
			this.frame.setContentPane(panel);
			this.frame.revalidate();
		}
		
	}

}
