package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// <form> 태그에 포함된 입력 요소들이 name 속성을 가지고 있다면, null처리를 할 수 없고, 빈 문자열("")로 처리해야 한다.
		// Post요청은 주소창에 어떤 값이든 넘어가지 않은 상태로 처리할 수 있도록 하는 것 !
		
		int price = 0;
		if(strPrice.isEmpty() == false) {	// 빈 문자열 점검
			price = Integer.parseInt(strPrice);
		}
		
		
		response.getWriter().append("model: " + model).append(", price: " + price);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post 요청이 들어옴");
		doGet(request, response);
	}

}
