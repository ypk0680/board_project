package com.twbh.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.twbh.common.dto.BoardVO;
import com.twbh.common.dto.CommentVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {

	} ////외부에서 생성을 못하도록 Default생성자는 private으로 선언합니다. 

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;

	}
	// 게시판의 전체 글 수를 가져오는 메서드
	public int getBoardCount() {
		String sql = "select count(*) from board";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCount=0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql); // '?'바인드를 사용해서 sql문을 효과 적으로
			rs = pstmt.executeQuery();

			
			if (rs.next()) {
				boardCount=rs.getInt(1); // 전체 글 개수
			} // if

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return boardCount;
	}

	
	
	
	// 게시글 리스트 읽어오기 page=현재 페이지  pageSize=페이지당 글 개수
	public ArrayList<BoardVO> selectAllBoards(int page, int pageSize) {

		int startNum=((page-1)*pageSize)+1;
		int endNum=page*pageSize;
		
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql=new StringBuffer();
		sql.append(" select * "); 
		sql.append(" from ( ");
		sql.append(" select rownum rno,num,name,email,title,content,readcount,writedate,comment_count");  //rownum rno 오라클에서 select할 떄마다 가상적으로 만들어지는 순서번호 페이징을 위해 사용
		sql.append(" from ( ");
		sql.append(" select num,name,email,title,content,readcount,writedate,comment_count");
		sql.append(" from board ");
		sql.append(" order by ref desc,STEP asc ) )");
		sql.append(" where rno >=? and rno <=?");
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setComment_count(rs.getInt("comment_count"));

				lists.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return lists;
	}

	
	//게시글 삽입
	public void insertBoard(BoardVO bVo){
		String sql = "insert into board(num, name,email,title,content,img,ref)" 
						+ "values(board_seq.nextval,?,?,?,?,?,board_seq.nextval)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getTitle());
			pstmt.setString(4, bVo.getContent());
			pstmt.setString(5, bVo.getPictureUrl());

			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	//조회수 증가 메소드
	public void updateReadCount(String num){
		String sql = "update board set readcount = readcount+1 where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//글 상세 내용 보기
	public BoardVO selectOneBoardByNum(String num){
		String sql = "select * from board where num=?";
		
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);  //pstmt에서 게시글 번호에 맞는 게시글 찾아온다음
			
			rs = pstmt.executeQuery(); // sql 실행값을 rs에 저장
			
			while(rs.next()){
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setPictureUrl(rs.getString("img"));
				bVo.setLike_it(rs.getInt("like_it"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return bVo;
	}
	
	//글수정
	public void updateBoard(BoardVO bVo){
		String sql = "update board set title=?, content=?,img=? where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setString(3, bVo.getPictureUrl());
			pstmt.setInt(4, bVo.getNum());
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}	
	}
	
	//패스워드 체크
	public BoardVO checkPassWord(String pass,String num){
		String sql = "select * from board where pass=? and num=?";
		
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pass);  //pstmt에서 게시글 번호에 맞는 게시글 찾아온다음
			pstmt.setString(2, num);
			
			rs = pstmt.executeQuery(); // sql 실행값을 rs에 저장
			
			while(rs.next()){
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bVo;
	}
	
	//게시글 삭제
	public void deleteBoard(String num){
		String sql = "delete board where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	//답글 쓰기
	public void replyBoard(BoardVO bVo){
		
		int idx= bVo.getNum();  //글번호
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			int indent=0;
			
			conn = DBManager.getConnection();
			stmt = conn.createStatement(); 
			
			//1. 부모글에서 ref, indent, step 가져오기
			String sql = "SELECT REF, INDENT, STEP FROM board WHERE NUM=" + idx;
			
			rs = stmt.executeQuery(sql); // sql 실행값을 rs에 저장
			
			if (rs.next()) {
				indent = rs.getInt("indent");
			}

			//2. 답글 입력 
			//ref 부모글 번호
			//indent 답글 들여쓰기 (답글의 답글 쓸때 필요한데 여기서는 필요x)
			//step 하나의 글에 답글 여러개 달렸을때 답글 순서를 위한 변수
			sql = "insert into board(num, name,email,title,content,img,ref,indent,step)" 
						+ "values(board_seq.nextval,?,?,?,?,?,?,?,board_seq.nextval)";
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getTitle());
			pstmt.setString(4, bVo.getContent());
			pstmt.setString(5, bVo.getPictureUrl());
			pstmt.setInt(6, idx);
			pstmt.setInt(7, indent+1);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}	
		
	}
	
	//댓글 쓰기
	public void insertComment(CommentVO cVo){	
		String sql = "insert into board_comment(comment_num ,board_num , userid ,content)" 
					+ "values(comment_seq.nextval,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentCount=0;
		try{
			//1.댓글쓰기
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setInt(1, cVo.getBoard_num());
			pstmt.setString(2, cVo.getUserid());
			pstmt.setString(3, cVo.getContent());
		
			pstmt.executeUpdate();
			
			
			//2.댓글 갯수 구하기		
			sql = "select count(*) from board_comment where board_num=?";
			
			pstmt = conn.prepareStatement(sql); // '?'바인드를 사용해서 sql문을 효과 적으로
			pstmt.setInt(1, cVo.getBoard_num());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				commentCount=rs.getInt(1); // 전체 글 개수
			}
		
			//3.board table에 댓글 갯수 업데이트하기 -> 리스트에서 댓글 몇갠지 보여주기 위함 
			sql = "update board set comment_count=? where num=?";
			
			pstmt = conn.prepareStatement(sql); // '?'바인드를 사용해서 sql문을 효과 적으로
			pstmt.setInt(1, commentCount);
			pstmt.setInt(2, cVo.getBoard_num());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	//댓글 리스트 읽어오기
	public ArrayList<CommentVO> comment_list(String num){
		String sql = "select * from board_comment where board_num=?";
		
		ArrayList<CommentVO> lists = new ArrayList<CommentVO>();
		CommentVO cVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);  //pstmt에서 게시글 번호에 맞는 게시글 찾아온다음
			
			rs = pstmt.executeQuery(); // sql 실행값을 rs에 저장
			
			while(rs.next()){
				cVo = new CommentVO();
				cVo.setComment_num(rs.getInt("comment_num"));
				cVo.setBoard_num(rs.getInt("board_num"));
				cVo.setUserid(rs.getString("userid"));
				cVo.setContent(rs.getString("content"));
				cVo.setWritedate(rs.getTimestamp("writedate"));

				lists.add(cVo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return lists;
	}
	
	//좋아요 업데이트
	public void update_Like(int bno){
		String sql = "update board set like_it=like_it+1 where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			
			pstmt.setInt(1,bno);
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}	
	}
	
	//좋아요 개수 찾기
	public int select_Like(int bno){
		String sql = "select like_it from board where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int like=0;
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);  // '?'바인드를 사용해서 sql문을 효과 적으로 사용할수있음
			pstmt.setInt(1,bno);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				like = rs.getInt("like_it");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return like;
	}
	
}
