package com.leoman.common.logger;

import org.apache.log4j.Logger;

public  class Log4JLogger extends BaseLogger{
	public static final Log4JLogger DEFAULT;
	static{
		DEFAULT = getLoggerInstance("Default");
	}
	
	
	/** The Log4J logger being used */
    private final Logger logger;
    
    public static Log4JLogger getLoggerInstance(String logName){
    	return new Log4JLogger(logName);
    }
    
    /**
     * Constructor using Log4J.
     * @param logName Name of the logging category
     */
    public Log4JLogger(String logName)
    {
        // Logging assumed to be configured by user via "log4j.configuration"
        // Use Logger instead of LogManager so that "logback" (http://logback.qos.ch) can work
        logger = Logger.getLogger(logName);
    }

    /**
     * Log a debug message.
     * @param msg The message
     */
    public void debug(String msg)
    {
        logger.debug(msg);
    }

    /**
     * Log a debug message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public void debug(String msg, Throwable thr)
    {
        logger.debug(msg, thr);
    }

    /**
     * Log an info message.
     * @param msg The message
     */
    public void info(String msg)
    {
        logger.info(msg);
    }

    /**
     * Log an info message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public void info(String msg, Throwable thr)
    {
        logger.info(msg, thr);
    }

    /**
     * Log a warning message.
     * @param msg The message
     */
    public void warn(String msg)
    {
        logger.warn(msg);
    }

    /**
     * Log a warning message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public void warn(String msg, Throwable thr)
    {
         logger.warn(msg, thr);
    }

    /**
     * Log an error message.
     * @param msg The message
     */
    public void error(String msg)
    {
         logger.error(msg);
    }

    /**
     * Log an error message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public void error(String msg, Throwable thr)
    {
        logger.error(msg, thr);
    }

    /**
     * Log an fatal message.
     * @param msg The message
     */
    public void fatal(String msg)
    {
        logger.fatal(msg);
    }

    /**
     * Log a fatal message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public void fatal(String msg, Throwable thr)
    {
        logger.fatal(msg, thr);
    }

    /**
     * Accessor for whether debug logging is enabled
     * @return Whether it is enabled
     */
    public boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }

    /**
     * Accessor for whether info logging is enabled
     * @return Whether it is enabled
     */
    public boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }
}
