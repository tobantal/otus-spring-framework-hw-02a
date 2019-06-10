package ru.otus.spring.hw01.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

public class AnswerTesterImplTest {

	private AnswerTester answerTester;
	private Queue<Twit> userAnswers;
	private Queue<Twit> rightAnswers;

	@BeforeEach
	public void setUp() {
		rightAnswers = new LinkedList<Twit>();
		userAnswers = new LinkedList<Twit>();
	}

	@Test
	public void check_right_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "a"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("1", answerTester.apply(userAnswers));
	}

	@Test
	public void check_wrong_answer() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(1L, "x"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("0", answerTester.apply(userAnswers));
	}

	@Test
	public void check_not_equals_id() {
		rightAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "a"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("0", answerTester.apply(userAnswers));
	}

	@Test
	public void check_total_count() {
		rightAnswers.add(new Twit(1L, "a"));
		rightAnswers.add(new Twit(2L, "b"));
		rightAnswers.add(new Twit(3L, "c"));
		rightAnswers.add(new Twit(4L, "d"));
		rightAnswers.add(new Twit(5L, "e"));
		
		userAnswers.add(new Twit(1L, "a"));
		userAnswers.add(new Twit(2L, "x"));
		userAnswers.add(new Twit(3L, "c"));
		userAnswers.add(new Twit(4L, "y"));
		userAnswers.add(new Twit(5L, "e"));
		answerTester = new AnswerTesterImpl(() -> rightAnswers);
		assertEquals("3", answerTester.apply(userAnswers));
	}

}
