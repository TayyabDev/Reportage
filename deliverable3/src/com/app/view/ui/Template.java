package com.app.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Template {
	 private JPanel panel;
	 private JButton create;
	 private JButton view;
	 private JButton select ;
	 
	
     public Template() {
    	 GridBagLayout gb = new GridBagLayout();
 		GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;
    	 create = UIHelpers.buttonGenerator("Create a template");
    	 view = UIHelpers.buttonGenerator("View the existing templates");
    	 select = UIHelpers.buttonGenerator("Select a template");
    	
    	 gb.setConstraints(create, c);
    	 gb.setConstraints(view, c);
    	 gb.setConstraints(select, c);
    	 
    	 panel.add(create);
    	 panel.add(view);
    	 panel.add(select);
    	 
    	 
    	 
     }
	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Template Settings");
	        frame.add(new Template().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
	    }

}


