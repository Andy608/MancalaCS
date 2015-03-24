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

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
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
	public static BufferedImage MANCALA_SHADOW;
	
	public static BufferedImage MANCALA_INSTRUCTIONS;
	
	private static BufferedImage OPEN_HAND_IMAGE;
	private static BufferedImage GRABBING_HAND_IMAGE;
	
	////////////////////////////////////////////////
	
	//Cursors
	public static Cursor CURSOR_OPEN_HAND;
	public static Cursor CURSOR_GRABBING_HAND;
	
	public static void loadResources() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			loadImages();
			loadCursors();
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
	
	private static void loadImages() {
		if (Start.getMancala() == null || !Start.getMancala().isRunning()) {
			TABLE_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "table_background.png");
			GOAL_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_goal.png");
			GOAL_HIGHLIGHT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_goal_highlight.png");
			SLOT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_slot.png");
			SLOT_HIGHLIGHT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_slot_highlight.png");
			MANCALA_BOARD = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_board_full.png");
			MANCALA_SHADOW = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_shadow.png");
			MANCALA_INSTRUCTIONS = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "mancala_rules.png");
			
//			OPEN_HAND_IMAGE = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "open_hand_cursor.png");
//			GRABBING_HAND_IMAGE = FileSystem.loadImageFromJar(DirectoryMaster.imagesFolder, "grabbing_cursor.png");
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
	
	private static void loadCursors() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		createCursors(toolkit);
	}
	
	private static void createCursors(Toolkit tk) {
		Point hotSpot = new Point(0, 0);
		
//		CURSOR_OPEN_HAND = tk.createCustomCursor(OPEN_HAND_IMAGE, hotSpot, "open_hand");
//		CURSOR_GRABBING_HAND = tk.createCustomCursor(GRABBING_HAND_IMAGE, hotSpot, "grabbing_hand");
		CURSOR_OPEN_HAND = tk.createCustomCursor(SLOT_BACKGROUND, hotSpot, "open_hand");
		CURSOR_GRABBING_HAND = tk.createCustomCursor(SLOT_BACKGROUND, hotSpot, "grabbing_hand");
	}
}
