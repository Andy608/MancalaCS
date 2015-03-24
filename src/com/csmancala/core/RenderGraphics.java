package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;

public class RenderGraphics {

	private static GamePanel gamePanel = Start.getMancala().getGamePanel();
	
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
//	private static int boardWidth = ResourceLoader.MANCALA_BOARD.getWidth();
//	private static int boardHeight = ResourceLoader.MANCALA_BOARD.getHeight();
//	private static double boardRatio = (double)boardWidth / (double)boardHeight;
//	private static double screenImageRatio = (double)1920 / (double)boardWidth;
//	
//	private static double scaledX, maxScaledX;
//	private static double scaledY, maxScaledY;
	
	public static void paintMancalaBoard(Graphics2D g2D) {
		paintMancalaBoardShadow(g2D);
		
//		scaledX = (double)gamePanel.getWidth() / (double)screenImageRatio;
//		scaledY = (double)scaledX / (double)boardRatio;
		
//		if (scaledY < gamePanel.getHeight() * 0.9f && scaledX < gamePanel.getWidth() * 0.9f) {
			startX = (int)(gamePanel.getWidth() - ResourceLoader.MANCALA_BOARD.getWidth()) / 2;
			startY = (int)(gamePanel.getHeight() - ResourceLoader.MANCALA_SHADOW.getHeight()) / 2;
			
			Start.getMancala().getGamePanel().player1Goal.setLocation(startX + 33, startY + 33);
			Start.getMancala().getGamePanel().topSlot1.setLocation(startX + 155, startY + 33);
			Start.getMancala().getGamePanel().topSlot2.setLocation(startX + 275, startY + 33);
			Start.getMancala().getGamePanel().topSlot3.setLocation(startX + 395, startY + 33);
			Start.getMancala().getGamePanel().topSlot4.setLocation(startX + 515, startY + 33);
			Start.getMancala().getGamePanel().topSlot5.setLocation(startX + 635, startY + 33);
			Start.getMancala().getGamePanel().topSlot6.setLocation(startX + 754, startY + 33);
			
			Start.getMancala().getGamePanel().player2Goal.setLocation(startX + 873, startY + 33);
			Start.getMancala().getGamePanel().bottomSlot1.setLocation(startX + 155, startY + 172);
			Start.getMancala().getGamePanel().bottomSlot2.setLocation(startX + 275, startY + 172);
			Start.getMancala().getGamePanel().bottomSlot3.setLocation(startX + 395, startY + 172);
			Start.getMancala().getGamePanel().bottomSlot4.setLocation(startX + 515, startY + 172);
			Start.getMancala().getGamePanel().bottomSlot5.setLocation(startX + 635, startY + 172);
			Start.getMancala().getGamePanel().bottomSlot6.setLocation(startX + 754, startY + 172);
			
//			g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, (int)scaledX, (int)scaledY, null);
//			maxScaledX = scaledX;
//			maxScaledY = scaledY;
//		}
//		else {
//			startX = (int)(gamePanel.getWidth() - maxScaledX) / 2;
//			startY = (int)(gamePanel.getHeight() - maxScaledY) / 2;
//			g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, (int)maxScaledX, (int)maxScaledY, null);
			g2D.drawImage(ResourceLoader.MANCALA_BOARD, startX, startY, null);
//		}
		
//		paintMancalaSlots();
	}
	
//	private static double shadowRatio = (double)boardWidth / (double)ResourceLoader.MANCALA_SHADOW.getHeight();
//	private static double maxScaledHeight;
	
	private static void paintMancalaBoardShadow(Graphics2D g2D) {
		
//		scaledX = (double)gamePanel.getWidth() / (double)screenImageRatio;
//		scaledY = (double)scaledX / (double)boardRatio;
//		
//		if (scaledY < gamePanel.getHeight() * 0.8f && scaledX < gamePanel.getWidth() * 0.8f) {
			startX = (int)(gamePanel.getWidth() - ResourceLoader.MANCALA_SHADOW.getWidth()) / 2;
			startY = (int)(gamePanel.getHeight() - ResourceLoader.MANCALA_BOARD.getHeight()) / 2;
//			
//			maxScaledY = scaledY;
//			scaledY = (double)scaledX / (double)shadowRatio;
//			
//			g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, (int)scaledX, (int)scaledY, null);
//			maxScaledX = scaledX;
//			maxScaledHeight = scaledY;
//		}
//		else {
//			startX = (int)(gamePanel.getWidth() - maxScaledX) / 2;
//			startY = (int)(gamePanel.getHeight() - maxScaledY) / 2;
//			g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, (int)maxScaledX, (int)maxScaledHeight, null);
		g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, null);
//		}
	}
	
//	private static double buttonImageRatio = (double)1920 / (double)ResourceLoader.GOAL_BACKGROUND.getWidth();
//	private static double buttonRatio = (double)ResourceLoader.GOAL_BACKGROUND.getWidth() / (double)ResourceLoader.GOAL_BACKGROUND.getHeight();
//	
//	private static double slotImageRatio = (double)1920 / (double)ResourceLoader.SLOT_BACKGROUND.getWidth();
//	private static double slotRatio = (double)ResourceLoader.SLOT_BACKGROUND.getWidth() / (double)ResourceLoader.SLOT_BACKGROUND.getHeight();
	
	public static void paintMancalaSlots() {
		
//		scaledX = (double)gamePanel.getWidth() / (double)buttonImageRatio;
//		scaledY = (double)scaledX / (double)buttonRatio;
		
		//Player1's Goal
//		BufferedImage scaledImage = scaleImage(ResourceLoader.GOAL_BACKGROUND, scaledX / ResourceLoader.GOAL_BACKGROUND.getWidth(), scaledY / ResourceLoader.GOAL_BACKGROUND.getHeight());
//		Start.getMancala().getGamePanel().player1Goal.setSize(scaledImage.getWidth(), scaledImage.getHeight());
		
//		Start.getMancala().getGamePanel().player1Goal.setIcon(new ImageIcon(scaledImage));
//		Start.getMancala().getGamePanel().player1Goal.setBounds(startX + 39, startY + 32, ResourceLoader.GOAL_BACKGROUND.getWidth(), ResourceLoader.GOAL_BACKGROUND.getHeight());
		
		//Player2's Goal
//		scaledImage = scaleImage(ResourceLoader.GOAL_BACKGROUND, scaledX / ResourceLoader.GOAL_BACKGROUND.getWidth(), scaledY / ResourceLoader.GOAL_BACKGROUND.getHeight());
//		Start.getMancala().getGamePanel().player2Goal.setIcon(new ImageIcon(scaledImage));
//		Start.getMancala().getGamePanel().player1Goal.setLocation(startX + 875, startY + 32);
		
		
//		scaledX = (double)gamePanel.getWidth() / (double)slotImageRatio;
//		scaledY = (double)scaledX / (double)slotRatio;
//		
//		//Player1 Slot 1
//		scaledImage = scaleImage(ResourceLoader.GOAL_BACKGROUND, scaledX / ResourceLoader.SLOT_BACKGROUND.getWidth(), scaledY / ResourceLoader.SLOT_BACKGROUND.getHeight());
//		Start.getMancala().getGamePanel().topSlot1.setIcon(new ImageIcon(scaledImage));
		Start.getMancala().getGamePanel().player2Goal.setLocation(startX + 182, startY + 32);
//		Start.getMancala().getGamePanel().topSlot1.setBounds(startX + 182, startY + 32, scaledImage.getWidth(), scaledImage.getHeight());
		
		
		
	}
	
//	private static BufferedImage scaleImage(BufferedImage image, double scaledX, double scaledY) {
//		
//		BufferedImage after = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		AffineTransform at = new AffineTransform();
//		
//		at.scale(scaledX, scaledY);
//		
//		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
//		return scaleOp.filter(image, after);
//	}
}
