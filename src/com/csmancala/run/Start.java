package com.csmancala.run;

import javax.swing.SwingUtilities;

import com.csmancala.file.FileManager;
import com.csmancala.file.ResourceLoader;
import com.csmancala.file.Errors.UnsupportedOperatingSystemException;

public class Start {
	
	public static Mancala gameInstance;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					FileManager.initalizeStoragePath();
				} catch (UnsupportedOperatingSystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
