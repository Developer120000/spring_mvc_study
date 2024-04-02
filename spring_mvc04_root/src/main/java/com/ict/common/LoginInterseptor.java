package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

public class LoginInterseptor implements AsyncHandlerInterceptor{
	
	// preHandle = Controller 로 가기전 구동됨
	// 컨트롤러 이전에 처리해야 하는 전처리 작업이나 요청 정보를 가공하거나 추가하는 경우에 사용할 수 있다.
	// 결과 true 이면 Cotroller 로, false 이면 특정 JSP 로 이동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 체크를 해서 만약에 로그인이 안된 상태이면 value 에 false 저장
		
		// 만약 session 이 삭제된 상태라면 새로운 session 을 생성해준다.
		// session 이 삭제가 안된 상태라면 사용하고 있는 session 을 그대로 전달해 준다.
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("loginChk");
		if(obj == null) {
			// 로그인 하지 않은 상태일 때 특정 jsp 로 이동시키기
			request.getRequestDispatcher("/WEB-INF/views/member/login_error.jsp").forward(request, response);
			return false;
		}
		
		return true;
	}
	
	// postHandle = Controller 에서 리턴되어 뷰 리졸버로 가기전에 구동
	// 컨트롤러 이후에 처리해야 하는 후처리 작업이 있을 때 사용할 수 있다.
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
	
	// afterCompletion = 뷰 리졸버가 뷰를 찾아서 보내고 나면 구동
	// 모든 뷰에서 최종 결과를 생성하는 일을 포함해 모든 작업이 완료된 후에 실행된다. ( View 렌더링 후)
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
}
