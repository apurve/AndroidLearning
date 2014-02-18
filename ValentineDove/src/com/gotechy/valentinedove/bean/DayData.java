package com.gotechy.valentinedove.bean;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class DayData {
	@ElementList
	List<Day> days;

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

}
