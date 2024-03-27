package com.ict.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getTotalCount() {
		return 0;
	}

	public List<BoardVO> getBoardList(int offset, int limit) {
		return null;
	}

	public int getBoardInsert(BoardVO bovo) {
		return 0;
	}

	public int getBoardHit(String bo_idx) {
		return 0;
	}

	public BoardVO getBoardDetail(String bo_idx) {
		return null;
	}

	public int getLevUpdate(Map<String, Integer> map) {
		return 0;
	}

	public int getAnsInsert(BoardVO bovo) {
		return 0;
	}
}
