package app.java.com.view.ui.uploadTemplateViews;

import app.java.com.view.interfaces.ConflictUploadTemplateView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Login;

import javax.swing.*;
import java.awt.*;

public class ConflictUploadTemplate implements ConflictUploadTemplateView {


//    when Uploading template failed
//- getting a list of InsertException
//- if ConflictException, print the message, create 'use new', 'use old' button
//- if InvalidException, print the message, create text box(s) based on the exception, ask the client to input the valid input.

    private JFrame frame;
    private JPanel panel;
    public ConflictUploadTemplate(JFrame frame){
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(null);



        this.frame.setContentPane(panel);
        this.frame.revalidate();
    }


    public  static void main(String [] args){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        UIHelpers.setLook();

        ConflictUploadTemplate c = new ConflictUploadTemplate(frame);
    }

}
