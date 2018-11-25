package app.java.com.view.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.java.com.view.ui.createAccountViews.Login;

public class Run {

	public static void main(String[] args) {

		JFrame frame = new JFrame("TEQ Login");
		frame.add(new Login(frame).panel);
		frame.setPreferredSize(new Dimension(800, 400));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		UIHelpers.setLook();
	}
}
