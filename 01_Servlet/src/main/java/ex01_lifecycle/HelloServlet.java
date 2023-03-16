package ex01_lifecycle;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	서블릿
 	
 	1. Servlet
 	2. 웹 화면을 결과로 만드는 클래스이다.
 	3. HttpServlet 클래스를 상속 받는다.
 	4. Jsp/Servlet Container인 Tomcat에 의해서 실행된다.(Tomcat이 없으면 컴파일 오류 발생)
 	5. 서블릿을 실행하면(Ctrl + F11) 웹 브라우저(Chrome)가 열리고 결과가 표시된다.  | Window - Web Browser에서 크롬으로 설정해놨기 때문에!
 */

/*
 	URL
 	
 	1. 구성
 		프로토콜://호스트:포트번호/ContextPath/URLMapping
 	2. HelloServlet의 URL
 		http://localhost:9090/01_Servlet/HelloServlet
*/


@WebServlet("/HelloServlet")	// URLMapping 값

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
/*
     1. 생성자
     	1) 가장 먼저 호출된다.
     	2) 호출 뒤 자동으로 init() 메소드가 호출된다.
*/
    public HelloServlet() {
        super();
        System.out.println("생성자 호출");
    }

/*
	 2. init()
	 	1) 서블릿 환경 설정을 담당한다.
	 	2) init() 호출 뒤 자동으로 service() 메소드가 호출된다.
*/
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출");
	}

/*
	 3. service()
	 	1) 클라이언트가 요청할 때마다 자동으로 호출된다.
	 	2) service() 메소드가 없으면 doGet() 또는 doPost() 메소드가 자동으로 호출된다.
	 	3) 클라이언트의 요청을 직접 처리할 수 있다.
	 	4) 클라이언트의 요청을 직접 처리하지 않으려면 요청에 따라 doGet() 또는 doPost() 메소드를 호출해야 한다.
*/
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("service() 호출");
		
		// HTTP Method(요청 메소드)에 따른 doGet() 또는 doPost() 메소드 호출하기
		switch(request.getMethod()) {
		case "GET": 
			doGet(request, response);
			break;
		case "POST":
			doPost(request, response);
			break;
		}
	}

/*
	 4. doGet()
	 	1) GET 방식의 요청을 처리하는 메소드이다.
	 	2) GET 방식의 요청 예시
	 		(1) <a href="http://localhost:9090/01_Servlet/HelloServlet">		//  a링크 클릭은(?) 100% GET방식이다  | html
	 		(2) <form action="http://localhgost:9090/01_Servlet/HelloServlet">	// 메소드를 생략하면 GET방식이다.	  | html
	 		(3) location.href='http://localhost:9090/01_Servlet/HelloServlet'	// js
	 		(4) open('http://localhost:9090/01_Servlet/HelloServlet', '', '')	// js
	 		(5) $.ajax({														// jQuery
	 				type: 'GET'
	 				url: 'http://localhost:9090/01_Servlet/HelloServlet',		
	 					})
	 		
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답 정보를 가진 객체: response
		
		// 클라이언트로 정보(텍스트)를 보내기 위한 출력 스트림: response.getWriter()는 PrintWriter이다.
		
		// 출력 스트림으로 정보(텍스트)를 보내는 메소드: append(), write(), print(), println()
		response.getWriter().append("Served at: ").append(request.getContextPath());	// 응답을 writer메소드를 통해서 출력한다 | client <----- server(Servlet) 
	}

/*
	 4. doPost()
	 	1) Post 방식의 요청을 처리하는 메소드이다.
	 	2) Post 방식의 요청 예시
	 		(1) <form method="Post" action="http://localhgost:9090/01_Servlet/HelloServlet">
	 		(2) $.ajax({														
	 				type: 'POST'
	 				url: 'http://localhost:9090/01_Servlet/HelloServlet',		
	 					})
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST 메소드를 통해서 넘어온 정보를 모두 doGet() 메소드에 넘긴다.
		
		doGet(request, response);
	}

}
