package com.csmancala.file;

public class DirectoryMaster {
	
	// Resources folder
	public static final String resourcesFolder = FileSystem.getClassLoaderResourceDirectory("resources", "");
	
	// resources/...
	public static final String imagesFolder = FileSystem.getClassLoaderResourceDirectory(resourcesFolder, "images");
}
