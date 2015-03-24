package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6954435685287527912L;

	public JButton player1Goal = new JButton(new ImageIcon(ResourceLoader.GOAL_BACKGROUND));
	public JButton topSlot1 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton topSlot2 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton topSlot3 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton topSlot4 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton topSlot5 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton topSlot6 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	
	public JButton player2Goal = new JButton(new ImageIcon(ResourceLoader.GOAL_BACKGROUND));
	public JButton bottomSlot1 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton bottomSlot2 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton bottomSlot3 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton bottomSlot4 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton bottomSlot5 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	public JButton bottomSlot6 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	
	public GamePanel() {
		super();
		this.setupSlots();
	}
	
	private void setupSlots() {
		
//		setAlpha(player1Goal);
//		setAlpha(player2Goal);
		
//		RenderGraphics.paintMancalaSlots();
//		this.player1Goal.setPreferredSize(new Dimension(40, 40));
		
		this.add(player1Goal);
		this.add(player2Goal);
	}
	
	private void setAlpha(JButton b) {
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setFocusPainted(false);
		b.setOpaque(false);
	}
	
	/**
	 * This method overrides the super.paint(g) method.
	 * It is called in the Mancala class every frame to force refresh the display.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintMancalaBoard(g2D);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == topSlot1) {
			Start.getMancala().getBoard().setCurrentSlot(1, 0);
		}
		else if (e.getSource() == topSlot2) {
			Start.getMancala().getBoard().setCurrentSlot(2, 0);
		}
		else if (e.getSource() == topSlot3) {
			Start.getMancala().getBoard().setCurrentSlot(3, 0);
		}
		else if (e.getSource() == topSlot4) {
			Start.getMancala().getBoard().setCurrentSlot(4, 0);
		}
		else if (e.getSource() == topSlot5) {
			Start.getMancala().getBoard().setCurrentSlot(5, 0);
		}
		else if (e.getSource() == topSlot6) {
			Start.getMancala().getBoard().setCurrentSlot(6, 0);
		}
		else if (e.getSource() == bottomSlot1) {
			Start.getMancala().getBoard().setCurrentSlot(1, 1);
		}
		else if (e.getSource() == bottomSlot2) {
			Start.getMancala().getBoard().setCurrentSlot(2, 1);
		}
		else if (e.getSource() == bottomSlot3) {
			Start.getMancala().getBoard().setCurrentSlot(3, 1);
		}
		else if (e.getSource() == bottomSlot4) {
			Start.getMancala().getBoard().setCurrentSlot(4, 1);
		}
		else if (e.getSource() == bottomSlot5) {
			Start.getMancala().getBoard().setCurrentSlot(5, 1);
		}
		else if (e.getSource() == bottomSlot6) {
			Start.getMancala().getBoard().setCurrentSlot(6, 1);
		}
	}
	
	public JButton getPlayer1Goal() {
		return player1Goal;
	}
}
