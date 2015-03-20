package com.csmancala.core;

import java.util.ArrayList;
import java.util.List;

public class Goal {

	private List<Stone> stones;
	
	public Goal() {
		stones = new ArrayList<>(Board.STONE_AMOUNT / 2);
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
}
