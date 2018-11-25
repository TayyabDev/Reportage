package app.java.com.view.ui.viewUserDataViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.model.entities.DataChanges;
import app.java.com.presenter.FetchUserDataPresenterImpl;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class UserData implements CreateUserDataView{

	private JFrame frame;
	private JPanel panel;
	private JLabel lTitle;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTemplates;
	private JTextField tfTarget;
	private JTextField tfConstraint;
	private JTable tbData;
	private boolean tableEditable = false;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;
	
	private JButton btnSelect = UIHelpers.buttonGenerator("Select");
	private JButton btnEdit = UIHelpers.buttonGenerator("Enable Editting");
	private JButton btnSubmit = UIHelpers.buttonGenerator("Submit");
	private JButton btnCancel = UIHelpers.buttonGenerator("Cancel");

	private List<List<String>> originalTableData;
	
	private FetchUserDataPresenter presenter;
	
	public UserData(JFrame frame, TeqAccount account) {
		
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));
		
		lTitle = new JLabel("View Data");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(390, 20, 400, 40);
		panel.add(lTitle);
		
		JButton back = UIHelpers.generateBackButton(0,0,50,50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
		    @Override
            public void actionPerformed(ActionEvent e) {
		    	new Dashboard(frame, false, account);
		    }
		});
		tbData = new JTable();
		scrollPanel = new JPanel();
		scrollPanel.add(tbData);

		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setBounds(0,150,frame.getWidth()-15,frame.getHeight() - 190);
		panel.add(scrollPane);

		cbTemplates = new JComboBox<String>();
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
		
		btnSelect.setBounds(700, 80, 100, 25);
		btnSelect.addActionListener(e -> {
			String templateName = (String) cbTemplates.getSelectedItem();
			presenter.fetchUserDataWithSelection(templateName);
			panel.revalidate();
        });
		panel.add(btnSelect);
		
		btnEdit.setBounds(810, 80, 140, 25);
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(e -> {
			enableEditButton();
		});
		panel.add(btnEdit);
		
		btnCancel.setBounds(700, 115, 100, 25);
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(e -> {
			disableEditButton();
		});
		panel.add(btnCancel);
		
		btnSubmit.setBounds(810, 115, 140, 25);
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(e -> {
			presenter.submitChanges(cbTemplates.getSelectedItem().toString(), originalTableData, getTableList());
		});
		panel.add(btnSubmit);
		
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
		originalTableData = data;
		int numColumns = data.get(0).size();
		int numRows = data.size();
		String[][] dataArray = new String[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			dataArray[i] = data.get(i).toArray(dataArray[i]);
		}
		tbData = new JTable(new DefaultTableModel(dataArray, columns.toArray())) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return tableEditable;
			}	
		};
		tbData.getTableHeader().setReorderingAllowed(false);
		btnEdit.setEnabled(true);
		tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPanel.removeAll();
		scrollPane.setViewportView(tbData);
		scrollPane.revalidate();
		panel.revalidate();
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void fillDropdownWithTemplateNames(List<String> templateNames) {
		cbTemplates.setModel(new DefaultComboBoxModel(templateNames.toArray()));
		
	}
	
	@SuppressWarnings("unchecked")
	private List<List<String>> getTableList() {
		List<List<String>> data = new ArrayList<List<String>>();
		Vector<Vector<String>> dataVector = ((DefaultTableModel) tbData.getModel()).getDataVector();
		for (int i = 0; i < tbData.getModel().getRowCount(); i++) {
			@SuppressWarnings("rawtypes")
			Vector row = dataVector.elementAt(i);
			data.add(row);
		}
		System.out.println(data.toString());
		return data;
	}

	@Override
	public void showDataChanges(List<DataChanges> changesList) {
		String[] options = {"Yes", "No"};
		String message = "";
		for (DataChanges change : changesList) {
			message += change.toString() + "\n";
		}
		JTextArea textArea = new JTextArea(10, 50);
	    textArea.setText(message);
	    textArea.setEditable(false);
	     JScrollPane scrollPane = new JScrollPane(textArea);
		int choice = JOptionPane.showOptionDialog(frame, scrollPane, "Confirm Changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Yes");
		if (choice == 0) {
			presenter.updateChanges(changesList, cbTemplates.getSelectedItem().toString());
		} else {
			disableEditButton();
		}
	}

	@Override
	public void dataUpdateSuccess(String message) {
		JOptionPane.showMessageDialog(frame, message, "Data Update Success", JOptionPane.INFORMATION_MESSAGE);
		String templateName = (String) cbTemplates.getSelectedItem();
		List<String> target = new ArrayList<String>();
		List<String> constraint = new ArrayList<String>();
		presenter.fetchUserDataWithSelection(templateName);
		panel.revalidate();
		disableEditButton();
	}
	
	@Override
	public void dataUpdateFail(String message) {
		JOptionPane.showMessageDialog(frame, message, "Data Update Unsuccessful", JOptionPane.ERROR_MESSAGE);
		disableEditButton();
	}

	private void enableEditButton() {
		cbTemplates.setEnabled(false);
		btnSelect.setEnabled(false);
		btnCancel.setEnabled(true);
		btnSubmit.setEnabled(true);
		btnEdit.setEnabled(false);
		lTitle.setText("Edit Data");
		tableEditable = true;
	}
	
	private void disableEditButton() {
		cbTemplates.setEnabled(true);
		btnSelect.setEnabled(true);
		btnEdit.setEnabled(true);
		btnSubmit.setEnabled(false);
		btnCancel.setEnabled(false);
		lTitle.setText("View Data");
		tableEditable = false;
	}
}
