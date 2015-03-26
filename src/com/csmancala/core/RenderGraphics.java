package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
		updatePlayerNames();
		updateButtons();
		updateButtonText();
	}
	
	private static void updateButtons() {
		
		Slot[][] board = Start.getMancala().getBoard().getSlotArray();
		
		for (int y = 0; y < board[0].length; y++) {
			for (int x = 0; x < board.length; x++) {
				if (!(x == 0 && y == 1) && !(x == 7 && y == 0)) {
					if (board[x][y].isHovered) setButtonProperties(gamePanel.boardButtons[x][y], true);
					else setButtonProperties(gamePanel.boardButtons[x][y], false);
				}
			}
		}
	}
	
	private static void setButtonProperties(JButton b, boolean hovered) {
		
		BufferedImage goalImage = scaleImage(ResourceLoader.GOAL_BACKGROUND, (int) (ResourceLoader.GOAL_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotImage = scaleImage(ResourceLoader.SLOT_BACKGROUND, (int) (ResourceLoader.SLOT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		BufferedImage goalHoveredImage = scaleImage(ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND, (int) (ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotHoveredImage = scaleImage(ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND, (int) (ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

		Dimension goalSize = new Dimension(goalImage.getWidth(), goalImage.getHeight());
		Dimension slotSize = new Dimension(slotImage.getWidth(), slotImage.getHeight());
		
		Dimension goalHoveredSize = new Dimension(goalHoveredImage.getWidth(), goalHoveredImage.getHeight());
		Dimension slotHoveredSize = new Dimension(slotHoveredImage.getWidth(), slotHoveredImage.getHeight());
		
		ImageIcon goalIcon = new ImageIcon(goalImage);
		ImageIcon slotIcon = new ImageIcon(slotImage);
		
		ImageIcon goalHoveredIcon = new ImageIcon(goalHoveredImage);
		ImageIcon slotHoveredIcon = new ImageIcon(slotHoveredImage);
		
		if (b.equals(gamePanel.boardButtons[0][0])) {
			if (hovered) {
				gamePanel.boardButtons[0][0].setLocation((int)(startX + 60 - 7.5 * multiplier), (int)(startY + 50 - 7.5 * multiplier));
				gamePanel.boardButtons[0][0].setIcon(goalHoveredIcon);
				gamePanel.boardButtons[0][0].setSize(goalHoveredSize);
			}
			else {
				gamePanel.boardButtons[0][0].setLocation((int)(startX + 60 * multiplier), (int)(startY + 50 * multiplier));
				gamePanel.boardButtons[0][0].setIcon(goalIcon);
				gamePanel.boardButtons[0][0].setSize(goalSize);
			}
		}
		else if (b.equals(gamePanel.boardButtons[7][1])) {
			if (hovered) {
				gamePanel.boardButtons[7][1].setLocation((int)(startX + 1320 - 7.5 * multiplier), (int)(startY + 50 - 7.5 * multiplier));
				gamePanel.boardButtons[7][1].setIcon(goalHoveredIcon);
				gamePanel.boardButtons[7][1].setSize(goalHoveredSize);
			}
			else {
				gamePanel.boardButtons[7][1].setLocation((int)(startX + 1320 * multiplier), (int)(startY + 50 * multiplier));
				gamePanel.boardButtons[7][1].setIcon(goalIcon);
				gamePanel.boardButtons[7][1].setSize(goalSize);
			}
		}
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				if (b.equals(gamePanel.boardButtons[x][y])) {
					
					if (y == 0) {
						if (hovered) {
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242 - 7.5) * multiplier), (int)(startY + ((50 - 7.5) * multiplier)));
							gamePanel.boardButtons[x][y].setIcon(slotHoveredIcon);
							gamePanel.boardButtons[x][y].setSize(slotHoveredSize);
						}
						else {
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242) * multiplier), (int)(startY + (50 * multiplier)));
							gamePanel.boardButtons[x][y].setIcon(slotIcon);
							gamePanel.boardButtons[x][y].setSize(slotSize);
						}
					}
					else {
						if (hovered) {
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242 - 7.5) * multiplier), (int)(startY + ((258 - 7.5) * multiplier)));
							gamePanel.boardButtons[x][y].setIcon(slotHoveredIcon);
							gamePanel.boardButtons[x][y].setSize(slotHoveredSize);
						}
						else {
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242) * multiplier), (int)(startY + 258 * multiplier));
							gamePanel.boardButtons[x][y].setIcon(slotIcon);
							gamePanel.boardButtons[x][y].setSize(slotSize);
						}
					}
				}
			}
		}
	}
	
	private static void updatePlayerNames() {
		JLabel p1Name = gamePanel.player1Name;
		JLabel p2Name = gamePanel.player1Name;
		Player p1 = Start.getMancala().getBoard().getPlayer1();
		Player p2 = Start.getMancala().getBoard().getPlayer2();
		Player currentPlayer = Start.getMancala().getCurrentPlayer();
		if (p1 != null && !p1Name.equals(p1.getName())) {
			p1Name.setText(p1.getName());
			int style = Font.PLAIN;
			if (p1Name.equals(currentPlayer.getName())) {
				style = Font.BOLD;
			}
			p1Name.setFont(new Font(p1Name.getText(), style, (int)(72 * multiplier)));
		}
		p1Name.setSize(new Dimension(p1Name.getFontMetrics(p1Name.getFont()).stringWidth(p1Name.getText()), p1Name.getFontMetrics(p1Name.getFont()).getHeight()));
		p1Name.setLocation(startX, startY - (int)(70 * multiplier));
		
		if (p2 != null && !p2Name.equals(p2.getName())) {
			p2Name.setText(p2.getName());
			int style = Font.PLAIN;
			if (p2Name.equals(currentPlayer.getName())) {
				style = Font.BOLD;
			}
			p2Name.setFont(new Font(p2Name.getText(), style, (int)(72 * multiplier)));
		}
		p2Name.setSize(new Dimension(p2Name.getFontMetrics(p2Name.getFont()).stringWidth(p2Name.getText()), p2Name.getFontMetrics(p2Name.getFont()).getHeight()));
		p2Name.setLocation(startX + scaledWidth - p2Name.getWidth(), startY + scaledHeight + (int)(70 * multiplier));
		
	}
	
	public static void updateButtonText() {
		
		gamePanel.boardButtons[0][0].setText(Integer.toString(Start.getMancala().getBoard().getPlayer1().getGoal().getStoneAmount()));
		gamePanel.boardButtons[7][1].setText(Integer.toString(Start.getMancala().getBoard().getPlayer2().getGoal().getStoneAmount()));
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				gamePanel.boardButtons[x][y].setText(Integer.toString(Start.getMancala().getBoard().getSlotArray()[x][y].getStoneAmount()));
			}
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
