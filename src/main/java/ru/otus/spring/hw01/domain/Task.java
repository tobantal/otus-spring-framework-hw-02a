package ru.otus.spring.hw01.domain;

public class Task {
	
	private Long id;
	private String question;
	private String answer;
	
	public Task(Long id, String text, String answer) {
		this.id = id;
		this.question = text;
		this.answer = answer;
	}

	public long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return String.format("Task [id=%s, question=%s, rightAnswer=%s]", id, question, answer);
	}
	
	

}
