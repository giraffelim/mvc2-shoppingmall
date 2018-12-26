package com.taeyang.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taeyang.dao.MemberDAO;
import com.taeyang.dto.MemberVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "member/login_fail.jsp";
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = mDao.getMember(id);
		if (mVo != null) {
			if (mVo.getPwd().equals(pwd)) {
				session.removeAttribute("id");
				session.setAttribute("loginUser", mVo);
				url="ShoppingServlet?command=index";
			}
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}