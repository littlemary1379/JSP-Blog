package com.cos.blog.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardDeleteProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session.getAttribute("principal")==null) {
			Script.outText("잘못된 접근입니다.", response);
			return;
		}
		
		if(
				request.getParameter("id")==null||
				request.getParameter("id").equals("")
		) {
			Script.outText("잘못된 접근입니다.", response);
			return;
		}
		
		int id=Integer.parseInt(request.getParameter("id"));
		BoardRepository boardRepository=BoardRepository.getInstance();
		int result = boardRepository.deleteByID(id);
		PrintWriter out=response.getWriter();
		out.print(result);

	}
}
