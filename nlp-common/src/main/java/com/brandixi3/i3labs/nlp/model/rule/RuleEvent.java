package com.brandixi3.i3labs.nlp.model.rule;

import java.util.ArrayList;
import java.util.List;

import com.brandixi3.i3labs.nlp.model.Rank;


public class RuleEvent {

	private Demographics demographics;
	
	private List<Finding> finding = new ArrayList<>();
	
	private List<Medication> medication = new ArrayList<Medication>();
	
	private Rank rank = new Rank();
	

	public Demographics getDemographics() {
		return demographics;
	}

	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}

	public List<Finding> getFinding() {
		return finding;
	}

	public void setFinding(List<Finding> finding) {
		this.finding = finding;
	}
	
	public void addFinding(Finding finding){
		this.finding.add(finding);
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public void setMedication(List<Medication> medication) {
		this.medication = medication;
	}
	
	public void addMedication(Medication medication){
		this.medication.add(medication);
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
}
