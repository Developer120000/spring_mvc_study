package com.ict.ex06;

import org.springframework.stereotype.Component;

@Component
public class OracleDAO implements DAO{
	public OracleDAO() {
		System.out.println("OracleDAO 생성자");
	}
	@Override
	public void prn() {
		System.out.println("OracleDAO prn() 메소드 실행");
	}
	
}
