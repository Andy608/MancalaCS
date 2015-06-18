package com.csmancala.file;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.csmancala.util.MancalaLogger;

public class ReadingHelper {
	
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice gd = ge.getDefaultScreenDevice();
	private static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	
	public static String readFile(Directory d, String fileName) {
		File f = FileManager.retrieveFile(d, fileName);
		StringBuilder fileBuilder = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null) {
				fileBuilder.append(line);
				fileBuilder.append(System.lineSeparator());
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "File could not be read!");
			MancalaLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + fileName);
			e.printStackTrace();
			return null;
		}
		return fileBuilder.toString();
	}
	
	public static String readFile(Directory d, String fileName, int line) {
		String text = readFile(d, fileName);
		String s = System.lineSeparator();
		String[] lines = text.split(s);
		if (line < 1 || line > lines.length) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "Line out of bounds! Returning null...");
			return null;
		}
		return lines[line - 1];
	}
	
	//Start line inclusive end line exclusive
	public static String readFile(Directory d, String fileName, int startLine, int endLine) {
		StringBuilder lines = new StringBuilder();
		if (startLine >= endLine) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "Start line >= end line while reading!");
			return null;
		}
		for (int i = startLine; i < endLine; i++) {
			String line = readFile(d, fileName, i);
			if (line == null) {
				return null;
			}
			lines.append(line);
			lines.append(System.lineSeparator());
		}
		return lines.toString();
	}
	
	public static BufferedImage retrieveImage(Directory d, String imageName) {
		Image im = null;
		BufferedImage bi = null;
		File f = FileManager.retrieveFile(d, imageName);
		
		try {
			im = ImageIO.read(f);
			bi = gc.createCompatibleImage(im.getWidth(null), im.getHeight(null), Transparency.TRANSLUCENT);
		} catch (Exception e) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "Image file could not be found/read!");
			MancalaLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + imageName);
			e.printStackTrace();
		}
		Graphics g = bi.createGraphics();
		g.drawImage(im, 0, 0, im.getWidth(null), im.getHeight(null), null);
		g.dispose();
		return bi;
	}
	
	public static BufferedImage getImageFromResourceFile(String filePath) {
		InputStream stream = ReadingHelper.class.getClass().getResourceAsStream("/" + filePath);
		BufferedImage image;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "File " + File.separator + filePath + " could not be found by class loader!");
			e.printStackTrace();
			return null;
		}
		return image;
	}
	
//	public static AudioThingy retrieveAudio(Directory d, String audioName) {}
}
