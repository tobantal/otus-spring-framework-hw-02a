package ru.otus.spring.hw01.dao;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring.hw01.service.LocaleMessageProvider;

//@Configuration
//@PropertySource("classpath:application.properties")
@Service("fakeLocaleMessageProvider")
public class FakeLocaleMessageProvider implements LocaleMessageProvider {
	
	private MessageSource messageSource; 
	
	private final Locale locale;

	public FakeLocaleMessageProvider(MessageSource messageSource, 
			@Value("${language}") String language,
			@Value("${country}") String country) {
		this.locale = new Locale(language, country);
	}
	
	@Override
	public String getMessage(String code, Object[] args) throws NoSuchMessageException {
		return messageSource.getMessage(code, args, locale);
	}

}
