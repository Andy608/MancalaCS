package com.csmancala.component;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.core.Board;
import com.csmancala.core.RenderGraphics;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6954435685287527912L;

	public JLabel player1Name;
	public JLabel player2Name;
	
	public MancalaButton[][] boardButtons;
	public MancalaButton returnButton = new MancalaButton();

	public GamePanel() {
		super();
		setLayout(null);
		setupSlots();
		initButtons();
	}
	
	public void initButtons() {
		returnButton.setBorderPainted(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setFocusPainted(false);
		returnButton.setOpaque(false);
		add(returnButton);
	}

	private void setupSlots() {

		boardButtons = new MancalaButton[8][2];
		for (int y = 0; y < boardButtons[0].length; y++) {
			for (int x = 0; x < boardButtons.length; x++) {
				if (!(x == 0 && y == 1) && !(x == 7 && y == 0)) {
					boardButtons[x][y] = configureButton();
				}
			}
		}

		player1Name = new JLabel();
		player1Name.setForeground(ResourceLoader.DARK_BROWN);
		player2Name = new JLabel();
		player2Name.setForeground(ResourceLoader.DARK_BROWN);
		
		player1Name.setText("Player 1");
		player1Name.setFont(new Font("Montserrat", Font.PLAIN, 48));
		FontMetrics fm = player1Name.getFontMetrics(player1Name.getFont());
		player1Name.setSize(new Dimension(fm.stringWidth(player1Name.getText()), fm.getHeight()));
		
		player2Name.setText("Player 2");
		player2Name.setFont(new Font("Montserrat", Font.PLAIN, 48));
		FontMetrics fm2 = player2Name.getFontMetrics(player2Name.getFont());
		player2Name.setSize(new Dimension(fm2.stringWidth(player2Name.getText()), fm2.getHeight()));

		add(player1Name);
		add(player2Name);
		
		for (int y = 0; y < boardButtons[0].length; y++) {
			for (int x = 0; x < boardButtons.length; x++) {
				
				if (!(x == 0 && y == 1) && !(x == 7 && y == 0)) {
					add(boardButtons[x][y]);
				}
			}
		}
	}

	@Override
	public Component add(final Component c) {
		if (c instanceof MancalaButton && c != boardButtons[0][0] && c != boardButtons[7][1]) {
			final MancalaButton b = (MancalaButton)c;
			b.addActionListener(this);
			
			b.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
					for (int y = 0; y < Start.getMancala().getBoard().getSlotArray()[0].length; y++) {
						for (int x = 0; x < Start.getMancala().getBoard().getSlotArray().length; x++) {
							if (b.equals(boardButtons[x][y])) {
								Start.getMancala().getBoard().getSlotArray()[x][y].setHovered(true);
								addToolTipText();
								break;
							}
						}
					}
					
					if (b.equals(returnButton)) {
						b.setHovered(true);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
					for (int y = 0; y < Start.getMancala().getBoard().getSlotArray()[0].length; y++) {
						for (int x = 0; x < Start.getMancala().getBoard().getSlotArray().length; x++) {
							if (b.equals(boardButtons[x][y])) {
								Start.getMancala().getBoard().getSlotArray()[x][y].setHovered(false);
								break;
							}
						}
					}
					
					if (b.equals(returnButton)) {
						b.setHovered(false);
					}
				}
			});
			
			b.addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					
					if (b.getLocationOnScreen().x < e.getLocationOnScreen().x && b.getLocationOnScreen().x + b.getWidth() > e.getLocationOnScreen().x &&
						b.getLocationOnScreen().y < e.getLocationOnScreen().y && b.getLocationOnScreen().y + b.getHeight() > e.getLocationOnScreen().y) {
						b.setHovered(true);
					}
					else {
						b.setHovered(false);
					}
				}
			});
		}
		return super.add(c);
	}

	private MancalaButton configureButton() {
		MancalaButton b = new MancalaButton();
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setOpaque(false);
		
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setVerticalTextPosition(JButton.CENTER);
		return b;
	}
	
	private void addToolTipText() {
		for (int y = 0; y < boardButtons[0].length; y++) {
			for (int x = 1; x < boardButtons.length - 1; x++) {
				if (boardButtons[x][y] != null) {
					boardButtons[x][y].setToolTipText(Integer.toString(Start.getMancala().getBoard().getSlotArray()[x][y].getStoneAmount()));
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintForeground(this, g2D);
	}

	/**
	 * This method overrides the super.paintComponent(g) method.
	 * It is called in the Mancala class every frame to force refresh the display.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintMancalaBoard(g2D);
		RenderGraphics.updateButtons();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == returnButton) {
			returnButton.setHovered(false);
			Start.getMancala().returnToMenu();
			return;
		}
		
		for (int y = 0; y < boardButtons[0].length; y++) {
			for (int x = 1; x < 7; x++) {
				if (e.getSource() == boardButtons[x][y]) {
					Start.getMancala().getBoard().setCurrentSlot(x, y);
				}
			}
		}

		Board logBoard = Start.getMancala().getBoard();
		int x = logBoard.getCurrentSlotX(), y = logBoard.getCurrentSlotY();
		System.out.println("Clicked Slot: " + x + ", " + y + " | Pits in slot: " + logBoard.getSlotArray()[x][y].getStoneAmount());
		Start.getMancala().progressGame();
	}
}
