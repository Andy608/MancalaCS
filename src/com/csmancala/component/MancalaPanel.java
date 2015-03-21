package com.csmancala.component;

import java.awt.Color;
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
		int bgWidth = ResourceLoader.woodenBackground.getWidth();
		int bgHeight = ResourceLoader.woodenBackground.getHeight();
		
		if (bgWidth < this.getWidth()) {
			for (int x = 0; x <= (this.getWidth() / bgWidth); x++) {
				if (bgHeight < this.getHeight()) {
					g2D.drawImage(ResourceLoader.woodenBackground, x * bgWidth, 0, bgWidth, this.getHeight(), null);
				}
				else {
					g2D.drawImage(ResourceLoader.woodenBackground, x * bgWidth, 0, bgWidth, bgHeight, null);
				}
			}
		}
		else {
			g2D.drawImage(ResourceLoader.woodenBackground, 0, 0, bgWidth, bgHeight, null);
		}
		
		g2D.setColor(Color.BLACK);
	}
}
