package com.csmancala.core;

import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;

import com.csmancala.file.ResourceLoader;
import com.csmancala.run.Start;
import com.csmancala.util.TransformImage;

public class Stone {

	private static Random rand = new Random();
	private BufferedImage stoneImage = ResourceLoader.BLUE_STONE;
	private Point location = null;
	
	public Stone() {
		this.setRandomStoneImage();
	}
	
	public void setImage(BufferedImage stoneScaled) {
		this.stoneImage = stoneScaled;
	}
	
	public BufferedImage getImage() {
		return this.stoneImage;
	}
	
	public void updateStone(int x, int y) {
		BufferedImage stoneScaled = TransformImage.scaleImage(this.getImage(), (int) (this.getImage().getWidth() * RenderGraphics.multiplier), (int) (this.getImage().getHeight() * RenderGraphics.multiplier), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		JButton button = Start.getMancala().getGamePanel().boardButtons[x][y];
		this.setLocation(button.getX() + rand.nextInt(button.getWidth() - stoneScaled.getWidth()), button.getY() + rand.nextInt(button.getHeight() - stoneScaled.getHeight()));
	}
	
	public void setLocation(int x, int y) {
		this.location = new Point(x, y);
	}
	
	public Point getLocation() {
		return this.location;
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
