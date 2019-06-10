package ru.otus.spring.hw01.service;

import org.springframework.stereotype.Service;

@Service
public class ReportBuilder {
	
	private final LocaleMessageProvider localeMessageProvider;

	public ReportBuilder(LocaleMessageProvider localeMessageProvider) {
		this.localeMessageProvider = localeMessageProvider;
	}
	
	public String buildReport(String firstName, String lastName, String grade) {
		return localeMessageProvider.getMessage("report.template", new String[]{firstName, lastName, grade});
	}

}
