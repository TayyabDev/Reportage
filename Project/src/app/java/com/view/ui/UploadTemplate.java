package app.java.com.view.ui;

import com.app.model.UploadTemplateModelImpl;
import com.app.presenter.UploadTemplatePresenterImpl;
import com.app.presenter.interfaces.CreateTemplatePresenter;
import com.app.presenter.interfaces.UploadTemplatePresenter;
import com.app.view.interfaces.UploadTemplateView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class UploadTemplate implements UploadTemplateView {
	JPanel panel = new JPanel();
	JLabel labelLogo;
	ImageIcon teqLogo;
	UploadTemplatePresenter presenter;


	
	public UploadTemplate() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));

		presenter = new UploadTemplatePresenterImpl(new UploadTemplateModelImpl());
		presenter.attachView(this);


		
		JLabel lblTitle = new JLabel("Upload Data");
		lblTitle.setFont(new Font(null, Font.BOLD, 36));
		lblTitle.setBounds(390, 20, 400, 40);
		panel.add(lblTitle);
		
		JLabel lblDate = new JLabel("Template date (MM/YYYY): ");
		lblDate.setBounds(80, 120, 180, 25);
		panel.add(lblDate);
		
		// Date textbox
		JTextField txtDate = new JTextField();
		txtDate.setBounds(400, 120, 200, 25);
		panel.add(txtDate);
		
		JLabel lblTemplate = new JLabel("Select template type: ");
		lblTemplate.setBounds(80, 160, 180, 25);
		panel.add(lblTemplate);
		
		String[] templateNames = {"Temp1", "Temp2", "Temp3"};
		JComboBox cbTemplate = new JComboBox(templateNames);
		cbTemplate.setBounds(400, 160, 200, 25);
		panel.add(cbTemplate);
		
		JLabel lblFile = new JLabel("Select file: ");
		lblFile.setBounds(80, 200, 180, 25);
		panel.add(lblFile);
		
		JButton btnSelectFile = UIHelpers.buttonGenerator("Upload");
		JButton btnSubmit = UIHelpers.buttonGenerator("Submit");
		
		 JLabel lblSelectedFile = new JLabel("You selected a file");
		 lblSelectedFile.setBounds(400, 230, 700, 25);
		
		// Select file to upload
		btnSelectFile.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				 jfc.setDialogTitle("Select your file");
				 int returnValue = jfc.showSaveDialog(null);
				 if (returnValue == JFileChooser.APPROVE_OPTION) {
					 if (jfc.getSelectedFile() != null) {
						 //JLabel lblSelectedFile = new JLabel("You selected the directory: " + jfc.getSelectedFile());
						 panel.add(lblSelectedFile);
						 panel.repaint();
					 }
				 }

			 }
		 });

		
		btnSelectFile.setBounds(400, 200, 100, 25);
		btnSubmit.setBounds(700, 400, 100, 25);
		panel.add(btnSelectFile);
		panel.add(btnSubmit);

	}

	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Upload Data");
	        frame.add(new UploadTemplate().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
	    }

	@Override
	public void onSuccessTemplateCreated() {

	}

	@Override
	public boolean isFileValid() {
		return false;
	}

	@Override
	public void onErrorUploadingFile() {

	}
}
