package com.csmancala.component;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.csmancala.core.Board;
import com.csmancala.core.RenderGraphics;

public class MainMenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5504065348067609290L;

	private JLabel mancalaLogo = new JLabel("Mancala"); 
	private JButton playButton = new JButton("Play!");
	private JButton rulesButton = new JButton("Rules");
	private JButton creditsButton = new JButton("Credits");
	private JButton quitButton = new JButton("Quit");
	
	private String player1Name;
	private String player2Name;
	
	public MainMenuPanel() {
		super();
		this.addGridAndComponents();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
	}
	
	private void addGridAndComponents() {
		this.setLayout(new GridLayout(5, 3));
		this.add(Box.createGlue());
		mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, 72));
		mancalaLogo.setHorizontalAlignment(JLabel.CENTER);
		this.add(mancalaLogo);
		this.add(Box.createGlue());
		
		playButton.addActionListener(this);
		this.add(playButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(rulesButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(creditsButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(quitButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
	}

	public String getPlayer1Name(){
		return player1Name;
	}
	
	public String getPlayer2Name(){
		return player2Name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playButton){
			player1Name = JOptionPane.showInputDialog("Player 1 Name: ");
			player2Name = JOptionPane.showInputDialog("Player 2 Name: ");
			
			Board board = new Board(player1Name, player2Name);
			
		}
	}
}
