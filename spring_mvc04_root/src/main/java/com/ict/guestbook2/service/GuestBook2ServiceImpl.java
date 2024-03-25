package com.ict.guestbook2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook2.dao.GuestBook2DAO;
import com.ict.guestbook2.dao.GuestBook2VO;

@Service
public class GuestBook2ServiceImpl implements GuestBook2Service{
	
	@Autowired
	private GuestBook2DAO dao;

	@Override
	public List<GuestBook2VO> getGuestBook2List() {
		return dao.getGuestBook2List();
	}

	@Override
	public GuestBook2VO getGuestBook2Detail(String idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGuestBook2Insert(GuestBook2VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGuestBook2Delete(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGuestBook2Update(GuestBook2VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
