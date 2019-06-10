package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dto.Twit;

@Service
public class AnswerTesterImpl implements AnswerTester {
	
	private final Supplier<Queue<Twit>> answersSupplier; 
	
	public AnswerTesterImpl(@Qualifier("answersSupplier") Supplier<Queue<Twit>> answersSupplier) {
		super();
		this.answersSupplier = answersSupplier;
	}

	@Override
	public String apply(Queue<Twit> userAnswers) {
		Queue<Twit> rightAnswers = answersSupplier.get();
		int count = 0;
		Twit rightAns;
		Twit userAns;
		while((rightAns = rightAnswers.poll()) != null && (userAns = userAnswers.poll()) != null) {
			if(rightAns.equals(userAns)) {
				count++;
			}
		}
		return "" + count;
	}


}
