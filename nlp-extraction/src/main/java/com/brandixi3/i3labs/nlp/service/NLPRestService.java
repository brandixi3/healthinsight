package com.brandixi3.i3labs.nlp.service;

import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestMethod;
import io.advantageous.qbit.annotation.RequestParam;

import java.util.List;

import com.brandixi3.i3labs.nlp.NLPModule;
import com.brandixi3.i3labs.nlp.NLPModuleImpl;
import com.brandixi3.i3labs.nlp.core.Context;
import com.brandixi3.i3labs.nlp.core.ModuleException;
import com.brandixi3.i3labs.nlp.model.Event;
import com.brandixi3.i3labs.nlp.model.rule.RuleEvent;
import com.brandixi3.i3labs.nlp.transformer.RuleEventTransformer;

// TODO: Auto-generated Javadoc
/**
 * The Class NLPRestService.
 */
@RequestMapping("/nlp")
public class NLPRestService {

	/**
	 * Size.
	 *
	 * @return the int
	 */
	@RequestMapping("/test")
	public int size() {
		return 1;
	}

	/**
	 * Adds the.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 * @throws ModuleException
	 *             the module exception
	 */
	@RequestMapping(value = "/process/simple")
	public List<Event> processSimple(@RequestParam("text") String text)
			throws ModuleException {
		System.out.println("text recived " + text);
		NLPModule nlpModule = (NLPModule) Context
				.getModule(NLPModuleImpl.class);
		return nlpModule.processText(text);
	}

	/**
	 * Process.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 * @throws ModuleException
	 *             the module exception
	 */
	@RequestMapping(value = "/process")
	public RuleEvent process(@RequestParam("text") String text)
			throws ModuleException {
		System.out.println("text recived " + text);
		NLPModule nlpModule = (NLPModule) Context
				.getModule(NLPModuleImpl.class);
		RuleEventTransformer transformer = new RuleEventTransformer();
		RuleEvent ruleEvent = transformer.transformEventsToRuleEvent(nlpModule
				.processText(text));

		System.out.println(ruleEvent);
		// Return from rule engine.
		return RuleService.getExecutedRule(ruleEvent);
	}

	/**
	 * Process post.
	 *
	 * @param text the text
	 * @return the rule event
	 * @throws ModuleException the module exception
	 */
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public RuleEvent processPOST(@RequestParam("text") String text)
			throws ModuleException {
		return process(text);
	}

	/**
	 * Highlight.
	 *
	 * @param text the text
	 * @return the string
	 * @throws ModuleException the module exception
	 */
	@RequestMapping(value = "/highlight")
	public String highlight(@RequestParam("text") String text, @RequestParam("begin") String beginHighlight, @RequestParam("end") String endHighlight) throws ModuleException {
		NLPModule nlpModule = (NLPModule) Context
				.getModule(NLPModuleImpl.class);
		return nlpModule.highlightText(text, beginHighlight, endHighlight);
	}
}
