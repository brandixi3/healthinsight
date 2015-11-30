package com.brandixi3.i3labs.nlp.core;

import org.apache.log4j.Logger;

public class InitializationException extends RuntimeException {

	private static final Logger LOGGER = Logger.getLogger(InitializationException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6213325255955496474L;

    /**
     * Instantiates a new initialization exception.
     * 
     * @param message
     *            the message
     */
    public InitializationException(String message) {
        super(message);
        LOGGER.error("Failed to initialize Module " + message);
    }

    /**
     * Instantiates a new initialization exception.
     * 
     * @param message
     *            the message
     * @param t
     *            the t
     */
    public InitializationException(String message, Throwable t) {
        super(message, t);
        LOGGER.error("Failed to initialize Module " + message, t);
    }
}
