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

	private JButton goalLeft = new JButton(new ImageIcon(ResourceLoader.GOAL_BACKGROUND));
	
	public GamePanel() {
		super();
		this.setupSlots();
	}
	
	private void setupSlots() {
		
		goalLeft.setBorderPainted(false);
		goalLeft.setContentAreaFilled(false);
		goalLeft.setFocusPainted(false);
		goalLeft.setOpaque(false);
		goalLeft.setBounds(0, 0, ResourceLoader.GOAL_BACKGROUND.getWidth(), ResourceLoader.GOAL_BACKGROUND.getHeight());
		this.add(goalLeft);
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
