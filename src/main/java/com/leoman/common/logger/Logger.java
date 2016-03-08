package com.leoman.common.logger;

public class Logger{
	
	public static BaseLogger Log4J=Log4JLogger.DEFAULT;
	private static BaseLogger LOGGER=Log4J;
	
	public static void debug(String msg) {
		LOGGER.debug(getMessage(msg));
	}

	public static void debug(String msg, Throwable thr) {
		LOGGER.debug(getMessage(msg), thr);
	}

	public static void info(String msg) {
		LOGGER.info(getMessage(msg));
	}

	public static void info(String msg, Throwable thr) {
		LOGGER.info(getMessage(msg), thr);
	}

	public static void warn(String msg) {
		LOGGER.warn(getMessage(msg));
	}

	public static void warn(String msg, Throwable thr) {
		LOGGER.warn(getMessage(msg), thr);
	}

	public static void error(String msg) {
		LOGGER.error(getMessage(msg));
	}

	public static void error(String msg, Throwable thr) {
		LOGGER.error(getMessage(msg), thr);
	}

	public static void fatal(String msg) {
		LOGGER.fatal(getMessage(msg));
	}

	public static void fatal(String msg, Throwable thr) {
		LOGGER.fatal(getMessage(msg), thr);
	}

	public static boolean isDebugEnabled() {
		return LOGGER.isDebugEnabled();
	}

	public static boolean isInfoEnabled() {
		return LOGGER.isInfoEnabled();
	}
	private static String getMessage(String msg){
		return msg==null?"":msg;
		
	}

}
