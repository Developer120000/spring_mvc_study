package com.ict.ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext context = 
				 new GenericXmlApplicationContext("com/ict/ex06/config.xml");
		Service service = (Service) context.getBean("service");
		service.biz();
	}
}
