package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;

@WebServlet("*.do")	//	 getAllBoardList.do		getBoardByNo.do		writeBoard.do		addBoard.do 	modifyBoard.do 		removeBoard.do			
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답의 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// URL Mapping 확인
		String requestURI = request.getRequestURI();					// /04_Dbcp/getAllBoardList.do	: 프로젝트 + 파일경로까지 가져온다.
		String contextPath = request.getContextPath();					// /04_Dbcp						: 프로젝트 Path만 가져온다.
		String urlMapping = requestURI.substring(contextPath.length()); // /getAllBoardList.do 		/는 언제나 contextPath의 길이이다. 따라서 contextPath.length라고 표기
		
		// 모든 서비스의 공통 타입 선언
		IBoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성
		switch(urlMapping) {
		case "/getAllBoardList.do":
			service = new BoardListService();
			break;	
		case "/getBoardByNo.do":
			service = new BoardDetailService();
			break;
		case "/addBoard.do":
			service = new BoardAddService();
			break;
		case "/modifyBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do":
			af = new ActionForward("board/write.jsp", false); 	// board폴더 아래 write.jsp로 forward 한다. (단순 이동의 경우 forward한다.)
			break;
		}
		
		// 서비스 실행
		if(service != null) {
			af = service.execute(request, response);	//	어디로 어떻게 이동할 것인지 af(ActionForward)에 저장한다.
		}
		
		// 응답 View로 이동
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
