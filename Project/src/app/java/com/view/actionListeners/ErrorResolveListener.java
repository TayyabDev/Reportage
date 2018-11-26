package app.java.com.view.actionListeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.presenter.interfaces.ResolveConflictPresenter;

public class ErrorResolveListener implements ActionListener {

	@SuppressWarnings("unused")
	private JFrame frame;
	private List<JRadioButton> errorOptionButtons;
	@SuppressWarnings("unused")
	private JPanel scrollPanel;
	private ResolveConflictPresenter presenter;
	private List<InsertException> errors;
	@SuppressWarnings("unused")
	private List<String> columns;

	public ErrorResolveListener(JFrame frame, List<JRadioButton> errorOptionButtons,
			JPanel scrollPanel, List<InsertException> errors, ResolveConflictPresenter presenter,
			List<String> columns) {
		this.frame = frame;
		this.errorOptionButtons = errorOptionButtons;
		this.scrollPanel = scrollPanel;
		this.presenter = presenter;
		this.errors = errors;
		this.columns = columns;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (errorOptionButtons != null) {
			int index = 0;
			for (JRadioButton errorOption : errorOptionButtons) {
				if (errorOption.isSelected()) {
					// locate the exception corresponding to the radio button
					InsertException insertException = errors.get(index);


					JScrollPane scrollPane = new JScrollPane();
					JPanel textFieldScrollPanel = new JPanel(new GridLayout(1, 0));

					JDialog dialog = new JDialog();
					dialog.setTitle("Resolve the Conflicts");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

					dialog.setBounds(300, 400, 900, 300);


					dialog.getContentPane().setLayout(new GridLayout(0, 1));

					dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

					List<JTextField> cols = new ArrayList<>();

					for (String col : insertException.getErrorValues()) {
						JTextField val = new JTextField(col);
						val.setBackground(Color.red);

						val.setPreferredSize(new Dimension(val.getText().length() + 5, 30));

						cols.add(val);
						textFieldScrollPanel.add(val);
					}
					textFieldScrollPanel.revalidate();

					scrollPane.setViewportView(textFieldScrollPanel);

					JButton done = new JButton("Done");

					dialog.add(done);
					done.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							List<String> correctValues = new ArrayList<>();

							for (JTextField col : cols) {
								correctValues.add(col.getText());
							}
							
							// remove message from list
							System.out.println("Removing the radioButton here!");
							errorOptionButtons.remove(errorOption);
							scrollPanel.remove(errorOption);
							scrollPanel.revalidate();

							presenter.attemptFixConflict(correctValues, insertException.getTable());

							dialog.dispose();

						}
					});

					dialog.setVisible(true);
				}
				index += 1;
			}
		}


	}
}
