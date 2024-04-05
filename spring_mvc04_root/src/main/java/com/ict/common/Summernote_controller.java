package com.ict.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Summernote_controller {
	@RequestMapping(value="saveImg.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveImg(ImgVO imgVO, HttpServletRequest request){
		try {
			Map<String, String> map = new HashMap<String, String>();
			MultipartFile file = imgVO.getS_file();
			String fname = null;
			if(file.getSize() > 0) {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				UUID uuid = UUID.randomUUID();
				fname = uuid.toString() + "_" + file.getOriginalFilename();
				
				file.transferTo(new File(path, fname));
				// 비동기 요청(ajax) 이므로 저장된 파일의 경로와 파일명을 보낸다.
				map.put("fname", fname);
				map.put("path", "resources/upload");
				return map;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
