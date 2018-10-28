package app.java.com.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Account {
	
	 private JPanel panel;
	 private JButton create;
	 private JButton edit;
	 private JButton search;
	 
	
     public Account() {
    	 GridBagLayout gb = new GridBagLayout();
 		GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;
    	 create = UIHelpers.buttonGenerator("Create an Account");
    	 edit = UIHelpers.buttonGenerator("Edit an Account");
    	 search = UIHelpers.buttonGenerator("Search for an Account");
    	
    	 gb.setConstraints(create, c);
    	 gb.setConstraints(edit, c);
    	 gb.setConstraints(search, c);
    	 
    	 panel.add(create);
    	 panel.add(edit);
    	 panel.add(search);
    	 
    	 
    	 
     }
	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Account Settings");
	        frame.add(new Account().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
	    }

}
