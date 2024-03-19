package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
	@GetMapping("start5.do")
	public ModelAndView Start05Command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("result5");
		mv.addObject("name", "홍길동");
		return mv;
	}

	@GetMapping("start6.do")
	public ModelAndView Start06Command(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("result6");
		mv.addObject("name", "고길동");
		return mv;
	}
}
