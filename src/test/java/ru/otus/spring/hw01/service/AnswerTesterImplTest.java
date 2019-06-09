package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.otus.spring.hw01.domain.Task;

public class AnswerTesterImplTest {

	private static AnswerTester answerTester;
	private Queue<Task> tasks;
	private Queue<String> answers;

	@BeforeAll
	public static void setUpClass() {
		answerTester = new AnswerTesterImpl();
	}

	@BeforeEach
	public void setUp() {
		tasks = new LinkedList<Task>();
		answers = new LinkedList<String>();
	}

	@Test
	public void check_right_answer() {
		tasks.add(new Task(1L, "Task1", "a"));
		answers.add("a");
		assertEquals(1, answerTester.test(tasks, answers));
	}

	@Test
	public void check_wrong_answer() {
		tasks.add(new Task(2L, "Task2", "b"));
		answers.add("x");
		assertEquals(0, answerTester.test(tasks, answers));
	}

	@Test
	public void check_equals_ignore_case() {
		tasks.add(new Task(2L, "Task2", "a"));
		answers.add("A");
		assertEquals(1, answerTester.test(tasks, answers));
	}

	@Test
	public void check_total_count() {
		tasks.add(new Task(1L, "Task1", "a"));
		tasks.add(new Task(2L, "Task2", "b"));
		tasks.add(new Task(3L, "Task3", "c"));
		tasks.add(new Task(4L, "Task4", "d"));
		tasks.add(new Task(5L, "Task5", "e"));
		answers.add("a");
		answers.add("x");
		answers.add("c");
		answers.add("D");
		answers.add("z");
		assertEquals(3, answerTester.test(tasks, answers));
	}

}
