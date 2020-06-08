package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardUpdateProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 세션 유효성 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.outText("잘못된 접근입니다.", response);
		}
		// 1. request에 title이 null인지 공백인지 확인
		if (request.getParameter("id") == null ||
			request.getParameter("id").equals("")||
			request.getParameter("title") == null ||
			request.getParameter("title").equals("")||
			request.getParameter("content") == null ||
			request.getParameter("content").equals("")) {
			Script.back("실패하셨습니다.", response);
			return;
		}
		// 2. request에 title 값과 content 값 받기
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 3. title 값과 content값, pricipal.getId()을 board 오브젝트에 담기
		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
		// 3. Repository 연결해서 save(board) 함수 호출
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.update(board);

		// 4. result==1이면 성공로직(index.jsp)로 이동
		if (result == 1) {
			Script.href("수정 성공.", "/blog/board?cmd=detail&id="+id, response);
		} else {
			// 5. result != 1이면 실패로직(history.back())
			Script.back("게시글 등록에 실패하셨습니다.", response);
		}
	}
}
