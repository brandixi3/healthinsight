package com.brandixi3.i3labs.nlp.model;

public class Code {

	private String code;
	
	private String system;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "Code [code=" + code + ", system=" + system + "]";
	}
}
