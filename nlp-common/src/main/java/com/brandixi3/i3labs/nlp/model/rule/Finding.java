package com.brandixi3.i3labs.nlp.model.rule;

import java.util.List;

import com.brandixi3.i3labs.nlp.model.Code;

public class Finding {

	private String finding;

	private String value;

	private List<Code> codes;

	public Finding() {

	}

	public Finding(String finding) {
		this.finding = finding;
	}

	public String getFinding() {
		return finding;
	}

	public void setFinding(String finding) {
		this.finding = finding;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finding == null) ? 0 : finding.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Finding other = (Finding) obj;
		if (finding == null) {
			if (other.finding != null)
				return false;
		} else if (!finding.equals(other.finding))
			return false;
		return true;
	}

}
