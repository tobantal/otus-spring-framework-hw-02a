package ru.otus.spring.hw01.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

@Service
public class LocaleMessageProviderImpl implements LocaleMessageProvider {
	
	private final MessageSource messageSource;
	private final Locale locale;

	public LocaleMessageProviderImpl(MessageSource messageSource, 
			@Value("${language}") String language,
			@Value("${country}") String country) {
		super();
		this.messageSource = messageSource;
		this.locale = new Locale(language, country);
	}
	
	@Override
	public String getMessage(String code, Object[] args) throws NoSuchMessageException {
		return messageSource.getMessage(code, args, locale);
	}

}
