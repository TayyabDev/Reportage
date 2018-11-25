package app.java.com.view.ui.createAccountViews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.ExistingTemplate;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

public class AgencyDashboard extends Dashboard {

	JFrame frame;
	JPanel panel;

	public AgencyDashboard(JFrame frame, boolean init, AgencyAccount account) {
		super(frame, init, account);
		this.frame = frame;

		JButton upload = UIHelpers.buttonGenerator("Upload data to template");

		upload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UploadTemplate(frame, account);
			}
		});
		gb.setConstraints(upload, c);
		panel.add(upload);

		JButton viewTemp = new JButton("View existing templates");
		viewTemp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ExistingTemplate(frame, account);
			}
		});
		gb.setConstraints(viewTemp, c);
		panel.add(viewTemp);

		revalidatePanel(init);

	}
}
