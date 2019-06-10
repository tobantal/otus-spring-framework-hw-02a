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
	
	/*
	 	System.out.println("Вызов метода : " + proceedinJoinPoint.getSignature().getName());
		Person person = null;
		try {
			String name = (String)proceedinJoinPoint.getArgs()[0];
			System.out.printf("Name = %s\n", name);
			
			person = (Person) proceedinJoinPoint.proceed();
			if (name == null) {
				person = new Person("unknown", person.getAge());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return person;
	 */
	
}
