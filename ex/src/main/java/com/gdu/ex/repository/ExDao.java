package com.gdu.ex.repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.ex.domain.ExDto;

public class ExDao {
	
	// field
		private SqlSessionFactory factory;		// SqlSession을 만드는 공장
		
		// Singleton Pattern
		private static ExDao dao = new ExDao();
		private ExDao() {
			try {
				String resource = "com/gdu/ex/config/mybatis-config.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				factory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		public static ExDao getInstance() {
			return dao;
		}
		
		
	/* 
	 	SqlSession이란?
		- MyBatis에서 사용하는 인터페이스
	 	- MyBatis하면 꼭 있어야 하는게 mapper인데 그 mapper에 있는 쿼리문을 읽어서 실행하는 객체
			ex) ex.xml파일이 mapper인데 그 파일안에 쿼리문을 읽으려면 SqlSession이 필요하다. 거기서 SqlSession을 사용하려면 공장이 필요하다. 
			즉 SqlSessionFactory는 MyBatis에서 사용하는 인터페이스이다.
	*/
		
		// 메소드 1개가 쿼리문 1개를 실행한다.
		private final String NS = "com.gdu.ex.repository.ex."; 	// mapper의 namespace
		
		/*
			ss.selectList()	: SELECT 결과 행이 2개 이상일 때 사용한다. 그냥 딱 쿼리문를 봤을 때 행이 여러개네?(데이터가 여러개인지 뭔지 알빠아님)하면 SELECTLIST() 메소드 사용
			ss.selectOne()	: SELECT 결과 행이 1개 일 때 사용한다.
			ss.insert()		: INSERT 실행할 때 사용한다.
			ss.update()		: UPDATE 실행할 때 사용한다.
			ss.delete()		: DELETE 실행할 때 사용한다.
		 */
		
		public List<ExDto> list() {
			SqlSession ss = factory.openSession();
			List<ExDto> result = ss.selectList(NS + "list");	// com.gdu.ex.repository.ex.list 랑 같은 뜻 
			ss.close();
			return result;
		}
		
		public ExDto detail(int exNo) {
			SqlSession ss = factory.openSession();
			ExDto result = ss.selectOne(NS + "detail", exNo);		// exNo가 detail 쿼리로 전달되는 parameter이다. 
			ss.close();
			return result;
		}
	

}
