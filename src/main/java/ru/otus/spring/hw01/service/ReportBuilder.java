package ru.otus.spring.hw01.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ReportBuilder {
	
	private final MessageSource messageSource;
	private Locale locale;

	public ReportBuilder(MessageSource messageSource, 
			//LocaleMessage implements BiFunction<String, String[], String>
			// зашить туда бин MessageSource
			@Value("${language}") String language,
			@Value("${country}") String country) {
		super();
		this.messageSource = messageSource;
		this.locale = new Locale(language, country);
	}
	
	// locale передавать парамтром из Examinator
	public String buildReport(String firstName, String lastName, String grade) {
		return messageSource.getMessage("report.template", 
				new String[]{firstName, lastName, grade}, locale);
	}

}
