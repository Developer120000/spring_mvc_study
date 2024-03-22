package com.ict.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.service.GuestBook2ServiceImpl;


@Repository
public class DAO {
	private static final Logger logger = LoggerFactory.getLogger(GuestBook2ServiceImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	
	public List<VO> getGuestBook2List() {
		try {
			return sqlSessionTemplate.selectList("guestbook2.list"); 
		} catch (Exception e) {
			// 4가지 알림이 있다.
//			logger.debug(null);
			logger.info("list", e);
//			logger.error(null);
//			logger.warn(null);
		}
		return null;
	}
	
	public int getGuestBook2Insert(VO vo) {
		try {
			return sqlSessionTemplate.insert("guestbook2.insert", vo); 
		} catch (Exception e) {
			logger.info("list", e);
		}
		return -1;
	}
	
	public VO getGuestBook2Detail(String idx) {
		try {
			return sqlSessionTemplate.selectOne("guestbook2.detail", idx); 
		} catch (Exception e) {
			logger.info("list", e);
		}
		return null;
	}
	
	public int getGuestBook2Delete(String idx) {
		try {
			return sqlSessionTemplate.delete("guestbook2.delete", idx);
		} catch (Exception e) {
			logger.info("list", e);
		}
		return -1;
	}
	
	public int getGuestBook2Update(VO vo) {
		try {
			return sqlSessionTemplate.update("guestbook2.update", vo);
		} catch (Exception e) {
			logger.info("list", e);
		}
		return -1;
	}
}
