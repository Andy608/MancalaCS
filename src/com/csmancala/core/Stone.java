package com.csmancala.core;

import java.awt.Color;
import java.util.Random;

public class Stone {

	private static Random rand = new Random();
	
	//TODO: The stone texture.
//	public static BufferedImage stoneTexture;
	
	private Color stoneColor;
	
	public Stone() {
		stoneColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	public Color getColor() {
		return stoneColor;
	}
}
