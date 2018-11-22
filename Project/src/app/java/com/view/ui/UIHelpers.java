package app.java.com.view.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

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

	public static void resizeTable(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			if(width > 300)
				width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}
