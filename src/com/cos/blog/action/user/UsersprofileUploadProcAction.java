package com.cos.blog.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UsersprofileUploadProcAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		/*
		 * InputStream in=request.getInputStream(); BufferedReader br=new
		 * BufferedReader(new InputStreamReader(in,"utf-8")); StringBuilder sb=new
		 * StringBuilder(); String input=null; while((input=br.readLine())!=null) {
		 * sb.append(input); } System.out.println("-----사진 있다");
		 * System.out.println(sb.toString()); Script.outText("테스트중",response);
		 */
	int id;
	String fileName=null;

	String realPath=request.getServletContext().getRealPath("image");
	String contextPath=request.getServletContext().getContextPath();
	String userProfile=null;
	
	System.out.println("realPath : "+realPath);
	System.out.println("contextPath : "+contextPath);
	
	
	try {
		MultipartRequest multi=new MultipartRequest(
											request,
											realPath,
											1024*1024*2,
											"UTF-8",
											new DefaultFileRenamePolicy()
											);
		fileName=multi.getFilesystemName("userProfile");
		System.out.println("fileName:"+fileName);
		id=Integer.parseInt(multi.getParameter("id"));
		System.out.println(id);
		
		userProfile=contextPath+"/image/"+fileName; // blog/image/파일 이름
		
		UsersRepository usersRepository=UsersRepository.getInstance();
		int result=usersRepository.update(id,userProfile);
		
		if(result==1) {
			HttpSession session=request.getSession();
			Users principal=usersRepository.findById(id);
			session.setAttribute("principal", principal);
			
			Script.href("사진 변경 완료","/blog/index.jsp", response);
		}else {
			Script.back("사진 변경 실패", response);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Script.outText("오류 : "+e.getMessage(), response);
	}
}
}
