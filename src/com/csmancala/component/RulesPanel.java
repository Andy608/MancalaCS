package com.csmancala.component;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.file.ResourceLoader;

public class RulesPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 695443524538627912L;
	
	private JLabel mancalaLogo = new JLabel("Mancala");
	private JLabel instructionsLabel = new JLabel(new ImageIcon(ResourceLoader.MANCALA_INSTRUCTIONS));
	private JButton returnButton = new JButton("Return to Main Menu");
	
	public RulesPanel() {
		super();
		this.setupInstructions();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
	}
	
	public void setupInstructions() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, 72));
		mancalaLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(mancalaLogo);
//		instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(instructionsLabel);
		this.add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
