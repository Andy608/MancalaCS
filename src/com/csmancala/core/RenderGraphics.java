package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;

public class RenderGraphics {

	private static GamePanel gamePanel = Start.getMancala().getGamePanel();
	
	public static void paintBackground(JPanel panel, Graphics2D g2D) {
		g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0,  panel.getWidth(), panel.getHeight(), null);
	}
	
	private static int startX;
	private static int startY;
	private static int scaledWidth;
	private static int scaledHeight;
	
	private static double multiplier;
	private static float maxMultiplier = 0.7f;
	
	public static void paintMancalaBoard(Graphics2D g2D) {
//		paintMancalaBoardShadow(g2D);
		paintPlayerNames();
		
		multiplier = (double)(gamePanel.getWidth() / (double)1920);
	
		if (multiplier > maxMultiplier && ((double)gamePanel.getHeight() / (double)gamePanel.getWidth() <= 0.5)) {
			multiplier = maxMultiplier;
		}
		
		scaledWidth = (int) (ResourceLoader.MANCALA_BOARD.getWidth() * multiplier);
		scaledHeight = (int) (ResourceLoader.MANCALA_BOARD.getHeight() * multiplier);
		startX = (int)(gamePanel.getWidth() - scaledWidth) / 2;
		startY = (int)(gamePanel.getHeight() - scaledHeight) / 2;
		
		BufferedImage scaledImage = scaleImage(ResourceLoader.MANCALA_BOARD, scaledWidth, scaledHeight, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		g2D.drawImage(scaledImage, startX, startY, null);
		updateButtons();
		updateButtonText();
	}
	
	private static void updateButtons() {
		
		
		BufferedImage goalImage = scaleImage(ResourceLoader.GOAL_BACKGROUND, (int) (ResourceLoader.GOAL_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotImage = scaleImage(ResourceLoader.SLOT_BACKGROUND, (int) (ResourceLoader.SLOT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		Dimension goalSize = new Dimension(goalImage.getWidth(), goalImage.getHeight());
		Dimension slotSize = new Dimension(slotImage.getWidth(), slotImage.getHeight());
		
		ImageIcon goalIcon = new ImageIcon(goalImage);
		ImageIcon slotIcon = new ImageIcon(slotImage);
		
		Start.getMancala().getGamePanel().player1Name.setText(Start.getMancala().getBoard().getPlayer1().getName());
		Start.getMancala().getGamePanel().player2Name.setText(Start.getMancala().getBoard().getPlayer2().getName());
		
		gamePanel.player1Name.setLocation(startX + 350, startY - 70);
		gamePanel.player2Name.setLocation(startX + 350, startY + 370);
		
		gamePanel.boardButtons[0][0].setLocation((int)(startX + 60 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[1][0].setLocation((int)(startX + 242 * multiplier), (int)(startY + 50* multiplier));
		gamePanel.boardButtons[2][0].setLocation((int)(startX + 422 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[3][0].setLocation((int)(startX + 602 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[4][0].setLocation((int)(startX + 782 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[5][0].setLocation((int)(startX + 962 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[6][0].setLocation((int)(startX + 1142 * multiplier), (int)(startY + 50 * multiplier));
		
		gamePanel.boardButtons[7][1].setLocation((int)(startX + 1320 * multiplier), (int)(startY + 50 * multiplier));
		gamePanel.boardButtons[1][1].setLocation((int)(startX + 242 * multiplier), (int)(startY + 258 * multiplier));
		gamePanel.boardButtons[2][1].setLocation((int)(startX + 422 * multiplier), (int)(startY + 258 * multiplier));
		gamePanel.boardButtons[3][1].setLocation((int)(startX + 602 * multiplier), (int)(startY + 258 * multiplier));
		gamePanel.boardButtons[4][1].setLocation((int)(startX + 782 * multiplier), (int)(startY + 258 * multiplier));
		gamePanel.boardButtons[5][1].setLocation((int)(startX + 962 * multiplier), (int)(startY + 258 * multiplier));
		gamePanel.boardButtons[6][1].setLocation((int)(startX + 1142 * multiplier), (int)(startY + 258 * multiplier));
		
		
		gamePanel.boardButtons[0][0].setIcon(goalIcon);
		gamePanel.boardButtons[7][1].setIcon(goalIcon);
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				gamePanel.boardButtons[x][y].setIcon(slotIcon);
			}
		}
		
		gamePanel.boardButtons[0][0].setSize(goalSize);
		gamePanel.boardButtons[7][1].setSize(goalSize);
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				gamePanel.boardButtons[x][y].setSize(slotSize);
			}
		}
	}
	
	private static void paintPlayerNames() {
		
		if (Start.getMancala().getCurrentPlayer() != null && !Start.getMancala().getCurrentPlayer().getName().equals(Start.getMancala().getGamePanel().player1Name.getText())) {
			String name = Start.getMancala().getCurrentPlayer().getName();
			JLabel nameLabel = Start.getMancala().getGamePanel().player1Name;
			nameLabel.setText(name);
			nameLabel.setFont(new Font("Montserrat", Font.BOLD, 48));
			FontMetrics fm = nameLabel.getFontMetrics(nameLabel.getFont());
			nameLabel.setSize(new Dimension(fm.stringWidth(nameLabel.getText()), fm.getHeight()));
		}
	}
	
//	private static void paintMancalaBoardShadow(Graphics2D g2D) {
//		
//		startX = (int)(gamePanel.getWidth() - ResourceLoader.MANCALA_SHADOW.getWidth()) / 2;
//		startY = (int)(gamePanel.getHeight() - ResourceLoader.MANCALA_BOARD.getHeight()) / 2;
//
//		g2D.drawImage(ResourceLoader.MANCALA_SHADOW, startX, startY, null);
//	}
	
	public static void updateButtonText() {
		
		gamePanel.boardButtons[0][0].setText(Integer.toString(Start.getMancala().getBoard().getPlayer1().getGoal().getStoneAmount()));
		gamePanel.boardButtons[7][1].setText(Integer.toString(Start.getMancala().getBoard().getPlayer2().getGoal().getStoneAmount()));
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				gamePanel.boardButtons[x][y].setText(Integer.toString(Start.getMancala().getBoard().getSlotArray()[x][y].getStoneAmount()));
			}
		}
		
		if(Start.getMancala().getCurrentPlayer() == Start.getMancala().getBoard().getPlayer1()) {
			Start.getMancala().getGamePanel().player1Name.setFont(new Font("Montserrat", Font.BOLD, 48));
			Start.getMancala().getGamePanel().player2Name.setFont(new Font("Montserrat", Font.PLAIN, 48));
		}
		else if(Start.getMancala().getCurrentPlayer() == Start.getMancala().getBoard().getPlayer2()) {
			Start.getMancala().getGamePanel().player2Name.setFont(new Font("Montserrat", Font.BOLD, 48));
			Start.getMancala().getGamePanel().player1Name.setFont(new Font("Montserrat", Font.PLAIN, 48));
		}
	}
	
	//Thanks to http://scaleimagesjava.blogspot.com/2011/09/scale-images-in-java.html for the amazing scaling method.
	public static BufferedImage scaleImage(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		int w, h;
		
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			
			if (w < targetWidth) {
				w = targetWidth;
			}
			
			h = img.getHeight();
			
			if (h < targetHeight) {
				h = targetHeight;
			}
		}
		else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w >>= 1;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h >>= 1;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}
}
