package com.csmancala.file;

import java.awt.image.BufferedImage;

import com.csmancala.run.Start;

public class ResourceLoader {

	//Images
	public static BufferedImage woodenBackground;
	
	public static void loadResources() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			woodenBackground = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "background.png");
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
}
