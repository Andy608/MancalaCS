package com.csmancala.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Takes information and logs it to a file.
 * It can log error reports, play messages etc.
 */
public class MancalaLogger {

	private static Logger mancalaLogger = Logger.getLogger("Mancala Logger");
	
	public static void logErrorMessage(Level l, String message) {
		mancalaLogger.log(l, message);
		//Send message to file system to be logged.
		
		
		//Send to Server Control Panel to be output
	}
	
	public static void logSuspiciousMessage(Level l, String message) {
		mancalaLogger.log(l, "Suspicious activity: " + message);
		System.err.println("MANCALA LOGGER: Suspicious activity: " + message);
		//Send message to file system to be logged.
		
		
		//Send to Server Control Panel to be output
	}
}
