package com.csmancala.run;

import com.csmancala.component.MancalaFrame;
import com.csmancala.component.MancalaPanel;
import com.csmancala.core.Board;

public class Mancala implements Runnable {

	protected MancalaFrame frameInstance;
	protected MancalaPanel mainPanel;
	
	private Board mancalaBoard;
	
	private Thread mancalaThread;
	private static boolean isRunning;
	
	private static final double TIME_SLICE = 60.0D;
	private static final double DELTA_TIME = 1 / TIME_SLICE;
	
	int ticks;
	int frameCount;
	int fps;
	double elapsedSeconds;
	
	public Mancala() {
		initBoard();
		mainPanel = new MancalaPanel();
		
		frameInstance = new MancalaFrame("Mancala!");
		frameInstance.add(mainPanel);
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
		
		mainPanel.repaint();
		//we will call methods in here that will be necessary to draw every frame.
	}
	
	public void initBoard() {
		mancalaBoard = new Board("Bob", "Jeff");
		
		for (int i = 0; i < 20; i++) {
			System.out.println(mancalaBoard.getCurrentSlotX() + ", " + mancalaBoard.getCurrentSlotY());
			mancalaBoard.advanceCurrentSlot();
		}
		
		System.out.println(mancalaBoard);
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
	
	public MancalaPanel getMainPanel() {
		return mainPanel;
	}
}
