package com.ict.guestbook2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook2.dao.GuestBook2VO;
import com.ict.guestbook2.service.GuestBook2Service;

@Controller
public class GuestBook2Controller {

	@Autowired
	private GuestBook2Service guestBook2Service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public ModelAndView getGeustBookIndex() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/gb2_list.do")
	public ModelAndView getGeustBook2List() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<GuestBook2VO> list = guestBook2Service.getGuestBook2List();
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("guestbook2/error");
	}
}
