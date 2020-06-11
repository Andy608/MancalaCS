package com.csmancala.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import com.csmancala.file.Errors.UnsupportedOperatingSystemException;
import com.csmancala.util.MancalaLogger;

public final class FileManager {

	private static final String s = File.separator;
	
	private enum OS {
		WINDOWS, MAC, LINUX, UNKNOWN
	}
	
	private static OS getOS() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		
		if(operatingSystem.contains("win")) return OS.WINDOWS;
		else if(operatingSystem.contains("mac")) return OS.MAC;
		else if(operatingSystem.contains("nix") || 
				operatingSystem.contains("nux") || 
				operatingSystem.contains("aix")) return OS.LINUX;
		else return OS.UNKNOWN;
	}
	
	public static void initalizeStoragePath() throws UnsupportedOperatingSystemException{
		OS operatingSystem = getOS();
		
		switch (operatingSystem) {
			case WINDOWS:
				String appData = System.getenv("APPDATA");
				FileStructure.appDir = new Directory(appData);
				break;
			case MAC:
				String appSupport = System.getProperty("user.home") + s + "Library" + s + "Application Support";
				FileStructure.appDir = new Directory(appSupport);
				break;
			case LINUX:
				String home = System.getProperty("user.home");
				FileStructure.appDir = new Directory(home);
				break;
			default:
				throw new UnsupportedOperatingSystemException("Unsupported Operating System (Windows, MacOS, Linux)");
		}
		
		if (operatingSystem.equals(OS.WINDOWS)) {
			//Windows
			String appData = System.getenv("APPDATA");
			FileStructure.appDir = new Directory(appData);
		}
		else if (operatingSystem.equals(OS.MAC)) {
			//Mac
			String appSupport = System.getProperty("user.home") + s + "Library" + s + "Application Support";
			FileStructure.appDir = new Directory(appSupport);
		}
		else if(operatingSystem.equals(OS.LINUX)) {
			//Linux, etc.
			String home = System.getProperty("user.home");
			FileStructure.appDir = new Directory(home);
		}
	}
	
//	private static void initDirectories() {
//		(FileStructure.mancalaDir = FileStructure.appDir.down("Mancala")).makeDirectories();
//		(FileStructure.optionsDir = FileStructure.sloverseDir.down("options")).makeDirectory();
//		(FileStructure.logDir = FileStructure.sloverseDir.down("logs")).makeDirectory();
//	}
	
//	private static void initFiles() {
//		createNewFile(FileStructure.optionsDir, "options.txt");
//		String options = "username=" + System.lineSeparator() + "ip=" + System.lineSeparator() + "port=";
//		WritingHelper.writeFile(FileStructure.optionsDir, "options.txt", options);
//		String s = ReadingHelper.readFile(FileStructure.optionsDir, "options.txt", 2, 3);
//		System.out.println(s);
//		
//		System.out.println(ReadingHelper.getImageFromResourceFile("test.png"));
//	}
	
	public static File retrieveFile(Directory d, String fileName) {
		try {
			return d.getFileFromDir(fileName);
		} catch (FileNotFoundException e) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "File: " + fileName + " not found after attempt to retrieve it!");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * WARNING: This is for ACTUAL files only. Directories should use Directory.mkdir()
	 * @return File was successfully created. False if it already exists or failed to be created.
	 */
	public static boolean createNewFile(Directory d, String fileName) {
		File f = new File(d.getFilePath() + File.separator + fileName);
		if (f.exists()) {
			return false;
		}
		
		try {
			f.createNewFile();
		} catch (IOException e) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "File could not be created!");
			MancalaLogger.logErrorMessage(Level.SEVERE, "Failed to create: " + d.getFilePath() + File.separator + fileName);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
