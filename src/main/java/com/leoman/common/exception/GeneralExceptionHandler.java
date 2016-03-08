package com.leoman.common.exception;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.leoman.common.logger.Logger;

public final class GeneralExceptionHandler {
	/**
	 * 
	 * @param e
	 * @throws GeneralException
	 */
	public static final void handle(Throwable e) throws GeneralException {
		Object failed = null;
		String msg = null;
		exceptionLog(msg, e, failed);
		throw new GeneralException(e);
	}

	public static final void log(Throwable e) throws GeneralException {
		Object failed = null;
		String msg = null;
		exceptionLog(msg, e, failed);
	}

	/**
	 * 
	 * @param msg
	 */
	public static final void handle(String msg) {
		Throwable[] nested = null;
		Object failed = null;
		exceptionLog(msg, null, failed);
		throw new GeneralException(msg, nested, failed);
	}

	public static final void log(String msg) {
		Object failed = null;
		exceptionLog(msg, null, failed);
	}

	/**
	 * Constructs a new exception with the specified detail message and nested
	 * <code>Throwable</code>.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public static final void handle(String msg, Throwable e) {
		Object failed = null;
		exceptionLog(msg, e, failed);
		throw new GeneralException(msg, e, failed);
	}

	public static final void log(String msg, Throwable e) {
		Object failed = null;
		exceptionLog(msg, e, failed);
	}

	/**
	 * Constructs a new exception with the specified detail message and failed
	 * object.
	 * 
	 * @param msg
	 *            the detail message.
	 * @param failed
	 *            the failed object.
	 */
	public static final void handle(String msg, Object failed) {
		Throwable[] nested = null;
		exceptionLog(msg, null, failed);
		throw new GeneralException(msg, nested, failed);
	}

	public static final void log(String msg, Object failed) {
		exceptionLog(msg, null, failed);
	}

	/**
	 * Constructs a new exception with the specified detail message, nested
	 * <code>Throwable</code>, and failed object.
	 * 
	 * @param msg
	 *            the detail message.
	 * @param failed
	 *            the failed object.
	 */
	public static final void handle(String msg, Throwable e, Object failed) {
		exceptionLog(msg, e, failed);
		throw new GeneralException(msg, e, failed);
	}

	public static final void log(String msg, Throwable e, Object failed) {
		exceptionLog(msg, e, failed);
	}

	private static final void exceptionLog(String msg, Throwable nested, Object failed) {
		if (nested != null) {
			if (msg != null){
				msg=msg+"\r\n"+ExceptionUtils.getFullStackTrace(nested);
			}else{
				msg=ExceptionUtils.getFullStackTrace(nested);
			}
		}
		Logger.error(msg);
	}
}
