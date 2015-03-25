package com.csmancala.file;

public class DirectoryMaster {
	
	// Resources folder
	public static final String resourcesFolder = FileSystem.getClassLoaderResourceDirectory("resources", "");
	
	// resources/...
	public static final String imagesFolder = FileSystem.getClassLoaderResourceDirectory(resourcesFolder, "images");
	public static final String backgroundsFolder = FileSystem.getClassLoaderResourceDirectory(imagesFolder, "backgrounds");
	public static final String boardFolder = FileSystem.getClassLoaderResourceDirectory(imagesFolder, "board");
	public static final String cursorsFolder = FileSystem.getClassLoaderResourceDirectory(imagesFolder, "cursors");
	public static final String stonesFolder = FileSystem.getClassLoaderResourceDirectory(imagesFolder, "stones");
}
