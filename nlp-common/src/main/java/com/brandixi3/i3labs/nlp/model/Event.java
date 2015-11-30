package com.brandixi3.i3labs.nlp.model;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 */
public class Event {

	/** The segments. */
	private List<Segment> segments = new ArrayList<>();

	/** The text. */
	private String text;

	/** The description. */
	private String description;
	/** The uuid. */
	private String uuid;

	/**
	 * Gets the segments.
	 *
	 * @return the segments
	 */
	public List<Segment> getSegments() {
		return segments;
	}

	/**
	 * Sets the segments.
	 *
	 * @param segments
	 *            the new segments
	 */
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	
	/**
	 * Adds the segment.
	 *
	 * @param segment the segment
	 */
	public void addSegment(Segment segment){
		this.segments.add(segment);
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 *
	 * @param uuid
	 *            the new uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "Event [segments=" + segments + ", text=" + text
				+ ", description=" + description + ", uuid=" + uuid + "]";
	}
}
