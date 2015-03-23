package com.csmancala.run;

import java.util.Random;

import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.component.MancalaFrame;
import com.csmancala.core.Board;
import com.csmancala.core.Player;

public class Mancala implements Runnable {

	protected MancalaFrame frameInstance;
	protected MainMenuPanel menuPanel;
	protected GamePanel gamePanel;
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
			
			System.out.println("Ticks: " + ticks + " FPS: " + fps);
			ticks = 0;
		}
	}
	
	public void renderGame() {
		frameCount++;
		refreshPanel();
		//we will call methods in here that will be necessary to draw every frame.
	}
	
	private void refreshPanel() {
		displayedPanel.revalidate();
		displayedPanel.repaint();
	}
	
	private void initPanels() {
		gamePanel = new GamePanel();
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
		
		//CURRENTLY WORKING ON THIS PLEASE LEAVE ALL LOGIC ALONE
//		while(continueGame()) {
//			playerTurn();
//		}
		
		if (compareGoals() == null) {
			System.out.println("TIE!");
			//TODO: call methods to display tie message!
		}
		else if (compareGoals().equals(mancalaBoard.getPlayer1())) {
			//TODO: call methods to display player1 winning message!
		}
		else {
			//TODO: call methods to display player2 winning message!
		}
		
		resetGame();
		System.out.println(mancalaBoard);
	}
	
	private void playerTurn() {
		//PLAYER PICKS BUTTON (THE ACTIONLISTENER METHOD FOR THE BUTTON SETS THE CURRENTLOCATION TO THAT INDEX
		//BUTTON ADD STONES TO THE PLAYERS HAND THAT WAS IN THAT PILE AND SETS PILE TO NULL BY CALLING THE PICKUP BOARD FUNCTION
		
		int initialHandAmount = currentPlayer.getHandAmount();
		for (int i = 0; i < initialHandAmount; i++) {
			
			mancalaBoard.advanceSlot(currentPlayer);
			
			//CHECK TO SEE IF THIS SHOULD BE - 1 OR NOT
			if ((i == initialHandAmount - 1) && (mancalaBoard.isInGoal(currentPlayer))) {
				if (continueGame()) {
					playerTurn();
				}
				else {
					//SEE IF THIS ENDS THE GAME
					System.out.println("THIS SHOULD END THE GAME!");
					return;
				}
			}
			//CHECK TO SEE IF THIS SHOULD BE - 1 OR NOT
			else if ((i == initialHandAmount - 1) && (mancalaBoard.getSlotArray()[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() == 0)) {
				
				//ADD STONE AND STONES ACROSS INTO CURRENT PLAYER'S PILE
				for (int y = 0; y < mancalaBoard.getSlotArray()[0].length; y++) {
					currentPlayer.setHand(mancalaBoard.getSlotArray()[mancalaBoard.getCurrentSlotX()][y].getStones());
					
					for (int k = 0; k < currentPlayer.getHandAmount(); k++) {
						currentPlayer.getGoal().getStones().add(currentPlayer.getHand().get(k));
						currentPlayer.getHand().remove(i);
						System.out.println("Player hand amount: " + currentPlayer.getHandAmount());
					}
				}
			}
			else {
				switchPlayers();
			}
		}
	}
	
	private void switchPlayers() {
		
		if (currentPlayer.equals(mancalaBoard.getPlayer1())) {
			currentPlayer = mancalaBoard.getPlayer2();
		}
		else {
			currentPlayer = mancalaBoard.getPlayer1();
		}
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
		
		System.out.println("isEmpty = " + isEmpty);
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
	
	public GamePanel getMainPanel() {
		return gamePanel;
	}
}
