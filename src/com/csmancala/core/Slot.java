package com.csmancala.core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class Slot extends JButton {
	private static final long serialVersionUID = 824551522813339680L;
	
	protected BufferedImage slotImage;
	protected List<Stone> stones;
	protected boolean isHovered;
	
	public Slot() {
		super.setEnabled(false);
		stones = new ArrayList<>();
		isHovered = false;
	}
	
	public Slot(Stone... defaultStones) {
		this();
		addStones(defaultStones);
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
			System.out.println("YO WASSUP!!!!");
			stones.add(stone);
		}
	}
	
	public List<Stone> clearStones() {
		List<Stone> s = new ArrayList<>();
		
		for (int i = stones.size() - 1; i >= 0; i--) {
			s.add(stones.remove(i));
		}
		return s;
	}
	
	public boolean isHovered() {
		return isHovered;
	}
	
	public void setHovered(boolean b) {
		isHovered = b;
	}
	
	@Override
	public String toString() {
		return "Stones in Pile: " + stones.size();
	}
}
