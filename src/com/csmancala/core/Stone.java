package com.csmancala.core;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.csmancala.component.MancalaFrame;
import com.csmancala.file.ResourceLoader;
import com.csmancala.util.TransformImage;

public class Stone {

	private static Random rand = new Random();
	private BufferedImage stoneImage;
	private Point offset;
	private Dimension buttonSize;
	private int rotation;
	
	public Stone() {
		buttonSize = new Dimension(0, 0);
		offset = new Point(0, 0);
		this.setRandomStoneImage();
		this.randomizeStone();
	}
	
	public void setImage(BufferedImage stoneScaled) {
		this.stoneImage = stoneScaled;
	}
	
	public BufferedImage getImage() {
		return this.stoneImage;
	}
	
	public void updateSize(int x, int y) {
		this.buttonSize = new Dimension(x, y);
	}
	
	public void setOffset(int x, int y) {
		this.offset = new Point(x, y);
	}
	
	public Point getOffset() {
		return offset;
	}
	
	public Dimension getStoneButton() {
		return buttonSize;
	}
	
	public void setRotation(int degrees) {
		rotation = degrees;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void randomizeStone() {
		if (buttonSize.width == 0 || buttonSize.height == 0) {
			this.updateSize((int)(ResourceLoader.SLOT_BACKGROUND.getWidth() * getDefaultMultiplier()), (int)(ResourceLoader.SLOT_BACKGROUND.getHeight()  * getDefaultMultiplier()));
		}
		
		if (RenderGraphics.getMultiplier() == 0.0) {
			this.setOffset(rand.nextInt((int)(buttonSize.width - (stoneImage.getWidth() * getDefaultMultiplier()))), rand.nextInt((int)(buttonSize.height - (stoneImage.getHeight() * getDefaultMultiplier()))));
		}
		else {
			this.setOffset(rand.nextInt((int)(buttonSize.width - (stoneImage.getWidth() * RenderGraphics.getMultiplier()))), rand.nextInt((int)(buttonSize.height - (stoneImage.getHeight() * RenderGraphics.getMultiplier()))));
		}
		TransformImage.rotateImage(stoneImage, rand.nextInt(361));
	}
	
	private void setRandomStoneImage() {
		BufferedImage[] stones = new BufferedImage[] {
				ResourceLoader.RED_STONE,
				ResourceLoader.ORANGE_STONE,
				ResourceLoader.YELLOW_STONE,
				ResourceLoader.GREEN_STONE,
				ResourceLoader.BLUE_STONE,
				ResourceLoader.PURPLE_STONE,
				ResourceLoader.GRAY_STONE
		};
		
		this.stoneImage = stones[rand.nextInt(stones.length)];
	}
	
	public Dimension getButtonSize() {
		return buttonSize;
	}
	
	private static double getDefaultMultiplier() {
		double width = MancalaFrame.minimumSize.getWidth();
		double multiplier = (double)(width / (double)1920);
		return multiplier;
	}
}
