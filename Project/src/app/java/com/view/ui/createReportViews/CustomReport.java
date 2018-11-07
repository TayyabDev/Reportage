package app.java.com.view.ui.createReportViews;

import javax.swing.*;

public class CustomReport {

    private  JFrame frame;
    private  JPanel panel;

    public  CustomReport(JFrame frame){
        /**
         *  TODO:
         *  -initialize dropdown with attributes
         *  -allow users to select multible attributes
         * - date range by year
         */
        this.frame= frame;
         panel = new JPanel();
         panel.setLayout(null);



         JComboBox attrs = new JComboBox();
         attrs.setBounds(300, 50, 400, 20);
         panel.add(attrs);

        this.frame.setContentPane(panel);
        this.frame.revalidate();

    }

}
