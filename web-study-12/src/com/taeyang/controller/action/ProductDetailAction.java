package com.taeyang.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.dao.ProductDAO;
import com.taeyang.dto.ProductVO;

public class ProductDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "product/productDetail.jsp";
		String pseq = request.getParameter("pseq");
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = pDao.getProduct(pseq);
		
		request.setAttribute("pVo", pVo);
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
