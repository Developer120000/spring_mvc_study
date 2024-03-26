package com.ict.bbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;
import com.ict.bbs.service.BbsService;

@Controller
public class BbsController {
	@Autowired
	private BbsService bbsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("bbs_list.do")
	public ModelAndView getBbsList() {
		ModelAndView mv = new ModelAndView("bbs/list");
		
		List<BbsVO> list = bbsService.getBbsList();
		
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("bbs/error");
	}
	
	@GetMapping("bbs_write.do")
	public ModelAndView getBbsWrite() {
		return new ModelAndView("bbs/write");
	}
	
	@PostMapping("bbs_write_ok.do")
	public ModelAndView getBbsWriteOK(BbsVO bvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("redirect:bbs_list.do");
			String path = request.getSession().getServletContext().getRealPath("resources/upload");
			MultipartFile file = bvo.getFile_name();
			
			if(file.isEmpty()) {
				bvo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				bvo.setF_name(f_name);
				
				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			
			String pwd = passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(pwd);
			
			// db저장
			int result = bbsService.getBbsInsert(bvo);
			if(result > 0) {
				return mv;
			}
			return new ModelAndView("bbs/error");
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("bbs/error");
	}
	
	@GetMapping("bbs_detail.do")
	public ModelAndView getBbs_Detail(String b_idx) {
		ModelAndView mv = new ModelAndView("bbs/detail");
		// 조회수, 상세보기는 같이 성공해야 조회수 증가해야하기때문에 같이 묶어처리하는게 맞다(트랜잭션 처리)(나중에)
		// 조회수 증가
		int result = bbsService.getHitUpdate(b_idx);
		// 상세보기
		BbsVO bvo = bbsService.getBbsDetail(b_idx);
		
		if(result > 0 && bvo != null) {
			// 댓글 가져오기
			// 해당 detail 관련 댓글만 가져오면 된다. 
			List<CommentVO> comm_list = bbsService.getCommentList(b_idx);
			mv.addObject("comm_list", comm_list);
			mv.addObject("bvo", bvo);
			return mv;
		}
		return new ModelAndView("bbs/error");
	}
	
	@PostMapping("comment_insert.do")
	public ModelAndView getCommentInsert(CommentVO cvo, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentInsert(cvo);
		return mv;
	}

	@PostMapping("comment_delete.do")
	// detail 글로 돌아가려면 c_idx 와 b_idx 둘다 필요하다
	public ModelAndView getCommentDelete(String c_idx, @ModelAttribute("b_idx")String b_idx) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentDelete(c_idx);
		return mv;
	}
}
