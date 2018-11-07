package app.java.com.view.ui.viewUserDataViews;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import app.java.com.model.CreateTemplateModelImpl;
import app.java.com.presenter.CreateTemplatePresenterImpl;
import app.java.com.presenter.FetchUserDataPresenterImpl;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.Dashboard;

public class UserData implements CreateUserDataView {

	private static JFrame frame;
	private JPanel panel;
	private JComboBox<String> cbTableNames;
	private JComboBox<String> cbTarget;
	private JComboBox<String> cbConstraint;
	private JTable tbData;
	private FetchUserDataPresenter presenter;
	
	public UserData(JFrame frame) {
		this.frame = frame;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));

        presenter = new FetchUserDataPresenterImpl(this);
        presenter.attachView(this);
		
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

	@Override
	public void invalidQuery(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayData(List<List<String>> data) {
		// TODO Auto-generated method stub
		
	}

}
