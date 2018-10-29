package app.java.com.view.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import app.java.com.presenter.CreateTemplatePresenterImpl;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.model.CreateTemplateModelImpl;
import app.java.com.model.UploadTemplateModelImpl;

import java.io.File;
public class Template implements CreateTemplateView{
	private static JFrame frame;
	 private JPanel panel;
	 private JButton create;
	 private JButton view;
	 private JButton select ;
	 private JButton createSQL;
	 private String sqlQuery;
	 private CreateTemplatePresenter presenter;
	
     public Template() {


         GridBagLayout gb = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         panel = new JPanel();
 		 panel.setLayout(gb);
 		 panel.setBackground(Color.decode("#f1f8e9"));



         presenter = new CreateTemplatePresenterImpl(new CreateTemplateModelImpl());
         presenter.attachView(this);


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
					 System.out.println("You selected the directory: " + jfc.getSelectedFile());
					 presenter.createTemplateWithFile(jfc.getSelectedFile());
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
                 // get sql query
                 String sqlQuery = JOptionPane.showInputDialog(frame, "Enter the query");
                 presenter.createTemplateWithQuery(sqlQuery);

                 System.exit(0);

         }});



    	 view = UIHelpers.buttonGenerator("View the existing templates");

    	 select = UIHelpers.buttonGenerator("Select a template");
		 select.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				// go to the upload template screen
				 UploadTemplate ut = new UploadTemplate();
				 ut.main(null);

			 }});



    	 gb.setConstraints(create, c);
    	 gb.setConstraints(createSQL, c);
    	 gb.setConstraints(view, c);
    	 gb.setConstraints(select, c);
    	 
    	 panel.add(create);
    	 panel.add(createSQL);
    	 panel.add(view);
    	 panel.add(select);
    	 
    	 
    	 
     }

     public ArrayList<String> parseFile(File file){
         // read the column names
         ArrayList<String> headers = new ArrayList<>();
         return headers;

     }
	
	 public static void main(String[] args) {
	        frame = new JFrame("Template Settings");
	        frame.add(new Template().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     }


    @Override
    public void onSuccessTemplateCreated() {

    }

    @Override
    public boolean isFileValid() {
        return true;
    }

    @Override
    public void onErrorUploadingFile() {

    }

    public void disposeView(){
         frame.dispose();
         presenter.unbindView();
    }


}


