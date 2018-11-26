package app.java.com.view.ui.createTemplateViews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import app.java.com.model.entities.account.Account;
import app.java.com.model.entities.account.AccountTypeFinder;
import app.java.com.model.entities.account.AgencyAccount;
import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.ExistingTemplatePresenterImpl;
import app.java.com.presenter.interfaces.ExistingTemplatePresenter;
import app.java.com.view.interfaces.ExistingTemplateView;
import app.java.com.view.ui.UIHelpers;
import app.java.com.view.ui.createAccountViews.AgencyDashboard;

public class ExistingTemplate implements ExistingTemplateView {

	private JFrame frame;
	private JPanel panel;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTemplates;
	private JTextField tfTarget;
	private JTextField tfConstraint;
	private JTable tbData;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;



	private ExistingTemplatePresenter presenter;

	@SuppressWarnings("rawtypes")
	public ExistingTemplate(JFrame frame, Account account) {

		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#f1f8e9"));

		presenter = new ExistingTemplatePresenterImpl();
		presenter.attachView(this);

		cbTemplates = new JComboBox();
		cbTemplates.setBounds(120, 80, 200, 25);
		panel.add(cbTemplates);

		presenter.fetchTemplateNames();

		JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (AccountTypeFinder.isTeqAccount(account)) {
					new CreateTemplate(frame, ((TeqAccount) account));
				} else {
					new AgencyDashboard(frame, false, ((AgencyAccount) account));
				}
				presenter.unbindView();
			}
		});

		JLabel lTitle = new JLabel("View Template");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(390, 20, 400, 40);
		panel.add(lTitle);



		tbData = new JTable();
		scrollPanel = new JPanel();
		scrollPanel.add(tbData);

		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setBounds(0, 150, frame.getWidth() - 15, frame.getHeight() - 190);
		panel.add(scrollPane);



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



		lTarget.setVisible(false);
		tfTarget.setVisible(false);
		lConstraint.setVisible(false);
		tfConstraint.setVisible(false);

		JButton btnSelect = UIHelpers.buttonGenerator("Select");
		btnSelect.setBounds(750, 80, 100, 25);
		btnSelect.addActionListener(e -> {
			String templateName = (String) cbTemplates.getSelectedItem();
			presenter.fetchTemplateColumns(templateName);
			System.out.println(templateName);
			panel.revalidate();
		});
		panel.add(btnSelect);

		this.frame.setContentPane(panel);
		this.frame.revalidate();
	}



	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void fillDropDownWithTemplates(List<String> templateNames) {
		System.out.println(templateNames);
		cbTemplates.setModel(new DefaultComboBoxModel(templateNames.toArray()));

	}

	@Override
	public void onErrorFetchingTemplates() {
		JOptionPane.showMessageDialog(frame, "Error getting the template names.");
	}

	@SuppressWarnings("serial")
	@Override
	public void displayData(List<String> columns) {

		String[][] dataArray = new String[1][1];
		dataArray[0] = columns.toArray(dataArray[0]);

		tbData = new JTable(dataArray, columns.toArray()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanel.removeAll();
		scrollPanel.add(tbData);
		scrollPane.revalidate();
		panel.revalidate();

	}



	@Override
	public void onErrorSelectTemplate(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
}
