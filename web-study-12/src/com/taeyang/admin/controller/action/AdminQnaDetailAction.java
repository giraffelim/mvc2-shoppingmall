package com.taeyang.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taeyang.controller.action.Action;
import com.taeyang.dao.QnaDAO;
import com.taeyang.dto.QnaVO;

public class AdminQnaDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "admin/qna/qnaDetail.jsp";
		
		String qseq=request.getParameter("qseq");
		
		QnaDAO qnaDAO  = QnaDAO.getInstance();
		QnaVO qnaVO = qnaDAO.getQna(Integer.parseInt(qseq));
		
		request.setAttribute("qnaVO", qnaVO);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
