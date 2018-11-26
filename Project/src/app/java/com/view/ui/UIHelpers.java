package app.java.com.view.ui;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
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

	public static JButton generateBackButton(int x, int y, int width, int height) {
		JButton back = buttonGenerator("←");
		back.setBounds(x, y, width, height);
		return back;
	}

	public static boolean sendReportToCSVFile(HashMap<String, List<List<String>>> data) {
		String output = "";

		for (String table : data.keySet()) {
			output += table + "\n";

			for (List<String> lis : data.get(table)) {
				for (String currentIndex : lis) {
					if (currentIndex == null) {
						output += ",";
					} else if (currentIndex.contains(",")) {
						output += "\"" + currentIndex + "\",";
					}

					else {
						output += currentIndex + ",";
					}
				}
				output += "\n";
			}
		}

		// get filename
		String userHomeFolder = System.getProperty("user.home")+ "/Desktop";
		String file =
				"report-" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".csv";
		File textFile = new File(userHomeFolder, file);
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(output);
			out.close();
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;


	}
}
