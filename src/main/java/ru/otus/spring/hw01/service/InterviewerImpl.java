package ru.otus.spring.hw01.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class InterviewerImpl implements Interviewer {

	// добавить метод для одного отчета
	// вернуться к счетчику запросов
	// считывать данные из файла properties

	private Scanner scanner = new Scanner(System.in);
	private int limitQuestions = 7; // <- form properties

	@Override
	public Queue<String> apply(Queue<String> questions) {
		Queue<String> answers = new LinkedList<String>();
		questions.forEach(question -> {
			String answer = ask(question);
			answers.add(answer);
			limitQuestions--;
		});
		return answers;
	}

	@Override
	public String ask(String question) {
		System.out.println(question);
		limitQuestions--; // можно декримент вынести в 
		return scanner.next();
	}

}
