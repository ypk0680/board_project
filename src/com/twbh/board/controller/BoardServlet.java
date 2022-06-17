package com.twbh.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.twbh.board.controller.action.Action;


@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			String page = request.getParameter("page");
			System.out.println("BoardServlet에서 요청을 받음을 확인:"+ command);
			System.out.println("page:"+page);
			
			ActionFactory af = ActionFactory.getInstance();
			Action action = af.getAction(command);
			if(action !=null){
				action.execute(request, response);
				}
			
		} catch (Exception e) {
			//파일 업로드를 하는 글쓰기,글수정은 session으로 커맨드 받아온다.
			HttpSession session= request.getSession();
			String command = (String)session.getAttribute("command"); 
			
			String page = request.getParameter("page");
			System.out.println("★BoardServlet에서 요청을 받음을 확인:"+ command);
			System.out.println("page:"+page);
			
			
			ActionFactory af = ActionFactory.getInstance();
			Action action = af.getAction(command);
			
			if(action !=null){
				action.execute(request, response);
			
			}
			//session.removeAttribute("command");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
