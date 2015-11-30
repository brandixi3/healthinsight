package com.brandixi3.i3labs.nlp.core;

public interface InitializableModule {

	/**
     * Initialize the component. Implementations should override this method to
     * perform any initialization logic necessary.
     * 
     */
    void init() throws InitializationException;

    /**
     * Stops the component.
     */
    void stop();
}
