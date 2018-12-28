package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.taeyang.dto.ProductVO;

import util.DBManager;

public class ProductDAO {

	private ProductDAO() {

	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	// 신상품 리스트 얻어오기
	public ArrayList<ProductVO> listNewProduct() {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from newProduct limit 0,4";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return productList;
	}

	// 베스트 상품 리스트 얻어오기
	public ArrayList<ProductVO> listBestProduct() {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from bestProduct";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return productList;
	}

	// 상품 번호로 상품 정보 한개 얻어오기
	public ProductVO getProduct(String pseq) {
		ProductVO pVo = null;
		String sql = "select * from product where pseq=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setKind(rs.getString("kind"));
				pVo.setPrice1(rs.getInt("price1"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setPrice3(rs.getInt("price3"));
				pVo.setContent(rs.getString("content"));
				pVo.setImage(rs.getString("image"));
				pVo.setUseyn(rs.getString("useyn"));
				pVo.setBestyn(rs.getString("bestyn"));
				pVo.setIndate(rs.getTimestamp("indate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return pVo;
	}

	// 상품 종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from product where kind=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kind);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return productList;
	}

	// 관리자 모드에서 사용되는 메소드
	public int totalRecord(String product_name) {
		int total_pages = 0;
		String sql = "select count(*) from product where name like ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet pageset = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (product_name.equals("")) {
				pstmt.setString(1, "%" + '%' + "%");
			} else {
				pstmt.setString(1, "%" + product_name + "%");
			}
			pageset = pstmt.executeQuery();

			if (pageset.next()) {
				total_pages = pageset.getInt(1); // 레코드의 개수
				pageset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return total_pages;
	}

	static int view_rows = 5; // 페이지의 개수
	static int counts = 5; // 한 페이지에서 나타날 상품의 개수

	// 페이지 이동을 위한 메소드
	public String pageNumber(int tpage, String name) {
		String str = "";

		int total_pages = totalRecord(name);
		int page_count = total_pages / counts + 1;
		if (total_pages % counts == 0) {
			page_count--;
		}
		if (tpage < 1) {
			tpage = 1;
		}

		int start_page = tpage - (tpage % view_rows) + 1;
		int end_page = start_page + (counts - 1);

		if (end_page > page_count) {
			end_page = page_count;
		}

		if (start_page > view_rows) {
			str += "<a href='ShoppingServlet?command=admin_product_list&tpage=1&key=" + name
					+ "'>&lt;&lt;</a>&nbsp;&nbsp;";
			str += "<a href='ShoppingServlet?command=admin_product_list&tpage=" + (start_page - 1);
			str += "&key=<%=product_name%>'>&lt;</a>&nbsp;&nbsp;";
			System.out.println(str);
		}

		for (int i = start_page; i <= end_page; i++) {
			if (i == tpage) {
				str += "<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
			} else {
				str += "<a href='ShoppingServlet?command=admin_product_list&tpage=" + i + "&key=" + name + "'>[" + i
						+ "]</a>&nbsp;&nbsp;";

			}
		}

		if (page_count > end_page) {
			str += "<a href='ShoppingServlet?command=admin_product_list&tpage=" + (end_page + 1) + "&key=" + name
					+ "'> &gt; </a>&nbsp;&nbsp;";
			str += "<a href='ShoppingServlet?command=admin_product_list&tpage=" + page_count + "&key=" + name
					+ "'> &gt; &gt; </a>&nbsp;&nbsp;";
		}
		return str;
	}

	public ArrayList<ProductVO> listProduct(int tpage, String product_name) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		
		String str = "select pseq,indate,name,price1,price2,useyn,bestyn from product where name like ? order by pseq desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int absolutepage = 1;

		try {
			conn = DBManager.getConnection();
			absolutepage = (tpage - 1) * counts + 1;
			pstmt = conn.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, "%"+product_name+"%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.absolute(absolutepage);
				int count = 0;
				while (count < counts) { //count가 4까지
					ProductVO product = new ProductVO();
					product.setPseq(rs.getInt(1));
					product.setIndate(rs.getTimestamp(2));
					product.setName(rs.getString(3));
					product.setPrice1(rs.getInt(4));
					product.setPrice2(rs.getInt(5));
					product.setUseyn(rs.getString(6));
					product.setBestyn(rs.getString(7));
					productList.add(product);
					if (rs.isLast()) {
						break;
					}
					rs.next();
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return productList;
	}

	public int insertProduct(ProductVO product) {
		int result = 0;

		String sql = "insert into product(kind, name, price1, price2, price3, content, image)"
				+ "values(?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getKind());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getPrice1());
			pstmt.setInt(4, product.getPrice2());
			pstmt.setInt(5, product.getPrice3());
			pstmt.setString(6, product.getContent());
			pstmt.setString(7, product.getImage());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("추가 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public int updateProduct(ProductVO product) {
		int result = -1;
		String sql = "update product set kind=?, useyn=?, name=?, price1=?, price2=?, price3=?, content=?, image=?, bestyn=? where pseq=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getKind());
			pstmt.setString(2, product.getUseyn());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice1());
			pstmt.setInt(5, product.getPrice2());
			pstmt.setInt(6, product.getPrice3());
			pstmt.setString(7, product.getContent());
			pstmt.setString(8, product.getImage());
			pstmt.setString(9, product.getBestyn());
			pstmt.setInt(10, product.getPseq());
			pstmt.executeUpdate();
			pstmt.close();
			
			//베스트 상품 추가
			if(product.getBestyn().equals("y")) {
				sql = "insert into bestProduct(pseq, name, kind, price1, price2, price3, content, image, useyn, bestyn)"
						+"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, product.getPseq());
				pstmt.setString(2, product.getName());
				pstmt.setString(3, product.getKind());
				pstmt.setInt(4, product.getPrice1());
				pstmt.setInt(5, product.getPrice2());
				pstmt.setInt(6, product.getPrice3());
				pstmt.setString(7, product.getContent());
				pstmt.setString(8, product.getImage());
				pstmt.setString(9, product.getUseyn());
				pstmt.setString(10, product.getBestyn());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
}
