package com.csmancala.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import com.csmancala.util.MancalaLogger;

public class WritingHelper {

	//Success or failure
	public static boolean writeFile(Directory d, String fileName, String contents) {
		File f = FileManager.retrieveFile(d, fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(contents);
			writer.close();
		}
		catch (IOException ioe) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "Writing to file failed!");
			MancalaLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + fileName);
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean appendToFile(Directory d, String fileName, String contents) {
		File f = FileManager.retrieveFile(d, fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.append(contents);
			writer.close();
		}
		catch (IOException ioe) {
			MancalaLogger.logErrorMessage(Level.SEVERE, "Appending to file failed!");
			MancalaLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + fileName);
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
}
