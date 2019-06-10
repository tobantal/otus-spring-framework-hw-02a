package ru.otus.spring.hw01.dao;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import ru.otus.spring.hw01.App;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.service.LocaleMessageProvider;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ComponentScan
//@Component
//@PropertySource(value = "file:./application.properties")

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader=ConfigClass.class)

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
//@TestPropertySource("classpath:test.properties")
class CsvDaoTest {
	
	@Value("${questions.limit}")
	private String limit;

	//private static final String TEST_CSV_FILE = "test-tasks.csv";
	private static final BiPredicate<Task, Integer> NTH_TASK = (task, i) -> task.getId() == i
			&& task.getQuestion().equals("Task" + i) && task.getAnswer().equals("Answer" + i);

	//@Autowired
	//@Qualifier("fakeLocaleMessageProvider")
	private LocaleMessageProvider fakeLocaleMessageProvider;
	{
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		fakeLocaleMessageProvider = new FakeLocaleMessageProvider(ms, "en", "US");
		System.out.println(fakeLocaleMessageProvider);
	}
	
	@Test
	void correct_loading_of_the_file() {
		System.out.println(">>> " + limit);
		
		TaskDao taskDao = new CsvDao(fakeLocaleMessageProvider);

		Queue<Task> queue = taskDao.get();
		System.out.println(queue.peek().getQuestion());

		assertTrue(IntStream.rangeClosed(1, 5).allMatch(i -> NTH_TASK.test(queue.poll(), i)));
		assertNull(queue.poll());
	}
	

}
