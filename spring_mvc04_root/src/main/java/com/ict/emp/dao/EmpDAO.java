package com.ict.emp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmpDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<EmpVO> getEmpList() throws Exception {
		return sqlSessionTemplate.selectList("emp.emp_list");
	}

	public List<EmpVO> getEmpSearchList(String searchType, String searchValue) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchValue", searchValue);
		return sqlSessionTemplate.selectList("emp.emp_search_list", map);
	}
}
