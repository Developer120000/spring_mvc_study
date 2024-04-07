package com.ict.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.emp.dao.EmpDAO;
import com.ict.emp.dao.EmpVO;

@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpDAO empDAO;
	
	@Override
	public List<EmpVO> getEmpList() throws Exception {
		return empDAO.getEmpList();
	}

	@Override
	public List<EmpVO> getEmpSearchList(String searchType, String searchValue) throws Exception {
		return empDAO.getEmpSearchList(searchType, searchValue);
	}
}
