package com.app.view.ui;

import java.awt.Dimension;

import javax.swing.*;

public class Account {
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("TEQ Dashboard");
	        frame.add(new Dashboard().panel);
	        frame.setPreferredSize(new Dimension(1000, 600));
	        frame.pack();
	        frame.setVisible(true);
	        
	    }

}
