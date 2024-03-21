package com.ict.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.service.GuestBookDAO;
import com.ict.guestbook.service.GuestBookVO;

@Controller
public class GuestBookController {
	@Autowired
	private GuestBookDAO guestBookDAO;
	
	@GetMapping("guestbook_go.do")
	public ModelAndView guestBookList() {
		ModelAndView mv = new ModelAndView("guestbook/list");
			List<GuestBookVO> list = guestBookDAO.guestBookList();
			mv.addObject("list", list);
			
			return mv;
	}
	
	@GetMapping("gb_write.do")
	public ModelAndView guestBookWrite() {
		// db 갔다오는게 아니라 창만 연결
		// ModelAndView mv = new ModelAndView("guestbook/write");
		// return mv;
		// 보통 위에 두문장을 하나로 쓴다.
		return new ModelAndView("guestbook/write");
	}
	
	@PostMapping("gb_write_ok.do")
	public ModelAndView guestBookWriteOK(GuestBookVO gvo) {
		// jsp 에서는 서블릿으로 cmd 가지고 갔었는데
		// 스프링에서는 redirect 사용해서 클라이언트에게 갔다오고
		// guestbook_go.do 로 다시 위에 메소드 실행되면서 DAO 통해서 DB 갔다옴
		ModelAndView mv = new ModelAndView("redirect:guestbook_go.do");
		int result = guestBookDAO.guestBookInsert(gvo);
		if(result > 0) {
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@GetMapping("gb_detail.do")
	public ModelAndView guestBookDetail(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		// 메퍼에서 파라미터 스트링이라 변환해줘야하니까 편하게 idx 로 받자
		GuestBookVO gbvo = guestBookDAO.guestBookDetail(gvo.getIdx());
		if(gbvo != null) {
			mv.addObject("gbvo", gbvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@PostMapping("gb_delete.do")
	public ModelAndView guestBookDelete(@ModelAttribute("gvo")GuestBookVO gvo) {
		return new ModelAndView("guestbook/delete");
	}
	@PostMapping("gb_update.do")
	public ModelAndView guestBookUpdate(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestBookVO gbvo = guestBookDAO.guestBookDetail(gvo.getIdx());
		if(gbvo != null) {
			mv.addObject("gbvo", gbvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@PostMapping("gb_delete_ok.do")
	public ModelAndView guestBookDeleteOK(String idx) {
		ModelAndView mv = new ModelAndView("redirect:guestbook_go.do");
		int result = guestBookDAO.guestBookDelete(idx);
		if(result > 0) {
			return mv;
		}
		return new ModelAndView("guestbook/error"); 
	}
	
	@PostMapping("gb_update_ok.do")
	public ModelAndView guestBookUpdateOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:gb_detail.do?idx=" + gvo.getIdx());
		int result = guestBookDAO.guestBookUpdate(gvo);
		if(result > 0) {
			return mv;
		}
		return new ModelAndView("guestbook/error"); 
	}
}
