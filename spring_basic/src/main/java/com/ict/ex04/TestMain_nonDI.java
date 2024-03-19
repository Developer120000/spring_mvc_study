package com.ict.ex04;

public class TestMain_nonDI {
	public static void main(String[] args) {
		Age age = new Age();
		MyProcess mp = new MyProcess("홍길동", "940808", "1", age);
		mp.prn();
		
		System.out.println("==========================");
		
		MyProcess mp2 = new MyProcess();
		mp2.setName("마이콜");
		mp2.setJumin("980808");
		mp2.setGender("2");
		mp2.setAge(age);
		mp2.prn();
	}
}
