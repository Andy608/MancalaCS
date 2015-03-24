package com.csmancala.component;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.csmancala.run.Start;
import com.csmancala.util.MonitorSpecs;

/**
 * Feel free to change what you need.
 */
public class MancalaFrame extends JFrame {

	private static final long serialVersionUID = -6158257631658504764L;
	
	private static Dimension minimumSize = new Dimension(MonitorSpecs.getDisplay().getWidth() / 2, MonitorSpecs.getDisplay().getHeight() / 2);
	private static Dimension maximumSize = new Dimension(MonitorSpecs.getDisplay().getWidth(), MonitorSpecs.getDisplay().getHeight());

	public MancalaFrame(String title) {
		super.setTitle(title);
//		super.setMinimumSize(minimumSize);
//		super.setMaximumSize(maximumSize);
		super.setMinimumSize(new Dimension(1280, 720));
		
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListeners();
		super.pack();
		super.setVisible(true);
	}

	public void addWindowListeners() {
		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Start.getMancala().stop();
			}
		});

		super.addWindowFocusListener(new WindowAdapter() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("Focused!");
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("Unfocused! :(");
			}
		});
	}
}
