package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.UnaryOperator;


public interface Interviewer extends UnaryOperator<Queue<String>> {

	String ask(String question);
	
}
