package com.csmancala.core;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private String name;
	private List<Stone> currentHand;
	private Goal playerGoal;
	
	/**
	 * The constructor for the Player class.
	 * @param n : The name of the player.
	 */
	public Player(String n) {
		
		name = n;
		currentHand = new ArrayList<>();
		playerGoal = new Goal();
	}
	
	/**
	 * @return the name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the list of stones the player is holding.
	 */
	public List<Stone> getHand() {
		return currentHand;
	}
	
	/**
	 * @return the amount of stones the player is holding.
	 */
	public int getHandAmount() {
		return currentHand.size();
	}
	
	/**
	 * @return the player's goal.
	 */
	public Goal getGoal() {
		return playerGoal;
	}
	
	public void setHand(List<Stone> stones) {
		currentHand = stones;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
