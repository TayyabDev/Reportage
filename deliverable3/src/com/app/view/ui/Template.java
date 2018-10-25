package com.app.view.ui;

import com.app.view.interfaces.CreateTemplateView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Template {
	private static JFrame frame;
	 private JPanel panel;
	 private JButton create;
	 private JButton view;
	 private JButton select ;
	 private JButton createSQL;
	 private String sqlQuery;

	
     public Template() {

         GridBagLayout gb = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;



    	 create = UIHelpers.buttonGenerator("Create a new template using file");
		 create.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				 jfc.setDialogTitle("Select your file");
				 int returnValue = jfc.showSaveDialog(null);
				 if (returnValue == JFileChooser.APPROVE_OPTION) {
					 if (jfc.getSelectedFile().isDirectory()) {
						 System.out.println("You selected the directory: " + jfc.getSelectedFile());
					 }
				 }

			 }
		 });

		 // tayyab task
         // SYNTAX
         /*CREATE TABLE table_name (
            column1 datatype,
            column2 datatype,
            column3 datatype,
                  ....
            );
          */
		 createSQL = UIHelpers.buttonGenerator("Create a new template using SQL");
         createSQL.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
				JDialog sqlDialog = new JDialog(frame,"Enter the SQL command");

				JTextArea txtAreaSqlQuery = new JTextArea();
				txtAreaSqlQuery.setLineWrap(true);
				txtAreaSqlQuery.setWrapStyleWord(true);

				JButton submit = UIHelpers.buttonGenerator("Submit");
                 submit.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         sqlQuery = txtAreaSqlQuery.getText();
                         sqlDialog.dispose();
                     }
                 });

				sqlDialog.add(txtAreaSqlQuery);
                sqlDialog.add(submit);

				sqlDialog.setSize(600, 200);
				sqlDialog.setVisible(true);
				sqlDialog.setLocationRelativeTo(frame);

             }
         });




    	 view = UIHelpers.buttonGenerator("View the existing templates");

    	 select = UIHelpers.buttonGenerator("Select a template");



    	 gb.setConstraints(create, c);
    	 gb.setConstraints(createSQL, c);
    	 gb.setConstraints(view, c);
    	 gb.setConstraints(select, c);
    	 
    	 panel.add(create);
    	 panel.add(createSQL);
    	 panel.add(view);
    	 panel.add(select);
    	 
    	 
    	 
     }
	
	 public static void main(String[] args) {
	        frame = new JFrame("Template Settings");
	        frame.add(new Template().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
     }




}


