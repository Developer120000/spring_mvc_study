package com.ict.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.dao.MemberDAO;
import com.ict.member.dao.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO getLogin(MemberVO mvo) {
		return memberDAO.getLogin(mvo);
	}
	
	@Override
	public List<MemberVO> getMemberList() {
		return memberDAO.getMemberList();
	}
	
	@Override
	public String getIdChk(String m_id) {
		return memberDAO.getIdChk(m_id);
	}
	
	@Override
	public int getAjaxJoin(MemberVO mvo) {
		return memberDAO.getAjaxJoin(mvo);
	}

	@Override
	public int getAjaxDelete(String m_idx) {
		return memberDAO.getAjaxDelete(m_idx);
	}
}
