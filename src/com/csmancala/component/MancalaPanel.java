package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.csmancala.core.RenderGraphics;

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
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintMancalaBoard(g2D);
	}
}
