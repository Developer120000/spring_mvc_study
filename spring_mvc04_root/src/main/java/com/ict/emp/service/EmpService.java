package com.ict.emp.service;

import java.util.List;

import com.ict.emp.dao.EmpVO;

public interface EmpService {
	List<EmpVO> getEmpList() throws Exception;
	List<EmpVO> getEmpSearchList(String searchType, String searchValue) throws Exception;
}
