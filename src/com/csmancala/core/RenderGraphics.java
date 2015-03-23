package com.csmancala.core;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;

public class RenderGraphics {

	private static GamePanel mainPanel = Start.getMancala().getMainPanel();
	
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
	private static double boardRatio = (double)boardWidth / (double)boardHeight;
	private static double screenImageRatio = (double)1920 / (double)boardWidth;
	
	private static double scaledX, maxScaledX;
	private static double scaledY, maxScaledY;
	
	public static void paintMancalaBoard(Graphics2D g2D) {
		paintMancalaBoardShadow(g2D);
		
		scaledX = (double)mainPanel.getWidth() / (double)screenImageRatio;
		scaledY = (double)scaledX / (double)boardRatio;
		
		if (scaledY < mainPanel.getHeight() * 0.8f && scaledX < mainPanel.getWidth() * 0.8f) {
			startX = (int)(mainPanel.getWidth() - scaledX) / 2;
			startY = (int)(mainPanel.getHeight() - scaledY) / 2;
			
			g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, (int)scaledX, (int)scaledY, null);
			maxScaledX = scaledX;
			maxScaledY = scaledY;
		}
		else {
			startX = (int)(mainPanel.getWidth() - maxScaledX) / 2;
			startY = (int)(mainPanel.getHeight() - maxScaledY) / 2;
			g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, (int)maxScaledX, (int)maxScaledY, null);
		}
	}
	
	private static double shadowRatio = (double)boardWidth / (double)ResourceLoader.MANCALA_SHADOW.getHeight();
	private static double maxScaledHeight;
	
	private static void paintMancalaBoardShadow(Graphics2D g2D) {
		
		scaledX = (double)mainPanel.getWidth() / (double)screenImageRatio;
		scaledY = (double)scaledX / (double)boardRatio;
		
		if (scaledY < mainPanel.getHeight() * 0.8f && scaledX < mainPanel.getWidth() * 0.8f) {
			startX = (int)(mainPanel.getWidth() - scaledX) / 2;
			startY = (int)(mainPanel.getHeight() - scaledY) / 2;
			
			maxScaledY = scaledY;
			scaledY = (double)scaledX / (double)shadowRatio;
			
			g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, (int)scaledX, (int)scaledY, null);
			maxScaledX = scaledX;
			maxScaledHeight = scaledY;
		}
		else {
			startX = (int)(mainPanel.getWidth() - maxScaledX) / 2;
			startY = (int)(mainPanel.getHeight() - maxScaledY) / 2;
			g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, (int)maxScaledX, (int)maxScaledHeight, null);
		}
	}
}
