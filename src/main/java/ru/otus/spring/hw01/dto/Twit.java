package ru.otus.spring.hw01.dto;

public class Twit {
	
	private final Long id;
	private final String text;
	
	public Twit(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
