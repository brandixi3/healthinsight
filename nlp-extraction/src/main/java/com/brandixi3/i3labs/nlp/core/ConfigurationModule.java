package com.brandixi3.i3labs.nlp.core;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

/**
 * The Class ConfigurationModule.
 */
public class ConfigurationModule implements InitializableModule {

	/** The config property location. */
	private String configPropertyLocation = "config/conf.properties";

	/** The resource loader. */
	ResourceLoader resourceLoader;

	/** The prop. */
	Properties prop = null;

	/* (non-Javadoc)
	 * @see com.brandixi3.i3labs.nlp.core.InitializableModule#init()
	 */
	@Override
	public void init() throws InitializationException {
		resourceLoader = new ResourceLoader(configPropertyLocation);
		prop = new Properties();
		try {
			prop.load(resourceLoader.getResource());
		} catch (NoSuchFileException e) {
			throw new InitializationException("No such config file", e);
		} catch (IOException e) {
			throw new InitializationException("io exception", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.brandixi3.i3labs.nlp.core.InitializableModule#stop()
	 */
	@Override
	public void stop() {
		prop.clear();
	}

	/**
	 * Sets the config property location.
	 *
	 * @param configPropertyLocation the new config property location
	 */
	public void setConfigPropertyLocation(String configPropertyLocation) {
		this.configPropertyLocation = configPropertyLocation;
	}

	/**
	 * Gets the config.
	 *
	 * @param key the key
	 * @return the config
	 */
	public String getConfig(String key) {
		return prop.getProperty(key);
	}
}
