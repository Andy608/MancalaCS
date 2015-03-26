package com.csmancala.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel extends JPanel implements ActionListener {

	private JButton mainMenuButton;
	
	private JLabel winnerLabelStatic;
	private JLabel winnerName;
	private JLabel player1Label;
	private JLabel player2Label;
	
	private String name1;
	private String name2;
	public WinPanel(String myName1, String myName2) {
		super();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenuButton) {
			
		}
		
		
	}

}
