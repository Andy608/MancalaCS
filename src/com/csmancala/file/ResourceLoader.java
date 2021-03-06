package com.csmancala.file;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.csmancala.run.Start;

public class ResourceLoader {
	//Images
	public static BufferedImage MANCALA_LOGO;
	public static BufferedImage WINNER_LOGO;
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
	public static BufferedImage WIN_TO_MENU_BUTTON;
	public static BufferedImage WIN_TO_MENU_HOVERED_BUTTON;
	public static BufferedImage RETURN_TO_MENU_BUTTON;
	public static BufferedImage RETURN_TO_MENU_HOVERED_BUTTON;
	
	public static BufferedImage MANCALA_INSTRUCTIONS;
	
	public static BufferedImage HIGHLIGHT_TIP;
	
	//Colors
	public static final Color GOLDEN = new Color(0xE7D065);
	public static final Color DARK_BROWN = new Color(0x2E251B);
	
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
		MANCALA_LOGO = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/mancala_logo.png");
		WINNER_LOGO = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/winner_logo.png");
		CREDITS_LOGO = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/credits_logo.png");
		CREDITS_NAMES = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/credits_names.png");
		
		TABLE_BACKGROUND = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/table_background.png");
		MANCALA_INSTRUCTIONS = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/mancala_rules.png");
		
		GOAL_BACKGROUND = ReadingHelper.getImageFromResourceFile("resources/images/board/mancala_goal.png");
		GOAL_HOVERED_BACKGROUND = ReadingHelper.getImageFromResourceFile("resources/images/board/mancala_goal_highlight.png");
		SLOT_BACKGROUND = ReadingHelper.getImageFromResourceFile("resources/images/board/mancala_slot.png");
		SLOT_HOVERED_BACKGROUND = ReadingHelper.getImageFromResourceFile("resources/images/board/mancala_slot_highlight.png");
		MANCALA_BOARD = ReadingHelper.getImageFromResourceFile("resources/images/board/mancala_board.png");
		
		RED_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/red_stone.png");
		ORANGE_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/orange_stone.png");
		YELLOW_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/yellow_stone.png");
		GREEN_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/green_stone.png");
		BLUE_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/blue_stone.png");
		PURPLE_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/purple_stone.png");
		GRAY_STONE = ReadingHelper.getImageFromResourceFile("resources/images/stones/gray_stone.png");
		
		PLAY_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/play_button.png");
		PLAY_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/play_hovered_button.png");
		RULES_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/rules_button.png");
		RULES_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/rules_hovered_button.png");
		CREDITS_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/credits_button.png");
		CREDITS_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/credits_hovered_button.png");
		QUIT_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/quit_button.png");
		QUIT_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/quit_hovered_button.png");
		WIN_TO_MENU_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/menu_button.png");
		WIN_TO_MENU_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/menu_hovered_button.png");
		RETURN_TO_MENU_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/return_to_menu.png");
		RETURN_TO_MENU_HOVERED_BUTTON = ReadingHelper.getImageFromResourceFile("resources/images/buttons/return_to_menu_hovered.png");
		
		HIGHLIGHT_TIP = ReadingHelper.getImageFromResourceFile("resources/images/backgrounds/highlight_tip.png");
	}
}
