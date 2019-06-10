package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.UnaryOperator;

import ru.otus.spring.hw01.dto.Twit;


public interface Interviewer extends UnaryOperator<Queue<Twit>> {
	
}
