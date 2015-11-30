package com.brandixi3.i3labs.nlp.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModuleContainer.
 */
public class ModuleContainer {

	/** The alive modules. */
	private final List<InitializableModule> initialModules = new CopyOnWriteArrayList<InitializableModule>();
	
	/** The alive modules. */
	private final List<InitializableModule> aliveModules = new CopyOnWriteArrayList<InitializableModule>();

	/**
	 * Gets the alive modules.
	 *
	 * @return the alive modules
	 */
	public List<InitializableModule> getAliveModules() {
		return aliveModules;
	}
	
	public InitializableModule getAliveModule(Class<?> moduleClass) {
		InitializableModule  module = null;
		for (InitializableModule initializableModule : aliveModules) {
			if (initializableModule.getClass().equals(moduleClass)) {
				module = initializableModule;
			}
		}
		return module;
	}
	
	/**
	 * Adds the module.
	 *
	 * @param module the module
	 */
	public void addModule(InitializableModule module){
		aliveModules.add(module);
	}
	
	/**
	 * Flush.
	 */
	public void flush(){
		aliveModules.clear();
	}
}
