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

public class CreateAccount implements CreateAccountView {
	
	 private JPanel panel;
	 private JButton create;
	 private JButton edit;
	 private JButton search;
	 private static JFrame frame;
	 private CreateAccountPresenter presenter;
	 private final String[] accountTypes = {"TEQ staff", "Agency"};

     public CreateAccount(JFrame frame) {
		this.frame = frame;


 		panel = new JPanel();


 		panel.setBackground(Color.decode("#f1f8e9"));
 		panel.setLayout(null);

 		presenter = new CreateAccountPresenterImpl();
 		presenter.attachView(this);
 		


 		JButton back = UIHelpers.generateBackButton(0,0,50,50);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard d = new Dashboard(frame, false);
            }
        });
 		
    	 create = UIHelpers.buttonGenerator("Create an Account");
    	 create.setBounds(400,150, 150,50);

    	 
    	 // create account dialog box
    	 create.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent e)
    	      {
    	        // create a new form to accept user input
				  JPanel createAccPanel = new JPanel();
				  createAccPanel.setLayout(null);
				  createAccPanel.setBackground(Color.decode("#f1f8e9"));


				  JLabel lblEnterInfo = new JLabel("Please specify the name and password.");
				  lblEnterInfo.setBounds(300,50, 400, 20);
				  JLabel lblName = new JLabel("Username");
				  lblName.setBounds(300, 100, 400, 20);
				  JTextField txtName = new JTextField();
				  txtName.setBounds(300, 150, 400, 20);
				  JLabel lblPassword = new JLabel("Password");
				  lblPassword.setBounds(300, 200, 400, 20);
				  JPasswordField txtPassword = new JPasswordField();
				  txtPassword.setBounds(300, 250, 400, 20);
				  JLabel lblAccountType = new JLabel("Account Type");
				  lblAccountType.setBounds(300, 300, 400, 20);
				  JComboBox accountTypesBox = new JComboBox(accountTypes);
				  accountTypesBox.setBounds(300, 350, 400, 20);
				  
				  

				  JButton submit = UIHelpers.buttonGenerator("Submit");
				  submit.setBounds( 325, 400, 150,50);
				  JButton cancel = UIHelpers.buttonGenerator("Cancel");
				  cancel.setBounds(500, 400, 150, 50);

				  createAccPanel.add(lblEnterInfo);
				  createAccPanel.add(lblName);
				  createAccPanel.add(txtName);
				  createAccPanel.add(lblPassword);
				  createAccPanel.add(txtPassword);
				  createAccPanel.add(lblAccountType);
				  createAccPanel.add(accountTypesBox);
				  createAccPanel.add(submit);
				  createAccPanel.add(cancel);
				  

				  frame.setContentPane(createAccPanel);
				  frame.revalidate();

				  submit.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                          //  NOTE TO PRESENTER: THIS IS THE USERS INFO!
                            String name = txtName.getText();
                            String password = String.valueOf(txtPassword.getPassword());
                            String accountType = (String) accountTypesBox.getSelectedItem();
                            // createAccount
                            // get account ID
                            // then show user dialog giving account id
						  	presenter.createAccount(name, password, accountType);

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
         edit.setBounds(400,250, 150,50);

    	 search = UIHelpers.buttonGenerator("Search for an Account");
         search.setBounds(400,350, 150,50);
    	 
    	 panel.add(create);
    	 panel.add(edit);
    	 panel.add(search);

		 this.frame.setContentPane(panel);
		 this.frame.revalidate();
    	 
    	 
    	 
    	 
    	 
     }


	@Override
	public void onSuccessAccountCreated() {
     	// must send in account id to user
		JOptionPane.showMessageDialog(frame, "Account created.");
	}

	@Override
	public void onErrorCreatingAccount(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}

	@Override
	public boolean isFieldsValid(String name, String password) {
		boolean valid = true;
		if (name.matches(".*\\d+.*")){
			valid = false;
		}
		if(password.length() == 0){
			valid = false;
		}
		return valid;
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame, "Error. Please make sure names do not contain any numbers and passwords are greater than length 0.");
	}
}
