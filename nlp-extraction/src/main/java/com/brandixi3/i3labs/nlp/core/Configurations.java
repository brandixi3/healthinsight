package com.brandixi3.i3labs.nlp.core;

/**
 * The Class Configurations.
 */
public class Configurations {

	/**
	 * Gets the config.
	 *
	 * @param propertyKey the property key
	 * @return the config
	 */
	public static String getConfig(String propertyKey) {
		return ((ConfigurationModule) Context
				.getModule(ConfigurationModule.class))
				.getConfig(propertyKey);
	}
}
