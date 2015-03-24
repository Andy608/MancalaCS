//package com.csmancala.file;
//
//import java.awt.image.BufferedImage;
//
//public class ResourceLoader {
//
//
//	public static void loadResources() {
//		//Images
//		public static final BufferedImage TABLE_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "table_background.png");
//		
//		public static final BufferedImage GOAL_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "goal_background.png");
//		public static final BufferedImage SLOT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "slot_background.png");
//
//		public static final BufferedImage MANCALA_BOARD = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_board.png");
//		public static final BufferedImage MANCALA_SHADOW = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_board_shadow.png");
//	}
//}

package com.csmancala.file;

import java.awt.image.BufferedImage;

import com.csmancala.run.Start;

public class ResourceLoader {

	//Images
	public static BufferedImage TABLE_BACKGROUND;
	
	public static BufferedImage GOAL_BACKGROUND;
	public static BufferedImage SLOT_BACKGROUND;

	public static BufferedImage MANCALA_BOARD;
	public static BufferedImage MANCALA_SHADOW;
	
	public static void loadResources() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			TABLE_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "table_background.png");
			GOAL_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "goal_background.png");
			SLOT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "slot_background.png");
			MANCALA_BOARD = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_board_concept.png");
			MANCALA_SHADOW = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_board_shadow.png");
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
}
