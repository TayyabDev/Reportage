package app.java.com.view.ui.uploadTemplateViews;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.interfaces.ResolveConflictsView;
import app.java.com.view.ui.UIHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ResolveConflicts implements ResolveConflictsView {


//    when Uploading template failed
//- getting a list of InsertException
//- if ConflictException, print the message, create 'use new', 'use old' button
//- if InvalidException, print the message, create text box(s) based on the exception, ask the client to input the valid input.

    private JFrame frame;
    private JPanel panel;

    private List<InsertException> errors;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;


    public ResolveConflicts(JFrame frame, TeqAccount account){
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(null);


        scrollPanel = new JPanel(new GridLayout(0, 1));
        scrollPanel.setBorder(BorderFactory.createTitledBorder("Please resolve the conflicts."));

        ButtonGroup bg = new ButtonGroup();
        List<JRadioButton> errorOptionButtons = new ArrayList<>();
        for (int i =0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JRadioButton jrb = new JRadioButton(String.format("Conflict in Row %d Col %d", i,j));
                bg.add(jrb);
                errorOptionButtons.add(jrb);
                scrollPanel.add(jrb);
            }
        }




        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(50,0,900,450);




        JButton resolve = UIHelpers.buttonGenerator("Resolve");
        resolve.setBounds(450, 470, 100,20);
        resolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JRadioButton errorOption : errorOptionButtons){
                    if(errorOption.isSelected()){
                        // conflict
                        String [] options = {"Use old", "Use new"};

                        // invalid
                    }
                }
            }
        });



        panel.add(scrollPane);
        panel.add(resolve);

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

        ResolveConflicts c = new ResolveConflicts(frame, null);
    }

    @Override
    public void getErrors(List<InsertException> errors) {
        this.errors = errors;
    }
}
