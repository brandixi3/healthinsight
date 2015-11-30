package com.brandixi3.i3labs.nlp;

import com.brandixi3.i3labs.nlp.core.ConfigurationModule;
import com.brandixi3.i3labs.nlp.core.Context;
import com.brandixi3.i3labs.nlp.core.ModuleException;

/**
 * The Class Main.
 */
public class Main implements Runnable{

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws ModuleException
	 *             the module exception
	 */
	public static void main(String[] args) throws ModuleException {
		Main main = new Main();
		main.run();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		Context.addModule(new ConfigurationModule());
		Context.addModule(new NLPModuleImpl());
		Context.addModule(new HTTPRestServerModule());

		Context.start();

		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Context.shutdown();
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
