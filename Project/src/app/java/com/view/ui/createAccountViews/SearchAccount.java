package app.java.com.view.ui.createAccountViews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.java.com.presenter.CreateReportPresenterImpl;
import app.java.com.presenter.SearchAccountPresenterImpl;
import app.java.com.presenter.interfaces.CreateAccountPresenter;
import app.java.com.presenter.interfaces.FetchUserDataPresenter;
import app.java.com.presenter.interfaces.SearchAccountPresenter;
import app.java.com.view.interfaces.CreateUserDataView;
import app.java.com.view.interfaces.SearchAccountView;
import app.java.com.view.ui.UIHelpers;

public class SearchAccount implements SearchAccountView{
	 private JPanel panel, scrollPanel;
	 private JTable tbData;
	 private JTextField txtAccount;
	 private JScrollPane scrollPane;
	 private static JFrame frame;
	 
	 private SearchAccountPresenter presenter;


    public SearchAccount(JFrame frame) {
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));
		
		JLabel lTitle = new JLabel("Search Account");
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
		
		presenter = new SearchAccountPresenterImpl();
    	presenter.attachView(this);
		
		tbData = new JTable();
		scrollPanel = new JPanel();
		scrollPanel.add(tbData);
		
		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setBounds(0,150,frame.getWidth(),frame.getHeight() - 190);
		panel.add(scrollPane);
		
		txtAccount = new JTextField("Search Account...");
		txtAccount.setBounds(120, 80, 200, 25);
		txtAccount.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        txtAccount.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		panel.add(txtAccount);
		
		JButton btnSearch = UIHelpers.buttonGenerator("Search");
		btnSearch.setBounds(750, 80, 100, 25);
		btnSearch.addActionListener(e -> {
			String query = "SELECT accountId, userName, accountType, registered FROM Account WHERE userName LIKE '%" + txtAccount.getText() + "%'";	
			presenter.searchAccount(query);
			panel.revalidate();
        });
		panel.add(btnSearch);
		
		this.frame.setContentPane(panel);
        this.frame.revalidate();
		
		
    }
    
	@SuppressWarnings("serial")
	@Override
	public void displayData(String report) {
		String[] rpt, columns;
		String[][] data;
		
		rpt = report.split("\n");
		columns = rpt[0].split(",");
		
		int numColumns = columns.length;
		int numRows = rpt.length - 1;
		
		data = new String[numRows+1][numColumns];
		data[0] = columns;
		for (int i = 0; i < numRows; i++) {
			data[i+1] = rpt[i+1].split(",");
		}
		tbData = new JTable(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
		//tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanel.removeAll();
        scrollPanel.add(tbData);
		scrollPane.revalidate();
		panel.revalidate();

	}

	@Override
	public void onSuccessSearchAccount() {
		JOptionPane.showMessageDialog(frame, "Successful search.");
		
	}

	@Override
	public void onErrorSearchAccount() {
		JOptionPane.showMessageDialog(frame, "Account not found");
	}

	@Override
	public boolean isFieldsValid(String query) {
		return true;
	}

	@Override
	public void invalidFields() {
		// nothing
	}


}
