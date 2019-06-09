package ru.otus.spring.hw01.mapper;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Component
public class TaskMapperImpl implements TaskMapper {

	@Override
	public Twit task2question(Task task) {
		return new Twit(task.getId(), task.getQuestion());
	}

	@Override
	public Twit task2answer(Task task) {
		return new Twit(task.getId(), task.getAnswer());
	}

	@Override
	public Queue<Twit> tasks2questions(Queue<Task> tasks) {
		Queue<Twit> questions = new LinkedList<Twit>();
		Task task;
		while( (task = tasks.poll()) != null ) {
			questions.add(task2question(task));
		}
		return questions;
	}

	@Override
	public Queue<Twit> tasks2answers(Queue<Task> tasks) {
		Queue<Twit> answers = new LinkedList<Twit>();
		Task task;
		while( (task = tasks.poll()) != null ) {
			answers.add(task2answer(task));
		}
		return answers;
	}

}
