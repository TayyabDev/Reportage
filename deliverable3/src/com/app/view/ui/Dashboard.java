package com.app.view.ui;

import java.awt.*;
import javax.swing.*;

public class Dashboard {
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
		
		// create the buttons
		c.weightx = 1.0;
		c.weighty = 2.0;
		
		JButton btnTemplate = buttonGenerator("Templates");
		JButton btnAccount = buttonGenerator("Accounts");
		JButton btnReport = buttonGenerator("Reports");
		JButton btnAgency = buttonGenerator("Agency");
		
		gb.setConstraints(btnTemplate, c);
		gb.setConstraints(btnAccount, c);
		gb.setConstraints(btnReport, c);
		gb.setConstraints(btnAgency, c);
		
		panel.add(btnTemplate);
		panel.add(btnAccount);
		panel.add(btnReport);
		panel.add(btnAgency);

	}
	
	private JButton buttonGenerator(String text) {
		JButton b = new JButton(text);
		b.setBackground(Color.decode("#fff8e1"));
		
		return b;
	}

	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("TEQ Dashboard");
	        frame.add(new Dashboard().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
	    }
}
