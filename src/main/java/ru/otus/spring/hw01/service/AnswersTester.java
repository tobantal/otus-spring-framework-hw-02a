package ru.otus.spring.hw01.service;

import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Service("answersTester")
public class AnswersTester implements Function<Queue<Twit>, Integer> {
	
	private final Supplier<Queue<Twit>> answersSupplier;
	
	public AnswersTester(@Qualifier("answersSupplier") Supplier<Queue<Twit>> answersSupplier) {
		super();
		this.answersSupplier = answersSupplier;
	}

	@Override
	public Integer apply(Queue<Twit> answers) {
		// TODO Auto-generated method stub
		int count = 0;
		Queue<Twit> rightAnswers = answersSupplier.get();
		Twit ans;
		Twit rightAns;
		while((rightAns = rightAnswers.poll()) != null && (ans = answers.poll()) != null) {
			// если uuid не равны, выбросить исключение
			if(rightAns.getText().equalsIgnoreCase(ans.getText())) {
				count++;
			}
		}
		return count;
	}

}
