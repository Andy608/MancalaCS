package com.csmancala.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MancalaPanel extends JPanel {

	private static final long serialVersionUID = 6954435685287527912L;

	public MancalaPanel() {
		super();
	}
	
	/**
	 * This method overrides the super.paint(g) method.
	 * It is called in the Mancala class every frame to force refresh the display.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
