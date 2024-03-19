package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Comtroller {
	
	// 컨트롤러 메소드의 반환형은 String 또는 ModelAndView 이다.
	@GetMapping("start4.do")
	public String  exec(HttpServletRequest request, HttpServletResponse response) {
		// 모델엔뷰 안됨 찾아보기
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("computer", "14세대 i7");
		
		request.setAttribute("computer", "14세대 i7");
		
		return "result4";
	}
}
