package com.csmancala.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;
import com.csmancala.util.MancalaButton;

public class RulesPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 695443524538627912L;
	
	public JLabel mancalaLogo = new JLabel("Mancala");
	//private JLabel instructionsLabel = new JLabel(new ImageIcon(ResourceLoader.MANCALA_INSTRUCTIONS));
	private MancalaButton returnButton = new MancalaButton("Return to Main Menu");
	
	public RulesPanel() {
		super();
		this.setLayout(null);
		this.setupInstructions();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
		RenderGraphics.paintRules(this, g2D);
	}
	
	public void setupInstructions() {
		
		//PLEASE MAKE THE NEW RULES IMAGE OVERLAY THE BACKGROUND. PLEASE ADD THE BUTTON AND TITLE BACK ON OVER THE PANEL ASWELL!!
		//this.instructionsPanel.add(instructionsLabel);
		mancalaLogo.setHorizontalAlignment(JLabel.CENTER);
		this.add(mancalaLogo);
		
//		returnButton.addActionListener(this);
//		this.add(returnButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnButton) {
			Start.getMancala().returnToMenu();
		}
	}
}
