package com.csmancala.component;

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

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6954435685287527912L;

	private JPanel boardPanel = new JPanel();
	private JButton goalPlayer1 = new JButton(new ImageIcon(ResourceLoader.GOAL_BACKGROUND));
	private JButton top1 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton top2 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton top3 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton top4 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton top5 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton top6 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom1 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom2 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom3 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom4 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom5 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton bottom6 = new JButton(new ImageIcon(ResourceLoader.SLOT_BACKGROUND));
	private JButton goalPlayer2 = new JButton(new ImageIcon(ResourceLoader.GOAL_BACKGROUND));
	
	public GamePanel() {
		super();
		this.setupSlots();
	}
	
	private void setupSlots() {
		
		this.removeButtonBackground(goalPlayer1);
		goalPlayer1.setBounds(0, 0, ResourceLoader.GOAL_BACKGROUND.getWidth(), ResourceLoader.GOAL_BACKGROUND.getHeight());
		this.add(goalPlayer1);
		
		
	}
	
	private void removeButtonBackground(JButton button) {
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
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
		
	}
}
