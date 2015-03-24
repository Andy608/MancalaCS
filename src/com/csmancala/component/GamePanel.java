package com.csmancala.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		this.setLayout(null);
		this.setupSlots();
	}
	
	private void setupSlots() {
		
		setAlpha(player1Goal);
		setAlpha(topSlot1);
		setAlpha(topSlot2);
		setAlpha(topSlot3);
		setAlpha(topSlot4);
		setAlpha(topSlot5);
		setAlpha(topSlot6);
		setAlpha(player2Goal);
		setAlpha(bottomSlot1);
		setAlpha(bottomSlot2);
		setAlpha(bottomSlot3);
		setAlpha(bottomSlot4);
		setAlpha(bottomSlot5);
		setAlpha(bottomSlot6);
		
//		RenderGraphics.paintMancalaSlots();
		this.player1Goal.setSize(new Dimension(ResourceLoader.GOAL_BACKGROUND.getWidth(), ResourceLoader.GOAL_BACKGROUND.getHeight()));
		this.topSlot1.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.topSlot2.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.topSlot3.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.topSlot4.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.topSlot5.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.topSlot6.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		
		this.player2Goal.setSize(new Dimension(ResourceLoader.GOAL_BACKGROUND.getWidth(), ResourceLoader.GOAL_BACKGROUND.getHeight()));
		this.bottomSlot1.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.bottomSlot2.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.bottomSlot3.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.bottomSlot4.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.bottomSlot5.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		this.bottomSlot6.setSize(new Dimension(ResourceLoader.SLOT_BACKGROUND.getWidth(), ResourceLoader.SLOT_BACKGROUND.getHeight()));
		
		this.add(player1Goal);
		this.add(this.topSlot1);
		this.add(this.topSlot2);
		this.add(this.topSlot3);
		this.add(this.topSlot4);
		this.add(this.topSlot5);
		this.add(this.topSlot6);
		
		this.add(player2Goal);
		this.add(this.bottomSlot1);
		this.add(this.bottomSlot2);
		this.add(this.bottomSlot3);
		this.add(this.bottomSlot4);
		this.add(this.bottomSlot5);
		this.add(this.bottomSlot6);
	}
	
	@Override
	public Component add(Component c) {
		 if (c instanceof JButton) {
			 ((JButton) c).addActionListener(this);
		 }
		 return super.add(c);
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
		System.out.println("CLICKED");
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
		Start.getMancala().progressGame();
	}
	
	public JButton getPlayer1Goal() {
		return player1Goal;
	}
}
