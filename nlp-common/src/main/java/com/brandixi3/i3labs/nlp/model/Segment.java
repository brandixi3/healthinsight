package com.brandixi3.i3labs.nlp.model;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Segment.
 */
public class Segment {

	/** The key. */
	private Key key;

	/** The value. */
	private Value value;

	/** The code. */
	private List<Code> codes = new ArrayList<>();
	
	/** The type. */
	private SegmentType type;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(Key key) {
		this.key = key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = new Key();
		this.key.setKey(key);
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(Value value) {
		this.value = value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = new Value();
		this.value.setValue(value);
		this.value.setType(String.class.getName());
	}
	
	/**
	 * Gets the codes.
	 *
	 * @return the codes
	 */
	public List<Code> getCodes() {
		return codes;
	}

	/**
	 * Sets the codes.
	 *
	 * @param codes the new codes
	 */
	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 * @param system the system
	 */
	public void addCode(String code, String system) {
		Code newCode = new Code();
		newCode.setCode(code);
		newCode.setSystem(system);
		this.codes.add(newCode);
	}

	
	public void addCode(Code code) {
		this.codes.add(code);
	}
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public SegmentType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(SegmentType type) {
		this.type = type;
	}
	
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = new SegmentType();
		this.type.setType(type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Segment [key=").append(key).append(", value=")
				.append(value).append(", codes=").append(codes)
				.append(", type=").append(type).append("]");
		return builder.toString();
	}
}
