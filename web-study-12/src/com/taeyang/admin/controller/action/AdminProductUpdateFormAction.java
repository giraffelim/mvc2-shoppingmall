package com.taeyang.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.controller.action.Action;
import com.taeyang.dao.ProductDAO;
import com.taeyang.dto.ProductVO;

public class AdminProductUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "admin/product/productUpdate.jsp";
		
		String pseq = request.getParameter("pseq");
		
		ProductDAO productDAO = ProductDAO.getInstance();
		ProductVO productVO = productDAO.getProduct(pseq);
		
		request.setAttribute("productVO", productVO);
		String tpage="1";
		if(request.getParameter("tpage") != null) {
			tpage = request.getParameter("tapge");
		}
		
		request.setAttribute("tpage", tpage);
		
		String kindList[] = {"Heels","Boots","Sandals", "Sneakers", "Sale"};
		
		request.setAttribute("kindList", kindList);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
