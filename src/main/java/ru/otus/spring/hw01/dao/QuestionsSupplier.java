package ru.otus.spring.hw01.dao;

import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.mapper.TaskMapper;

@Repository("questionsSupplier")
public class QuestionsSupplier implements Supplier<Queue<Twit>> {
	
	private final Supplier<Queue<Task>> tasksSupplier;
	private final TaskMapper taskMapper;
	
	public QuestionsSupplier(Supplier<Queue<Task>> tasksSupplier, TaskMapper taskMapper) {
		super();
		this.tasksSupplier = tasksSupplier;
		this.taskMapper = taskMapper;
	}

	@Override
	public Queue<Twit> get() {
		return taskMapper.tasks2questions(tasksSupplier.get()); //taskMapper->Function
	}

}
