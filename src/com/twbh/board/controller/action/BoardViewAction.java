package com.twbh.board.controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.twbh.common.dao.BoardDAO;
import com.twbh.common.dto.BoardVO;
import com.twbh.common.dto.CommentVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";

		String num = request.getParameter("num");

		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateReadCount(num);
		
		//게시글 읽어오기
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		System.out.println(bVo.toString());
		request.setAttribute("board", bVo); // request로 보내는 정보
	
		//댓글 읽어오기
		ArrayList<CommentVO> lists = new ArrayList<CommentVO>();
		lists = bDao.comment_list(num);
		request.setAttribute("cmt_list", lists);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
