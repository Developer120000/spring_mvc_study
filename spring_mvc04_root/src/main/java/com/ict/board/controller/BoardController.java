package com.ict.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.service.BoardService;
import com.ict.common.Paging;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private Paging paging;
	
	@RequestMapping("board_list.do")
	public ModelAndView boardList(HttpServletRequest request) {
		
		return new ModelAndView("board/error");
	}
}
