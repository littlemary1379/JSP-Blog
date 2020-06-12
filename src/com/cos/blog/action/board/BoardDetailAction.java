package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.util.Script;
import com.cos.blog.util.YoutubeParser;

public class BoardDetailAction implements Action{
	private String result=null;

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie viewCookie=null;
		Cookie[] cookies=request.getCookies();
		BoardRepository boardRepository=BoardRepository.getInstance();
		ReplyRepository replyRepository=ReplyRepository.getInstance();
		
		System.out.println("cookie : "+cookies);
		//0. 유효성 검사
		if(
				request.getParameter("id")==null||
				request.getParameter("id").equals("")
		) {
			Script.back("잘못된 접근입니다.", response);
			return;
		}
		
		//1. 페이지에서 게시판의 id 값을 가지고 옴
		int boardId=Integer.parseInt(request.getParameter("id"));

		
		//2. 접근 시 데이터베이스에 접근해 readCount(조회수) 증가
		//접근 시 이 페이지에 접근한 쿠키를 가지고 있으면 조회수 증가처리 X

		
		if(cookies !=null) {
			
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println("쿠키 이름 : "+cookies[i].getName());
				if(cookies[i].getName().equals("|"+boardId+"|")) {
					System.out.println("if문 쿠키 이름 : "+cookies[i].getName());
					
					viewCookie=cookies[i];
				}
			}
			//System.out.println("for문 돌고 나온 쿠키 : "+viewCookie);
		}else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}

		
		if(viewCookie==null) {
			System.out.println("viewCookie 확인 로직 : 쿠키 없당");
			try {
				Cookie newCookie=new Cookie("|"+boardId+"|","readCount");
				response.addCookie(newCookie);
				int result=boardRepository.update(boardId);
					if(result==0) {
						System.out.println("이게뭐셔");
						return;
					}
			} catch (Exception e) {
				//System.out.println("쿠키 넣을때 오류 나나? : "+e.getMessage());
				e.getStackTrace();
				
			}

		}else {
			System.out.println("viewCookie 확인 로직 : 쿠키 있당");
			//String value=viewCookie.getValue();
			//System.out.println("viewCookie 확인 로직 : 쿠키 value : "+value);
		}
		
		
		//3. id값을 이용해 DB에서 게시판 글을 가져옴(게시물의 글과 작성자)
		BoardResponseDto boardDto = boardRepository.findById(boardId);
		
		//Reply,user(게시물의 댓글과 댓글의 작성자) 복수임

		List<ReplyResponseDto> replyDtos=replyRepository.findAll(boardId);
		
		System.out.println("replyDtos : "+replyDtos);
		
		DetailResponseDto detailDto=DetailResponseDto.builder()
									.boardDto(boardDto)
									.replysDto(replyDtos)
									.build();
		System.out.println("replyDto : "+detailDto.getReplysDto());
		//4. RequestDispatch를 이용해 받아온 데이터를 세션에 담아 페이지로 이동
		if(detailDto!=null) {
			
			Board board=detailDto.getBoardDto().getBoard();
			result=YoutubeParser.getYoutubePreview(board.getContent());
			board.setContent(result);
			request.setAttribute("detailDto", detailDto);
			//request를 유지하기 때문에 데이터를 담고 이동할때 사용
			RequestDispatcher dis=request.getRequestDispatcher("board/detail.jsp"); 
			dis.forward(request, response);
			
		}else {
			Script.back("잘못된 접근입니다.", response);
		}
	}
}
