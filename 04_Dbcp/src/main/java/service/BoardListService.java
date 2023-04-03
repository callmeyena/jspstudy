package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardListService implements IBoardService {

	@Override
	public ActionForward run(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. BoardDAO객체 준비(singleton으로 이미 생성되어있는 상태)
		BoardDAO dao = BoardDAO.getInstance();
		
		// 2. BoardDAO의 selectBoardList 메소드 호출
		List<BoardDTO> boardList = dao.selectBoardList();		// 호출 결과 값이 arrayList
		
		// 3. DB로부터 가져온 게시글 목록 boardList를 request에 저장(forward 하기 위해서)
		request.setAttribute("boardList", boardList);
		
		// 4. 어디로 and 어떻게 이동할 것인가?
		ActionForward af = new ActionForward("board/list.jsp", false);	// board폴더 아래 list.jsp로 forward(request를 전달) 하시오.
		return af;
	}

}
