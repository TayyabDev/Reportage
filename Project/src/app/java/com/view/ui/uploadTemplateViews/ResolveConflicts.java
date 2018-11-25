package app.java.com.view.ui.uploadTemplateViews;

import app.java.com.model.Exceptions.DuplicateKeyException;
import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.ResolveConflictPresenterImpl;
import app.java.com.view.actionListeners.ErrorResolveListener;
import app.java.com.view.interfaces.ResolveConflictsView;
import app.java.com.view.ui.UIHelpers;

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ResolveConflicts implements ResolveConflictsView {


//    when Uploading template failed
//- getting a list of InsertException
//- if ConflictException, print the message, create 'use new', 'use old' button
//- if InvalidException, print the message, create text box(s) based on the exception, ask the client to input the valid input.


    /**
     * Show the column names
     * Get the old row values using the exception
     *
     * List of Exceptions
     * Mes 2            *
     * Mes3
     *
     * List:
     *
     * Row 1: _val1__ _val2___ _val3___ __val4__ _conflicting__
     *
     *
     *
     */

    private JFrame frame;
    private JPanel panel;

    private List<InsertException> errors;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;

    private ButtonGroup bg;
    List<JRadioButton> errorOptionButtons;

    private ResolveConflictPresenterImpl presenter;


    public ResolveConflicts(JFrame frame, TeqAccount account, List<InsertException> errors){
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(null);

        presenter = new ResolveConflictPresenterImpl();
        presenter.attachView(this);


        scrollPanel = new JPanel(new GridLayout(0, 1));
        scrollPanel.setBorder(BorderFactory.createTitledBorder("Please resolve the conflicts."));


        this.errors = errors;

        scrollPanel.add(new JLabel("Hello"));


        //presenter.processDuplicateRowConflicts(errors);

        //getErrors(errors);


        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(50,0,900,450);

        JButton resolve = UIHelpers.buttonGenerator("Resolve");
        resolve.setBounds(450, 470, 100,20);
        resolve.addActionListener(new ErrorResolveListener(frame, errorOptionButtons, scrollPanel, this.errors, presenter));

        panel.add(scrollPane);
        panel.add(resolve);

        this.frame.setContentPane(panel);
        this.frame.revalidate();
    }


    public  static void main(String [] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        UIHelpers.setLook();

        List<String> duplicateVals = Arrays.asList("1","2","3");

        DuplicateKeyException exception = new DuplicateKeyException("Client_Profile", duplicateVals);
        ResolveConflicts rc = new ResolveConflicts(frame, null, Arrays.asList(exception));

    }

    /**
     * Populates the list of conflicts
     * @param errors
     */
    public void getErrors(List<InsertException> errors) {
        bg = new ButtonGroup();
        errorOptionButtons = new ArrayList<>();
        for (InsertException exception : errors) {
            if(exception.getIsFixed()) {
                JLabel jl = new JLabel( exception.getMessage() + " - Automatically Fixed");
                scrollPanel.add(jl);
            } else {
                JRadioButton jrb = new JRadioButton("");
                bg.add(jrb);
                errorOptionButtons.add(jrb);
                scrollPanel.add(jrb);
            }
        }
        scrollPanel.revalidate();

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
        JOptionPane.showMessageDialog(frame,"You have a new error: " + error);
        // add new message to the list of errors
        JRadioButton jrb = new JRadioButton(error);
        bg.add(jrb);
        errorOptionButtons.add(jrb);
        scrollPanel.add(jrb);
        scrollPanel.revalidate();
    }

    @Override
    public void updateExceptions(List<InsertException> exceptions) {
        this.errors = exceptions;

    }




}
