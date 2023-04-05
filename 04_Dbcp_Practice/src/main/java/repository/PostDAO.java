package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.PostVO;
import lombok.Getter;

public class PostDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource dataSource;		// Connection Pool 관리하는게 DataSource = CP
	
	private static PostDAO dao = new PostDAO();
	private PostDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");	// webapp - META-INF - context.xml의 name부분
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // 밖에서 사용할 수 없도록 private처리
	public static PostDAO getInstance() {
		return dao;
		
	} // Post내부에서 사용하기 위해선 getInstance를 통해서 사용한다

	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PostVO> getAllPosts() throws Exception {
		List<PostVO> posts = new ArrayList<PostVO>();
		con = dataSource.getConnection();
		sql = "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";	// 1열: POST_NO 2열: WRITER 3열: TITLE ...
		sql += " FROM POST";
		sql += " ORDER BY POST_NO DESC";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			PostVO post = PostVO.builder() 	// PostVO 만드는 메소드
							.post_no(rs.getInt(1))	// setPost_no(rs.getInt(1))과 같은 역할이다.
							.writer(rs.getString(2))
							.title(rs.getString(3))
							.content(rs.getString(4))
							.ip(rs.getString(5))
							.modified_date(rs.getDate(6))
							.created_date(rs.getDate(7))
							.build();
			posts.add(post);
		}
		close();
		return posts;	
	}
	
	public int savePost (PostVO post) throws Exception {
		con = dataSource.getConnection();
		sql = "INSERT INTO POST(POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE)";
		sql += " VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, NULL, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getWriter());
		ps.setString(2, post.getTitle());
		ps.setString(3, post.getContent());
		ps.setString(4, post.getIp());
		int saveResult = ps.executeUpdate();
		close();
		return saveResult;
	}
	
	public PostVO getPostByNo (int post_no) throws Exception {
		PostVO post = null;
		con = dataSource.getConnection();
		sql = "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";	// 1열: POST_NO 2열: WRITER 3열: TITLE ...
		sql += " FROM POST";
		sql += " WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, post_no);
		rs = ps.executeQuery();
		if(rs.next()) {
			post = PostVO.builder()
					.post_no(post_no)
					.writer(rs.getString(2))
					.title(rs.getString(3))
					.content(rs.getNString(4))
					.ip(rs.getString(5))
					.modified_date(rs.getDate(6))
					.created_date(rs.getDate(7))
					.build();
		}
		close();
		return post;
		
	}
	
	public int updatePost (PostVO post) throws Exception {
		con = dataSource.getConnection();
		sql = "UPDATE POST";
		sql += " SET TITLE = ?, CONTENT = ?, MODIFIED_DATE = SYSDATE";
		sql += " WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getTitle());
		ps.setString(2, post.getContent());
		ps.setInt(3, post.getPost_no());
		int updateResult = ps.executeUpdate();
		close();
		return updateResult;
	}
	
	public int deletePos (int post_no) throws Exception {
		con = dataSource.getConnection();
		sql = "DELETE FROM POST WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, post_no);
		int deleteResult = ps.executeUpdate();
		return deleteResult;	
	}
	
	
	
	
	
	
	
	
	
	
}
