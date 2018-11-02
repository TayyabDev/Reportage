package app.java.com.view.ui.createAccountViews;

import app.java.com.view.ui.UIHelpers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    	        // create a new form to accept user input
				  JPanel createAccPanel = new JPanel();
				  createAccPanel.setLayout(null);
				  createAccPanel.setBackground(Color.decode("#f1f8e9"));


				  JLabel lblEnterInfo = new JLabel("Please specify the name and password.");
				  lblEnterInfo.setBounds(150,5, 400, 20);

				  JLabel lblName = new JLabel("Name");
				  lblName.setBounds(150, 50, 400, 20);

				  JTextField txtName = new JTextField();
				  txtName.setBounds(150, 100, 400, 20);

				  JLabel lblPassword = new JLabel("Password");
				  lblPassword.setBounds(150, 150, 400, 20);

				  JPasswordField txtPassword = new JPasswordField();
				  txtPassword.setBounds(150, 200, 400, 20);

				  JButton submit = UIHelpers.buttonGenerator("Submit");
				  submit.setBounds( 150, 250, 150,50);

				  JButton cancel = UIHelpers.buttonGenerator("Cancel");
				  cancel.setBounds(350, 250, 150, 50);


				  createAccPanel.add(lblEnterInfo);
				  createAccPanel.add(lblName);
				  createAccPanel.add(txtName);
				  createAccPanel.add(lblPassword);
				  createAccPanel.add(txtPassword);
				  createAccPanel.add(submit);
				  createAccPanel.add(cancel);
				  

				  frame.setContentPane(createAccPanel);
				  frame.revalidate();

				  submit.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                          //  NOTE TO PRESENTER: THIS IS THE USERS INFO!
                            String name = txtName.getText();
                            char [] password = txtPassword.getPassword();

                            // createAccount
                            // get account ID
                            // then show user dialog giving account id
                            JOptionPane.showMessageDialog(frame, "Account created with ID <insert id>");

                          frame.setContentPane(panel);
                          frame.revalidate();
                      }
                  });

                  cancel.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                          // go back to previous frame
                          frame.setContentPane(panel);
                          frame.revalidate();
                      }
                  });


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
		 	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            UIHelpers.setLook();




	 }

}
