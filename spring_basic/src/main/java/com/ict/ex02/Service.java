package com.ict.ex02;

public class Service {
	//  개체생성 기존 자바 방식
//	OracleDAO oracleDAO = new OracleDAO();
//	MariaDBDAO mariaDBDAO = new MariaDBDAO();
	
	// 인터페이스 또는 클래스를 자료형으로 사용
	private DAO dao;
	public Service() {	}
	// 기본생성자에 인터페이스 들어오면 디스사용해서 전역변수로 넘긴다.
	public Service(DAO dao) {
		this.dao = dao;
	}
	
	public DAO getDao() {
		return dao;
	}
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	// 사용하고 싶은 메소드
	public void biz() {
		// OracleDAO, MariaDBDAO 의 prn 메소드 실행
		//  개체생성 기존 자바 방식
//		oracleDAO.prn();
//		mariaDBDAO.prn();
		
		dao.prn();
		
	}
}
