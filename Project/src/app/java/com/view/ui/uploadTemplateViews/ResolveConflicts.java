package app.java.com.view.ui.uploadTemplateViews;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.entities.account.Account;
import app.java.com.presenter.ResolveConflictPresenterImpl;
import app.java.com.view.actionListeners.ErrorResolveListener;
import app.java.com.view.interfaces.ResolveConflictsView;
import app.java.com.view.ui.UIHelpers;

public class ResolveConflicts implements ResolveConflictsView {


	// when Uploading template failed
	// - getting a list of InsertException
	// - if ConflictException, print the message, create 'use new', 'use old' button
	// - if InvalidException, print the message, create text box(s) based on the exception, ask the
	// client to input the valid input.


	/**
	 * Show the column names Get the old row values using the exception
	 *
	 * List of Exceptions Mes 2 * Mes3
	 *
	 * List:
	 *
	 * Row 1: _val1__ _val2___ _val3___ __val4__ _conflicting__
	 *
	 *
	 *
	 */

	private JFrame frame;
	private JPanel panel;


	private String template;
	private List<String> columns;

	private List<InsertException> errors;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;


	private ButtonGroup bg;
	List<JRadioButton> errorOptionButtons;

	private ResolveConflictPresenterImpl presenter;


	public ResolveConflicts(JFrame frame, Account account, List<InsertException> errors) {
		System.out.println("errors are :");
		System.out.println(errors);

		this.frame = frame;

		panel = new JPanel();
		panel.setLayout(null);

		presenter = new ResolveConflictPresenterImpl();
		presenter.attachView(this);

		this.errors = errors;
		this.template = this.errors.get(0).getTable();

		errorOptionButtons = new ArrayList<>();


		presenter.fetchTemplateColumns(this.template);


		scrollPanel = new JPanel(new GridLayout(0, 1));
		scrollPanel.setBorder(BorderFactory.createTitledBorder("Please resolve the conflicts."));

		bg = new ButtonGroup();

		presenter.processDuplicateRowConflicts(errors);

		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setBounds(50, 0, 900, 450);

		JButton resolve = UIHelpers.buttonGenerator("Resolve");
		resolve.setBounds(450, 470, 100, 20);
		// resolve.addActionListener(new ErrorResolveListener(frame, errorOptionButtons,
		// scrollPanel, this.errors,
		// presenter, this.columns));
		resolve.addActionListener(new ErrorResolveListener(frame, errorOptionButtons, scrollPanel,
				errors, presenter, columns));


		panel.add(scrollPane);
		panel.add(resolve);

		this.frame.setContentPane(panel);
		this.frame.revalidate();
	}


	/**
	 * Populates the list of conflicts
	 * 
	 * @param errors
	 */
	public void getErrors(List<InsertException> errors) {
		for (InsertException exception : errors) {
			if (exception.getIsFixed()) {
				JLabel jl = new JLabel( "Automatically Fixed - Duplicate Row Found");
				scrollPanel.add(jl);
			} else {
				JRadioButton jrb = new JRadioButton(exception.getMessage());
				bg.add(jrb);
				System.out.println("Hey Why adding here2 ?");
				errorOptionButtons.add(jrb);
				scrollPanel.add(jrb);
			}
		}
		scrollPanel.revalidate();

		this.errors = errors;
	}

	@Override
	public boolean isFieldsValid(String update) {
		return update.length() > 0;
	}

	@Override
	public void onSuccessConflictFix() {
		// show message saying that fixed
		JOptionPane.showMessageDialog(frame, "Successfully fixed the error");		
	}

	@Override
	public void onErrorConflictFix(String error) {
		JOptionPane.showMessageDialog(frame, "You have a new error: " + error);
		// add new message to the list of errors
		JRadioButton jrb = new JRadioButton(error);
		bg.add(jrb);
		errorOptionButtons.add(jrb);
		System.out.println("Hey Why adding here 3?");
		scrollPanel.add(jrb);
		scrollPanel.revalidate();
	}

	@Override
	public void updateExceptions(List<InsertException> exceptions) {
		this.errors = exceptions;

	}

	@Override
	public void supplyTemplateColumns(List<String> columns) {
		this.columns = columns;
	}

	@Override
	public void errorSupplyingColumns(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}


}
