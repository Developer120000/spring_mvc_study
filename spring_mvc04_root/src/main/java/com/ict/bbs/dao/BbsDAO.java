package com.ict.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BbsDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BbsVO> getBbsList() {
		try {
			return sqlSessionTemplate.selectList("bbs.bbslist");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getBbsInsert(BbsVO bvo) {
		try {
			return sqlSessionTemplate.insert("bbs.bbsinsert", bvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getHitUpdate(String b_idx) {
		try {
			return sqlSessionTemplate.update("bbs.bbshitupdate", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public BbsVO getBbsDetail(String b_idx) {
		try {
			return sqlSessionTemplate.selectOne("bbs.bbsdetail", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<CommentVO> getCommentList(String b_idx) {
		try {
			return sqlSessionTemplate.selectList("bbs.commentlist", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getCommentInsert(CommentVO cvo) {
		try {
			return sqlSessionTemplate.insert("bbs.commentinsert", cvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public int getCommentDelete(String c_idx) {
		try {
			return sqlSessionTemplate.delete("bbs.commentdelete", c_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
