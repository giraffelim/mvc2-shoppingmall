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
	
	//신상품 리스트 얻어오기
	public ArrayList<ProductVO> listNewProduct(){
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from newProduct limit 0,4";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, stmt, rs);
		}
		return productList;
	}
	
	//베스트 상품 리스트 얻어오기
	public ArrayList<ProductVO> listBestProduct(){
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from bestProduct";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, stmt, rs);
		}
		return productList;
	}
	
	//상품 번호로 상품 정보 한개 얻어오기
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
			while(rs.next()) {
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
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return pVo;
	}
	
	//상품 종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind){
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
			while(rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				productList.add(pVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return productList;
	}
}
