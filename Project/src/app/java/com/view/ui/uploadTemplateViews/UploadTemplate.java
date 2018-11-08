package app.java.com.view.ui.uploadTemplateViews;

import app.java.com.presenter.UploadTemplatePresenterImpl;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.view.interfaces.UploadTemplateView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.Template;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class UploadTemplate implements UploadTemplateView {

	private JPanel panel;
	private JLabel labelLogo;
	private ImageIcon teqLogo;
	private UploadTemplatePresenter presenter;
	private String filePath;
	private boolean templateCompatible = false;
	private JComboBox templateDropdown;
	private JFrame jFrame;


	public UploadTemplate(JFrame frame) {

        this.jFrame = frame;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));

        templateDropdown = new JComboBox();
        templateDropdown.setBounds(400, 160, 200, 25);
        panel.add(templateDropdown);

		presenter = new UploadTemplatePresenterImpl();
		presenter.attachView(this);
		presenter.fetchTemplateNames();


		JButton back = UIHelpers.generateBackButton(50,50,50,50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
		    @Override
            public void actionPerformed(ActionEvent e) {
		        Template t = new Template(frame);
		    }
		});

		JLabel lblTitle = new JLabel("Upload Data");
		lblTitle.setFont(new Font(null, Font.BOLD, 36));
		lblTitle.setBounds(390, 20, 400, 40);
		panel.add(lblTitle);

		JLabel lblDate = new JLabel("Template date (MM/YYYY): ");
        lblDate.setBounds(80, 120, 180, 25);
        panel.add(lblDate);

        // Date Chooser
        JDateChooser chooser = new JDateChooser();
        chooser.setBounds(400, 120, 200, 25);
        panel.add(chooser);
		
		JLabel lblTemplate = new JLabel("Select template type: ");
		lblTemplate.setBounds(80, 160, 180, 25);
		panel.add(lblTemplate);
		
		JLabel lblFile = new JLabel("Select file: ");
		lblFile.setBounds(80, 200, 180, 25);
		panel.add(lblFile);
		
		JButton btnSelectFile = UIHelpers.buttonGenerator("Upload");
		JButton btnSubmit = UIHelpers.buttonGenerator("Submit");
		
		JLabel lblSelectedFile = new JLabel("");
		lblSelectedFile.setBounds(400, 230, 700, 25);

		// Select file to upload
		btnSelectFile.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Select your file");
            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile() != null) {
                    filePath = jfc.getSelectedFile().getPath();
                    String fileName = jfc.getSelectedFile().getName();
                    lblSelectedFile.setText("Selected File Name : " + fileName);
                    panel.add(lblSelectedFile);
                    panel.repaint();

                    String selectedTemplate = (String)templateDropdown.getSelectedItem();
                    presenter.verifyFileUploaded(filePath, selectedTemplate);
                }
            }

        });

		
		// Check if template uploaded is incompatible
		btnSubmit.addActionListener(e -> {
            Date dateSelected = chooser.getDate();

            if(dateSelected == null) {
                showPopUpWithMessage("No Date Selected", "Information");
                return;
            } else if(!templateCompatible) {
                showPopUpWithMessage("No Valid Template Selected", "Information");
                return;
            }

            String templateName = (String)templateDropdown.getSelectedItem();

		    presenter.uploadTemplateWithFile(dateSelected, templateName, filePath);
        });

		
		btnSelectFile.setBounds(400, 200, 100, 25);
		btnSubmit.setBounds(700, 400, 100, 25);
		panel.add(btnSelectFile);
		panel.add(btnSubmit);

        this.jFrame.setContentPane(panel);
        this.jFrame.revalidate();
	}

	private void showPopUpWithMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

	@Override
	public boolean isFileValid(String selectedFilePath) {
        if (selectedFilePath.substring(selectedFilePath.length()-3).equals("csv") ||
                selectedFilePath.substring(selectedFilePath.length()-4).equals("xlsx")) {
            return true;
        }

        return false;
	}

	@Override
	public void onErrorUploadingFile() {
        showPopUpWithMessage("Error uploading file", "Alert Message");
	}

	@Override
	public void onCompatibleTemplateSelected(boolean compatible) {
		if (compatible) {
			templateCompatible = true;
            showPopUpWithMessage("File uploaded valid", "Information");

		} else {
		    showPopUpWithMessage("File uploaded not valid", "Information");
        }

	}

	@Override
	public void fillDropdownWithTemplateNames(List<String> templateNames) {
        templateDropdown.setModel(new DefaultComboBoxModel(templateNames.toArray()));
	}


	@Override
	public void onSuccessTemplateUploaded() {
        showPopUpWithMessage("Template successfully uploaded", "Information");
	}
}
