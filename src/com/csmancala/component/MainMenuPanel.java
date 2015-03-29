package com.csmancala.component;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;

public class MainMenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5504065348067609290L;

	public JLabel mancalaLogo = new JLabel("Mancala");
	
	public MancalaButton[] menuButtons = new MancalaButton[4];
	
	private String player1Name;
	private String player2Name;
	
	public MainMenuPanel() {
		super();
		this.setLayout(null);
		this.setUpMenu();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.updateMenuText(this);
		RenderGraphics.updateButtons();
	}
	
	private void setUpMenu() {
		
		for (int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = initButtons();
		}
		
		mancalaLogo.setHorizontalAlignment(JLabel.CENTER);
		this.add(mancalaLogo);
		
		for (MancalaButton b : menuButtons) {
			add(b);
		}
	}
	
	private MancalaButton initButtons() {
		MancalaButton b = new MancalaButton();
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setOpaque(false);
		return b;
	}

	public String getPlayer1Name(){
		return player1Name;
	}
	
	public String getPlayer2Name(){
		return player2Name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (MancalaButton b : menuButtons) {
			b.setHovered(false);
		}
		
		if(e.getSource().equals(menuButtons[0])) {
			executePlayAction();
		}
		else if (e.getSource().equals(menuButtons[1])) {
			executeRulesAction();
		}
		else if (e.getSource().equals(menuButtons[2])) {
			System.out.println("Lol credits");
		}
		else if (e.getSource().equals(menuButtons[3])) {
			executeQuitAction();
		}
	}
	
	private void executePlayAction() {
		
		player1Name = getPlayer1NameInput();
		if (player1Name == null) {
			return;
		}
		
		player2Name = getPlayer2NameInput();
		if (player2Name == null) {
			return;
		}
		
		if (player1Name.equals(player2Name)) {
			player2Name += "_2";
		}
		
		Start.getMancala().initBoard(player1Name, player2Name);
		Start.getMancala().startGame();
	}
	
	private void executeRulesAction() {
		Start.getMancala().openRules();
	}
	
	private void executeQuitAction() {
		Start.getMancala().stop();
		System.exit(0);
	}
	
	@Override
	public Component add(final Component c) {
		if (c instanceof MancalaButton) {
			final MancalaButton b = (MancalaButton)c;
			b.addActionListener(this);
			
			b.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					System.out.println("I'VE BEEN TOUCHED!");
					c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					b.setHovered(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					b.setHovered(false);
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
	
	public String getPlayer1NameInput() {
		player1Name = JOptionPane.showInputDialog("Player 1's Name: ");
		if (player1Name != null && !player1Name.isEmpty()) {
			return player1Name;
		}
		System.out.println("You can't enter no name... Returning to main menu.");
		Start.getMancala().returnToMenu();
		return null;
	}
	
	public String getPlayer2NameInput() {
		player2Name = JOptionPane.showInputDialog("Player 2's Name: ");
		if (player2Name != null && !player2Name.isEmpty()) {
			return player2Name;
		}
		System.out.println("You can't enter no name... Returning to main menu.");
		Start.getMancala().returnToMenu();
		return null;
	}
	
	public void setPlayer1Name(String name) {
		player1Name = name;
	}
	
	public void setPlayer2Name(String name) {
		player2Name = name;
	}
}