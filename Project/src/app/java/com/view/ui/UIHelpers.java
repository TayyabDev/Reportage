package app.java.com.view.ui;

import java.awt.Color;

import javax.swing.*;

public class UIHelpers {
	public static JButton buttonGenerator(String text) {
		JButton b = new JButton(text);
		b.setBackground(Color.decode("#fff8e1"));

		return b;
	}

	public static void setLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
