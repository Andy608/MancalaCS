package com.csmancala.component;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.core.Board;
import com.csmancala.core.Player;
import com.csmancala.core.RenderGraphics;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;

public class WinPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 9015876069663842418L;

	public MancalaButton menuButton;
	
	private JLabel winnerName;
	private JLabel player1Label;
	private JLabel player2Label;
	
	private Player winner;
	
	public WinPanel() { 
		super();
		this.setLayout(null);
		this.initComp();
		this.initButtons();
	}
	
	public void initButtons() {
		menuButton.setBorderPainted(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setFocusPainted(false);
		menuButton.setOpaque(false);
		this.add(menuButton);
	}

	public void setWinner(Player playerWinner) {
		this.winner = playerWinner;
		this.setLabelsToText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(menuButton)) {
			menuButton.setHovered(false);
			Start.getMancala().returnToMenu();
		}
	}
	
	public void initComp() {
		winnerName = new JLabel();
		winnerName.setForeground(ResourceLoader.GOLDEN);
		
		player1Label = new JLabel();
		player1Label.setForeground(ResourceLoader.DARK_BROWN);
		
		player2Label = new JLabel();
		player2Label.setForeground(ResourceLoader.DARK_BROWN);

		menuButton = new MancalaButton();
		add(winnerName);
		add(player1Label);
		add(player2Label);
	}
	
	@Override
	public Component add(final Component c) {
		if (c instanceof MancalaButton) {
			final MancalaButton b = (MancalaButton)c;
			b.addActionListener(this);
			
			b.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
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
	
	public void setLabelsToText() {
		if (Start.getMancala() != null && Start.getMancala().getBoard() != null 
			&& Start.getMancala().getBoard().getPlayer1() != null && Start.getMancala().getBoard().getPlayer2() != null) {
			Board board = Start.getMancala().getBoard();
			
			if (this.winner != null) {
				winnerName.setText(this.winner.getName());
			}
			else {
				winnerName.setText("TIE!");
			}
			
			player1Label.setText(board.getPlayer1().getName() + " scored: "+ board.getPlayer1().getGoal().getStoneAmount());
			player2Label.setText(board.getPlayer2().getName() + " scored: "+ board.getPlayer2().getGoal().getStoneAmount());
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintWinScreen(this, g2D);
	}
	
	public JLabel getWinnerLabel() {
		return winnerName;
	}
	
	public JLabel getPlayer1Label() {
		return player1Label;
	}
	
	public JLabel getPlayer2Label() {
		return player2Label;
	}
}
