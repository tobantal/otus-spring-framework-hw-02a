package ru.otus.spring.hw01.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Repository;

import ru.otus.spring.hw01.domain.Task;
import ru.otus.spring.hw01.exception.ColumnNumberException;
import ru.otus.spring.hw01.service.LocaleMessageProvider;

@Repository
public class CsvDao implements TaskDao { //Supplier<Queue<Task>>
	
	private int totalRows = 5; // загружать из properties
	private int totalColumn = 3;
	private static final String DELIMITER = ";"; // загружать из properties
	private final LocaleMessageProvider localeMessageProvider;
	
	public CsvDao(LocaleMessageProvider localeMessageProvider) {
		this.localeMessageProvider = localeMessageProvider;
	}
	
	// добавить проверку на кол-во строк != 5
	// RowNumberException()

	@Override
	public Queue<Task> get() {
		String csvPath = getCsvPathOrThrow();
		
		InputStream inputStream = getCsvFileInputStreamOrThrow(csvPath);
		
		Queue<Task> queue = new LinkedList<>();
		
		try(Scanner scanner = new Scanner(inputStream);) {
			while (scanner.hasNextLine()) {
		        Task task = taskParseOrThrow(scanner.nextLine());
		        queue.add(task);
		    }
		} catch(ArrayIndexOutOfBoundsException aiobe) {
			// add to properties error.filenotfound
			// CsvRowFormatException
			throw new RuntimeException(new IOException(csvPath + " has bad format"));
		}
		
		return queue;
	}
	
	// NoSuchFieldInPropertiesException()
	private String getCsvPathOrThrow() throws NoSuchMessageException {
		return localeMessageProvider.getMessage("csvfile", null);
	}
	
	// CsvFileNotFoundException()
	private InputStream getCsvFileInputStreamOrThrow(String csvPath) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(csvPath);
		if (inputStream == null) {
			// add to properties error.filenotfound
			throw new RuntimeException(new FileNotFoundException(csvPath + " not found"));
		}
		return inputStream;
	}
	
	//TaskParseException()
	private Task taskParseOrThrow(String line) {
		String[] args = line.split(DELIMITER);
		if(args.length != totalColumn) { // проверка длины == 3
			throw new ColumnNumberException();
		}
		
		// проверка полей на null
		// ColumnNumberException
		return new Task(Long.parseLong(args[0]), args[1], args[2]);
	}

}
