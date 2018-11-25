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
import app.java.com.view.ui.createTemplateViews.ExistingTemplate;
import app.java.com.view.ui.createTemplateViews.Template;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

public class AgencyDashboard {

	static JFrame frame;
	JPanel panel;
	
	public AgencyDashboard(JFrame frame, boolean init, AgencyAccount account) {
		this.frame = frame;
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel = new JPanel();
        panel.setLayout(gb);
		panel.setBackground(Color.decode("#f1f8e9"));
		panel.setLayout(gb);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;


        c.gridwidth = 1;
        c.gridheight = 1;
        // create the buttons
        c.weightx = 1.0;
        c.weighty = 2.0;

		JButton upload = UIHelpers.buttonGenerator("Upload data to template");

		upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadTemplate ut = new UploadTemplate(frame, account);
            }
        });
		gb.setConstraints(upload, c);
		panel.add(upload);

		JButton viewTemp = new JButton("View existing templates");
		viewTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExistingTemplate ext = new ExistingTemplate(frame, account);
            }
        });
		gb.setConstraints(viewTemp, c);
		panel.add(viewTemp);

		
		
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
