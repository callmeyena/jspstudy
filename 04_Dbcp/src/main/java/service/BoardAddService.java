package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 	BoardDAO의 getInstance() 메소드 호출하기
		 	메소드 호출 방법
		 	- 객체로 호출: 객체.메소드() 	==> 불가한 방법
		 	ex) BoardDAO dao = new BoardDAO();
		 	 	dao.getInstance();	 => 생성자를 private처리했기 때문에 생성 불가
		 	 	
		 	- 클래스로 호출: 클래스.메소드() 	
		 		
		 	ex)	BoardDAO dao = BoardDAO.getInstance();
		 */
		
		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. 파라미터를 이용해서 BoardDTO 객체 생성
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달(BoardDAO의 insertBoard 메소드)
		int insertResult = BoardDAO.getInstance().insertBoard(board);	// BoardDAO.getInstance() 부분이 DAO / 결과값이 0 OR 1
		System.out.println(insertResult == 1 ? "삽입성공" : "삽입실패");
		
		// 4. 어디로 and 어떻게 이동
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true);		// board/list.jsp에 redirect 형식으로 이동
	}

}
