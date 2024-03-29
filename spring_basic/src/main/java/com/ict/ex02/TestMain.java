package com.ict.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		// 1. 일반 방식
//		Service service = new Service();
//		service.biz();
		
		// 기본생성자를 이용
//		Service service = new Service(new OracleDAO());
//		service.biz();
		
		// 3. setter 를 이용
//		Service service = new Service(new OracleDAO());
//		service.setDao(new MariaDBDAO());
		
		// 4. DI 는 Spring Container 에서 객체(Bean) 생성하고 관리한다.
		// Spring Container 에게 필요한 정보를 제공해야 한다. => 설정정보(configuration metadata)
		// 설정정보(configuration metadata) => 기본적으로 XML 파일 형태이다.
		// 추가적으로 Annotation 을 이용한다. (Spring boot 에서는 무조건 Annotation 사용)
		
		// Spring Container => beanFactory => ApplicationContext 일반적으로 사용
		//								   => WebApplicationContext (Web) 웹에서 사용
		// ApplicationContext context = 
		//		   new GenericXmlApplicationContext("설정정보위치");
		ApplicationContext context = 
				 new GenericXmlApplicationContext("com/ict/ex02/config.xml");
		// config.xml 에 있는 id=service 사용해서 
		Service service = (Service) context.getBean("service");
		service.biz();
	}
}
