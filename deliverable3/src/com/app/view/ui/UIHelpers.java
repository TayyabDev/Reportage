package com.app.view.ui;

import java.awt.Color;

import javax.swing.JButton;

public class UIHelpers {
	protected static JButton buttonGenerator(String text) {
		JButton b = new JButton(text);
		b.setBackground(Color.decode("#fff8e1"));
		
		return b;
	}
}
