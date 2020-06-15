package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.Users;

//싱글톤 패턴
//Dao
public class ReplyRepository {
	
	private static final String TAG="ReplyRepository : ";
	private static ReplyRepository instance=new ReplyRepository();
	private ReplyRepository(){}
	public static ReplyRepository getInstance() {
		return instance;
	}
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public int save(Reply reply) {
		final String SQL="insert into reply(id,userId,boardid,content,createdate) values (reply_SEQ.nextval,?,?,?,sysdate)";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			pstmt.setInt(1, reply.getUserId());
			pstmt.setInt(2, reply.getBoardId());
			pstmt.setString(3, reply.getContent());
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"save : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int update(Reply reply) {
		final String SQL="";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"update : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int deleteByID(int id) {
		final String SQL="delete from reply where id = ?";
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
	
	
	public List<ReplyResponseDto> findAll(int boardId) {
		StringBuffer sb= new StringBuffer();
		sb.append("select r.id, r.userId, r.boardId, r.content, r.createDate, ");
		sb.append(" u.username, u.userprofile ");
		sb.append("from reply r inner join users u ");
		sb.append("on r.userid=u.id ");
		sb.append("where boardid=? ");
		sb.append("order by r.id desc");
		
		final String SQL=sb.toString();
		List<ReplyResponseDto> replyDtos=null;
		
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, boardId);
			rs=pstmt.executeQuery();
			
			replyDtos=new ArrayList<>();
			while(rs.next()){
				Reply reply=Reply.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.boardId(rs.getInt(3))
						.content(rs.getString(4))
						.createDate(rs.getTimestamp(5))
						.build();
				
				ReplyResponseDto replydto=ReplyResponseDto.builder()
									.reply(reply)
									.username(rs.getString(6))
									.userProfile(rs.getString(7))
									.build();
				replyDtos.add(replydto);
				
			}
			
			System.out.println("DB replydto : "+replyDtos);
			//while
			return replyDtos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(boardID) : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	public Reply findById(int id) {
		final String SQL="";
		Reply reply=new Reply();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			
			//if -> rs
			return reply;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	
	
}
