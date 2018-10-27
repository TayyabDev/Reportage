package com.app.view.ui;

import javax.swing.*;
import java.awt.*;

public class Report {
    private static JFrame frame;
    private JPanel panel = new JPanel();

    public Report(){
        panel = new JPanel();
        panel.setBackground(Color.decode("#f1f8e9"));


    }

    public static void main(String[] args) {
        frame = new JFrame("Reports");
        frame.add(new Report().panel);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);

    }
}
