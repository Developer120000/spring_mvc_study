package com.ict.sns;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddrDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int addr_Insert(AddrVO avo) {
		try {
		  return  sqlSessionTemplate.insert("member.addr_insert", avo);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
