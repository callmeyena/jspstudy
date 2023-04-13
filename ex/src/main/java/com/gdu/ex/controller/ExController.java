package com.gdu.ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.service.ExListService;
import com.gdu.ex.service.ExService;


@WebServlet("*.do")
public class ExController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI(); 						// /ex/list.do
		String contextPath = request.getContextPath();						// /ex
		String urlMapping = requestURI.substring(contextPath.length());		// /list.do
		
		ExService service = null;
		
		String path = null;
		
		
		switch(urlMapping) {
		case"/list.do":
			service = new ExListService();
			break;
		}
		
		path = service.execute(request, response);
		
		request.getRequestDispatcher(path).forward(request, response);		// forward경로 코드 전달 완
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
