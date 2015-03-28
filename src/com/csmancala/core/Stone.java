package com.csmancala.core;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.csmancala.file.ResourceLoader;

public class Stone {

	private static Random rand = new Random();
	private BufferedImage stoneImage = ResourceLoader.BLUE_STONE;
	
	public Stone() {
		this.setRandomStoneImage();
	}
	
	public BufferedImage getImage() {
		return this.stoneImage;
	}
	
	private void setRandomStoneImage() {
		BufferedImage[] stones = new BufferedImage[] {
				ResourceLoader.BLUE_STONE,
				ResourceLoader.GREEN_STONE,
				ResourceLoader.RED_STONE,
				ResourceLoader.YELLOW_STONE
		};
		
		this.stoneImage = stones[rand.nextInt(4)];
	}
}
