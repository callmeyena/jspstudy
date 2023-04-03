package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {
	
	// 모든 메소드가 사용할 공통 필드
	private Connection con;					//  Oracle DataBase와 연결할 때 사용한다. Connection은 기본적으로 null값을 가지고 있기 때문에 초기화 하지 않아도 된다.
	private PreparedStatement ps;			// 자바에서 쿼리문을 작성하기 위해 사용한다.
	private ResultSet rs;				 	// SELECT문의 결과를 저장하는 객체이다.
	private String sql;						// 쿼리문 자체
	
	// Connection 관리를 위한 DataSource가 필드
	private DataSource dataSource;

	// Single Pattern으로 DAO 생성하기
	// Singleton Pattern : 객체의 인스턴스가 오직 1개만 생성되는 패턴을 의미한다.
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어들여서 DataSource 객체 생성하기 (JNDI 방식)
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61");
			/*
			 	위의 세 줄과 같은 방법(인데 더 간결한)
			 	Context context = new InitialContext();
			 	dataSource = (DataSource)context.lookup("java:comp/envjdbc/GDJ61");
			*/
		
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
		
	}
	public static BoardDAO getInstance() {
		return dao;
	}
	// dao를 필요로 하는 건 service => 따라서 new dao가 필요함(5개) 하지만 그걸 private으로 허용하지 않는 상태, 가져다 쓸 수 는 있게 한다 가져다 사용하라고 public으로 하나 공개해줌
	
	// 자원 (Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null)con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해서 쿼리문 실행(SELECT문 실행은 정해진 메소드 executeQuery() 로 한다.
			rs = ps.executeQuery();
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList로 만든다
			while(rs.next()) {
				// STEP1. Board 테이블의 결과 행(ROW)을 읽는다.
				int boar_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// STEP2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(boar_no, title, content, modified_date, created_date);
				
				// STPE3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);                                                                          
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. ArrayList 반환
		return boardList;
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		return null;
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(BoardDTO board_no) {
		
		return 0;
	}

}
