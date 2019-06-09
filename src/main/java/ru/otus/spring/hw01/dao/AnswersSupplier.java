package ru.otus.spring.hw01.dao;

import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.stereotype.Repository;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;
import ru.otus.spring.hw01.mapper.TaskMapper;

@Repository("answersSupplier")
public class AnswersSupplier implements Supplier<Queue<Twit>> {
	
	private final Supplier<Queue<Task>> tasksSupplier;
	private final TaskMapper taskMapper;
	
	public AnswersSupplier(Supplier<Queue<Task>> tasksSupplier, TaskMapper taskMapper) {
		super();
		this.tasksSupplier = tasksSupplier;
		this.taskMapper = taskMapper;
	}

	@Override
	public Queue<Twit> get() {
		return taskMapper.tasks2answers(tasksSupplier.get()); //taskMapper->Function
	}

}
