package app.java.com.view.ui.createReportViews;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.glass.ui.Pixels.Format;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.AddNewReportFormatImpl;
import app.java.com.presenter.ExistingReportFormatImpl;
import app.java.com.presenter.interfaces.AddNewReportFormatPresenter;
import app.java.com.presenter.interfaces.ExistingReportFormatPresenter;
import app.java.com.view.interfaces.CreateReportView;
import app.java.com.view.interfaces.ExistingReportFormatView;
import app.java.com.view.ui.UIHelpers;

public class ExistingReportFormat implements ExistingReportFormatView{

	private static JFrame frame;
    private JPanel panel;
    
    private JScrollPane scrollPane;
    private JPanel scrollPanel;
    private JPanel resultScrollPanel;
    
    private List<JCheckBox> attrsComboBoxes;
    private List<JCheckBox> results = new ArrayList<>();
    
    private JButton generate;
    private ExistingReportFormatPresenter presenter;
    private TeqAccount account;
    private HashMap<String,String> reportMap;
    
	public ExistingReportFormat(JFrame frame, TeqAccount account) {
		
		this.frame = frame;
		this.account = account;
		
		panel = new JPanel();
        panel.setBackground(Color.decode("#f1f8e9"));
        panel.setLayout(null);
        presenter = new ExistingReportFormatImpl();
        presenter.attachView(this);
        
        JButton back = UIHelpers.generateBackButton(0,0,50,50);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	CreateReportView report = new Report(frame, account);
            }
        });
        
        scrollPanel = new JPanel(new GridLayout(0,1));
        scrollPanel.setBorder(BorderFactory.createTitledBorder("Please select the reports."));
        
        attrsComboBoxes = new ArrayList<>();
        
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(150,60,755,300);

        presenter.fetchExistingReports();
        
        generate = UIHelpers.buttonGenerator("Generate");
        generate.setBounds(300, 450, 400, 30);
        generate.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		HashMap<String, String> selectedQuerys = new HashMap<String, String>();
                for(JCheckBox cb : attrsComboBoxes){
                    if(cb.isSelected()){
                    	selectedQuerys.put(cb.getText(), reportMap.get(cb.getText()));
                    }
                }
                presenter.generateReport(selectedQuerys);
                
        	}
        });
		
        panel.add(scrollPane);
        panel.add(generate);
        
        this.frame.setContentPane(panel);
        this.frame.revalidate();
	}

	@Override
	public void errorFetchReportFormats() {
		JOptionPane.showMessageDialog(frame, "Error happens when fetch existing reports.");
	}
	
	@Override
	public void errorFetchReportFormats(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}

	@Override
	public void displayExistingReport(HashMap<String, String> nameQuery) {
		this.reportMap = nameQuery;
		for (String reportName : nameQuery.keySet()){
            JCheckBox cb = new JCheckBox(reportName);
            attrsComboBoxes.add(cb);
            scrollPanel.add(cb);
        }
        scrollPanel.revalidate();
		
	}

	@Override
	public void onSuccessCreateReport() {
		JOptionPane.showMessageDialog(frame, "All selected reports created under src");
	}

	@Override
	public void onErrorCreateReport(List<String> failedReportNames) {
		String formatStr = "";
		for (String reportName:failedReportNames) {
			formatStr = formatStr + reportName + "\n";
		}
		JOptionPane.showMessageDialog(frame, 
				"There are something wrong when creating following reports: \n"
				+ formatStr);
		
	}

}
