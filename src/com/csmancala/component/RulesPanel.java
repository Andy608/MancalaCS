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

import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;

public class RulesPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 695443524538627912L;
	
	public MancalaButton returnButton = new MancalaButton();
	
	public RulesPanel() {
		super();
		this.setLayout(null);
		this.initButtons();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintRules(this, g2D);
	}
	
	public void initButtons() {
		returnButton.setBorderPainted(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setFocusPainted(false);
		returnButton.setOpaque(false);
		this.add(returnButton);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnButton) {
			returnButton.setHovered(false);
			Start.getMancala().returnToMenu();
		}
	}
}
