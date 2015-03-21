package com.csmancala.file;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FileSystem {
	
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice gd = ge.getDefaultScreenDevice();
	private static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	private static ClassLoader classLoader = FileSystem.class.getClassLoader();
	
	public static String getClassLoaderResourceDirectory(String parent, String fileName) {
		if (fileName != null && !fileName.isEmpty()) {
			return parent + "/" + fileName;
		}
		else {
			return parent;
		}
	}
	
	public static File getClassLoaderResourceFile(String directory, String fileName) {
		URL url = null;
		File f = null;
		url = classLoader.getResource(directory + "/" + fileName);
		try {
			f = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public static BufferedImage readImageFile(File f) throws IOException {
		Image im = null;
		BufferedImage bi = null;

		try {
			im = ImageIO.read(f);
			bi = gc.createCompatibleImage(im.getWidth(null), im.getHeight(null), Transparency.TRANSLUCENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Graphics g = bi.createGraphics();
		g.drawImage(im, 0, 0, im.getWidth(null), im.getHeight(null), null);
		g.dispose();
		return bi;
	}
	
	public static BufferedImage loadImageFromJar(String directory, String imageNameDotExtension) {
		BufferedImage bi = null;
		
		try {
			bi = FileSystem.readImageFile(FileSystem.getClassLoaderResourceFile(directory, imageNameDotExtension));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
	
}
