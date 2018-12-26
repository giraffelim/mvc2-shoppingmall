package com.taeyang.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taeyang.dao.MemberDAO;
import com.taeyang.dto.MemberVO;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "member/login.jsp";
		HttpSession session = request.getSession();
		MemberVO mVo = new MemberVO();
		
		mVo.setId(request.getParameter("id"));
		mVo.setPwd(request.getParameter("pwd"));
		mVo.setName(request.getParameter("name"));
		mVo.setEmail(request.getParameter("email"));
		mVo.setZip_num(request.getParameter("zipNum"));
		mVo.setAddress(request.getParameter("addr1")+request.getParameter("addr2"));
		mVo.setPhone(request.getParameter("phone"));
		
		session.setAttribute("id", request.getParameter("id"));
		
		MemberDAO mDao = MemberDAO.getInstance();
		mDao.insertMember(mVo);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
