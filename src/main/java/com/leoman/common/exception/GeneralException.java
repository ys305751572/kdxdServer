package com.leoman.common.exception;


public class GeneralException extends BaseRuntimeException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 45761323734227434L;

	/**
     * Constructs a new exception without a detail message.
     */
    public GeneralException()
    {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param msg the detail message.
     */
    public GeneralException(String msg)
    {
        super(msg);
    }

    /**
     * Constructs a new exception with the specified detail message and nested <code>Throwable</code>s.
     * @param msg the detail message.
     * @param nested the nested <code>Throwable[]</code>.
     */
    public GeneralException(String msg, Throwable[] nested)
    {
        super(msg, nested);
    }
    /**
     * 
     * @param nested
     */
    public GeneralException(Throwable nested)
    {
        super(nested);
    }
    /**
     * Constructs a new exception with the specified detail message and nested <code>Throwable</code>.
     * @param msg the detail message.
     * @param nested the nested <code>Throwable</code>.
     */
    public GeneralException(String msg, Throwable nested)
    {
        super(msg, nested);
    }

    /**
     * Constructs a new exception with the specified detail message and failed object.
     * @param msg the detail message.
     * @param failed the failed object.
     */
    public GeneralException(String msg, Object failed)
    {
        super(msg, failed);
    }

    /**
     * Constructs a new exception with the specified detail
     * message, nested <code>Throwable</code>s, and failed object.
     * @param msg the detail message.
     * @param nested the nested <code>Throwable[]</code>.
     * @param failed the failed object.
     */
    public GeneralException(String msg, Throwable[] nested, Object failed)
    {
        super(msg, nested, failed);
    }

    /**
     * Constructs a new exception with the specified detail message, nested <code>Throwable</code>,
     * and failed object.
     * @param msg the detail message.
     * @param nested the nested <code>Throwable</code>.
     * @param failed the failed object.
     */
    public GeneralException(String msg, Throwable nested, Object failed)
    {
        super(msg, nested, failed);
    }
}