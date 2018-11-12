package app.java.com.view.ui.viewUserDataViews;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.java.com.presenter.FetchUserDataPresenterImpl;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class UserData implements CreateUserDataView{

	private JFrame frame;
	private JPanel panel;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTemplates;
	private JTextField tfTarget;
	private JTextField tfConstraint;
	private JTable tbData;
	
	private FetchUserDataPresenter presenter;
	
	public UserData(JFrame frame) {
		
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));
		
		JLabel lTitle = new JLabel("View Data");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(390, 20, 400, 40);
		panel.add(lTitle);
		
		JButton back = UIHelpers.generateBackButton(0,0,50,50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
		    @Override
            public void actionPerformed(ActionEvent e) {
		    	Dashboard d = new Dashboard(frame, false);
		    }
		});
		
		cbTemplates = new JComboBox();
		cbTemplates.setBounds(120, 80, 200, 25);
		panel.add(cbTemplates);
		
		JLabel lTarget = new JLabel("Target:");
		lTarget.setBounds(350, 80, 100, 25);
		panel.add(lTarget);
		
        tfTarget = new JTextField();
        tfTarget.setBounds(400, 80, 100, 25);
        tfTarget.setEditable(false);
        panel.add(tfTarget);
        
        JLabel lConstraint = new JLabel("Constraint:");
        lConstraint.setBounds(550, 80, 100, 25);
		panel.add(lConstraint);
		
        tfConstraint = new JTextField();
        tfConstraint.setBounds(620, 80, 100, 25);
        tfConstraint.setEditable(false);
        panel.add(tfConstraint);
        
		presenter = new FetchUserDataPresenterImpl(this);
		presenter.attachView(this);
		presenter.fetchTemplateNames();

		
		lTarget.setVisible(false);
		tfTarget.setVisible(false);
		lConstraint.setVisible(false);
		tfConstraint.setVisible(false);
		
		JButton btnSelect = UIHelpers.buttonGenerator("Select");
		btnSelect.setBounds(750, 80, 100, 25);
		btnSelect.addActionListener(e -> {
			String templateName = (String) cbTemplates.getSelectedItem();
			List<String> target = new ArrayList<String>();
			List<String> constraint = new ArrayList<String>();			
			presenter.fetchUserDataWithSelection(target, templateName, constraint);
        });
		panel.add(btnSelect);
		
		this.frame.setContentPane(panel);
        this.frame.revalidate();
	}

	@Override
	public void invalidQuery(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@SuppressWarnings("serial")
	@Override
	public void displayData(List<String> columns, List<List<String>> data) {
		int numColumns = data.get(0).size();
		int numRows = data.size();
		String[][] dataArray = new String[numRows][numColumns];
		for (int i = 0; i < numRows; i ++) {
			dataArray[i] = data.get(i).toArray(dataArray[i]);
		}
		tbData = new JTable(dataArray, columns.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbData.setBounds(100, 150, 800, 250);
		tbData.setVisible(true);
		tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel.add(new JScrollPane(tbData, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		panel.add(tbData);
		
		JOptionPane.showMessageDialog(null, "There is a bug where data isn't shown.\nClose popup and click the centre area\nfor data table to appear.");
	}

	@Override
	public void fillDropdownWithTemplateNames(List<String> templateNames) {
		cbTemplates.setModel(new DefaultComboBoxModel(templateNames.toArray()));
		
	}
	
}
