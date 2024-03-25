package com.ict.guestbook2.service;

import java.util.List;

import com.ict.guestbook2.dao.GuestBook2VO;

public interface GuestBook2Service {
	// 전체보기
	List<GuestBook2VO> getGuestBook2List();

	// 상세보기
	GuestBook2VO getGuestBook2Detail(String idx);

	// 삽입
	int getGuestBook2Insert(GuestBook2VO vo);

	// 삭제
	int getGuestBook2Delete(String idx);

	// 수정
	int getGuestBook2Update(GuestBook2VO vo);
}
