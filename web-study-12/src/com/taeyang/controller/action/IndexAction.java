package com.taeyang.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.dao.ProductDAO;
import com.taeyang.dto.ProductVO;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		ProductDAO pDao = ProductDAO.getInstance();
	    ArrayList<ProductVO> newProductList = pDao.listNewProduct();
	    ArrayList<ProductVO> bestProductList = pDao.listBestProduct();
	    
	    request.setAttribute("newProductList", newProductList);
	    request.setAttribute("bestProductList", bestProductList);
	    
	    RequestDispatcher dis = request.getRequestDispatcher(url);
	    dis.forward(request, response);
	}

	
}
