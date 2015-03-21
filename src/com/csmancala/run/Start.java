package com.csmancala.run;

import javax.swing.SwingUtilities;

import com.csmancala.file.ResourceLoader;


public class Start {
	
	public static Mancala gameInstance;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ResourceLoader.loadResources();
				
				gameInstance = new Mancala();
				gameInstance.start();
			}
		});
	}
	
	public static Mancala getMancala() {
		return gameInstance;
	}
}
