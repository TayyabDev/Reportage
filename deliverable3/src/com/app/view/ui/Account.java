package com.app.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Account {
	
	 private JPanel panel;
	 private JButton create;
	 private JButton edit;
	 private JButton search;
	 private static JFrame frame;
	
     public Account() {
    	 GridBagLayout gb = new GridBagLayout();
 		GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;
 		
    	 create = UIHelpers.buttonGenerator("Create an Account");
    	 
    	 // create account dialog box
    	 create.addActionListener(new ActionListener()
    	    {
    	      public void actionPerformed(ActionEvent e)
    	      {
    	        // display/center the jdialog when the button is pressed
    	        JDialog d = new JDialog(frame, "Create an account", true);
    	        
    	        GridBagLayout dgb = new GridBagLayout();
    	 		GridBagConstraints dc = new GridBagConstraints();
    	 		d.setLayout(dgb);
    	 		dc.fill = GridBagConstraints.BOTH;
    	 		dc.gridwidth = GridBagConstraints.REMAINDER;
    	 		
    	 		JLabel lblFirstName = new JLabel("First Name");
    	        JTextField firstName = new JTextField();
    	        
    	        JLabel lblLastName = new JLabel("Last Name");
    	        JTextField lastName = new JTextField();
    	        
    	        JLabel lblAge = new JLabel("Age");
    	        JTextField age = new JTextField();
    	 		
    	        
    	 		dgb.setConstraints(lblFirstName, dc);
    	    	dgb.setConstraints(firstName, dc);    
    	    	dgb.setConstraints(lblLastName, dc);
    	    	dgb.setConstraints(lastName, dc);  
    	    	dgb.setConstraints(lblAge, dc);
    	    	dgb.setConstraints(age, dc);  
    	        
    	        d.add(lblFirstName);    	   
    	        d.add(firstName);
    	        d.add(lblLastName);
    	        d.add(lastName);
    	        d.add(lblAge);
    	        d.add(age);
    	        
    	        d.setBackground(Color.decode("#f1f8e9"));
    	        d.setLocationRelativeTo(frame);
    	        d.setSize(500, 600);
    	        d.setVisible(true);
    	      }
    	    });
    	 
    	 
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
	        frame = new JFrame("Account Settings");
	        frame.add(new Account().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	       
	        
	        
	        
	    }

}
