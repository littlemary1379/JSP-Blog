package com.cos.blog.action.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

public class ReplyWriteProcAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	//form 타입이 아니고 json이기 때문에 request.getParameter 못씀
	
		BufferedReader br=request.getReader();
		StringBuffer sb= new StringBuffer();
		String input=null;
		while((input=br.readLine())!=null) {
			sb.append(input);
		}
		System.out.println(sb.toString());
		
		
		Gson gson=new Gson();
		Reply reply=gson.fromJson(sb.toString(), Reply.class);
		
		//ReplyRepository 연결 - save(reply)
		//script.outText()응답
		
		ReplyRepository replyRepository=ReplyRepository.getInstance();
		int result=replyRepository.save(reply);
		//save 성공하면 1 실패하면 0,1
		if(result==1) {
			List<ReplyResponseDto> replyDtos =replyRepository.findAll(reply.getBoardId());
			String replyDtosJson=gson.toJson(replyDtos);
			Script.outJson(replyDtosJson, response);

		}else {
			Script.outText("error", response);
		}
		
	}
}
