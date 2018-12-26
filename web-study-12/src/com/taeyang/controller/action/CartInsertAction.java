package com.taeyang.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taeyang.dao.CartDAO;
import com.taeyang.dto.CartVO;
import com.taeyang.dto.MemberVO;

public class CartInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "ShoppingServlet?command=cart_list";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			url = "ShoppingServlet?command=login_form";
		}else {
			CartVO cartVO = new CartVO();
			cartVO.setId(loginUser.getId());
			cartVO.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cartVO.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			
			CartDAO cartDAO = CartDAO.getInstance();
			cartDAO.insertCart(cartVO);
		}
		response.sendRedirect(url);
	}

}
