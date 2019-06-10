package ru.otus.spring.hw01.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;

@Service
public class InterviewerImpl implements Interviewer {

	// добавить метод для одного отчета
	// вернуться к счетчику запросов
	// считывать данные из файла properties

	private int limitQuestions;
	private String errorMsg;
	
	public InterviewerImpl(@Value("${questions.limit}") int limitQuestions,
			@Value("${error.questions.limit}") String errorMsg) {
		this.limitQuestions = limitQuestions;
	}

	@Override
	public Queue<Twit> apply(Queue<Twit> questions) {
		try(Scanner scanner = new Scanner(System.in)) {
			Queue<Twit> answers = new LinkedList<Twit>();
			questions.forEach(questionTwit -> {
				System.out.println(questionTwit.getText());
				String answerText = scanner.next();
				answers.add(new Twit(questionTwit.getId(), answerText));
				if(--limitQuestions < 0) {
					throw new IndexOutOfBoundsException(errorMsg);
				};
			});
			return answers;
		}
	}

}
