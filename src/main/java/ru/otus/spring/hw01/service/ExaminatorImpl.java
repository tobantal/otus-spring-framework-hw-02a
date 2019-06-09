package ru.otus.spring.hw01.service;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.dao.TaskDao;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.dto.Twit;

@Service // @Controller
public class ExaminatorImpl implements Examinator {

	private final TaskDao taskDao;
	private final AnswerTester answerTester;
	private final Interviewer interviewer;
	private final ReportBuilder reportBuilder;
	private final LocaleMessageProvider localeMessageProvider;

	public ExaminatorImpl(TaskDao taskDao, AnswerTester answerTester, 
			Interviewer interviewer, ReportBuilder reportBuilder,
			LocaleMessageProvider localeMessageProvider) {
		this.taskDao = taskDao; // <- TasksDispatcher
		this.answerTester = answerTester;
		this.interviewer = interviewer;
		this.reportBuilder = reportBuilder;
		this.localeMessageProvider = localeMessageProvider;
		// может для частных вопросов отдельный интервьювер
		// или добавить в интревьювер метод для одного вопроса
	}
	
	// сделать методы пабликами для тестирования
	// можно подсовывать псевдо интервьюера
	private String askFirstName() {
		String questionFirstName = localeMessageProvider.getMessage("question.firstname", null);
		return interviewer.ask(questionFirstName);
	}
	
	private String askLastName() {
		String questionLastName = localeMessageProvider.getMessage("question.lastname", null);
		return interviewer.ask(questionLastName);
	}
	
	// askQuestions()
	
	private String checkAnswers(Queue<Task> tasks, Queue<String> answers) {
		return answerTester.test(tasks, answers);
	}
	
	private void printResult(String firstName, String lastName, String grade) {
		String report = localeMessageProvider.getMessage("report.template", 
				new String[]{firstName, lastName, grade});
				//reportBuilder.buildReport(firstName, lastName, grade);
		System.out.println(report);
	}


	@Override
	public void takeAnExam() {
		// ask fName and LastName
		// Report.setName ...
		String firstName = askFirstName();
		String lastName = askLastName();
		
		// Queue<> = askQuestions()
		Queue<String> questions = new LinkedList<String>();
		Queue<Task> tasks = taskDao.get();
		tasks.forEach(task -> questions.add(task.getQuestion()));
		Queue<String> answers = interviewer.apply(questions);

		//String firstName = answers.poll();
		//String lastName = answers.poll();

		String grade = checkAnswers(tasks, answers); // return String, or Grade

		printResult(firstName, lastName, grade);
	}

}
