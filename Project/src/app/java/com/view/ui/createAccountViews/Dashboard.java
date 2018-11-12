package app.java.com.view.ui.createAccountViews;

import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createReportViews.Report;
import app.java.com.view.ui.createTemplateViews.Template;
import app.java.com.view.ui.viewUserDataViews.UserData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard {
    static JFrame frame;
	JPanel panel = new JPanel();
	JLabel labelLogo;
	ImageIcon teqLogo;



	
	public Dashboard(JFrame frame, boolean init) {
		this.frame = frame;

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(gb);
		panel.setBackground(Color.decode("#f1f8e9"));


		
		// create the logo
		teqLogo = new ImageIcon(getClass().getResource("Logo.png"));
		labelLogo= new JLabel(teqLogo);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		gb.setConstraints(labelLogo, c);
		panel.add(labelLogo);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		// create the buttons
		c.weightx = 1.0;
		c.weighty = 2.0;
		
		JButton btnTemplate = UIHelpers.buttonGenerator("Templates");
        btnTemplate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Template t  = new Template(frame);
            }
        });


		JButton btnAccount = UIHelpers.buttonGenerator("Accounts");
        btnAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account ac = new Account(frame);
            }
        });

		JButton btnReport = UIHelpers.buttonGenerator("Reports");
        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report r = new Report(frame);
            }
        });



		JButton btnAgency = UIHelpers.buttonGenerator("Agency");
		
		JButton btnUserData = UIHelpers.buttonGenerator("User Data");
		btnUserData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	UserData ud = new UserData(frame);
            }
        });
		
		
		gb.setConstraints(btnTemplate, c);
		gb.setConstraints(btnAccount, c);
		gb.setConstraints(btnReport, c);
		gb.setConstraints(btnAgency, c);
		gb.setConstraints(btnUserData, c);
		
		panel.add(btnTemplate);
		panel.add(btnAccount);
		panel.add(btnReport);
		panel.add(btnAgency);
		panel.add(btnUserData);

		if(init) {
            this.frame.add(panel);
            this.frame.setPreferredSize(new Dimension(1000, 600));
            this.frame.pack();
            this.frame.setVisible(true);
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            UIHelpers.setLook();
        }
        else{
            this.frame.setContentPane(panel);
            this.frame.revalidate();
        }

	}

}
