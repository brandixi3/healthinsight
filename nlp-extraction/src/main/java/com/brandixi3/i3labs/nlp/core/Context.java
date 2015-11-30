package com.brandixi3.i3labs.nlp.core;

import java.util.List;

/**
 * The Class ContextImpl.
 */
public class Context {

	/** The container. */
	private static ModuleContainer container = new ModuleContainer();

	/**
	 * Instantiates a new context impl.
	 */
	public Context() {
		
	}
	

	/**
	 * Start.
	 */
	public static void start() {
		if (container.getAliveModules().isEmpty()) {
			System.out.println("No Active Modules to initialize..");
		}
		List<InitializableModule> modules = container.getAliveModules();
		for (InitializableModule module : modules) {
			module.init();
		}
	}


	/**
	 * Shutdown.
	 */
	public static void shutdown() {
		List<InitializableModule> modules = container.getAliveModules();
		for (InitializableModule module : modules) {
			module.stop();
		}
		container.flush();
	}

	/**
	 * Sets the modules.
	 *
	 * @param modules the new modules
	 */
	public static void setModules(List<InitializableModule> modules) {
		for (InitializableModule initializableModule : modules) {
			container.addModule(initializableModule);
		}		
	}
	
	/**
	 * Adds the module.
	 *
	 * @param module the module
	 */
	public static void addModule(InitializableModule module){
		container.addModule(module);
	}


	/**
	 * Disable.
	 *
	 * @param moduleClass the module class
	 */
	public static void disable(Class<?> moduleClass) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Gets the module.
	 *
	 * @param moduleClass the module class
	 * @return the module
	 */
	public static InitializableModule getModule(Class<?> moduleClass) {
		return container.getAliveModule(moduleClass);
	}
	
		
}
