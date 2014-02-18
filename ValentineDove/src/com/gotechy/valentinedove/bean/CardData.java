package com.gotechy.valentinedove.bean;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class CardData {

	@ElementList
	List<C> cards;

	public List<C> getCards() {
		return cards;
	}

	public void setCards(List<C> cards) {
		this.cards = cards;
	}
	
	
}
