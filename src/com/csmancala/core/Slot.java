package com.csmancala.core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class Slot extends JButton {
	private static final long serialVersionUID = 824551522813339680L;
	
	protected BufferedImage slotImage;
	protected List<Stone> stones;
	
	public Slot() {
		super.setEnabled(false);
		this.addListeners();
		stones = new ArrayList<>();
	}
	
	public Slot(Stone... defaultStones) {
		this();
		addStones(defaultStones);
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
	public int getStoneAmount() {
		return stones.size();
	}
	
	/**
	 * Adds stones to the desired pile.
	 * @param s is an array of stones that will be added to the pile.
	 */
	public void addStones(Stone... s) {
		for (Stone stone : s) {
			stones.add(stone);
		}
	}
	
	@Override
	public String toString() {
		return "Stones in Pile: " + stones.size();
	}
}
