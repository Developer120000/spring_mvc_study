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
//	@Autowired
//	private Paging paging;
	
	@RequestMapping("bbs_list.do")
//	public ModelAndView getBbsList(HttpServletRequest request) {
	public ModelAndView getBbsList() {
		ModelAndView mv = new ModelAndView("bbs/list");
//		// 페이징 기법 이전

		List<BbsVO> list = bbsService.getBbsList();
		
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("bbs/error");

		
		// 전체 게시물의 수를 구한다. 그래야 페이지의 수를 정할수 있다.
//		int count = bbsService.getTotalCount();
//		paging.setTotalRecord(count);
//		
//		// 전체 페이지의 수를 구하자.
//		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
//			// 게시물의 수가 10보다 작으면 페이지는 1이다
//			paging.setTotalPage(1);
//		}else {
//			// 게시물 수가 10보다 많을때 (게시물의 수 / 10) 페이지 수
//			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
//			// 나머지가 있을 시에 1페이지 추가
//			if(paging.getTotalPage() % paging.getNumPerPage() != 0) {
//				paging.setTotalPage(paging.getTotalPage() + 1);
//			}
//		}
//		
//		// 현재 페이지 구해야 begin, end 를 구한다.
//		String cPage = request.getParameter("cPage");
//		// 맨 처음에 페이지로 들어오면 cPage 가 없으므로 null 이다.
//		// 맨 처음 오면 무조건 1 페이지 이다.
//		if(cPage == null) {
//			paging.setNowPage(1);
//		}else {
//			// cPage 는 String 이라 인트로 형변환
//			paging.setNowBlock(Integer.parseInt(cPage));
//		}
//		// 오라클은 begin, end 사용
//		// 마리아DB 는 limit, offset 사용
//		// offset = limit * (현재 페이지 -1)
//		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() -1));
//		
//		List<BbsVO> bbs_list = bbsService.getBbsList(paging.getOffset(), paging.getNumPerPage());
//		System.out.println(bbs_list.size());
//		return mv;
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
