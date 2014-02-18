package com.gotechy.valentinedove.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Day {
	
	@Attribute
	int d;
	@Element
	String m;
	
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	
	
}
