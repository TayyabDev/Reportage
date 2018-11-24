package app.java.com.view.actionListeners;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.presenter.interfaces.ResolveConflictPresenter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ErrorResolveListener implements ActionListener {

    private JFrame frame;
    private List<JRadioButton> errorOptionButtons;
    private JPanel scrollPanel;
    private ResolveConflictPresenter presenter;
    private List<InsertException> errors;

    public ErrorResolveListener(JFrame frame, List<JRadioButton> errorOptionButtons, JPanel scrollPanel,
                                List<InsertException> errors, ResolveConflictPresenter presenter) {
        this.frame = frame;
        this.errorOptionButtons = errorOptionButtons;
        this.scrollPanel = scrollPanel;
        this.presenter = presenter;
        this.errors = errors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(errorOptionButtons != null){
            int index = 0;
            for(JRadioButton errorOption : errorOptionButtons) {
                if(errorOption.isSelected()) {
                    // locate the exception corresponding to the radio button
                    InsertException insertException = errors.get(index);


                    JScrollPane scrollPane = new JScrollPane();
                    JPanel scrollPanel = new JPanel(new GridLayout(1,0));

                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);

                    dialog.setBounds(100, 100, 450, 300);


                    dialog.getContentPane().setLayout(new GridLayout(0,1));

                    dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

                    List<JTextField> cols = new ArrayList<>();
                    for (String col : insertException.getErrorValues()){
                        JTextField val = new JTextField(col);
                        cols.add(val);
                        scrollPanel.add(val);
                    }
                    scrollPanel.revalidate();

                    scrollPane.setViewportView(scrollPanel);

                    JButton done = new JButton("Done");

                    dialog.add(done);
                    done.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            List<String> correctValues = new ArrayList<>();

                            for(JTextField col : cols){
                                correctValues.add(col.getText());
                            }
                            presenter.attemptFixConflict(correctValues, insertException.getTable());
                        }
                    });

                    dialog.setVisible(true);

                    // remove message from list
                    errorOptionButtons.remove(errorOption);
                    scrollPanel.remove(errorOption);
                    scrollPanel.revalidate();

                }
                index += 1;
            }
       }


    }
}
