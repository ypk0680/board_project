package com.twbh.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.twbh.common.dao.BoardDAO;
import com.twbh.common.dto.BoardVO;

public class BoardReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		BoardVO bVo = new BoardVO();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		//업로드 경로
		String savePath =  "/upload/";
				
		//현재 프로젝트에 대한 정보를 관리하는 클래스
		ServletContext application = request.getServletContext();
						
		//upload폴더까지의 절대경로
		String path = application.getRealPath(savePath);
		System.out.println(path); //절대경로 출력
		
		int uploadFileSizeLimit = 5 * 1024*1024;
		String enType = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(
				request, path,uploadFileSizeLimit,enType,new DefaultFileRenamePolicy());
		System.out.println("멀티 네임"+multi.getParameter("name"));
		//System.out.println("리퀘스트 네임"+request.getParameter("name"));
		
		bVo.setName(multi.getParameter("name"));
		bVo.setEmail(multi.getParameter("email"));
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		bVo.setPictureUrl(multi.getFilesystemName("pictureUrl"));
		bVo.setNum(Integer.parseInt(multi.getParameter("num")));  //부모글번호 
		System.out.println(bVo.toString());

		BoardDAO bDao = BoardDAO.getInstance();
		bDao.replyBoard(bVo);
		
		new BoardListAction().execute(request, response);

	}

}
