package com.taeyang.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.controller.action.Action;

public class AdminProductWriteFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "admin/product/ProductWrite.jsp";
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneakers", "Sale"};
		request.setAttribute("kindList", kindList);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
