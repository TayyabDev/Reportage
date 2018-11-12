package app.java.com.view.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public static JButton generateBackButton(int x, int y, int width, int height){
		JButton back = buttonGenerator("←");
		back.setBounds(x,y,width,height);
		return back;
	}
}
