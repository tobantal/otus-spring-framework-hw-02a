package ru.otus.spring.hw01.service;

import java.util.Queue;

import ru.otus.spring.hw01.dto.Twit;

// TaskInfoProvider
public interface TaskDispatcher { // AnswersSupplier & QuestionsSupplier repositories
	
	Queue<Twit> getAnswers();
	
	Queue<Twit> getQuestions();
}
