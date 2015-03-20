package com.csmancala.run;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.csmancala.component.MFrame;

public class Mancala implements Runnable {

	protected JFrame frameInstance;
	protected JPanel panelInstance;
	
	private Thread mancalaThread;
	private static boolean isRunning;
	
	private static final double TIME_SLICE = 60.0D;
	private static final double DELTA_TIME = 1 / TIME_SLICE;
	
	int ticks;
	int frameCount;
	int fps;
	
	public Mancala() {
		panelInstance = new JPanel();
		frameInstance = new MFrame("Mancala!");
		frameInstance.add(panelInstance);
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
		double elapsedSeconds = 0.0D;
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
		
		//we will call methods in here that will be necessary to draw every frame.
	}
}
