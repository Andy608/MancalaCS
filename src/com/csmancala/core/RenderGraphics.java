package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;
import com.csmancala.util.TransformImage;

public class RenderGraphics {

	private static GamePanel gamePanel = Start.getMancala().getGamePanel();
	private static Random rand = new Random();
	
	public static void paintBackground(JPanel panel, Graphics2D g2D) {
		g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0,  panel.getWidth(), panel.getHeight(), null);
	}
	
	private static int startX;
	private static int startY;
	private static int scaledWidth;
	private static int scaledHeight;
	
	private static double multiplier;
	private static float maxMultiplier = 0.5f;
	
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
		
		BufferedImage scaledImage = TransformImage.scaleImage(ResourceLoader.MANCALA_BOARD, scaledWidth, scaledHeight, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		g2D.drawImage(scaledImage, startX, startY, null);
		updatePlayerNames();
		updateButtons();
		updateButtonText();
	}
	
	public static void updateMenuText(MainMenuPanel panel) {
		
		multiplier = (double)(panel.getWidth() / (double)1920);
		
		if (multiplier > maxMultiplier && ((double)panel.getHeight() / (double)panel.getWidth() <= 0.5)) {
			multiplier = maxMultiplier;
		}
		
		panel.mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, (int)(112 * multiplier)));
	}
	
	public static void updateButtons() {
		
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
		
		BufferedImage goalImage = TransformImage.scaleImage(ResourceLoader.GOAL_BACKGROUND, (int) (ResourceLoader.GOAL_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotImage = TransformImage.scaleImage(ResourceLoader.SLOT_BACKGROUND, (int) (ResourceLoader.SLOT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		BufferedImage goalHoveredImage = TransformImage.scaleImage(ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND, (int) (ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_HIGHLIGHT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotHoveredImage = TransformImage.scaleImage(ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND, (int) (ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_HIGHLIGHT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

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
				gamePanel.boardButtons[0][0].setIcon(goalHoveredIcon);
				gamePanel.boardButtons[0][0].setLocation((int)(startX + 60 - 7.5 * multiplier), (int)(startY + 50 - 7.5 * multiplier));
				gamePanel.boardButtons[0][0].setSize(goalHoveredSize);
			}
			else {
				gamePanel.boardButtons[0][0].setIcon(goalIcon);
				gamePanel.boardButtons[0][0].setLocation((int)(startX + 60 * multiplier), (int)(startY + 50 * multiplier));
				gamePanel.boardButtons[0][0].setSize(goalSize);
			}
		}
		else if (b.equals(gamePanel.boardButtons[7][1])) {
			if (hovered) {
				gamePanel.boardButtons[7][1].setIcon(goalHoveredIcon);
				gamePanel.boardButtons[7][1].setLocation((int)(startX + 1320 - 7.5 * multiplier), (int)(startY + 50 - 7.5 * multiplier));
				gamePanel.boardButtons[7][1].setSize(goalHoveredSize);
			}
			else {
				gamePanel.boardButtons[7][1].setIcon(goalIcon);
				gamePanel.boardButtons[7][1].setLocation((int)(startX + 1320 * multiplier), (int)(startY + 50 * multiplier));
				gamePanel.boardButtons[7][1].setSize(goalSize);
			}
		}
		
		for (int y = 0; y < 2; y++) {
			for (int x = 1; x < 7; x++) {
				if (b.equals(gamePanel.boardButtons[x][y])) {
					
					if (y == 0) {
						if (hovered) {
							gamePanel.boardButtons[x][y].setIcon(slotHoveredIcon);
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242 - 7.5) * multiplier), (int)(startY + ((50 - 7.5) * multiplier)));
							gamePanel.boardButtons[x][y].setSize(slotHoveredSize);
						}
						else {
							gamePanel.boardButtons[x][y].setIcon(slotIcon);
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242) * multiplier), (int)(startY + (50 * multiplier)));
							gamePanel.boardButtons[x][y].setSize(slotSize);
						}
					}
					else {
						if (hovered) {
							gamePanel.boardButtons[x][y].setIcon(slotHoveredIcon);
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242 - 7.5) * multiplier), (int)(startY + ((258 - 7.5) * multiplier)));
							gamePanel.boardButtons[x][y].setSize(slotHoveredSize);
						}
						else {
							gamePanel.boardButtons[x][y].setIcon(slotIcon);
							gamePanel.boardButtons[x][y].setLocation((int)(startX + (((x - 1) * 180) + 242) * multiplier), (int)(startY + 258 * multiplier));
							gamePanel.boardButtons[x][y].setSize(slotSize);
						}
					}
				}
			}
		}
	}
	
	private static void updatePlayerNames() {
		JLabel p1Name = gamePanel.player1Name;
		JLabel p2Name = gamePanel.player2Name;
		
		String stringOfName1 = Start.getMancala().getBoard().getPlayer1().getName();
		String stringOfName2 = Start.getMancala().getBoard().getPlayer2().getName();
		
		Player p1 = Start.getMancala().getBoard().getPlayer1();
		Player p2 = Start.getMancala().getBoard().getPlayer2();
		Player currentPlayer = Start.getMancala().getCurrentPlayer();
		
		if (p1 != null && !p1Name.equals(p1.getName())) {
			p1Name.setText(p1.getName());
		}
		
		if (p2 != null && !p2Name.equals(p2.getName())) {
			p2Name.setText(p2.getName());
		}
		
		if(currentPlayer.equals(p1)) {
			p1Name.setText("Your Turn: "+stringOfName1);
			p2Name.setText(stringOfName2);
			p1Name.setFont(new Font("Montserrat", Font.BOLD, (int)(72 * multiplier)));
			p2Name.setFont(new Font("Montserrat", Font.PLAIN, (int)(64 * multiplier)));
		}
		else if(currentPlayer.equals(p2)) {
			p1Name.setText(stringOfName1);
			p2Name.setText("Your Turn: "+stringOfName2);
			p1Name.setFont(new Font("Montserrat", Font.PLAIN, (int)(64 * multiplier)));
			p2Name.setFont(new Font("Montserrat", Font.BOLD, (int)(72 * multiplier)));
		}

		p1Name.setSize(new Dimension(p1Name.getFontMetrics(p1Name.getFont()).stringWidth(p1Name.getText()), p1Name.getFontMetrics(p1Name.getFont()).getHeight()));
		p1Name.setLocation(startX, startY - (int)(100 * multiplier));
		
		p2Name.setSize(new Dimension(p2Name.getFontMetrics(p2Name.getFont()).stringWidth(p2Name.getText()), p2Name.getFontMetrics(p2Name.getFont()).getHeight()));
		p2Name.setLocation(startX + scaledWidth - p2Name.getWidth(), (int)((startY + (70 * multiplier)) + scaledHeight));
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
	
	public static void paintForeground(GamePanel panel, Graphics2D g2d) {
		Board board = Start.getMancala().getBoard();
		
		for (int y = 0; y < panel.boardButtons[0].length; y++) {
			for (int x = 0; x < panel.boardButtons.length; x++) {
				JButton button = panel.boardButtons[x][y];
				if (button != null) {
					Slot slot = board.getSlotArray()[x][y];
					for (int i = 0; i < slot.getStones().size(); i++) {
						Stone currentStone = slot.getStones().get(i);
						if (currentStone != null) {
							BufferedImage stoneScaled = TransformImage.scaleImage(currentStone.getImage(), (int) (currentStone.getImage().getWidth() * multiplier), (int) (currentStone.getImage().getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
							BufferedImage finalStone = TransformImage.rotateImage(stoneScaled, Start.getMancala().getTicks() % 360);
							currentStone.setLocation(button.getX() + rand.nextInt(button.getWidth() - finalStone.getWidth()), button.getY() + rand.nextInt(button.getHeight() - finalStone.getHeight()));
							if (!(slot instanceof Goal)) {
								if (slot.isHovered) {
									g2d.drawImage(finalStone, currentStone.getLocation().x + (int)(7.5 * multiplier), currentStone.getLocation().y + (int)(7.5 * multiplier), null);
								}
								else {
									g2d.drawImage(finalStone, currentStone.getLocation().x, currentStone.getLocation().y, null);
								}
							}
							else {
								g2d.drawImage(finalStone, currentStone.getLocation().x, currentStone.getLocation().y, null);
							}
						}
					}
				}
			}
		}
	}
}
