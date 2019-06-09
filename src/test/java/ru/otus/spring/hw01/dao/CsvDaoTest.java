package ru.otus.spring.hw01.dao;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import ru.otus.spring.hw01.domain.Task;

class CsvDaoTest {

	private static final String TEST_CSV_FILE = "test-tasks.csv";
	private static final BiPredicate<Task, Integer> NTH_TASK = (task, i) -> task.getId() == i
			&& task.getQuestion().equals("Task" + i) && task.getAnswer().equals("Answer" + i);

	/*
	@Test
	void correct_loading_of_the_file() {
		TaskDao taskDao = new CsvDao(TEST_CSV_FILE);

		Queue<Task> queue = taskDao.get();
		System.out.println(queue.peek().getQuestion());

		assertTrue(IntStream.rangeClosed(1, 5).allMatch(i -> NTH_TASK.test(queue.poll(), i)));
		assertNull(queue.poll());
	}
	*/

}
