package app.java.com.view.ui.createReportViews;

import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Report {
    private static JFrame frame;
    private JPanel panel = new JPanel();

    public Report(JFrame frame){
        this.frame = frame;
        panel = new JPanel();
        panel.setBackground(Color.decode("#f1f8e9"));
        panel.setLayout(null);

        JButton back = UIHelpers.generateBackButton(50,50,50,50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard d = new Dashboard(frame, false);
            }
        });


        this.frame.setContentPane(panel);
        this.frame.revalidate();

    }

}
