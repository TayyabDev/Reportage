package app.java.com.view.ui.createAccountViews;

import app.java.com.presenter.CreateAccountPresenterImpl;
import app.java.com.presenter.interfaces.CreateAccountPresenter;
import app.java.com.view.interfaces.CreateAccountView;
import app.java.com.view.ui.UIHelpers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Account implements CreateAccountView {
	
	 private JPanel panel;
	 private JButton create;
	 private JButton edit;
	 private JButton search;
	 private static JFrame frame;
	 private CreateAccountPresenter presenter;

     public Account() {


    	 GridBagLayout gb = new GridBagLayout();
 		GridBagConstraints c = new GridBagConstraints();
 		panel = new JPanel();
 		panel.setLayout(gb);
 		panel.setBackground(Color.decode("#f1f8e9"));

 		presenter = new CreateAccountPresenterImpl();
 		presenter.attachView(this);
 		
 		
 		c.fill = GridBagConstraints.BOTH;
 		
 		c.gridwidth = GridBagConstraints.REMAINDER;
 		
    	 create = UIHelpers.buttonGenerator("Create an Account");
    	 
    	 // create account dialog box
    	 create.addActionListener(new ActionListener() {
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
                            String password = String.valueOf(txtPassword.getPassword());

                            // createAccount
                            // get account ID
                            // then show user dialog giving account id
						  	presenter.createAccount(name, password);



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

	@Override
	public void onSuccessAccountCreated() {
     	// must send in account id to user
		JOptionPane.showMessageDialog(frame, "Account created with ID <insert id>.");
	}

	@Override
	public void onErrorCreatingAccount() {
		JOptionPane.showMessageDialog(frame, "Error creating the account.");
	}

	@Override
	public boolean isFieldsValid(String name, String password) {
     	System.out.println(name);
		System.out.println(password);

		boolean valid = true;
		if (name.matches(".*\\d+.*")){
			valid = false;
		}
		if(password.length() == 0){
			valid = false;
		}
		System.out.println(valid);


		return valid;
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame, "Error. Please make sure names do not contain any numbers and passwords are greater than length 0.");
	}
}
