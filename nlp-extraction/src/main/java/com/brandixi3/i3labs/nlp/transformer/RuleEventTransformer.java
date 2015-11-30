package com.brandixi3.i3labs.nlp.transformer;

import java.util.List;

import com.brandixi3.i3labs.nlp.model.Event;
import com.brandixi3.i3labs.nlp.model.Rank;
import com.brandixi3.i3labs.nlp.model.Segment;
import com.brandixi3.i3labs.nlp.model.rule.Finding;
import com.brandixi3.i3labs.nlp.model.rule.Medication;
import com.brandixi3.i3labs.nlp.model.rule.RuleEvent;

public class RuleEventTransformer {

	public RuleEvent transformEventsToRuleEvent(List<Event> events){
		
		RuleEvent ruleEvent = new RuleEvent();
		Rank rank = new Rank();
		rank.setValue(0);
		ruleEvent.setRank(rank);
		
		for (Event event : events) {
			List<Segment> segments = event.getSegments();
			for (Segment segment : segments) {
				if (segment != null && "Medication".equals(segment.getType().getType())) {
					Medication med = new Medication();
					med.setName(segment.getKey().getKey());
					med.setCode(segment.getCodes());
					ruleEvent.addMedication(med);
				} else if (segment != null && "Symptom".equals(segment.getType().getType())){
					Finding finding = new Finding();
					finding.setFinding(segment.getKey().getKey());
					//finding.setValue(segment.getValue().getValue());
					finding.setCodes(segment.getCodes());
					ruleEvent.addFinding(finding);
				}
			}
			
			
		}
		
		return ruleEvent;
	}
}
