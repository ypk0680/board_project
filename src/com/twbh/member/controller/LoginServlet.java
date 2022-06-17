package com.twbh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twbh.common.dao.MemberDAO;
import com.twbh.common.dto.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser")!=null){  //이미 로그인 된 사용자이면
			url="main.jsp";  //메인 페이지로 이동한다.
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="index.jsp";
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.userCheck(userid,pwd);
		
		if(result==1){
			MemberVO login_user_info = mDao.getMember(userid);  //유저의 정보를 받아와서 vo에 저장
			HttpSession session = request.getSession();  //세션 객체 생성
			session.setAttribute("loginUser", login_user_info);  // vo에 저장한 유저정보를 세션  값으로 입력
			request.setAttribute("message", "로그인에 성공했습니다.");
			//url="BoardServlet?command=board_list";
			url="main.jsp";
		}else if (result == 0) {
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		} else if (result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
