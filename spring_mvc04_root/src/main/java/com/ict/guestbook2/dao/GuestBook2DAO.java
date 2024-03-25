package com.ict.guestbook2.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestBook2DAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<GuestBook2VO> getGuestBook2List() {
		try {
			return sqlSessionTemplate.selectList("guestbook2.list");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
