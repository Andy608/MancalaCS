package com.csmancala.core;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.csmancala.component.MancalaPanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;

public class RenderGraphics {

	private static MancalaPanel mainPanel = Start.getMancala().getMainPanel();
	
	private static int backgroundWidth = ResourceLoader.TABLE_BACKGROUND.getWidth();
	private static int backgroundHeight = ResourceLoader.TABLE_BACKGROUND.getHeight();
	public static void paintBackground(JPanel panel, Graphics2D g2D) {
		
		if (backgroundWidth < panel.getWidth() && backgroundHeight < panel.getHeight()) {
			g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0, panel.getWidth(), panel.getHeight(), null);
		}
		else if (backgroundWidth < panel.getWidth()) {
			g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0, panel.getWidth(), backgroundHeight, null);
		}
		else if (backgroundHeight < panel.getHeight()) {
			g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0, backgroundWidth, panel.getHeight(), null);
		}
		else {
			g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0, backgroundWidth, backgroundHeight, null);
		}
	}
	
	private static int startX;
	private static int startY;
	private static int boardWidth = ResourceLoader.MANCALA_BOARD.getWidth();
	private static int boardHeight = ResourceLoader.MANCALA_BOARD.getHeight();
	
	public static void paintMancalaBoard(Graphics2D g2D) {
		paintMancalaBoardShadow(g2D);
		
		startX = (mainPanel.getWidth() - boardWidth) / 2;
		startY = (mainPanel.getHeight() - boardHeight) / 2;
		
		g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, null);
	}
	
	private static int boardShadowWidth = ResourceLoader.MANCALA_SHADOW.getWidth();
	
	private static void paintMancalaBoardShadow(Graphics2D g2D) {
		
		startX = (mainPanel.getWidth() - boardShadowWidth) / 2;
		startY = (mainPanel.getHeight() - boardHeight) / 2;
		
		g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, null);
	}
}
