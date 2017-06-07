package com.cly.logging;

 
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CLYLogger {
	
	private Logger logger; 
	
	public static int LEVEL_DEBUG=Level.DEBUG_INT;
	public static int LEVEL_ERROR=Level.ERROR_INT;
	public static int LEVEL_FATAL=Level.FATAL_INT;
	public static int LEVEL_INFO=Level.INFO_INT;
	public static int LEVEL_OFF=Level.OFF_INT;
	public static int LEVEL_TRACE=Level.TRACE_INT;
	public static int LEVEL_WARN=Level.WARN_INT;
	public static int LEVEL_ALL=Level.ALL_INT;
	
	
	public CLYLogger(Logger logger){
		this.logger=logger;		 
	}
	
	public void info(Object msg){
		logger.info(msg);
	}
	
	public void debug(Object msg){
		logger.debug(msg);
	}
	
	public void fatal(Object msg){
		logger.fatal(msg);
	}
	
	public void fatalException(Exception e){
		logger.fatal(e.getMessage());
	}	
	
	
	public void error(Object msg){
		logger.error(msg);
	} 
	
	public void errorException(Exception e){
		logger.error(e.getMessage());
	} 
	
	public void warn(Object msg){
		logger.warn(msg);
	} 
	
	public int getLevel(){
		
	 	 return logger.getLevel().toInt();
	}
	
	public static void systemErr(Exception e){
		System.err.println(e.getMessage());
	}
	
	public static void systemErr(Object msg){
		System.err.println(msg);
	}
	
	public static void systemInfo(Object msg){
		System.out.println(msg);
	}

}
