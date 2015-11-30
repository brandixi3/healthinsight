package com.brandixi3.i3labs.nlp.model;

public class Value {

	private String value;
	
	private String type;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Value [value=" + value + ", type=" + type + "]";
	}
}


