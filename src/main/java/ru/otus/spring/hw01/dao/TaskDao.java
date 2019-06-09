package ru.otus.spring.hw01.dao;

import java.util.Queue;
import java.util.function.Supplier;

import ru.otus.spring.hw01.domain.Task;


public interface TaskDao extends Supplier<Queue<Task>> {
	
}
