package com.mycompany.myapp;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Assignment {
	
	public static void main(String[] arg) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		JFrame window = new JFrame(GUIPanel.NAME);
		window.setContentPane(new GUIPanel());
		window.setBounds((width - GUIPanel.WIDTH) / 2, (height - GUIPanel.HEIGHT) / 4, width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	} // main

} // Assignment
