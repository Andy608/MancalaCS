package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.csmancala.file.ResourceLoader;

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
		paintBackground(g2D);
	}
	
	private void paintBackground(Graphics2D g2D) {
		int bgWidth = ResourceLoader.woodenBackground.getWidth();
		int bgHeight = ResourceLoader.woodenBackground.getHeight();
		
		if (bgWidth < this.getWidth() && bgHeight < this.getHeight()) {
			g2D.drawImage(ResourceLoader.woodenBackground, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		else if (bgWidth < this.getWidth()) {
			g2D.drawImage(ResourceLoader.woodenBackground, 0, 0, this.getWidth(), bgHeight, null);
		}
		else if (bgHeight < this.getHeight()) {
			g2D.drawImage(ResourceLoader.woodenBackground, 0, 0, bgWidth, this.getHeight(), null);
		}
		else {
			g2D.drawImage(ResourceLoader.woodenBackground, 0, 0, bgWidth, bgHeight, null);
		}
	}
}
