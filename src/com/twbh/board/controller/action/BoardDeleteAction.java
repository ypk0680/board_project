package com.twbh.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twbh.common.dao.BoardDAO;

public class BoardDeleteAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String num =request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.deleteBoard(num);
		
		new BoardListAction().execute(request, response);

		
	}
}

