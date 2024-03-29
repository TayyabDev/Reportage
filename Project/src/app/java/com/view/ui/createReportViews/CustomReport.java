package app.java.com.view.ui.createReportViews;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.presenter.CustomReportPresenterImpl;
import app.java.com.presenter.interfaces.CustomReportPresenter;
import app.java.com.view.interfaces.CustomReportView;
import app.java.com.view.ui.UIHelpers;

public class CustomReport implements CustomReportView {

	private JFrame frame;
	private JPanel panel; // main panel
	private JScrollPane scrollPane;

	private JPanel scrollPanel;
	private JPanel resultScrollPanel;

	private List<JCheckBox> attrsComboBoxes;
	private List<JCheckBox> results = new ArrayList<>();
	private CustomReportPresenter presenter;



	public CustomReport(JFrame frame, TeqAccount account) {
		/**
		 * TODO: -initialize dropdown with attributes -allow users to select multible attributes -
		 * date range by year
		 */
		this.frame = frame;
		panel = new JPanel();
		panel.setLayout(null);

		presenter = new CustomReportPresenterImpl();
		presenter.attachView(this);

		JButton back = UIHelpers.generateBackButton(0, 0, 50, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			    new Report(frame, account);
			}
		});

		JLabel lTitle = new JLabel("Create Custom Report");
		lTitle.setFont(new Font(null, Font.BOLD, 36));
		lTitle.setBounds(200, 20, 400, 40);
		panel.add(lTitle);
		
		scrollPanel = new JPanel(new GridLayout(0, 1));
		scrollPanel.setBorder(BorderFactory
				.createTitledBorder("Please select attributes. Format is Template -- Attribute"));


		attrsComboBoxes = new ArrayList<>();
		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setBounds(150, 70, 755, 290);

		presenter.fetchTemplatesAndAttributes();

		JTextField search = new JTextField();
		search.setBounds(150, 370, 375, 50);

		JButton buttonSearch = UIHelpers.buttonGenerator("\uD83D\uDD0E");
		buttonSearch.setBounds(530, 370, 50, 50);
		buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = search.getText();
				results = new ArrayList<>();
				if (query.length() > 0) {
					results = new ArrayList<>();
					resultScrollPanel = new JPanel(new GridLayout(0, 1));
					for (JCheckBox cb : attrsComboBoxes) {
						if (cb.getText().toLowerCase().contains(query.toLowerCase())) { // ignore if
																						// its upper
																						// case or
																						// lower
							results.add(cb);
							resultScrollPanel.add(cb);
						}
					}
					scrollPane.setViewportView(resultScrollPanel);
					scrollPane.revalidate();
				}
			}
		});


		JButton viewSel = new JButton("View Selection");
		viewSel.setBounds(585, 370, 120, 50);
		viewSel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				results = new ArrayList<>();
				resultScrollPanel = new JPanel(new GridLayout(0, 1));
				for (JCheckBox cb : attrsComboBoxes) {
					if (cb.isSelected()) { // check if selected
						results.add(cb);
						resultScrollPanel.add(cb);
					}
				}
				scrollPane.setViewportView(resultScrollPanel);
				scrollPane.revalidate();
			}
		});

		JButton clear = new JButton("Clear Search");
		clear.setBounds(705, 370, 100, 50);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search.setText("");
				for (JCheckBox cb : results) {
					scrollPanel.add(cb);

				}
				scrollPane.setViewportView(scrollPanel);
				scrollPane.revalidate();
			}
		});


		JButton reset = new JButton("Reset");
		reset.setBounds(805, 370, 100, 50);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// unselect all fields
				for (JCheckBox cb : attrsComboBoxes) {
					if (cb.isSelected()) {
						cb.setSelected(false);
					}
				}
				// clear search criteria
				clear.getActionListeners()[0].actionPerformed(e);
			}
		});



		JLabel fromText = new JLabel("From");
		fromText.setBounds(500, 470, 50, 20);
		JTextField from = new JTextField();
		from.setToolTipText("MM/YYYY");
		from.setBounds(500, 490, 50, 20);

		JLabel toText = new JLabel("To");
		toText.setBounds(570, 470, 50, 20);
		JTextField to = new JTextField();
		to.setToolTipText("MM/YYYY");
		to.setBounds(570, 490, 50, 20);


		JButton generate = UIHelpers.buttonGenerator("Generate");
		generate.setBounds(630, 470, 100, 50);
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> attrs = new ArrayList<>();
				for (JCheckBox cb : attrsComboBoxes) {
					if (cb.isSelected()) {
						attrs.add(cb.getText());
					}
				}
				presenter.createReport(attrs, from.getText(), to.getText());
			}
		});

		panel.add(scrollPane);
		panel.add(search);
		panel.add(buttonSearch);
		panel.add(clear);
		panel.add(from);
		panel.add(to);
		panel.add(fromText);
		panel.add(toText);
		panel.add(reset);
		panel.add((generate));
		panel.add(viewSel);
		this.frame.setContentPane(panel);
		this.frame.revalidate();

	}


	@Override
	public void onSuccessReportCreated() {
		JOptionPane.showMessageDialog(frame, "Report created!");

	}

	@Override
	public void onErrorCreatingReport() {
		JOptionPane.showMessageDialog(frame, "Error creating the report.");
	}

	@Override
	public boolean isFieldsValid(String date1, String date2) {
		boolean selection = false;
		int m1, y1, m2, y2;

		for (JCheckBox cb : attrsComboBoxes) {
			if (cb.isSelected()) {
				selection = true;
				break;
			}
		}
		try {
			m1 = Integer.parseInt(date1.split("/")[0]);
			y1 = Integer.parseInt(date1.split("/")[1]);
			m2 = Integer.parseInt(date2.split("/")[0]);
			y2 = Integer.parseInt(date2.split("/")[1]);
		} catch (Exception e) {
			return selection;
		}
		return selection && validDate(m1, y1) && validDate(m2, y2);

	}

	boolean validDate(int month, int year) {
		return month > 0 && year > 0;
	}

	@Override
	public void invalidFields() {
		JOptionPane.showMessageDialog(frame, "Please select valid fields.");

	}

	@Override
	public boolean sendReport(HashMap<String, List<List<String>>> data) {
		return UIHelpers.sendReportToCSVFile(data);
	}



	@Override
	public void fetchAttributes(List<String> attrs) {
		for (String attr : attrs) {
			JCheckBox cb = new JCheckBox(attr);
			attrsComboBoxes.add(cb);
			scrollPanel.add(cb);
		}
		scrollPanel.revalidate();
	}

	@Override
	public void errorFetchingAttributes() {
		JOptionPane.showMessageDialog(frame, "Error fetching attributes.");
	}
}
