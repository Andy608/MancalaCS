package com.csmancala.core;

public class Board {
	
	//The total number of stones on the board.
	public static final int STONE_AMOUNT = 50;
	
	private Pit boardArray [][];
	private Player player1, player2;
	
	public Board(Pit myBoardArray[][]){
		
		this.boardArray = new Pit[2][6];
	}
	
	public void makeMove(int r, int c){
		// alot to do here
	}
	
	private boolean willLandInGoal(){
		return true; // Just to keep it error free i added a return statement
	}
	
	private boolean willLandInEmptyPit(int r, int c){
		return true;
	}
	
	private void pointsGained(Player p, int pointValue){
		//add points to player
	}
	
	public int winStatus(){
		/**
		 * return -1 = tie
		 * return 0 = game not complete
		 * return 1 = player 1 wins
		 * return 2 = player 2 wins
		 */
		return 0;
	}
	
	@Override
	public String toString(){
		String s = "";
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 6; j++){
				s += this.boardArray[i][j];
			}
			
			s += "\n"; // not sure if this works as intended
			
		}
		return s;
	}
	
}
