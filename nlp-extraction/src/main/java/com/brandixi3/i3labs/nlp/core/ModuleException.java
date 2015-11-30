package com.brandixi3.i3labs.nlp.core;

import org.apache.log4j.Logger;

/**
 * The Class ModuleException.
 */
public class ModuleException extends Exception {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(InitializationException.class);

	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4852580383596831745L;

    /**
     * Instantiates a new module exception.
     * 
     * @param message
     *            the message
     */
    public ModuleException(String message) {
        super(message);
        LOGGER.warn("Handler Exception Occured: " + message);
    }

    /**
     * Instantiates a new module exception.
     * 
     * @param message
     *            the message
     * @param clazz
     *            the clazz
     * @param e
     *            the e
     */
    public ModuleException(String message, Class<? extends InitializableModule> clazz, Throwable e) {
        super(message, e);
        LOGGER.warn("Handler Exception Occured in " + clazz.getName() + ":" + message);
    }
}
