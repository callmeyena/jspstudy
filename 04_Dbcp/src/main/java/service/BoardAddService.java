package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward run(HttpServletRequest request, HttpServletResponse response) {
		/*
		 	BoardDAO의 getInstance() 메소드 호출하기
		 	메소드 호출 방법
		 	- 객체로 호출: 객체.메소드() 	==> 불가한 방법
		 	ex) BoardDAO dao = new BoardDAO();
		 	 	dao.getInstance();	 => 생성자를 private처리했기 때문에 생성 불가
		 	 	
		 	- 클래스로 호출: 클래스.메소드() 	
		 		
		 	ex)	BoardDAO dao = BoardDAO.getInstance();
		 */
		
		
		return null;
	}

}
