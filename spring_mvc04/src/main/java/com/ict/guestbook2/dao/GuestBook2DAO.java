package com.ict.guestbook2.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.guestbook2.service.GuestBook2ServiceImpl;


@Repository
public class GuestBook2DAO {
	private static final Logger logger = LoggerFactory.getLogger(GuestBook2ServiceImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<GuestBook2VO> getGuestBook2List() {
		try {
			return sqlSessionTemplate.selectList("guestbook2.list");
		} catch (Exception e) {
			logger.info("list", e);
		}
		return null;
	}
	
	public int getGuestBook2Insert(GuestBook2VO vo) {
		try {
			return sqlSessionTemplate.insert("guestbook2.insert", vo);
		} catch (Exception e) {
			logger.info("insert", e);
		}
		return -1;
	}
	
	public GuestBook2VO getGuestBook2Detail(String idx) {
		try {
			return sqlSessionTemplate.selectOne("guestbook2.detail", idx);
		} catch (Exception e) {
			logger.info("detail", e);
		}
		return null;
	}
	
	public int getGuestBook2Delete(String idx) {
		try {
			return sqlSessionTemplate.delete("guestbook2.delete", idx);
		} catch (Exception e) {
			logger.info("delete", e);
		}
		return -1;
	}
	
	public int getGuestBook2Update(GuestBook2VO vo) {
		try {
			return sqlSessionTemplate.update("guestbook2.update", vo);
		} catch (Exception e) {
			logger.info("update", e);
		}
		return -1;
	}
}
