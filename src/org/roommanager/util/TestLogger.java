package org.roommanager.util;

import org.apache.log4j.Logger;

public class TestLogger {
	
	   static Logger log = Logger.getLogger(TestLogger.class.getName());
	   
	   public static void debug(String message){
		   log.debug(message);
	   }
	   
	   public static void info(String message){
		   log.info(message);
	   }
	   
	   public static void warn(String message){
		   log.warn(message);
	   }
	   
	   public static void error(String message){
		   log.error(message);
	   }
}
