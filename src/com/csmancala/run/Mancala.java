package com.csmancala.run;

import java.util.Random;

import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.component.MancalaFrame;
import com.csmancala.component.RulesPanel;
import com.csmancala.core.Board;
import com.csmancala.core.Player;
import com.csmancala.core.Slot;

public class Mancala implements Runnable {

	protected MancalaFrame frameInstance;
	protected MainMenuPanel menuPanel;
	protected GamePanel gamePanel;
	protected RulesPanel rulesPanel;
	protected JPanel displayedPanel;
	
	private Board mancalaBoard;
	private Player currentPlayer;
	
	private static Random rand = new Random();
	
	private Thread mancalaThread;
	private static boolean isRunning;
	
	private static final double TIME_SLICE = 60.0D;
	private static final double DELTA_TIME = 1 / TIME_SLICE;
	
	int ticks;
	int frameCount;
	int fps;
	double elapsedSeconds;
	
	public Mancala() {
		initPanels();
		frameInstance = new MancalaFrame("Mancala!");
		frameInstance.add(menuPanel);
	}
	
	public synchronized void start() {
		if (isRunning) return;
		isRunning = true;
		mancalaThread = new Thread(this);
		mancalaThread.start();
	}
	
	public synchronized void stop() {
		if (!isRunning) return;
		isRunning = false;
		try {
			mancalaThread.join();
			System.out.println("Goodbye!");
		} catch(InterruptedException e) {
			System.out.println("THREAD WAS INTERUPTED WHEN TRYING TO END. :(");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		double currentNanoSeconds = System.nanoTime();
		double newNanoSeconds;
		final float LAG_CAP = 0.2f;
		
		while (isRunning) {
			newNanoSeconds = System.nanoTime();
			elapsedSeconds += (newNanoSeconds - currentNanoSeconds) / 1_000_000_000;
			
			currentNanoSeconds = newNanoSeconds;
			
			//lol must be a bad computer to not be able to handle mancala
			if (elapsedSeconds > LAG_CAP) {
				elapsedSeconds = LAG_CAP;
			}
			
			while (elapsedSeconds > DELTA_TIME) {
				updateGame();
				elapsedSeconds -= DELTA_TIME;
			}
			renderGame();
		}
	}
	
	public void updateGame() {
		ticks++;
		
		if (ticks % 60 == 0) {
			fps = frameCount;
			frameCount = 0;
			
//			System.out.println("Ticks: " + ticks + " FPS: " + fps);
			ticks = 0;
		}
	}
	
	public void renderGame() {
		frameCount++;
		refreshPanel();
		//we will call methods in here that will be necessary to draw every frame.
	}
	
	public void refreshPanel() {
		displayedPanel.revalidate();
		displayedPanel.repaint();
	}
	
	private void initPanels() {
		gamePanel = new GamePanel();
		rulesPanel = new RulesPanel();
		menuPanel = new MainMenuPanel();
		displayedPanel = menuPanel;
	}
	
	public void initBoard(String p1Name, String p2Name) {
		mancalaBoard = new Board(p1Name, p2Name);
	}
	
	private Player pickStartingPlayer() {
		
		int r = rand.nextInt(2);
		
		switch (r) {
		case 0:  return mancalaBoard.getPlayer1();
		default: return mancalaBoard.getPlayer2();
		}
	}
	
	public void startGame() {
		frameInstance.remove(menuPanel);
		frameInstance.add(gamePanel);
		displayedPanel = gamePanel;
		
		currentPlayer = pickStartingPlayer();
		
		System.out.println("STARTING PLAYER: " + currentPlayer);
	}
	
	public void progressGame() {
		if (continueGame()) {
			playerTurn();
//			System.out.println(mancalaBoard);
		}
		else {
			endGame();
		}
	}
	
	public void endGame() {
		
		if (compareGoals() == null) {
			//TODO: call methods to display tie message!
			System.out.println("It's a tie!");
		}
		else if (compareGoals().equals(mancalaBoard.getPlayer1())) {
			//TODO: call methods to display player1 winning message!
			System.out.println(mancalaBoard.getPlayer1() + " Wins!");
		}
		else {
			//TODO: call methods to display player2 winning message!
			System.out.println(mancalaBoard.getPlayer2() + " Wins!");
		}
		
		resetGame();
	}
	
	public void openRules() {
		frameInstance.remove(menuPanel);
		frameInstance.add(rulesPanel);
		displayedPanel = rulesPanel;
	}
	
	public void returnToMenu() {
		frameInstance.remove(displayedPanel);
		frameInstance.add(menuPanel);
		displayedPanel = menuPanel;
	}
	
	//Never call this method unless it is from the button listeners!
	private void playerTurn() {
		
		Slot[][] slotArray = mancalaBoard.getSlotArray();
		
		//Pick up stones from a pile.
			//If there are no stones in the pile, or chosen a pile on the wrong side, then display message telling them to go again and return (Does not switch the player).
		if (slotArray[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() <= 0) {
			System.out.println("There's no stones in that pile! Pick another one.");
			return;
		}
		else if (mancalaBoard.isCorrectSide(currentPlayer)) {
			System.out.println("YO BUB, YOU TRYIN'A BREAK THE GAME? PLEASE PICK AGAIN.");
			return;
		}
		else {
			mancalaBoard.pickUpStones(currentPlayer);
			System.out.println("Picking up stones!");
		}
		
		//while there are still stones in the players hand, continue to remove a stone from the players hand and place it in the next spot.
		while (currentPlayer.getHandAmount() > 0) {
			mancalaBoard.advanceSlot(currentPlayer);
			System.out.println(currentPlayer + "'s Hand: " + currentPlayer.getHandAmount());
		}
		
		//if the current slot is equal to the players goal, then return.
		//else if the current slot is empty and on the current players then add the stone in the current spot and the stones in the pile across from the current spot.
		if (mancalaBoard.isInGoal(currentPlayer)) {
			if (!continueGame()) {
				endGame();
				return;
			}
			
			//Display saying the player can go again.
			System.out.println("Goal! Go again!");
			return;
		}
		else if (mancalaBoard.isCorrectSide(currentPlayer) && slotArray[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() == 0) {
			//THIS NEEDS TO BE TESTED!
			mancalaBoard.captureStones(currentPlayer);
		}
		
		//call switchplayer
		switchPlayers();
		
//		if (mancalaBoard.getSlotArray()[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() > 0) {
//			mancalaBoard.pickUpStones(currentPlayer);
//			System.out.println(currentPlayer + " picked up " + currentPlayer.getHandAmount() + " stones.");
//		}
//		else {
//			System.out.println(currentPlayer + ": Invalid move, go again!");
//			return;
//		}
//		
//		int initialHandAmount = currentPlayer.getHandAmount();
//		for (int i = 0; i < initialHandAmount; i++) {
//			
//			System.out.println(currentPlayer.getHandAmount());
//			if (currentPlayer.getHandAmount() > 0) {
//				mancalaBoard.advanceSlot(currentPlayer);
//			}
//			else if ((i == initialHandAmount - 1) && (mancalaBoard.isInGoal(currentPlayer))) {
//				if (continueGame()) {
//					playerTurn();
//					break;
//				}
//				else {
//					//SEE IF THIS ENDS THE GAME
//					System.out.println("THIS SHOULD END THE GAME!");
//					return;
//				}
//			}
//			else if ((i == initialHandAmount - 1) && (mancalaBoard.getSlotArray()[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() == 0)) {
//				
//				//ADD STONE AND STONES ACROSS INTO CURRENT PLAYER'S PILE
//				for (int y = 0; y < mancalaBoard.getSlotArray()[0].length; y++) {
//					currentPlayer.setHand(mancalaBoard.getSlotArray()[mancalaBoard.getCurrentSlotX()][y].getStones());
//					
//					for (int k = 0; k < currentPlayer.getHandAmount(); k++) {
//						currentPlayer.getGoal().getStones().add(currentPlayer.getHand().get(k));
//						currentPlayer.getHand().remove(i);
//						System.out.println("Player hand amount: " + currentPlayer.getHandAmount());
//					}
//				}
//			}
//			else {
//				switchPlayers();
//				break;
//			}
//		}
	}
	
	private void switchPlayers() {
		
		if (currentPlayer.equals(mancalaBoard.getPlayer1())) {
			currentPlayer = mancalaBoard.getPlayer2();
		}
		else {
			currentPlayer = mancalaBoard.getPlayer1();
		}
		System.out.println("SWITCHING PLAYERS! PLAYER IS NOW : " + currentPlayer);
	}
	
	private boolean continueGame() {
		return !checkIfSideIsEmpty();
	}
	
	private boolean checkIfSideIsEmpty() {
		
		boolean isEmpty = false;
		if (mancalaBoard.isSideEmpty(mancalaBoard.getPlayer1())) {
			currentPlayer = mancalaBoard.getPlayer1();
			isEmpty = true;
		}
		else if (mancalaBoard.isSideEmpty(mancalaBoard.getPlayer2())) {
			currentPlayer = mancalaBoard.getPlayer2();
			isEmpty = true;
		}
		
		if (isEmpty) {
			addStonesToOpponent();
		}
		
//		System.out.println("isEmpty = " + isEmpty);
		return isEmpty;
	}
	
	private void addStonesToOpponent() {
		int y = getYStatus();
		
		for (int x = 1; x < mancalaBoard.getSlotArray().length - 1; x++) {
			
			currentPlayer.setHand(mancalaBoard.getSlotArray()[x][y].getStones());
			
			for (int i = 0; i < currentPlayer.getHandAmount(); i++) {
				currentPlayer.getGoal().getStones().add(currentPlayer.getHand().get(i));
				currentPlayer.getHand().remove(i);
				System.out.println("Player hand amount: " + currentPlayer.getHandAmount());
			}
		}
	}
	
	private int getYStatus() {
		int y = 0;
		//Set the y to the opposite player's side so they can collect their stones.
		if (currentPlayer.equals(mancalaBoard.getPlayer1())) y = 1;
		else y = 0;
		return y;
	}
	
	/**
	 * Compares the goals of each player.
	 * @return the player with more stones.
	 */
	private Player compareGoals() {
		
		int p1Amount = mancalaBoard.getPlayer1().getGoal().getStoneAmount();
		int p2Amount = mancalaBoard.getPlayer2().getGoal().getStoneAmount();
		
		if (p1Amount == p2Amount) {
			return null;
		}
		else if (p1Amount < p2Amount) {
			return mancalaBoard.getPlayer2();
		}
		else {
			return mancalaBoard.getPlayer1();
		}
	}
	
	private void resetGame() {
		
		menuPanel.setPlayer1Name(null);
		menuPanel.setPlayer2Name(null);
		frameInstance.remove(gamePanel);
		frameInstance.add(menuPanel);
		displayedPanel = menuPanel;
	}
	
	public Board getBoard() {
		return mancalaBoard;
	}
	
	public double getDeltaTime() {
		return elapsedSeconds;
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public MainMenuPanel getMenuPanel() {
		return menuPanel;
	}
}
