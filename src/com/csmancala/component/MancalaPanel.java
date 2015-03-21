package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MancalaPanel extends JPanel {

	private static final long serialVersionUID = 6954435685287527912L;

	public MancalaPanel() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
//		g2d.drawImage(img, op, x, y);
	}
	
}
