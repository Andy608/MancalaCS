package com.csmancala.util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class MonitorSpecs {

	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static DisplayMode userDisplay = gd.getDisplayMode();
	
	public static GraphicsDevice getGraphics() {
		return gd;
	}
	
	public static DisplayMode getDisplay() {
		return userDisplay;
	}
	
	public static int getDisplayDepth() {
		return userDisplay.getBitDepth();
	}
}
