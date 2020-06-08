package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardDetailAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(
				request.getParameter("id")==null||
				request.getParameter("id").equals("")
		) {
			Script.back("잘못된 접근입니다.", response);
			return;
		}
		
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println("id");
		BoardRepository boardRepository=BoardRepository.getInstance();
		DetailResponseDto dto = boardRepository.findById(id);
		
		if(dto!=null) {
			request.setAttribute("dto", dto);
			//request를 유지하기 때문에 데이터를 담고 이동할때 사용
			RequestDispatcher dis=request.getRequestDispatcher("board/detail.jsp"); 
			dis.forward(request, response);
		}else {
			Script.back("잘못된 접근입니다.", response);
		}
	}
}
