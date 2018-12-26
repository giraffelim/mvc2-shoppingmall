package com.taeyang.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.dao.ProductDAO;
import com.taeyang.dto.ProductVO;

public class ProductKindAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "product/productKind.jsp";
		// 종류 인자값 받기
		String kind = request.getParameter("kind").trim();
		ProductDAO pDao = ProductDAO.getInstance();
		ArrayList<ProductVO> productList = pDao.listKindProduct(kind);
		request.setAttribute("productKindList", productList);
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
