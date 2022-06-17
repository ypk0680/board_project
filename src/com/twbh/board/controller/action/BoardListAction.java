package com.twbh.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twbh.common.dao.BoardDAO;
import com.twbh.common.dto.BoardVO;


public class BoardListAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String url = "/board/boardList.jsp";
		
		//한 페이지당보여줄 글 갯수
		final int pageSize=10;
				
		BoardDAO bDao = BoardDAO.getInstance();
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		
	
		int currentPage = 1; 
		if(request.getParameter("page")!=null){ 
			currentPage = Integer.parseInt(request.getParameter("page")); //현재 페이지
		}

		lists = bDao.selectAllBoards(currentPage, pageSize);
		
		request.setAttribute("boardList", lists);  				
				
		//전체 글 갯수 구하기
		int totalRow = bDao.getBoardCount();
		//전체 페이지 갯수       (전체글 개수-1)/페이지당 글 수+1   
		int totalPage = (totalRow-1)/pageSize+1;  
		
		//페이지 그룹화  [1][2][3] [4][5][6] [7][8][9]  =>3페이지가 한그룹
		int pagePerGroup = 3;
		//시작페이지	((현재페이지-1)/그룹당 보여줄 페이지)*그룹당 보여줄 페이지+1   [1] [4] [7]
		int startPage = ((currentPage-1)/pagePerGroup)*pagePerGroup+1;
		//끝페이지		시작페이지+그룹당 보여줄 페이지-1  [3] [6] [9]
		int endPage = startPage+pagePerGroup-1;
		
		//★만약 endPage가 totalPage보다 크면 endPage에 totalPage를 넣어준다.
		if(endPage>totalPage){endPage=totalPage;}
		System.out.println("startPage:"+startPage);
		System.out.println("endpage:"+endPage);
		System.out.println("totalPage:"+totalPage);
		
		//request에 setAttribute를 사용하여 모든 정보를 붙여 보낸다.
		request.setAttribute("currentPage",currentPage); 
		request.setAttribute("totalRow",new Integer(totalRow));   //총 글 갯수 생략 가능성
		request.setAttribute("totalPage",new Integer(totalPage)); 
		request.setAttribute("startPage",new Integer(startPage)); 
		request.setAttribute("endPage",new Integer(endPage)); 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);   
		dispatcher.forward(request, response);
		
	}
}
