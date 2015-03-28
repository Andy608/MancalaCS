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
		initCurrentSlotLocation();
		initPlayers(player1Name, player2Name);
		initSlots();
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
//					/**/if ((x == 1 && y == 0) || (x == 6 && y == 1)) {
//						/**/System.out.println("ADDING STONE");
//						/**/slotArray[x][y] = new Slot(new Stone[5]);
					Stone[] stones = new Stone[4];
					for (int s = 0; s < stones.length; s++) {
						stones[s] = new Stone();
					}
					
					slotArray[x][y] = new Slot(stones);
//						/**/}
//					/**/else {
//						/**/slotArray[x][y] = new Slot();
//						/**/}
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
		
		if (slotArray[currentSlotLocation[0]][currentSlotLocation[1]].getStoneAmount() > 0) {
			currentPlayer.setHand(slotArray[currentSlotLocation[0]][currentSlotLocation[1]].clearStones());
		}
	}
	
	/**
	 * Moves the players hand forward one on the board.
	 * Loops around the array and jumps over the extra goal spaces.
	 */
	public void advanceSlot(Player currentPlayer) {
		
		if (currentSlotLocation[0] == 1 && currentSlotLocation[1] == 0 && currentPlayer.equals(player1)) {
			currentSlotLocation[0]--;
		}
		else if (currentSlotLocation[0] == 1 && currentSlotLocation[1] == 0 && currentPlayer.equals(player2)) {
			currentSlotLocation[1]++;
		}
		else if (currentSlotLocation[0] == 6 && currentSlotLocation[1] == 1 && currentPlayer.equals(player2)) {
			currentSlotLocation[0]++;
		}
		else if (currentSlotLocation[0] == 6 && currentSlotLocation[1] == 1 && currentPlayer.equals(player1)) {
			currentSlotLocation[1]--;
		}
		else if (currentSlotLocation[0] == 0 && currentSlotLocation[1] == 0) {
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
		
		System.out.println("Advancing to slot " + currentSlotLocation[0] + ", " + currentSlotLocation[1]);
		addStoneToNewPile(currentPlayer);
	}
	
	public boolean isCorrectSide(Player currentPlayer) {
		
		if ((currentPlayer.equals(player1) && currentSlotLocation[1] == 0) || (currentPlayer.equals(player2) && currentSlotLocation[1] == 1)) {
			return true;
		}
		return false;
	}
	
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
	public void addStoneToNewPile(Player currentPlayer) {
		
		int stoneIndex = rand.nextInt(currentPlayer.getHandAmount());
		
		slotArray[currentSlotLocation[0]][currentSlotLocation[1]].getStones().add(currentPlayer.getHand().get(stoneIndex));
		System.out.println(currentSlotLocation[0] + ", " + currentSlotLocation[1] + ": " + slotArray[currentSlotLocation[0]][currentSlotLocation[1]]);
		currentPlayer.getHand().remove(stoneIndex);
		System.out.println("Stones in hand: " + currentPlayer.getHandAmount());
	}
	
	public boolean isInGoal(Player currentPlayer) {
		
		if ((currentPlayer.equals(player1) && currentSlotLocation[0] == 0 && currentSlotLocation[1] == 0) ||
				(currentPlayer.equals(player2) && currentSlotLocation[0] == 7 && currentSlotLocation[1] == 1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void captureStones(Player currentPlayer) {
		int y = 0;
		if (currentSlotLocation[1] == 0) y = 1;
		
		int[] oppositeSlot = new int[] { currentSlotLocation[0], y };
		
		//Add stone from current slot to currentPlayers hand (for gameloop delay purposes when displaying), and then to currentPlayers goal
		//Add all the stones in the opposite pile, add them to the players goal
		for (int i = 0; i < 2; i++) {
			pickUpStones(currentPlayer);
			
			if (currentPlayer.equals(player1)) setCurrentSlot(0, 0);
			else setCurrentSlot(7, 1);
			
			while (currentPlayer.getHandAmount() > 0)
				addStoneToNewPile(currentPlayer);
			
			if (i == 0)
				setCurrentSlot(oppositeSlot[0], oppositeSlot[1]);
		}
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
