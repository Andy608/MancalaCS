package com.csmancala.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csmancala.run.Start;

public class WinPanel extends JPanel implements ActionListener {

	private JButton mainMenuButton;
	
	private JLabel winnerLabelStatic;
	private JLabel winnerName;
	private JLabel player1Label;
	private JLabel player2Label;
	
	private String name1;
	private String name2;
	
	private int whoWins;
	
	public WinPanel() { 
		super();
		initComp();
		this.setLayout(new GridLayout(8,1));
		this.add(winnerLabelStatic);
		this.add(winnerName);
		this.add(player1Label);
		this.add(player2Label);
		this.add(mainMenuButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenuButton) {
			
		}
		
		
	}
	
	public void initComp() {
		//edit if you want, i did this in a hurry, but i keep getting a 
		winnerLabelStatic = new JLabel();
		winnerName = new JLabel();
		player1Label = new JLabel();
		player2Label = new JLabel();
		
		mainMenuButton = new JButton();
	}
	
	public void setLabelsToText() {
		whoWins = Start.getMancala().getWhoWins();
		
		name1 = Start.getMancala().getBoard().getPlayer1().getName();
		name2 = Start.getMancala().getBoard().getPlayer2().getName();
		
		
		if(whoWins == 0)
			winnerName.setText("TIE");
		else if(whoWins == 1)
			winnerName.setText(name1);
		else if(whoWins == 2)
			winnerName.setText(name2);
	}

}
