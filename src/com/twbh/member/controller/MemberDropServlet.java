package com.twbh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twbh.common.dao.MemberDAO;
import com.twbh.common.dto.MemberVO;


@WebServlet("/memberDrop.do")
public class MemberDropServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		
		request.setAttribute("userid", userid);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberDrop.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐을 방지
		String userid = request.getParameter("userid");
		
		MemberDAO mDao = MemberDAO.getInstance();
		mDao.dropMember(userid);
		System.out.println(userid +"doPost");
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('탈퇴가 완료되었습니다.'); location.href='index.jsp';</script>");
		out.flush();
		
	}

}
