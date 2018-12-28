package com.taeyang.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.taeyang.controller.action.Action;
import com.taeyang.dao.ProductDAO;
import com.taeyang.dto.ProductVO;

public class AdminProductUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "ShoppingServlet?command=admin_product_list";

		HttpSession session = request.getSession();

		int sizeLimit = 5 * 1024 * 1024;
		String save_path = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(save_path);

		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8",
				new DefaultFileRenamePolicy());

		ProductVO productVO = new ProductVO();
		productVO.setPseq(Integer.parseInt(multi.getParameter("pseq")));
		productVO.setBestyn(multi.getParameter("bestyn"));
		productVO.setUseyn(multi.getParameter("useyn"));
		productVO.setKind(multi.getParameter("kind"));
		productVO.setName(multi.getParameter("name"));
		productVO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		productVO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		productVO.setPrice3(
				Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		productVO.setContent(multi.getParameter("content"));
		if(multi.getParameter("image") == null) {
			productVO.setImage(multi.getParameter("nomakeImg"));
		}
		else {
			productVO.setImage(multi.getFilesystemName("image"));
		}
		
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.updateProduct(productVO);
		
		response.sendRedirect(url);
	}

}
