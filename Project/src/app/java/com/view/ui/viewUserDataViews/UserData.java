package app.java.com.view.ui.viewUserDataViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import app.java.com.model.entities.template.UserDataTemplate;
import app.java.com.presenter.FetchUserDataPresenterImpl;
import app.java.com.presenter.UploadTemplatePresenterImpl;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.presenter.interfaces.UploadTemplatePresenter;
import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.interfaces.UploadTemplateView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class UserData implements CreateUserDataView, UploadTemplateView {

	private JFrame frame;
	private JPanel panel;
	private JComboBox cbTableNames;
	private JComboBox cbTarget;
	private JComboBox cbConstraint;
	private JTable tbData;
	private FetchUserDataPresenter presenter1;
	private UploadTemplatePresenter presenter2;
	
	public UserData(JFrame frame) {
		this.frame = frame;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));

        presenter1 = new FetchUserDataPresenterImpl(this);
        presenter2 = new UploadTemplatePresenterImpl();
        presenter1.attachView(this);
        presenter2.attachView(this);
		
        JButton back = UIHelpers.generateBackButton(50,50,50,50);
        panel.add(back);
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard d = new Dashboard(frame, false);
            }
        });
        
        
        panel.add(cbTableNames);
        panel.add(tbData);
        
        this.frame.setContentPane(panel);
		this.frame.revalidate();
	}

	@Override
	public void invalidQuery(String message) {
		JOptionPane.showMessageDialog(frame, message);
		
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void fillDropdownWithTemplateNames(List<String> templateNames) {
		cbTableNames.setModel(new DefaultComboBoxModel(templateNames.toArray()));
		cbTableNames.setBounds(400, 160, 200, 25);
	}
	
	@Override
	public void fillTableWithUserData(List<List<String>> data) {
//		String[][] data = new String[userData.getData().size()][];
//		String[] row = new String[0];
//		for(int i=0; i < data.length; i++) {
//		    data[i] = row;
//		}
//		TableModel tableModel = new DefaultTableModel(data, userData.getColumnNames().toArray());
//		tbData.setModel(tableModel);
	}

	@Override
	public void onSuccessTemplateCreated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFileValid(String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onErrorUploadingFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInCompatibleTemplateSelected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> fillDropdownWithTemplateNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
