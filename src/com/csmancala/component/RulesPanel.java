package com.csmancala.component;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;

public class RulesPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 695443524538627912L;
	
	private JPanel instructionsPanel = new JPanel();
	private JLabel mancalaLogo = new JLabel("Mancala Remove White space!!!");
	//private JLabel instructionsLabel = new JLabel(new ImageIcon(ResourceLoader.MANCALA_INSTRUCTIONS));
	private JButton returnButton = new JButton("Return to Main Menu");
	private JLabel instructionsLabelObject = new JLabel();
	private JLabel instructionsLabelHowPlay = new JLabel();
	private JLabel instructionsLabelHowWin = new JLabel();
	
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
		
		initLabelWithText();
		//PLEASE MAKE THE NEW RULES IMAGE OVERLAY THE BACKGROUND. PLEASE ADD THE BUTTON AND TITLE BACK ON OVER THE PANEL ASWELL!!
		//this.instructionsPanel.add(instructionsLabel);
		instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.PAGE_AXIS));
		mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, 72));
		mancalaLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		instructionsPanel.add(instructionsLabelObject);
		instructionsPanel.add(instructionsLabelHowPlay);
		instructionsPanel.add(instructionsLabelHowWin);
		
		//this.instructionsLabelHowPlay.setBackground(null); THIS DOESNT WORK
		
		instructionsLabelObject.setFont(new Font("Montserrat", Font.PLAIN, 20));
		instructionsLabelHowPlay.setFont(new Font("Montserrat", Font.PLAIN, 20));
		instructionsLabelHowWin.setFont(new Font("Montserrat", Font.PLAIN, 20));
		this.add(mancalaLogo);
////		instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
//		this.add(instructionsLabel);
		returnButton.addActionListener(this);
		this.add(returnButton);
		this.add(instructionsPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnButton) {
			Start.getMancala().returnToMenu();
		}
	}
	
	public void initLabelWithText() {
		String instructionsLabelObjectText = "<html> OBJECT <br/>Have the most stones in your mancala after all the"
				+ "all the stones on your game Board are captured<br/><br/>";
		String instructionsLabelHowPlayText = "<html>HOW TO PLAY<br/>on your turn choose a slot to pick up all your stones"
				+ "and going counter clock-wise stones will fall into<br/> the following slots including your goal."
				+ "After you do this and capture any stones your turn is over.<br/>"
				+ "Unless your final stone in your hand falls in your goal, in which case you go again<br/>"
				+ "You also gain stones if your final stone land in an empty slot, then you take all<br/> stones from the oppose side and placed them in your goal!<br/><br/>";
		String instructionLabelHowWinText = "<html>HOW TO WIN<br/>The player with the most stones in their goal after no stones are left on the board wins!<br/>"
				+ "HAVE FUN";
		instructionsLabelObject.setText("<html><div style=\"text-align: center;\">" + instructionsLabelObjectText + "</html>");
		instructionsLabelHowPlay.setText("<html><div style=\"text-align: center;\">" + instructionsLabelHowPlayText + "</html>");
		instructionsLabelHowWin.setText("<html><div style=\"text-align: center;\">" + instructionLabelHowWinText + "</html>");
	}
	

}
