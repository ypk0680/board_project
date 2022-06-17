package com.twbh.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.twbh.common.dao.BoardDAO;
import com.twbh.common.dto.CommentVO;

public class CommentWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		CommentVO cVo = new CommentVO();
		
		cVo.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		//cVo.setComment_num(Integer.parseInt(request.getParameter(arg0)));
		cVo.setContent(request.getParameter("comment_content"));
		cVo.setUserid(request.getParameter("comment_id"));

		System.out.println(cVo.toString());
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertComment(cVo);
		
		response.sendRedirect(request.getHeader("referer")); //request.getHeader("referer") 이전페이지를 기억, <a>태그나 <form>의 주소만 사용 가능 

	}

}
