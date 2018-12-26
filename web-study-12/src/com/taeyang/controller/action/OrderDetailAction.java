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

public class OrderDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "mypage/orderDetail.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "ShoppingServlet?command=login_form";
		}else {
			int oseq=Integer.parseInt(request.getParameter("oseq"));
			OrderDAO orderDAO = OrderDAO.getInstance();
			ArrayList<OrderVO> orderList = orderDAO.listOrderByID(loginUser.getId(), "%", oseq);
			
			int totalPrice = 0;
			for(OrderVO oVo:orderList) {
				totalPrice += oVo.getPrice2()*oVo.getQuantity();
			}
			request.setAttribute("orderDetail", orderList.get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
