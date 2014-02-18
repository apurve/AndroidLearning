package com.gotechy.valentinedove.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class C {
	@Attribute
	int d;
	@Element
	String n;
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
}
