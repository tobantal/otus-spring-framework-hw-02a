package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.BiFunction;

import ru.otus.spring.hw01.domain.Task;


public interface AnswerTester { //extends BiFunction<Queue<Task>, Queue<String>, String> 

	public String test(Queue<Task> tasks, Queue<String> answers);

}
