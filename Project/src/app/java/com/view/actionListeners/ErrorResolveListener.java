package app.java.com.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class ErrorResolveListener implements ActionListener {

    private JFrame frame;
    private List<JRadioButton> errorOptionButtons;

    public ErrorResolveListener(JFrame frame, List<JRadioButton> errorOptionButtons) {
        this.frame = frame;
        this.errorOptionButtons = errorOptionButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(JRadioButton errorOption : errorOptionButtons) {
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
}
