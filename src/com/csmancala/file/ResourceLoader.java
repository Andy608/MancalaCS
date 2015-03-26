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
	public static BufferedImage GOAL_HIGHLIGHT_BACKGROUND;
	public static BufferedImage SLOT_BACKGROUND;
	public static BufferedImage SLOT_HIGHLIGHT_BACKGROUND;

	public static BufferedImage MANCALA_BOARD;
	
	public static BufferedImage MANCALA_INSTRUCTIONS;
	
	////////////////////////////////////////////////
	
	public static void loadResources() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			loadImages();
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
	
	private static void loadImages() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			TABLE_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "table_background.png");
			MANCALA_INSTRUCTIONS = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "mancala_rules.png");
			
			GOAL_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_goal.png");
			GOAL_HIGHLIGHT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_goal_highlight.png");
			SLOT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_slot.png");
			SLOT_HIGHLIGHT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_slot_highlight.png");
			MANCALA_BOARD = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_board.png");
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
}
