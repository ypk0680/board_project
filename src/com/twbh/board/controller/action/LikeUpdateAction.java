package com.twbh.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.twbh.common.dao.BoardDAO;

public class LikeUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//BoardVO bVo = new BoardVO();
		
		int bno = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.update_Like(bno);
		
		
		int like=bDao.select_Like(bno);
		System.out.println("LikeUpdateAction.java의 like 개수:"+ like);
		
		
		JSONObject obj = new JSONObject();
		obj.put("like",like);
		
		//request.setAttribute("json", json); 
		
		 response.setContentType("application/x-json; charset=UTF-8");
		 response.getWriter().print(obj);
		
	}

}
