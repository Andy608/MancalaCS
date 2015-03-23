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

import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;

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
		this.addListeners();
		this.setLayout(new GridLayout(10, 3));
		
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		mancalaLogo.setFont(new Font("Montserrat", Font.BOLD, 72));
		mancalaLogo.setHorizontalAlignment(JLabel.CENTER);
		this.add(mancalaLogo);
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(playButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(rulesButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(creditsButton);
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
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
		if(e.getSource() == playButton) {
			executePlayAction();
		}
		else if (e.getSource() == rulesButton) {
			System.out.println("lol rules.");
		}
		else if (e.getSource() == creditsButton) {
			System.out.println("lol credits.");
		}
		else if (e.getSource() == quitButton) {
			executeQuitAction();
		}
	}
	
	private void executePlayAction() {
		
		player1Name = getPlayer1NameInput();	
		player2Name = getPlayer2NameInput();
		
		Start.getMancala().initBoard(player1Name, player2Name);
		Start.getMancala().startGame();
	}
	
	private void executeQuitAction() {
		Start.getMancala().stop();
		System.exit(0);
	}
	
	private void addListeners() {
		playButton.addActionListener(this);
		rulesButton.addActionListener(this);
		creditsButton.addActionListener(this);
		quitButton.addActionListener(this);
	}
	
	public String getPlayer1NameInput(){
		while(player1Name == null || player1Name.equals("")) {
			player1Name = JOptionPane.showInputDialog("Player 1's Name: ");
			if(player1Name != null && !player1Name.equals("")){
				return player1Name;
			}
			
		}
		return ""; //This should never be reachable
	}
	
	public String getPlayer2NameInput(){
		while(player2Name == null || player2Name.equals("")) {
			player2Name = JOptionPane.showInputDialog("Player 2's Name: ");
			if(player2Name != null && !player2Name.equals("")){
				return player2Name;
			}
			
		}
		return ""; //This should never be reachable
	}
}
