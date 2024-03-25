package com.ict.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.dao.GuestBookVO;
import com.ict.guestbook.service.GuestBookService;

@Controller
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("gb_list.do")
	public ModelAndView getGuestBookList() {
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestBookVO> list = guestBookService.getGuestList();
		
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@GetMapping("gb_write.do")
	public ModelAndView getGuestBookInsert() {
		return new ModelAndView("guestbook/write");
	}
	
	@PostMapping("gb_write_ok.do")
	public ModelAndView getGuestBookInsertOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:gb_list.do");
		
		// 비번암호화
		String pwd = passwordEncoder.encode(gvo.getPwd());
		gvo.setPwd(pwd);
		
		int result = guestBookService.getGuestInsert(gvo);
		if(result > 0){
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@GetMapping("gb_detail.do")
	public ModelAndView getGuestBookDetail(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestBookVO gvo = guestBookService.getGuestDetail(idx);
		if(gvo != null) {
			mv.addObject("gvo", gvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@PostMapping("gb_delete.do")
	public ModelAndView getGuestBookDelete(@ModelAttribute("idx")String idx) {
		// 페이지 이동을 하는데 idx 가져가야되니까 @ModelAttribute("idx") 로 가져간다
		return new ModelAndView("guestbook/delete");
	}
	// 다른 방식2, 코딩늘어남
//	@PostMapping("gb_delete.do")
//	public ModelAndView getGuestBookDelete(@ModelAttribute("idx")String idx) {
//		ModelAndView mv = new ModelAndView("guestbook/delete");
//		mv.addObject("idx", idx);
//		return mv;
//	}
	
	@PostMapping("gb_delete_ok.do")
	public ModelAndView getGuestBookDeleteOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView();
		// 클라이언트 비밀번호
		String cpwd = gvo.getPwd();
		GuestBookVO gvo2 = guestBookService.getGuestDetail(gvo.getIdx()); 
		// DB 에 저장되어있는 비밀번호
		String dpwd = gvo2.getPwd();
		// passwordEncoder.matches(비암호화, 암호화)
		// 일치하면 true, 아니면 false
		if(! passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook/delete");
			mv.addObject("pwdchk", "fail");
			mv.addObject("idx", gvo.getIdx());
			return mv;
		}else {
			int result = guestBookService.getGuestDelete(gvo.getIdx());
			if(result > 0) {
				mv.setViewName("redirect:gb_list.do");
				return mv;
			}
			mv.setViewName("guestbook/error");	
			return mv;
		}
	}
	
	@PostMapping("gb_update.do")
	public ModelAndView getGuestBookUpdate(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestBookVO gvo = guestBookService.getGuestDetail(idx);
		if(gvo != null) {
			mv.addObject("gvo", gvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	
	@PostMapping("gb_update_ok.do")
	public ModelAndView getGuestBookUpdateOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView();
		// 클라이언트 비밀번호
		String cpwd = gvo.getPwd();
		GuestBookVO gvo2 = guestBookService.getGuestDetail(gvo.getIdx()); 
		// DB 에 저장되어있는 비밀번호
		String dpwd = gvo2.getPwd();
		// passwordEncoder.matches(비암호화, 암호화)
		// 일치하면 true, 아니면 false
		if(! passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook/update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("idx", gvo.getIdx());
			// 패스워드 일치 안할시에
			// 수정 전 내용을 되돌려 주려면 : gvo2
			// 수정 후 내용을 되돌려 주려면 : gvo
			mv.addObject("gvo", gvo);
			return mv;
		}else {
			// gvo2 는 DB에 저장되어있는 예전거기 때문에 현재정보 받아온 gvo 넣어야한다.
			int result = guestBookService.getGuestUpdate(gvo);
			if(result > 0) {
				mv.setViewName("redirect:gb_detail.do?idx=" + gvo.getIdx());
				return mv;
			}
			mv.setViewName("guestbook/error");	
			return mv;
		}
	}
}
















