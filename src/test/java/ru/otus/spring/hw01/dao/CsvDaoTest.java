package ru.otus.spring.hw01.dao;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import ru.otus.spring.hw01.App;
import ru.otus.spring.hw01.ConfigClass;
import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.service.LocaleMessageProvider;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.*;

@ContextConfiguration(classes = ConfigClass.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Методы Tasks DAO должны ")
class CsvDaoTest {
    
    @Value("${questions.limit}")
    private String limit;

    @Autowired
    LocaleMessageProvider mockLocaleMessageProvider;
    
    @Autowired
    LocaleMessageProvider fakeLocaleMessageProvider;
    
    @Deprecated
    @Test
    @DisplayName("находить csv-файлы в бандлах по строке в application.properties")
    void correct_loading_of_the_file0() {
    	given(mockLocaleMessageProvider.getMessage("csvfile", null)).willReturn("test_tasks.csv");
    	assertThatThrownBy(() -> new CsvDao(mockLocaleMessageProvider).get()).isNotInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("выдавать корректные задачи из корректного csv-файла")
    void correct_loading_of_the_file() {
        TaskDao taskDao = new CsvDao(fakeLocaleMessageProvider);
        Queue<Task> queue = taskDao.get();
        BiPredicate<Task, Integer> p = (t, i) -> t.getId() == i && t.getQuestion().equals("Task" + i) && t.getAnswer().equals("Answer" + i);
        assertTrue(IntStream.rangeClosed(1, 5).allMatch(i -> p.test(queue.poll(), i)));
        assertNull(queue.poll());
    }
}
