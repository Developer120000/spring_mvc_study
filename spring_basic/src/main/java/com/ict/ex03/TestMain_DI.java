package com.ict.ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain_DI {
	public static void main(String[] args) {
		ApplicationContext context =
				new GenericXmlApplicationContext("com/ict/ex03/config.xml");
		
		// config 를 읽어온 context 에서 getBean 에 설정한 id 를 넣는다 
		MyProcess myProcess = (MyProcess) context.getBean("mp3");
		myProcess.prn();
	}
}
