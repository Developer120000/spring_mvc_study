package com.ict.ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain_DI {
	public static void main(String[] args) {
		ApplicationContext context =
				new GenericXmlApplicationContext("com/ict/ex05/config.xml");
		
		// id 지정을 안했기때문에 MyProcess 객체에 이름을 따는데 첫글자 자동으로 소문자 
//		MyProcess myProcess = (MyProcess) context.getBean("myProcess");
//		myProcess.prn();
		
		// id 지정한 방법
		MyProcess myProcess2 = (MyProcess) context.getBean("mp");
		myProcess2.prn();
	}
}
