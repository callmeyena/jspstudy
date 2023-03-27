package ex10_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet1")
public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CookieServlet1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 	Cookie 과자 부스러기를 남겨둔다는 개념 like 헨젤과 그레텔
		 	
		 	1. 서버가 만들어서 클라이언트가 저장한다.
		 	2. 보안에 취약하다.	 
		 	3. 주소가 달라지면 쿠키가 달라진다.	
		 */
		
		// 쿠키 만들기
		Cookie cookie1 = new Cookie("name", "김민서");
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 독산동", "UTF-8"));	// 유효하지 않은 문자(대표적으로 공백)는 UTF-8로 인코딩해서 저장한다.
		Cookie cookie3 = new Cookie("job", URLEncoder.encode("요양보호사", "UTF-8"));
		
		
		// 쿠키가 저장될 경로 설정하기 (생략하면 컨텍스트 패스에 저장된다.)
		cookie1.setPath("/01_Servlet");	// 컨텍스트 패스 : request.getContextPath()
		cookie2.setPath("/01_Servlet/CookieServlet1");	// 서블릿 경로	 : request.getRequestURI()
		
		// 쿠키 유효시간 설정하기 (생략하면 세션쿠키가 된다. : 브라우저를 닫을 때까지 보관된다.)
		cookie1.setMaxAge(60 * 60 * 24 * 7); 		// 7일간 보관되는 쿠키
		cookie2.setMaxAge(60 * 60);					// 1시간동안 보관되는 쿠키
		
		// 쿠키 저장하기(응답으로 처리해야 한다.)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		// CookieServlet2으로 redirect 이동
		response.sendRedirect("/01_Servlet/CookieServlet2");	// 동일한 경로에 동일한 이름이 있다면 쿠키를 또 저장하게 되면 덮어쓰기 된다.
		
		/* 		쿠키 삭제하는 법
		 	- 유효시간을 0으로 주기 => 쿠키 삭제
		 	- 유효시간을 n으로 주기 => 쿠키 저장
		 */
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
