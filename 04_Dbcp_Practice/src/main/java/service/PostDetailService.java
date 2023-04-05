package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PostVO;
import repository.PostDAO;

public class PostDetailService implements IPostService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Optional<String> opt = Optional.ofNullable(request.getParameter("post_no"));		// post_no가 null일 수도 있다.
		int post_no = Integer.parseInt(opt.orElse("0"));		
		
		PostVO post = PostDAO.getInstance().getPostByNo(post_no);
		
		if(post == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 포스트 입니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
			return null;	// 이미 응답이 되었으므로 컨트롤러로 이동할 경로를 반환 하면 안 된다.
		} else {
			request.setAttribute("post", post);	// postController if절 path로 이동
			return "post/detail.jsp";			// 처리 된 path값 리턴 받기
		}
		
		
	}

}
