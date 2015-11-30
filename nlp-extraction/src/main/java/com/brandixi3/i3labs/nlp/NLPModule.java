package com.brandixi3.i3labs.nlp;

import java.util.List;

import com.brandixi3.i3labs.nlp.core.InitializableModule;
import com.brandixi3.i3labs.nlp.core.ModuleException;
import com.brandixi3.i3labs.nlp.model.Event;

// TODO: Auto-generated Javadoc
/**
 * The Interface NLPModule.
 */
public interface NLPModule extends InitializableModule {

	/**
	 * Process text.
	 *
	 * @param text            the text
	 * @return the list
	 * @throws ModuleException the module exception
	 */
	List<Event> processText(String text) throws ModuleException;

	/**
	 * Enhance text html.
	 *
	 * @param text the text
	 * @return the string
	 * @throws ModuleException the module exception
	 */
	String highlightText(String text) throws ModuleException;

	/**
	 * Highlight text.
	 *
	 * @param text the text
	 * @param beginInsert the begin insert
	 * @param endInsert the end insert
	 * @return the string
	 * @throws ModuleException the module exception
	 */
	String highlightText(String text, String beginInsert, String endInsert)
			throws ModuleException;
}
