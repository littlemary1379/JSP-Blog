package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Users;

//싱글톤 패턴
//Dao
public class UsersRepository {
	
	private static final String TAG="UserRepository : ";
	private static UsersRepository instance=new UsersRepository();
	private UsersRepository(){}
	public static UsersRepository getInstance() {
		return instance;
	}
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public int findByUsername(String username) {
		final String SQL="select count(*) from users where username=?";
		Users user=null;
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1); //0 아니면 1
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findByUsername : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public Users findByUsernameAndPassword(String username, String password) {
		final String SQL="select id, username, email, address, userprofile, userrole, createDate from users where username=? and password=?";
		Users user=null;
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			//if -> rs
			if(rs.next()) {
				user=new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createDate"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findByUsernameAndPassword : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	
	
	
	public int save(Users user) {
		final String SQL="insert into users (id,username,password,email,address,userrole,createdate) values(USER_SEQ.nextval,?,?,?,?,?,sysdate) ";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserRole());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"save : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public int update(Users user) {
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
		final String SQL="";
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"deleteByID : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public List<Users> findAll() {
		final String SQL="";
		List<Users> users=new ArrayList<>();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			
			//while
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	public Users findById(int id) {
		final String SQL="";
		Users user=new Users();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			
			//if -> rs
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findById : "+e.getMessage());
		}finally {
			DBConn.close(conn, pstmt);
		}
		
		return null;
	}
	
	
	
}
