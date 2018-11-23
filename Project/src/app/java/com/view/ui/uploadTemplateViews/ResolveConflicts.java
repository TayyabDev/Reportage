package app.java.com.view.ui.uploadTemplateViews;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.ResolveConflictPresenterImpl;
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

    private ButtonGroup bg;
    List<JRadioButton> errorOptionButtons;

    private ResolveConflictPresenterImpl presenter;


    public ResolveConflicts(JFrame frame, TeqAccount account){
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(null);

        presenter = new ResolveConflictPresenterImpl();
        presenter.attachView(this);


        scrollPanel = new JPanel(new GridLayout(0, 1));
        scrollPanel.setBorder(BorderFactory.createTitledBorder("Please resolve the conflicts."));



        presenter.fillListWithErrors();





        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(50,0,900,450);




        JButton resolve = UIHelpers.buttonGenerator("Resolve");
        resolve.setBounds(450, 470, 100,20);
        resolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JRadioButton errorOption : errorOptionButtons){
                    if(errorOption.isSelected()) {
                        // if type is conflict
                        String [] options = {"Use old", "Use new"};
                        int ret = JOptionPane.showOptionDialog(frame, "Select one of the options",
                                "Resolve conflict", JOptionPane.YES_NO_OPTION, 1,
                                null, options, options[0]);

                        // return value ret is value in options array (use old / use new)
                        System.out.println(options[ret]);

                        // invalid conflict stuff goes here
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

        ResolveConflicts rc = new ResolveConflicts(frame, null);
    }

    @Override
    public void getErrors(List<InsertException> errors) {
        bg = new ButtonGroup();
        errorOptionButtons = new ArrayList<>();
        for (int i =0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JRadioButton jrb = new JRadioButton(String.format("Conflict in Row %d Col %d", i,j));
                bg.add(jrb);
                errorOptionButtons.add(jrb);
                scrollPanel.add(jrb);
            }
        }

        this.errors = errors;
    }

    @Override
    public boolean isFieldsValid(String update) {
        return update.length() > 0;
    }

    @Override
    public void onSuccessConflictFix() {
        // show message saying that fixed
    }

    @Override
    public void onErrorConflictFix(String error) {
        JOptionPane.showMessageDialog(frame,error);
    }

    @Override
    public void onSuccessInvalidFix() {
        // show message saying that fixed
    }

    @Override
    public void onErrorInvalidFix(String error) {
        JOptionPane.showMessageDialog(frame,error);

    }
}
