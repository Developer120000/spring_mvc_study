package com.ict.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.emp.dao.EmpVO;
import com.ict.emp.service.EmpService;

@Controller
public class EmpController {
	   @Autowired
	   private EmpService empService;
	   
	   @PostMapping("emp_list.do")
	   public ModelAndView getEmpList() {
		 try {
			 ModelAndView mv = new ModelAndView("emp/emp_list");
		    List<EmpVO> empList = empService.getEmpList();
		    if(empList != null) {
		    	mv.addObject("empList", empList);
		    	return mv;
		    }
		} catch (Exception e) {
			System.out.println(e);
		}
		   return new ModelAndView("emp/error");
	   }
	   @PostMapping("emp_search.do")
	   public ModelAndView getEmpSearchList(@ModelAttribute("idx")String idx, String keyword) {
		   try {
				ModelAndView mv = new ModelAndView("emp/emp_searchlist");
				List<EmpVO> searchlist = empService.getEmpSearchList(idx, keyword);
				if(searchlist != null) {
					mv.addObject("searchlist", searchlist);
					return mv;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		   return new ModelAndView("emp/error");
	   }
}
