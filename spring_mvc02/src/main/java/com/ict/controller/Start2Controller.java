package com.ict.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start2Controller implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("admin/result2");
		
		String[] dogName = {"땅콩이", "진돌이", "바둑이", "점순이"};
		mv.addObject("dogName", dogName);
		ArrayList<String> list = new ArrayList<String>();
		list.add("해리");
		list.add("엠마");
		list.add("론");
		list.add("볼드모트");
		mv.addObject("list", list);
		return mv;
	}

}
