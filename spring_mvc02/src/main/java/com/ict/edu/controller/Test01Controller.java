package com.ict.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu.service.Test01Service;

@Controller
public class Test01Controller {
	
	// 변수 이름과 참조하는 클래스의 id 가 같을 때 : @Autowired
	// Test01Service.jsp 를 객체생성하는게 아니라 참조방식
	@Autowired
	private Test01Service test01Service;
	
	@GetMapping("hello.do")
	public ModelAndView HelloCommand(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("test01/result");
		
		String msg = test01Service.getGreeting();
		mv.addObject("msg", msg);
		
		return mv;
	}
}
