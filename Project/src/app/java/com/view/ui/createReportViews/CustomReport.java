package app.java.com.view.ui.createReportViews;

import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createTemplateViews.Template;
import app.java.com.view.ui.uploadTemplateViews.UploadTemplate;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.List;

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

        JButton back = UIHelpers.generateBackButton(0,0,50,50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Report r = new Report(frame);
            }
        });


        JPanel scrollPanel = new JPanel(new GridLayout(0,1));
         scrollPanel.setBorder(BorderFactory.createTitledBorder("Please select some attributes."));
         List<JRadioButton> jrbs = new ArrayList<>();
         ButtonGroup group = new ButtonGroup();
         String [] attrs = {"Name", "Age", "Country", "Agency", "Last Name", "Test1", "test2"}; // will be changed to fetched attrs

         for (String attr : attrs){
             JRadioButton jrb = new JRadioButton(attr);
            group.add(jrb);
            jrbs.add(jrb);
            scrollPanel.add(jrb);
         }
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(0,60,400,300);


        JButton add = UIHelpers.buttonGenerator("Add");
        add.setBounds(450,160, 100, 20);
        JButton remove = UIHelpers.buttonGenerator("Remove");
        remove.setBounds(450, 180,100,20);


        JPanel scrollPanelSel = new JPanel(new GridLayout(0,1));
        scrollPanelSel.setBorder(BorderFactory.createTitledBorder("Selected attributes"));
        List<JRadioButton> jrbsSel = new ArrayList<>();
        ButtonGroup groupSel = new ButtonGroup();
        String [] attrsSel = {};

        for (String attr : attrsSel){
            JRadioButton jrb = new JRadioButton(attr);
            groupSel.add(jrb);
            jrbsSel.add(jrb);
            scrollPanelSel.add(jrb);
        }
        JScrollPane scrollPaneSel = new JScrollPane(scrollPanelSel);
        scrollPaneSel.setBounds(600,60,385,300);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JRadioButton jrb : jrbs){
                    if(jrb.isSelected()){
                        if(!jrbsSel.contains(jrb)){
                            jrbsSel.add(jrb);
                            groupSel.add(jrb);
                            scrollPanelSel.add(jrb);
                        }
                    }
                }
            }
        });



         panel.add(add);
         panel.add(remove);
         panel.add(scrollPane);
         panel.add(scrollPaneSel);



        this.frame.setContentPane(panel);
        this.frame.revalidate();


    }

}
