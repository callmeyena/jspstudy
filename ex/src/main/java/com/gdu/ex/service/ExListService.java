package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.repository.ExDao;

public class ExListService implements ExService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// forward는 request를 전달하는 것
		
		// Dao를 불러서 목록을 가져온 다음 request에 exList라는 이름으로 저장해서 forward 준비를 해 준다.
		request.setAttribute("exList", ExDao.getInstance().list());	
		
		return "ex/list.jsp";		// ex 폴더 아래 list.jsp로 forward 해 달라는 의미로 경로를 반환한다.
	}

}
