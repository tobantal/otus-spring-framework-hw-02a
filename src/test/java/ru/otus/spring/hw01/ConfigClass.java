package ru.otus.spring.hw01;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import ru.otus.spring.hw01.service.LocaleMessageProvider;
import ru.otus.spring.hw01.service.LocaleMessageProviderImpl;


@PropertySource("classpath:application.properties")
@Configuration
public class ConfigClass {
	
    @Bean
    public LocaleMessageProvider fakeLocaleMessageProvider() {
    	ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return new LocaleMessageProviderImpl(ms, "en", "US");
    }
    
    @Bean
    public LocaleMessageProvider mockLocaleMessageProvider() {
        return Mockito.mock(LocaleMessageProvider.class);
    }
    
}