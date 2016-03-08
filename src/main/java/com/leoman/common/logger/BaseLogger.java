package com.leoman.common.logger;


public abstract class BaseLogger {
	/**
     * Log a debug message.
     * @param msg The message
     */
    public abstract void debug(String msg);

    /**
     * Log a debug message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public abstract void debug(String msg, Throwable thr);

    /**
     * Log an info message.
     * @param msg The message
     */
    public abstract void info(String msg);

    /**
     * Log an info message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public abstract void info(String msg, Throwable thr);

    /**
     * Log a warning message.
     * @param msg The message
     */
    public abstract void warn(String msg);

    /**
     * Log a warning message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public abstract void warn(String msg, Throwable thr);

    /**
     * Log an error message.
     * @param msg The message
     */
    public abstract void error(String msg);

    /**
     * Log an error message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public abstract void error(String msg, Throwable thr);

    /**
     * Log a fatal message.
     * @param msg The message
     */
    public abstract void fatal(String msg);

    /**
     * Log a fatal message with throwable.
     * @param msg The message
     * @param thr A throwable
     */
    public abstract void fatal(String msg, Throwable thr);

    /**
     * Accessor for whether debug logging is enabled
     * @return Whether it is enabled
     */
    public abstract boolean isDebugEnabled();

    /**
     * Accessor for whether info logging is enabled
     * @return Whether it is enabled
     */
    public abstract boolean isInfoEnabled();

}
