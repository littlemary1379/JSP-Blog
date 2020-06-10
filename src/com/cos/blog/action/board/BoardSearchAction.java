package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardSearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. DB연결해서 Board 목록 다 불러와서
		int page=Integer.parseInt(request.getParameter("page"));
		String keyword=request.getParameter("keyword");
		if(request.getParameter("keyword")==null ||
			request.getParameter("keyword")=="") {
			Script.back("검색 키워드가 없습니다.", response);
			return;
		}
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		// 2. 검색해서 서치로 가져오기.
		List<Board> boards = boardRepository.findAll(page,keyword);
		int totalCount=boardRepository.countAll(keyword);

		// 본문 짧게 가공하기
		for (Board board : boards) {
			String preview = HtmlParser.getContentPreview(board.getContent());
			board.setContent(preview);
		}
	
		request.setAttribute("boards", boards);
		request.setAttribute("totalCount", totalCount);
		
		RequestDispatcher dis = 
				request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}
}