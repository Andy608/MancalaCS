package com.csmancala.file;

import java.awt.image.BufferedImage;
import com.csmancala.run.Start;

public class ResourceLoader {

	//Images
	public static BufferedImage MANCALA_LOGO;
	public static BufferedImage CREDITS_LOGO;
	public static BufferedImage CREDITS_NAMES;
	
	public static BufferedImage TABLE_BACKGROUND;
	
	public static BufferedImage GOAL_BACKGROUND;
	public static BufferedImage GOAL_HOVERED_BACKGROUND;
	public static BufferedImage SLOT_BACKGROUND;
	public static BufferedImage SLOT_HOVERED_BACKGROUND;

	public static BufferedImage RED_STONE;
	public static BufferedImage ORANGE_STONE;
	public static BufferedImage YELLOW_STONE;
	public static BufferedImage GREEN_STONE;
	public static BufferedImage BLUE_STONE;
	public static BufferedImage PURPLE_STONE;
	public static BufferedImage GRAY_STONE;
	
	public static BufferedImage MANCALA_BOARD;
	
	public static BufferedImage PLAY_BUTTON;
	public static BufferedImage PLAY_HOVERED_BUTTON;
	public static BufferedImage RULES_BUTTON;
	public static BufferedImage RULES_HOVERED_BUTTON;
	public static BufferedImage CREDITS_BUTTON;
	public static BufferedImage CREDITS_HOVERED_BUTTON;
	public static BufferedImage QUIT_BUTTON;
	public static BufferedImage QUIT_HOVERED_BUTTON;
	public static BufferedImage RETURN_TO_MENU_BUTTON;
	public static BufferedImage RETURN_TO_MENU_HOVERED_BUTTON;
	
	public static BufferedImage MANCALA_INSTRUCTIONS;
	
	public static BufferedImage HIGHLIGHT_TIP;
	
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
			MANCALA_LOGO = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "mancala_logo.png");
			CREDITS_LOGO = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "credits_logo.png");
			CREDITS_NAMES = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "credits_names.png");
			
			TABLE_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "table_background.png");
			MANCALA_INSTRUCTIONS = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "mancala_rules.png");
			
			GOAL_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_goal.png");
			GOAL_HOVERED_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_goal_highlight.png");
			SLOT_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_slot.png");
			SLOT_HOVERED_BACKGROUND = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_slot_highlight.png");
			MANCALA_BOARD = FileSystem.loadImageFromJar(DirectoryMaster.boardFolder, "mancala_board.png");
			
			RED_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "red_stone.png");
			ORANGE_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "orange_stone.png");
			YELLOW_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "yellow_stone.png");
			GREEN_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "green_stone.png");
			BLUE_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "blue_stone.png");
			PURPLE_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "purple_stone.png");
			GRAY_STONE = FileSystem.loadImageFromJar(DirectoryMaster.stonesFolder, "gray_stone.png");
			
			PLAY_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "play_button.png");
			PLAY_HOVERED_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "play_hovered_button.png");
			RULES_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "rules_button.png");
			RULES_HOVERED_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "rules_hovered_button.png");
			CREDITS_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "credits_button.png");
			CREDITS_HOVERED_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "credits_hovered_button.png");
			QUIT_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "quit_button.png");
			QUIT_HOVERED_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "quit_hovered_button.png");
			RETURN_TO_MENU_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "return_to_menu.png");
			RETURN_TO_MENU_HOVERED_BUTTON = FileSystem.loadImageFromJar(DirectoryMaster.buttonsFolder, "return_to_menu_hovered.png");
			
			HIGHLIGHT_TIP = FileSystem.loadImageFromJar(DirectoryMaster.backgroundsFolder, "highlight_tip.png");
		}
		else {
			System.out.println("Resources can only be loaded before the game starts. Derp");
		}
	}
}
