package com.csmancala.file;

public class FileStructure {

	/*
	 * REMEMBER TO INIT DIRS IN FILEMANAGER WHEN ADDING NEW ONES
	 */
	
	//FS parent
	protected static Directory appDir;
	
	//Mancala main folder
	protected static Directory mancalaDir;
	
	public static Directory getAppDirectory() {
		return appDir;
	}
	
	public static Directory getMancalaDirectory() {
		return mancalaDir;
	}
}
