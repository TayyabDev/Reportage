package app.java.com.view.ui.createAccountViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.Template;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

public class AgencyDashboard {

	static JFrame frame;
	JPanel panel = new JPanel();
	JLabel labelLogo;
	ImageIcon teqLogo;
	
	public AgencyDashboard(JFrame frame, boolean init, AgencyAccount account) {
		this.frame = frame;
		
		panel.setBackground(Color.decode("#f1f8e9"));
		panel.setLayout(null);
		
		JButton upload = UIHelpers.buttonGenerator("Upload data to a template");
		upload.setBounds(400,150, 150,50);
		
		upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadTemplate ut = new UploadTemplate(frame, account);
            }
        });
		
		panel.add(upload);
		frame.add(panel);
		
		
		if(init) {
            this.frame.add(panel);
            this.frame.setPreferredSize(new Dimension(1000, 600));
            this.frame.pack();
            this.frame.setVisible(true);
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            this.frame.setResizable(false);
            UIHelpers.setLook();
        }
        else{
            this.frame.setContentPane(panel);
            this.frame.revalidate();
        }
	}
}
