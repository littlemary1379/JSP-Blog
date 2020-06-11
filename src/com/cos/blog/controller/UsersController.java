package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;
import com.cos.blog.action.user.UsersLoginProcAction;
import com.cos.blog.action.user.UsersLogoutAction;
import com.cos.blog.action.user.UsersUpdateAction;
import com.cos.blog.action.user.UsersUpdateProcAction;
import com.cos.blog.action.user.UsersUsernameCheckAction;
import com.cos.blog.action.user.UsersprofileUploadAction;
import com.cos.blog.action.user.UsersprofileUploadProcAction;


//http://localhost:8000/blog/user

@WebServlet("/user")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String TAG="UsersController : ";

    public UsersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8000/blog/user?cmd=join
		String cmd=request.getParameter("cmd");
		System.out.println(TAG+"router : "+cmd);
		Action action=router(cmd);
		action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("join")) {
			return new UsersJoinAction();
		}else if(cmd.equals("joinProc")) {
			//회원가입 진행 후 -> index.jsp 이동
			return new UsersJoinProcAction();
		}else if(cmd.equals("update")) {
			return new UsersUpdateAction();
			//회원 수정 페이지 이동(세션에 user object를 가지고 있을 예정)
		}else if(cmd.equals("updateProc")) {
			//회원 수정 진행 -> index 이동
			return new UsersUpdateProcAction();
		}else if(cmd.equals("delete")) {
			//회원 삭제 진행 후 -> 로그아웃 -> index.jsp
		}else if(cmd.equals("login")) {
			//회원 로그인 페이지로 이동
			return new UsersLoginAction();
		}else if(cmd.equals("loginProc")) {
			return new UsersLoginProcAction();
			//로그인 수행 -> 세션 등록 -> index.jsp
		}else if(cmd.equals("logout")) {
			return new UsersLogoutAction();
			//로그아웃 수행 -> index.jsp
		}else if(cmd.equals("usernameCheck")) {
			return new UsersUsernameCheckAction();
			//아이디와 일치하는 이름이 있는지, 수행 -> index.jsp
		}else if(cmd.equals("profileUpload")) {
			return new UsersprofileUploadAction();
			//아이디와 일치하는 이름이 있는지, 수행 -> index.jsp
		}else if(cmd.equals("profileUploadProc")) {
			return new UsersprofileUploadProcAction();
			//아이디와 일치하는 이름이 있는지, 수행 -> index.jsp
		}
		return null;
	}

}
