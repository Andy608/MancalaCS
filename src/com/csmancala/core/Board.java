package com.csmancala.core;

import java.util.Random;


public class Board {

	//The total number of stones on the board.
	public static final int STONE_AMOUNT = 48;
	
	private static final int PIT_WIDTH = 8;
	private static final int PIT_HEIGHT = 2;
	
	private static Random rand = new Random();
	
	private Player player1;
	private Player player2;
	
	private Slot slotArray[][];
	private int[] currentSlotLocation;
	
	public Board(String player1Name, String player2Name) {
		initPlayers(player1Name, player2Name);
		initSlots();
		initCurrentSlotLocation();
	}
	
	private void initSlots() {
		slotArray = new Slot[PIT_WIDTH][PIT_HEIGHT];
		
		for (int y = 0; y < slotArray[0].length; y++) {
			for (int x = 0; x < slotArray.length; x++) {
				
				if (x == 0 && y == 0) {
					slotArray[x][y] = player1.getGoal();
				}
				else if (x == 7 && y == 1) {
					slotArray[x][y] = player2.getGoal();
				}
				else if (!(x == 0 && y == 1) && !(x == 7 && y == 0)) {
					slotArray[x][y] = new Slot(new Stone[4]);
				}
			}
		}
	}
	
	private void initCurrentSlotLocation() {
		currentSlotLocation = new int[2];
	}
	
	private void initPlayers(String p1Name, String p2Name) {
		player1 = new Player(p1Name);
		player2 = new Player(p2Name);
	}
	
	public void pickUpStones(Player currentPlayer) {
		currentPlayer.setHand(slotArray[currentSlotLocation[0]][currentSlotLocation[1]].getStones());
		slotArray[currentSlotLocation[0]][currentSlotLocation[1]] = null;
	}
	
	/**
	 * Moves the players hand forward one on the board.
	 * Loops around the array and jumps over the extra goal spaces.
	 */
	public void advanceSlot(Player currentPlayer) {
		
		if (currentSlotLocation[0] == 0 && currentSlotLocation[1] == 0) {
			currentSlotLocation[0]++;
			currentSlotLocation[1]++;
		}
		else if (currentSlotLocation[0] == 7 && currentSlotLocation[1] == 1) {
			currentSlotLocation[0]--;
			currentSlotLocation[1]--;
		}
		else if (currentSlotLocation[1] % 2 == 0 && currentSlotLocation[0] > 0 && currentSlotLocation[0] < slotArray.length) {
			currentSlotLocation[0]--;
		}
		else if (currentSlotLocation[1] % 2 == 1 && currentSlotLocation[0] > 0 && currentSlotLocation[0] < slotArray.length - 1) {
			currentSlotLocation[0]++;
		}
		
		addStoneToNewPile(currentPlayer);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSideEmpty(Player currentPlayer) {
		
		int y;
		
		if (currentPlayer.equals(player1)) y = 0;
		else y = 1;
		
		for (int x = 1; x < slotArray.length - 1; x++) {
			if (!slotArray[x][y].getStones().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds a random stone from the player's hand to the new slot and removes a stone from the player's hand.
	 */
	private void addStoneToNewPile(Player currentPlayer) {
		
		int stoneIndex = rand.nextInt(currentPlayer.getHandAmount());
		
		slotArray[currentSlotLocation[0]][currentSlotLocation[1]].getStones().add(currentPlayer.getHand().get(stoneIndex));
		currentPlayer.getHand().remove(stoneIndex);
	}
	
	public void setCurrentSlot(int x, int y) {
		currentSlotLocation[0] = x;
		currentSlotLocation[1] = y;
	}
	
	public Slot[][] getSlotArray() {
		return slotArray;
	}
	
	public int getCurrentSlotX() {
		return currentSlotLocation[0];
	}
	
	public int getCurrentSlotY() {
		return currentSlotLocation[1];
	}
	
	/**
	 * @return the player1 object.
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * @return the player2 object.
	 */
	public Player getPlayer2() {
		return player2;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		
		for (int y = 0; y < slotArray[0].length; y++) {
			for (int x = 0; x < slotArray.length; x++) {
				string.append(slotArray[x][y] + " ");
			}
			string.append("\n");
		}
		return string.toString();
	}
}
