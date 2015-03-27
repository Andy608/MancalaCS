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
import javax.swing.JPanel;

import com.csmancala.core.Board;
import com.csmancala.core.Player;
import com.csmancala.core.RenderGraphics;
import com.csmancala.run.Start;

public class WinPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 9015876069663842418L;

	private JButton mainMenuButton;
	
	private JLabel winnerLabelStatic;
	private JLabel winnerName;
	private JLabel player1Label;
	private JLabel player2Label;
	
	private String name1;
	private String name2;
	
	private Player winner;
	
	public WinPanel(Player playerWinner) { 
		super();
		this.winner = playerWinner;
		this.initComp();
		this.setLayout(new GridLayout(5, 3));
		this.add(Box.createGlue());
		this.add(winnerLabelStatic);
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		this.add(winnerName);
		this.add(Box.createGlue());
		
		this.add(player1Label);
		this.add(Box.createGlue());
		this.add(player2Label);
		
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		
		this.add(Box.createGlue());
		this.add(mainMenuButton);
		this.add(Box.createGlue());
	}

	public void setWinner(Player playerWinner) {
		this.winner = playerWinner;
		this.setLabelsToText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainMenuButton) {
			Start.getMancala().returnToMenu();
		}
		
		
	}
	
	public void initComp() {
		winnerLabelStatic = new JLabel("WINNER!");
		winnerLabelStatic.setFont(new Font("Montserrat", Font.BOLD, 72));
		winnerLabelStatic.setHorizontalAlignment(JLabel.CENTER);
		winnerName = new JLabel();
		winnerName.setFont(new Font("Montserrat", Font.PLAIN, 64));
		winnerName.setHorizontalAlignment(JLabel.CENTER);
		player1Label = new JLabel();
		player1Label.setFont(new Font("Montserrat", Font.PLAIN, 36));
		player1Label.setHorizontalAlignment(JLabel.CENTER);
		player2Label = new JLabel();
		player2Label.setFont(new Font("Montserrat", Font.PLAIN, 36));
		player2Label.setHorizontalAlignment(JLabel.CENTER);
		mainMenuButton = new JButton("Return to Menu!");
		mainMenuButton.addActionListener(this);
	}
	
	public void setLabelsToText() {
		if (Start.getMancala() != null && Start.getMancala().getBoard() != null 
			&& Start.getMancala().getBoard().getPlayer1() != null && Start.getMancala().getBoard().getPlayer2() != null) {
			Board board = Start.getMancala().getBoard();
			
			if (this.winner != null) {
				winnerName.setText(this.winner.getName());
			}
			else {
				winnerName.setText("TIE!");
			}
			
			name1 = board.getPlayer1().getName();
			name2 = board.getPlayer2().getName();
			
			System.out.println(Start.getMancala().getBoard().getPlayer1().getGoal().getStoneAmount());
			player1Label.setText(name1 + " scored: "+ board.getPlayer1().getGoal().getStoneAmount());
			player2Label.setText(name2 + " scored: "+ board.getPlayer2().getGoal().getStoneAmount());
			
			
		}
//		else {
//			name1 = "Test1";
//			name2 = "Test2";
//			
//			player1Label.setText(name1 + " scored: 5");
//			player2Label.setText(name2 + " scored: 7");
//			
//			winnerName.setText(name2);
//		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		RenderGraphics.paintBackground(this, g2D);
	}

}
