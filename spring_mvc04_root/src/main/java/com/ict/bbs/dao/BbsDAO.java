package com.ict.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("bbs.count");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	public List<BbsVO> getBbsList(int Offset, int limit) {
		try {
			// offset, limit 는 VO 에 없어서 파라미터를 map 으로 넣어야한다
			// VO 에 넣는 방법도 가능
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("offset", Offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("bbs.list", map);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public int getBbsDelete(String b_idx) {
		try {
			// 댓글있는 원글 삭제 오류
			// return sqlSessionTemplate.delete("bbs.bbsdelete", b_idx);
			// 댓글있는 원글 삭제하려면 쿼리문은 업데이트로 작성
			return sqlSessionTemplate.update("bbs.bbsdelete", b_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
