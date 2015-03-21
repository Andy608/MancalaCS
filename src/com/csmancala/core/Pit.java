package com.csmancala.core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;

public class Pit extends JButton {
	private static final long serialVersionUID = 824551522813339680L;
	
	protected BufferedImage pitImage;
	protected List<Stone> stones;
	
	public Pit() {
		super.setEnabled(false);
		this.addListeners();
	}
	
	private void addListeners() {
		super.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				//TODO:ADD HIGHLIGHT AROUND EDGE.
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				//TODO:REMOVE HIGHLIGHT AROUND EDGE.
			}
		});
	}
	
	/**
	 * @return the arraylist of stones that this goal carries.
	 */
	public List<Stone> getStones() {
		return stones;
	}
	
	/**
	 * Returns an integer value of how many stones are in the goal.
	 * @return how many stones are in the goal.
	 */
	public int getStoneCount() {
		return stones.size();
	}
}
