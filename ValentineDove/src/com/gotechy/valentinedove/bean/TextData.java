package com.gotechy.valentinedove.bean;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class TextData {
	@ElementList
	List<T> texts;

	public List<T> getTexts() {
		return texts;
	}

	public void setTexts(List<T> texts) {
		this.texts = texts;
	}
	
}
