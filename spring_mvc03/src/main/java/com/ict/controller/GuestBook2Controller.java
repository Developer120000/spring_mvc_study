package com.ict.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.dao.VO;
import com.ict.service.GuestBook2Service;

@Controller
public class GuestBook2Controller {
	// 오토와이어드는 서비스에다가
	@Autowired
	private GuestBook2Service guestBook2Service;
	
	// 암호화는 spring security 에서 지원하므로 porm.xml 에 추가 해야 된다.
	// spring-security.xml 을 만들어서 bean 생성
	// web.xml 에서 지정해 줘야 실행할때  spring-security.xml 를 읽는다.
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@GetMapping("gb2_list.do")
	public ModelAndView getGuestBook2List() {
		ModelAndView mv = new ModelAndView("list");
		List<VO> list = guestBook2Service.getGuestBook2List();
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("error");
	}
	// web.xml 에서 첫페이지 지정 안하고 하는 방법
//	@GetMapping("/")
//	public ModelAndView getFirst() {
//		return new ModelAndView("redirect:gb2_list.do");
//	}
	
	@GetMapping("gb2_write.do")
	public ModelAndView getGuestBook2Write() {
		return new ModelAndView("write");
	}
	
	@PostMapping("gb2_write_ok.do")
	public ModelAndView getGuestBook2WriteOK(VO vo, HttpServletRequest request) {
		// 파일 업로드 때문에 트라이캐치 필요
		try {
			ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			// 넘어온 파일의 정보 중 파일의 이름은 f_name 에 넣어줘야 DB 에 저장할 수 있다.
			MultipartFile file = vo.getFile();
			// 파일을 올릴수도있고 안올릴수도 있는 상황
			if(file.isEmpty()) {
				vo.setF_name("");
			}else {
				// 파라미터로 받은 file 을 이용해서 DB 에 저장할 f_name 을 채워주자
				// 그러나 같은 이름의 파일이 있으면 업로드가 안되므로
				// 파일 이름을 UUID 를 이용해서 변경 후 DB 에 저장 하자.
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				vo.setF_name(f_name);
				
				// 이미지 저장
				byte[] in = vo.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			String pwd = passwordEncoder.encode(vo.getPwd());
			vo.setPwd(pwd);
			
			// DB 저장
			int result = guestBook2Service.getGuestBook2Insert(vo);
			if(result > 0) {
				return mv;
			}
			return new ModelAndView("error");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("error");
	}
	
	@GetMapping("gb2_detail.do")
	public ModelAndView getGuestBook2Detail(String idx) {
		ModelAndView mv = new ModelAndView("onelist");
		VO vo = guestBook2Service.getGuestBook2Detail(idx);
		if(vo != null) {
			mv.addObject("vo", vo);
			return mv;
		}
		return new ModelAndView("error");
	}
	
	@GetMapping("guestbook2_down.do")
	public void getGuestBook2Down(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/" + f_name);
			String r_path = URLEncoder.encode(path, "UTF-8");
			// 웹브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
			
			File file = new File(new String(path.getBytes(),  "UTF-8"));
			FileInputStream in = new FileInputStream(file);
			// 브라우저에 저장
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@PostMapping("gb2_delete.do")
	public ModelAndView getGuestBook2Delete(@ModelAttribute("vo2")VO vo) {
		return new ModelAndView("delete");
	}
	
	@PostMapping("gb2_delete_ok.do")
	public ModelAndView getGuestBook2DeleteOK(VO vo) {
		ModelAndView mv = new ModelAndView();
		// 비밀번호 맞는지 틀린지 검사 하자 (DB 에 있는 비밀번호가 암호화 되어있다.)
		// delete.jsp 에서 입력한 암호 받아서 저장
		String cpwd = vo.getPwd();
		
		VO vo2 = guestBook2Service.getGuestBook2Detail(vo.getIdx());
		// DB 에서 가지고 온 암호화 된 비밀번호
		String dpwd = vo2.getPwd();
		
		// passwordEncoder.matches(비암호화 비번, 암호화 비번)
		// 일치하면 true, 아니면 false
		if(! passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("delete");
			mv.addObject("pwdchk", "fail");
			mv.addObject("vo2", vo);
			return mv;
		}else {
			int result = guestBook2Service.getGuestBook2Delete(vo.getIdx());
			if(result>0) {
				mv.setViewName("redirect:gb2_list.do");
				return mv;
			}
		}
		return new ModelAndView("error");
	}
	
	@PostMapping("gb2_update.do")
	public ModelAndView getGuestBook2Update(String idx) {
		ModelAndView mv = new ModelAndView("update");
		VO vo = guestBook2Service.getGuestBook2Detail(idx);
		if(vo != null) {
			mv.addObject("vo", vo);
			return mv;
		}
		return new ModelAndView("error");
	}
	
	@PostMapping("gb2_update_ok.do")
	public ModelAndView getGuestBook2UpdateOK(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String cpwd = vo.getPwd();
		
		VO vo2 = guestBook2Service.getGuestBook2Detail(vo.getIdx());
		String dpwd = vo2.getPwd();
		
		if(! passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("vo", vo2);
			return mv;
		}else {
			// 파일 있으니까 트라이캐치
			try {
				String path  = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = vo.getFile();
				String old_f_name = vo.getOld_f_name();
				if(file.isEmpty()) {
					vo.setF_name(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString()+"_"+file.getOriginalFilename();
					vo.setF_name(f_name);
					
					// 이미지 복사 붙이기
					byte[] in = file.getBytes();
					File out = new File(path, f_name);
					FileCopyUtils.copy(in, out);
				}
				int result = guestBook2Service.getGuestBook2Update(vo);
				if(result > 0) {
					mv.setViewName("redirect:gb2_detail.do?idx="+vo.getIdx());
					return mv;
				}
				return new ModelAndView("error");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return new ModelAndView("error");
	}
}












