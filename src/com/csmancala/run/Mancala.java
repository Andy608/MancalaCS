package com.csmancala.run;

import java.util.Random;

import javax.swing.JPanel;

import com.csmancala.component.CreditsPanel;
import com.csmancala.component.GamePanel;
import com.csmancala.component.MainMenuPanel;
import com.csmancala.component.MancalaFrame;
import com.csmancala.component.RulesPanel;
import com.csmancala.component.WinPanel;
import com.csmancala.core.Board;
import com.csmancala.core.Player;
import com.csmancala.core.Slot;

public class Mancala implements Runnable {

	protected MancalaFrame frameInstance;
	protected MainMenuPanel menuPanel;
	protected GamePanel gamePanel;
	protected RulesPanel rulesPanel;
	protected CreditsPanel creditsPanel;
	protected JPanel displayedPanel;
	protected WinPanel winPanel;
	
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
		creditsPanel = new CreditsPanel();
		menuPanel = new MainMenuPanel();
		winPanel = new WinPanel();
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
		frameInstance.remove(displayedPanel);
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
		
		Player winner = this.compareGoals();
		
		if (winner != null) {
			System.out.println(winner.getName() + " Wins!");
		}
		else {
			System.out.println("It's a tie!");
		}
		
		this.winPanel.setWinner(winner);
		openWinScreen();
	}
	
	public void openRules() {
		switchPanel(rulesPanel);
	}
	
	public void switchPanel(JPanel newPanel) {
		//System.out.println("SWAPPING SCREENS");
		frameInstance.remove(displayedPanel);
		frameInstance.add(newPanel);
		displayedPanel = newPanel;
	}
	
	public void openCredits() {
		switchPanel(creditsPanel);
	}
	
	public void returnToMenu() {
		switchPanel(menuPanel);
	}
	
	public void openWinScreen() {
		switchPanel(winPanel);
	}
	
	//Never call this method unless it is from the button listeners!
	private void playerTurn() {
		
		//Glitch when player is about to win
		
		Slot[][] slotArray = mancalaBoard.getSlotArray();
		
		
		//This method gets called when the player clicks on a slot to pick up.
		
		//If the selected spot is on the wrong side of the board, tell them and return.
		//else if there are no stones in the selected pile, tell them and return.
		//else pick up the stones in that pile.
		
		//while there are still stones in the pile, advance one slot and remove a stone.
		
		//if the current slot is on the current players side and the current slot 1 stone then capture!
		
		
		//Pick up stones from a pile.
			//If there are no stones in the pile, or chosen a pile on the wrong side, then display message telling them to go again and return (Does not switch the player).
		if (!mancalaBoard.isCorrectSide(currentPlayer)) {
			System.out.println("YO BUB, YOU TRYIN'A BREAK THE GAME? PLEASE PICK AGAIN.");
			return;
		}
		else if (slotArray[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() <= 0) {
			System.out.println("There's no stones in that pile! Pick another one.");
			return;
		}
		else {
			mancalaBoard.pickUpStones(currentPlayer);
			System.out.println("Picking up stones!");
		}
		
		//while there are still stones in the players hand, continue to remove a stone from the players hand and place it in the next spot.
		while (currentPlayer.getHandAmount() > 0) {
			mancalaBoard.advanceSlot(currentPlayer);
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
		else if (mancalaBoard.isCorrectSide(currentPlayer) && slotArray[mancalaBoard.getCurrentSlotX()][mancalaBoard.getCurrentSlotY()].getStoneAmount() == 1) {
			System.out.println("CAPTURE!!");
			mancalaBoard.captureStones(currentPlayer);
		}
		
		//call switchplayer
		if (!continueGame()) {
			endGame();
			return;
		}
		else {
			switchPlayers();
		}
	}
	
	private void switchPlayers() {
		
		if (currentPlayer.equals(mancalaBoard.getPlayer1())) {
			currentPlayer = mancalaBoard.getPlayer2();
			//Start.getMancala().getGamePanel().player1Name
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
		
		return isEmpty;
	}
	
	private void addStonesToOpponent() {
		
		int y = getOppositeSide();
		Player oppositePlayer;
		
		if (currentPlayer.equals(mancalaBoard.getPlayer1())) oppositePlayer = mancalaBoard.getPlayer2();
		else oppositePlayer = mancalaBoard.getPlayer1();
		
		for (int x = 1; x < 7; x++) {
			
			mancalaBoard.setCurrentSlot(x, y);
			mancalaBoard.pickUpStones(oppositePlayer);
			
			if (oppositePlayer.equals(mancalaBoard.getPlayer1())) mancalaBoard.setCurrentSlot(0, 0);
			else mancalaBoard.setCurrentSlot(7, 1);
			
			int hand = oppositePlayer.getHandAmount();
			for (int i = 0; i < hand; i++) {
				mancalaBoard.addStoneToNewPile(oppositePlayer);
			}
		}
	}
	
	private int getOppositeSide() {
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
	
	public void resetGame() {
		menuPanel.setPlayer1Name(null);
		menuPanel.setPlayer2Name(null);
		switchPanel(menuPanel);
	}
	
	public Board getBoard() {
		return mancalaBoard;
	}
	
	public double getDeltaTime() {
		return elapsedSeconds;
	}
	
	public int getTicks() {
		return ticks;
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public JPanel getDisplayedPanel() {
		return displayedPanel;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public MainMenuPanel getMenuPanel() {
		return menuPanel;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
}
