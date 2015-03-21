package com.csmancala.core;

public class Board {
	
	//The total number of stones on the board.
	public static final int STONE_AMOUNT = 50;
	
	private static final int PIT_WIDTH = 6;
	private static final int PIT_HEIGHT = 2;
	private Pit boardArray[][];
	private Player player1;
	private Player player2;
	
	public Board(Pit myBoardArray[][]) {
		this.boardArray = new Pit[PIT_WIDTH][PIT_HEIGHT];
	}
	
	public void makeMove(int r, int c) {
		// alot to do here
	}
	
	private int willLandInGoal(int r, int c) {
		
		if(r == 0 && c == 0) {
			if(player1.getHandAmount() == 1){
				return 1;
			}
		}
		else if(r == 1 && c == 5) {
			if(player2.getHandAmount() == 1){
				return 2;
			}
		}
		
		return 0;
	}
	
	private boolean willLandInEmptyPit(int r, int c) {
		return true;
	}
	
	private void pointsGained(Player p, int pointValue) {
		p.getGoal().setMoreStones(pointValue);
	}
	
	public int winStatus() {
		/**
		 * return -1 = tie
		 * return 0 = game not complete
		 * return 1 = player 1 wins
		 * return 2 = player 2 wins
		 */
		return 0;
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
}
