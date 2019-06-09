package ru.otus.spring.hw01.service;

import java.util.Queue;

import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.domain.Task;

@Service
public class AnswerTesterImpl implements AnswerTester {

	@Override
	public String test(Queue<Task> tasks, Queue<String> answers) {
		int count = 0;
		Task task;
		String answer;
		while((task = tasks.poll()) != null && (answer = answers.poll()) != null) {
			if(task.getAnswer().equalsIgnoreCase(answer)) {
				count++;
			}
		}
		return "" + count;
	}

}
