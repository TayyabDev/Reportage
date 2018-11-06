package app.java.com.view.ui.createTemplateViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import app.java.com.presenter.CreateTemplatePresenterImpl;
import app.java.com.presenter.interfaces.CreateTemplatePresenter;
import app.java.com.view.interfaces.CreateTemplateView;
import app.java.com.model.CreateTemplateModelImpl;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

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
	
     public Template(JFrame frame) {
     	 this.frame = frame;

         panel = new JPanel();
 		 panel.setLayout(null);
 		 panel.setBackground(Color.decode("#f1f8e9"));

         presenter = new CreateTemplatePresenterImpl(new CreateTemplateModelImpl());
         presenter.attachView(this);


         JButton back = UIHelpers.generateBackButton(50,50,50,50);
         panel.add(back);
         back.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Dashboard d = new Dashboard(frame, false);
             }
         });


    	 create = UIHelpers.buttonGenerator("Create a new template using file");
         create.setBounds(400,100, 250,50);
         create.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				 jfc.setDialogTitle("Select your file");
				 jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
				 jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.xlsx", "xlsx"));

				 int returnValue = jfc.showSaveDialog(null);
				 if (returnValue == JFileChooser.APPROVE_OPTION) {
					 presenter.createTemplateWithFile(jfc.getSelectedFile().getAbsolutePath());
				 }

			 }
		 });

		 createSQL = UIHelpers.buttonGenerator("Create a new template using SQL");
         createSQL.setBounds(400,200, 250,50);
         createSQL.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // get sql query
                 String sqlQuery = JOptionPane.showInputDialog(frame, "Enter the query");
                 if(sqlQuery != null ){
					 presenter.createTemplateWithQuery(sqlQuery);
				 }
         }});

    	 view = UIHelpers.buttonGenerator("View the existing templates");
         view.setBounds(400,300, 250,50);


         select = UIHelpers.buttonGenerator("Upload data to a template");
         select.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 UploadTemplate ut = new UploadTemplate(frame);
                 presenter.unbindView();
             }
         });

         select.setBounds(400,400, 250,50);

    	 
    	 panel.add(create);
    	 panel.add(createSQL);
    	 panel.add(view);
    	 panel.add(select);

		 this.frame.setContentPane(panel);
		 this.frame.revalidate();
    	 
    	 
     }

     public ArrayList<String> parseFile(File file){
         // read the column names
         ArrayList<String> headers = new ArrayList<>();
         return headers;

     }


    @Override
    public void onSuccessTemplateCreated() {
        JOptionPane.showMessageDialog(frame, "Template created.");
    }

    @Override
    public boolean isFileValid(String filePath) {
     	// check if excel or csv file
        if (filePath.substring(filePath.length()-3).equals("csv") || filePath.substring(filePath.length()-4).equals("xlsx")) {
            return true;
        }
        return false;
    }

    @Override
    public void onErrorUploadingFile() {
        JOptionPane.showMessageDialog(frame, "There was an error uploading the file.");
    }

    public void disposeView(){
         frame.dispose();
         presenter.unbindView();
    }


}


