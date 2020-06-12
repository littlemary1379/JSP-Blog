package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

//싱글톤 패턴
//Dao
public class BoardRepository {
	
	private static final String TAG="BoardRepository : ";
	private static BoardRepository instance=new BoardRepository();
	private BoardRepository(){}
	public static BoardRepository getInstance() {
		return instance;
	}
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public int save(Board board) {
		final String SQL="insert into board(id,userId,title,content,createdate) values (Board_SEQ.nextval,?,?,?,sysdate)";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());

	
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"save : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int update(Board board) {
		final String SQL="Update board set title =?, content=? where id = ? ";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"update : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int update(int id) {
		final String SQL="Update board set readCount=readCount+1 where id = ? ";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"update : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int deleteByID(int id) {
		final String SQL="delete from board where id=?";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"deleteByID : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public List<Board> findAll(int page) {
		StringBuilder sb=new StringBuilder();
		sb.append("select /*+ INDEX_DESC(BOARD SYS_C008232)*/id, userid, title, content, readcount, createdate ");
		sb.append("FROM board ");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");

		final String SQL=sb.toString();
		List<Board> boards=new ArrayList<>();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, page*3);
			
			rs=pstmt.executeQuery();
			//while
			while(rs.next()) {
				Board board=new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);		
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
	
		return null;
	}
	
	
	public List<Board> findAll() {
		final String SQL="select * from board order by id desc";
		List<Board> boards=new ArrayList<>();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			rs=pstmt.executeQuery();
			//while
			while(rs.next()) {
				Board board=new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);		
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	public List<Board> findAll(int page,String keyword) {
		StringBuilder sb=new StringBuilder();
		sb.append("select /*+ INDEX_DESC(BOARD SYS_C008232)*/id, userid, title, content, readcount, createdate ");
		sb.append("FROM board ");
		sb.append("where title like ? or content like ? "); //여기선 %안먹음.
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");

		final String SQL=sb.toString();
		List<Board> boards=new ArrayList<>();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, page*3);
			
			rs=pstmt.executeQuery();
			//while
			while(rs.next()) {
				Board board=new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);		
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page,keyword) : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
	
		return null;
	}
	
	public int countAll() {
		final String SQL="select count(*) from board";
		int totalCount=0;
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			rs=pstmt.executeQuery();
			//while
			if(rs.next()) {
				totalCount=rs.getInt("count(*)");
			}
			return totalCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int countAll(String keyword) {
		final String SQL="select count(*) from board where title like ? or content like ?";
		int totalCount=0;
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			
			rs=pstmt.executeQuery();
			//while
			if(rs.next()) {
				totalCount=rs.getInt("count(*)");
			}
			return totalCount;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"count(keyword) : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public BoardResponseDto findById(int id) {
		StringBuilder sb=new StringBuilder();
		sb.append("select b.id,b.userid,b.title,b.content,b.readCount,b.createdate,u.username ");
		sb.append("from board b inner join users u ");
		sb.append("on b.userid=u.id ");
		sb.append("where b.id=?");

		final String SQL=sb.toString();
		
		BoardResponseDto boardDto=null;
		
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, id);
			
			rs=pstmt.executeQuery();
			
			//if -> rs
			if(rs.next()) {
				boardDto=new BoardResponseDto();
				Board board=Board.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
				boardDto.setBoard(board);
				boardDto.setUsername(rs.getString(7));
				
			}
			return boardDto;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findById : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	
	
}
