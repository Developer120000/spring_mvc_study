package com.ict.ex01;

public class TestMain {
	public static void main(String[] args) {
		// 1. 일반 방식
//		Service service = new Service();
//		service.biz();
		
		// 기본생성자를 이용
//		Service service = new Service(new OracleDAO());
//		service.biz();
		
		// 3. setter 를 이용
		Service service = new Service(new OracleDAO());
		service.setDao(new MariaDBDAO());
		service.biz();
	}
}
