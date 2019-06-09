package ru.otus.spring.hw01.loggin;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

	// Around
	// сравнивать UUID твита на входе и на выходе
	@Before("@target(org.springframework.stereotype.Repository)")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
		
	}
	
	// throws new TwitUuidMatchedException()
	
}
