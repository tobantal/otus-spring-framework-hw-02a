package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.Function;

import ru.otus.spring.hw01.dto.Twit;


public interface AnswerTester extends Function<Queue<Twit>, String> {

}
