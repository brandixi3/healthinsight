package com.brandixi3.i3labs.nlp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ctakes.typesystem.type.textspan.Sentence;
//import org.apache.ctakes.web.client.servlet.Pipeline;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;

import com.brandixi3.i3labs.nlp.core.InitializationException;
import com.brandixi3.i3labs.nlp.core.ModuleException;
import com.brandixi3.i3labs.nlp.model.Code;
import com.brandixi3.i3labs.nlp.model.Event;
import com.brandixi3.i3labs.nlp.model.Segment;
import com.brandixi3.i3labs.nlp.model.WordIndex;
import com.mysql.jdbc.Buffer;

/**
 * The Class NLPModule.
 */
public class NLPModuleImpl implements NLPModule {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(InitializationException.class);

	/** The pipeline. */
	private AnalysisEngine pipeline;

	/**
	 * Instantiates a new NLP module.
	 */
	public NLPModuleImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brandixi3.i3labs.nlp.core.InitializableModule#init()
	 */
	@Override
	public void init() throws InitializationException {
		LOGGER.info("Initilizing Pipeline...");
		AggregateBuilder aggregateBuilder = null;
		try {
			aggregateBuilder = Pipeline.getAggregateBuilder();
		} catch (Exception e) {
			throw new InitializationException("", e);
		}
		try {
			pipeline = aggregateBuilder.createAggregate();
		} catch (ResourceInitializationException e) {
			throw new InitializationException("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.brandixi3.i3labs.nlp.core.InitializableModule#stop()
	 */
	@Override
	public void stop() {
		pipeline.destroy();
	}

	/**
	 * Process text.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 * @throws ModuleException
	 * @throws ResourceInitializationException
	 * @throws AnalysisEngineProcessException
	 */
	public List<Event> processText(String text) throws ModuleException {
		System.out.println("Processing text");
		List<Event> events = null;
		if (text != null && text.trim().length() > 0) {
			JCas jcas;
			try {
				jcas = pipeline.newJCas();
			} catch (ResourceInitializationException e) {
				throw new ModuleException("", NLPModule.class, e);
			}
			jcas.setDocumentText(text);
			try {
				pipeline.process(jcas);
			} catch (AnalysisEngineProcessException e) {
				throw new ModuleException("", NLPModule.class, e);
			}

			events = extractForEvents(jcas);

			Collection<Sentence> sentences = JCasUtil.select(jcas,
					Sentence.class);
			for (Sentence sentence : sentences) {

				// System.out.println("SENTENSE: " + sentence.getCoveredText());
			}

			jcas.reset();

			// =======
			/*
			 * StringWriter sw = new StringWriter(); BufferedWriter writer = new
			 * BufferedWriter(sw); Collection<Sentence> sentences =
			 * JCasUtil.select(jcas, Sentence.class); for (Sentence sentence :
			 * sentences) { try { PrettyTextWriter.writeSentence(jcas, sentence,
			 * writer); } catch (IOException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } } try { writer.close(); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } System.out.println(sw.toString());
			 */

			// =======================
		}
		return events;
	}
	@Override
	public String highlightText(String text) throws ModuleException {
		return highlightText(text, "[", "]");
	}
	
	@Override
	public String highlightText(String text, String beginHighlight, String endHighlight) throws ModuleException {
		System.out.println("Processing text");
		
		String beginInsert = "[";
		String endInsert = "]";
		
		if (beginHighlight != null) {
			beginInsert = beginHighlight;
		}
		
		if (endHighlight != null) {
			endInsert= endHighlight;
		}
		
		
		StringBuilder buffer = new StringBuilder(text);
		if (text != null && text.trim().length() > 0) {
			JCas jcas;
			try {
				jcas = pipeline.newJCas();
			} catch (ResourceInitializationException e) {
				throw new ModuleException("", NLPModule.class, e);
			}
			jcas.setDocumentText(text);
			try {
				pipeline.process(jcas);
			} catch (AnalysisEngineProcessException e) {
				throw new ModuleException("", NLPModule.class, e);
			}

			Collection<TOP> annotations = JCasUtil.selectAll(jcas);

			List<WordIndex> wordIndexes = new ArrayList<>();
			for (TOP a : annotations) {
				WordIndex wordIndex = null;
				System.out.println(a.getType().getShortName());

				if ("SignSymptomMention".equals(a.getType().getShortName())) {
					wordIndex = new WordIndex();
					extractWordIndex(wordIndex, (FeatureStructure) a);
					wordIndexes.add(wordIndex);
				} else if ("AnatomicalSiteMention".equals(a.getType()
						.getShortName())) {
					wordIndex = new WordIndex();
					extractWordIndex(wordIndex, (FeatureStructure) a);
					wordIndexes.add(wordIndex);
				} else if ("MedicationMention".equals(a.getType()
						.getShortName())) {
					wordIndex = new WordIndex();
					extractWordIndex(wordIndex, (FeatureStructure) a);
					wordIndexes.add(wordIndex);
				}

			}

			jcas.reset();
			
			
			int offset = 0;
			
			for (int i = 0; i < wordIndexes.size(); i++) {
				
				
				int currentBegin = wordIndexes.get(i).getBegin();
				int nextBegin = 0;
				if (i+ 1 < wordIndexes.size()) {
					nextBegin = wordIndexes.get(i+ 1).getBegin();
				}
				
				int currentEnd = wordIndexes.get(i).getEnd();
				int max = 0;
				
				if ( currentBegin != nextBegin) {
					buffer.insert(currentBegin + offset, beginInsert);
					offset = offset + beginInsert.length();
				}
				
				if (currentBegin != nextBegin && nextBegin == 0){
					buffer.insert(currentEnd + offset, endInsert);
					offset = offset + endInsert.length();
				} else if (currentBegin != nextBegin && max <= currentEnd && currentEnd < nextBegin) {
					buffer.insert(currentEnd + offset, endInsert);
					offset = offset + endInsert.length();
				}

				if (max < currentEnd) {
					max = currentEnd;
				}
				
			}
			System.out.println("--------------------------------");
			
			System.out.println(buffer.toString());
		}
		return buffer.toString();
	}

	/**
	 * Extract for events.
	 *
	 * @param jcas
	 *            the jcas
	 * @return the list
	 */
	public List<Event> extractForEvents(JCas jcas) {
		Collection<TOP> annotations = JCasUtil.selectAll(jcas);

		List<Event> events = new ArrayList<>();

		Event event = null;
		for (TOP a : annotations) {
			Segment segment = null;
			System.out.println(a.getType().getShortName());
			if ("Sentence".equals(a.getType().getShortName())) {
				if (event != null) {
					events.add(event);
				}
				event = new Event();
			} else if ("SignSymptomMention".equals(a.getType().getShortName())) {
				segment = new Segment();
				segment.setType("Symptom");
			} else if ("AnatomicalSiteMention".equals(a.getType()
					.getShortName())) {
				segment = new Segment();
				segment.setType("Anatomy");
			} else if ("MedicationMention".equals(a.getType().getShortName())) {
				segment = new Segment();
				segment.setType("Medication");
			}

			extractFeatures(segment, (FeatureStructure) a);
			if (event != null) {
				event.addSegment(segment);
				segment = null;
			}

		}

		if (event != null) {
			events.add(event);
		}

		return events;

	}

	/**
	 * Extract features.
	 *
	 * @param segment
	 *            the segment
	 * @param fs
	 *            the fs
	 */
	public static void extractFeatures(Segment segment, FeatureStructure fs) {

		List<?> plist = fs.getType().getFeatures();
		Code code = null;
		for (Object obj : plist) {
			if (obj instanceof Feature) {
				Feature feature = (Feature) obj;
				String val = "";
				if (feature.getRange().isPrimitive()) {
					val = fs.getFeatureValueAsString(feature);
				} else if (feature.getRange().isArray()) {
					// Flatten the Arrays
					FeatureStructure featval = fs.getFeatureValue(feature);
					if (featval instanceof FSArray) {
						FSArray valarray = (FSArray) featval;
						for (int i = 0; i < valarray.size(); ++i) {
							FeatureStructure temp = valarray.get(i);
							extractFeatures(segment, temp);
						}
					}
				}
				if (feature.getName() != null
						&& val != null
						&& val.trim().length() > 0
						&& !"confidence".equalsIgnoreCase(feature
								.getShortName())) {
					if (segment != null) {
						if ("codingScheme".equals(feature.getShortName())) {
							code = new Code();
							code.setSystem(val);
						} else if ("code".equals(feature.getShortName())) {
							if (code != null) {
								code.setCode(val);
								segment.addCode(code);
							}

						} else if ("preferredText".equals(feature
								.getShortName())) {
							segment.setKey(val);
						} else if ("canonicalForm".equals(feature
								.getShortName())) {
							segment.setValue(val);
						}
					}
					System.out.println(feature.getShortName() + ": " + val);
				}
			}
		}

	}

	public static void extractWordIndex(WordIndex wordIndex, FeatureStructure fs) {

		// fs.
		List<?> plist = fs.getType().getFeatures();
		for (Object obj : plist) {
			if (obj instanceof Feature) {
				Feature feature = (Feature) obj;
				String val = "";
				if (feature.getRange().isPrimitive()) {
					val = fs.getFeatureValueAsString(feature);
				} else if (feature.getRange().isArray()) {
					// Flatten the Arrays
					FeatureStructure featval = fs.getFeatureValue(feature);
					if (featval instanceof FSArray) {
						FSArray valarray = (FSArray) featval;
						for (int i = 0; i < valarray.size(); ++i) {
							FeatureStructure temp = valarray.get(i);
							extractWordIndex(wordIndex, temp);
						}
					}
				}
				if (feature.getName() != null
						&& val != null
						&& val.trim().length() > 0
						&& !"confidence".equalsIgnoreCase(feature
								.getShortName())) {
					if (wordIndex != null) {
						if ("begin".equals(feature.getShortName())) {
							wordIndex.setBegin(Integer.parseInt(val));
						} else if ("end".equals(feature.getShortName())) {
							wordIndex.setEnd(Integer.parseInt(val));
						}
					}
					System.out.println(feature.getShortName() + ": " + val);
				}
			}
		}

	}

	public static void main(String[] args) throws ModuleException {
		NLPModuleImpl nlpMod = new NLPModuleImpl();
		nlpMod.init();

		nlpMod.highlightText("This patient is having cardiac arrest. He is a sevier patient. Patient fall from bed.");
	}
}
