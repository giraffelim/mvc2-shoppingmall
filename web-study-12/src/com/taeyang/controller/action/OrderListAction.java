package com.taeyang.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taeyang.dao.OrderDAO;
import com.taeyang.dto.MemberVO;
import com.taeyang.dto.OrderVO;

public class OrderListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url ="mypage/orderList.jsp";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "ShoppingServlet?command=index";
		}else {
			OrderDAO orderDAO = OrderDAO.getInstance();
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			ArrayList<OrderVO> orderList = orderDAO.listOrderByID(loginUser.getId(), "1", oseq);
			
			int totalPrice = 0;
			for(OrderVO orderVO:orderList) {
				totalPrice += orderVO.getPrice2()*orderVO.getQuantity();
			}
			
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
