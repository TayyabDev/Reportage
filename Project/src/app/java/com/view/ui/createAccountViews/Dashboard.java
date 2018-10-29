package app.java.com.view.ui.createAccountViews;

import app.java.com.view.ui.uploadTemplateViews.Report;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.Template;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard {
    static JFrame frame;
	JPanel panel = new JPanel();
	JLabel labelLogo;
	ImageIcon teqLogo;



	
	public Dashboard() {
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
                Template t  = new Template();
                t.main(null);
                frame.dispose();
            }
        });


		JButton btnAccount = UIHelpers.buttonGenerator("Accounts");
        btnAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account ac = new Account();
                ac.main(null);
                frame.dispose();
            }
        });

		JButton btnReport = UIHelpers.buttonGenerator("Reports");
        btnReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report r = new Report();
                r.main(null);
                frame.dispose();
            }
        });



		JButton btnAgency = UIHelpers.buttonGenerator("Agency");
		
		
		gb.setConstraints(btnTemplate, c);
		gb.setConstraints(btnAccount, c);
		gb.setConstraints(btnReport, c);
		gb.setConstraints(btnAgency, c);
		
		panel.add(btnTemplate);
		panel.add(btnAccount);
		panel.add(btnReport);
		panel.add(btnAgency);

	}
	


	
	 public static void main(String[] args) {
	        frame = new JFrame("TEQ Dashboard");
	        frame.add(new Dashboard().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
		 	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


	 }
}
