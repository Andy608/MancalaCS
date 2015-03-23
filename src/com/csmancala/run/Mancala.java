package com.csmancala.run;

import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.component.MancalaFrame;
import com.csmancala.core.Board;
import com.csmancala.core.Player;
import com.csmancala.core.Stone;

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
	
	public void startGame() {
		frameInstance.remove(menuPanel);
		frameInstance.add(gamePanel);
		displayedPanel = gamePanel;
		
		currentPlayer = pickStartingPlayer();
		
		System.out.println("STARTING PLAYER: " + currentPlayer);
		
//		while(continueGame()) {
//			
//		}
		
		//PLAYER PICKS BUTTON
		
//		if (player.getHand.isEmpty()) {
//		}
		
		System.out.println(mancalaBoard);
	}
	
	private Player pickStartingPlayer() {
		
		int r = rand.nextInt(2);
		
		switch (r) {
		case 0:  return mancalaBoard.getPlayer1();
		default: return mancalaBoard.getPlayer2();
		}
	}
	
	private boolean continueGame() {
		return checkIfSideIsEmpty();
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
			int y;
			//Set the y to the opposite player's side so they can collect their stones.
			if (currentPlayer.equals(mancalaBoard.getPlayer1())) y = 1;
			else y = 0;
			
			for (int x = 1; x < mancalaBoard.getSlotArray().length - 1; x++) {
				mancalaBoard.pickUpStones(currentPlayer);
				
				for (int i = 0; i < currentPlayer.getHandAmount(); i++) {
					currentPlayer.getGoal().getStones().add(currentPlayer.getHand().get(i));
					currentPlayer.getHand().remove(i);
					System.out.println("Player hand amount: " + currentPlayer.getHandAmount());
				}
			}
			return true;
		}
		return false;
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
