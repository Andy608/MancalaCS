package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;

/**
 * WILL BE USED IN A FUTURE UPDATE. THIS PANEL WILL ASK THE USERS FOR THEIR NAMES, HOW MANY STONES TO START OUT WITH IN EACH PILE, AND THE GAME DIFFICULTY.
 */
public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 2926968015424719601L;

	public OptionsPanel() {
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
	}
	
}
