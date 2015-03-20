package com.csmancala.run;

import javax.swing.SwingUtilities;


public class Start {
	
	public static Mancala gameInstance;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				gameInstance = new Mancala();
				gameInstance.start();
			}
		});
	}
	
	public static Mancala getMancala() {
		return gameInstance;
	}
}
