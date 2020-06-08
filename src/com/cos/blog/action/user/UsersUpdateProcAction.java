package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

import sun.font.CreatedFontTracker;

public class UsersUpdateProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 유효성 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.outText("잘못된 접근입니다.", response);
		}
		if(
			request.getParameter("username").equals("")||
			request.getParameter("username")==null||
			request.getParameter("email").equals("")||
			request.getParameter("email")==null||
			request.getParameter("address").equals("")||
			request.getParameter("address")==null
		) {
			return ;
		}
		
		
		
		//1. 파라메터 받기(x-www-form-urlencoded mime type -> key = value)
		Users users=(Users)session.getAttribute("principal");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String address=request.getParameter("address");
		
		//2. User 오브젝트 변환
		Users user=Users.builder()
				.id(users.getId())
				.username(username)
				.address(address)
				.email(email)
				.userRole(users.getUserRole())
				.createDate(users.getCreateDate())
				.build();
		//3. DB 연결 - Repository save 호출
		UsersRepository usersRepository=UsersRepository.getInstance();
		int result=usersRepository.update(user);
		
		//4. index.jsp 페이지 이동
		if(result==1) {
			
			session.setAttribute("principal", user);
			Script.href("수정에 성공하였습니다.", "/blog/board?cmd=home", response);
		}else {
			Script.back("수정에 실패하셨습니다.", response);
		}
	}
}
