package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.component.RulesPanel;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;
import com.csmancala.util.TransformImage;

public class RenderGraphics {

	private static MainMenuPanel menuPanel = Start.getMancala().getMenuPanel();
	private static GamePanel gamePanel = Start.getMancala().getGamePanel();
	
	public static void paintBackground(JPanel panel, Graphics2D g2D) {
		g2D.drawImage(ResourceLoader.TABLE_BACKGROUND, 0, 0,  panel.getWidth(), panel.getHeight(), null);
	}
	
	private static int startX;
	private static int startY;
	private static int scaledWidth;
	private static int scaledHeight;
	
	public static double multiplier;
	private static float maxMultiplier = 0.5f;
	
	public static void paintMancalaBoard(Graphics2D g2D) {
		
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
	}
	
	public static void updateMenuText(MainMenuPanel panel, Graphics2D g2D) {
		
		multiplier = (double)(panel.getWidth() / (double)1920);
		
		if (multiplier > maxMultiplier && ((double)panel.getHeight() / (double)panel.getWidth() <= 0.5)) {
			multiplier = maxMultiplier;
		}
		
		BufferedImage mainLogo = TransformImage.scaleImage(ResourceLoader.MANCALA_LOGO, (int) (ResourceLoader.MANCALA_LOGO.getWidth() * multiplier), (int) (ResourceLoader.MANCALA_LOGO.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		g2D.drawImage(mainLogo, (panel.getWidth() - mainLogo.getWidth()) / 2, (int) ((panel.getHeight() - mainLogo.getHeight()) / 2 - (300 * multiplier)), null);
		
//		panel.mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, (int)(112 * multiplier)));
//		
//		//THIS WILL BE REPLACED WITH AN IMAGE. THIS IS TEMPORARY!
//		panel.mancalaLogo.setSize(500, 200);
//		
//		panel.mancalaLogo.setLocation((panel.getWidth() - panel.mancalaLogo.getWidth()) / 2, (int) ((panel.getHeight() - panel.mancalaLogo.getHeight()) / 2 - (250 * multiplier)));
	}
	
	public static void updateButtons() {
		
		if (Start.getMancala().getDisplayedPanel().equals(gamePanel)) {
			Slot[][] board = Start.getMancala().getBoard().getSlotArray();
			
			for (int y = 0; y < board[0].length; y++) {
				for (int x = 0; x < board.length; x++) {
					if (!(x == 0 && y == 1) && !(x == 7 && y == 0)) {
						setButtonProperties(gamePanel.boardButtons[x][y], board[x][y].isHovered());
					}
				}
			}
			updateButtonText();
		}
		else if (Start.getMancala().getDisplayedPanel().equals(menuPanel)) {
			
			for (int i = 0; i < menuPanel.menuButtons.length; i++) {
				updateMainMenuButtons(menuPanel.menuButtons[i], menuPanel.menuButtons[i].isHovered());
			}
		}
	}
	
	private static void setButtonProperties(MancalaButton b, boolean hovered) {
		
		BufferedImage goalImage = TransformImage.scaleImage(ResourceLoader.GOAL_BACKGROUND, (int) (ResourceLoader.GOAL_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotImage = TransformImage.scaleImage(ResourceLoader.SLOT_BACKGROUND, (int) (ResourceLoader.SLOT_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		BufferedImage goalHoveredImage = TransformImage.scaleImage(ResourceLoader.GOAL_HOVERED_BACKGROUND, (int) (ResourceLoader.GOAL_HOVERED_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.GOAL_HOVERED_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage slotHoveredImage = TransformImage.scaleImage(ResourceLoader.SLOT_HOVERED_BACKGROUND, (int) (ResourceLoader.SLOT_HOVERED_BACKGROUND.getWidth() * multiplier), (int) (ResourceLoader.SLOT_HOVERED_BACKGROUND.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

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
	
	public static void updateMainMenuButtons(MancalaButton b, boolean hovered) {
		
		BufferedImage playImage = TransformImage.scaleImage(ResourceLoader.PLAY_BUTTON, (int) (ResourceLoader.PLAY_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.PLAY_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage playHoveredImage = TransformImage.scaleImage(ResourceLoader.PLAY_HOVERED_BUTTON, (int) (ResourceLoader.PLAY_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.PLAY_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage rulesImage = TransformImage.scaleImage(ResourceLoader.RULES_BUTTON, (int) (ResourceLoader.RULES_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.RULES_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage rulesHoveredImage = TransformImage.scaleImage(ResourceLoader.RULES_HOVERED_BUTTON, (int) (ResourceLoader.RULES_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.RULES_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage creditsImage = TransformImage.scaleImage(ResourceLoader.CREDITS_BUTTON, (int) (ResourceLoader.CREDITS_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.CREDITS_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage creditsHoveredImage = TransformImage.scaleImage(ResourceLoader.CREDITS_HOVERED_BUTTON, (int) (ResourceLoader.CREDITS_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.CREDITS_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage quitImage = TransformImage.scaleImage(ResourceLoader.QUIT_BUTTON, (int) (ResourceLoader.QUIT_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.QUIT_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage quitHoveredImage = TransformImage.scaleImage(ResourceLoader.QUIT_HOVERED_BUTTON, (int) (ResourceLoader.QUIT_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.QUIT_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		Dimension buttonSize = new Dimension(playImage.getWidth(), playImage.getHeight());
		
		ImageIcon playIcon = new ImageIcon(playImage);
		ImageIcon playHoveredIcon = new ImageIcon(playHoveredImage);
		ImageIcon rulesIcon = new ImageIcon(rulesImage);
		ImageIcon rulesHoveredIcon = new ImageIcon(rulesHoveredImage);
		ImageIcon creditsIcon = new ImageIcon(creditsImage);
		ImageIcon creditsHoveredIcon = new ImageIcon(creditsHoveredImage);
		ImageIcon quitIcon = new ImageIcon(quitImage);
		ImageIcon quitHoveredIcon = new ImageIcon(quitHoveredImage);
		
		for (int i = 0; i < menuPanel.menuButtons.length; i++) {
			menuPanel.menuButtons[i].setSize(buttonSize);
		}
		
		if (b.equals(menuPanel.menuButtons[0])) {
			b.setLocation((int)((menuPanel.getWidth() - playImage.getWidth()) / 2), (int)(((menuPanel.getHeight() - playImage.getHeight()) / 2) - (100 * multiplier)));
			if (hovered) b.setIcon(playHoveredIcon);
			else b.setIcon(playIcon);
		}
		else if (b.equals(menuPanel.menuButtons[1])) {
			b.setLocation((int)((menuPanel.getWidth() - playImage.getWidth()) / 2), (int)(((menuPanel.getHeight() - playImage.getHeight()) / 2) + (50 * multiplier)));
			if (hovered) b.setIcon(rulesHoveredIcon);
			else b.setIcon(rulesIcon);
		}
		else if (b.equals(menuPanel.menuButtons[2])) {
			b.setLocation((int)((menuPanel.getWidth() - playImage.getWidth()) / 2), (int)(((menuPanel.getHeight() - playImage.getHeight()) / 2) + (200 * multiplier)));
			if (hovered) b.setIcon(creditsHoveredIcon);
			else b.setIcon(creditsIcon);
		}
		else if (b.equals(menuPanel.menuButtons[3])) {
			b.setLocation((int)((menuPanel.getWidth() - playImage.getWidth()) / 2), (int)(((menuPanel.getHeight() - playImage.getHeight()) / 2) + (350 * multiplier)));
			if (hovered) b.setIcon(quitHoveredIcon);
			else b.setIcon(quitIcon);
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
		
		if (currentPlayer.equals(p1)) {
			p1Name.setText("Your Turn: " + stringOfName1);
			p2Name.setText(stringOfName2);
			p1Name.setFont(new Font("Montserrat", Font.BOLD, (int)(72 * multiplier)));
			p2Name.setFont(new Font("Montserrat", Font.PLAIN, (int)(64 * multiplier)));
		}
		else if (currentPlayer.equals(p2)) {
			p1Name.setText(stringOfName1);
			p2Name.setText("Your Turn: " + stringOfName2);
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
				
				MancalaButton button = panel.boardButtons[x][y];
				
				if (button != null) {
					
					Slot slot = board.getSlotArray()[x][y];
					
					for (int i = 0; i < slot.getStones().size(); i++) {
						
						Stone currentStone = slot.getStones().get(i);
						
						if (currentStone != null) {
							BufferedImage stoneScaled = TransformImage.scaleImage(currentStone.getImage(), (int) (currentStone.getImage().getWidth() * multiplier), (int) (currentStone.getImage().getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
//							currentStone.setImage(stoneScaled);
							
							if (currentStone.getLocation() == null) {
								currentStone.updateStone(x, y);
							}
							
							currentStone.setLocation((int)(button.getX() + currentStone.getOffset().x), (int)(button.getY() + currentStone.getOffset().y));
							
							if (slot.isHovered()) {
								g2d.drawImage(stoneScaled, (int)(currentStone.getLocation().x + 7.5 * multiplier), (int)(currentStone.getLocation().y + 7.5 * multiplier), null);
							}
							else {
								g2d.drawImage(stoneScaled, currentStone.getLocation().x, currentStone.getLocation().y, null);
							}
						}
					}
				}
			}
		}
	}

	public static void paintRules(RulesPanel panel, Graphics2D g2d) {
		
		multiplier = (double)(panel.getWidth() / (double)1920);
		
		if (multiplier > maxMultiplier && ((double)panel.getHeight() / (double)panel.getWidth() <= 0.5)) {
			multiplier = maxMultiplier;
		}
		
		BufferedImage scaledInstructions = TransformImage.scaleImage(ResourceLoader.MANCALA_INSTRUCTIONS, (int) (ResourceLoader.MANCALA_INSTRUCTIONS.getWidth() * multiplier * 1.3), (int) (ResourceLoader.MANCALA_INSTRUCTIONS.getHeight() * multiplier * 1.3), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		g2d.drawImage(scaledInstructions, (panel.getWidth() - scaledInstructions.getWidth()) / 2, (panel.getHeight() - scaledInstructions.getHeight()) / 2, null);
		panel.mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, (int)(112 * multiplier)));
		
		//THIS WILL BE REPLACED WITH AN IMAGE. THIS IS TEMPORARY!
		panel.mancalaLogo.setSize(500, 200);
		
		panel.mancalaLogo.setLocation((panel.getWidth() - panel.mancalaLogo.getWidth()) / 2, (int) ((panel.getHeight() - panel.mancalaLogo.getHeight()) / 2 - (400 * multiplier)));
		
		BufferedImage returnToMenuImage = TransformImage.scaleImage(ResourceLoader.RETURN_TO_MENU_BUTTON, (int) (ResourceLoader.RETURN_TO_MENU_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.RETURN_TO_MENU_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		BufferedImage returnToMenuHoveredImage = TransformImage.scaleImage(ResourceLoader.RETURN_TO_MENU_HOVERED_BUTTON, (int) (ResourceLoader.RETURN_TO_MENU_HOVERED_BUTTON.getWidth() * multiplier), (int) (ResourceLoader.RETURN_TO_MENU_HOVERED_BUTTON.getHeight() * multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		Dimension buttonSize = new Dimension(returnToMenuImage.getWidth(), returnToMenuImage.getHeight());
		
		ImageIcon returnToMenuIcon = new ImageIcon(returnToMenuImage);
		ImageIcon returnToMenuHoveredIcon = new ImageIcon(returnToMenuHoveredImage);
		
		panel.returnButton.setSize(buttonSize);
		
		panel.returnButton.setLocation((int)((panel.getWidth() - returnToMenuImage.getWidth()) / 6), (int)(5 * ((panel.getHeight() - returnToMenuImage.getHeight()) / 6) + (25 * multiplier)));
		if (panel.returnButton.isHovered()) panel.returnButton.setIcon(returnToMenuHoveredIcon);
		else panel.returnButton.setIcon(returnToMenuIcon);
	}
}
