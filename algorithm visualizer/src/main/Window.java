package main;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	private Canvas canvas;
	
	public Window(String title, int width, int height) {
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getJFrame() {
		return frame;
	}

	
}