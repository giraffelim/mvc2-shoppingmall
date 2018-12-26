package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.taeyang.dto.CartVO;
import com.taeyang.dto.OrderVO;

import util.DBManager;

public class OrderDAO {

	private OrderDAO() {
		
	}
	
	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance() {
		return instance;
	}
	
	//insert
	public int insertOrder(ArrayList<CartVO> cartList, String id) {
		int maxOseq = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String selectMaxOseq = "select max(oseq) from orders";
			pstmt = conn.prepareStatement(selectMaxOseq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxOseq = rs.getInt(1);
			}
			pstmt.close();
			
			String insertOrder = "insert into orders(id) values(?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			for(CartVO cartVO : cartList) {
				insertOrderDetail(cartVO, maxOseq);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return maxOseq;
	}
	
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			
			String insertOrderDetail = "insert into order_detail(oseq, pseq, quantity) values(?, ?, ?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, maxOseq);
			pstmt.setInt(2, cartVO.getPseq());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.executeUpdate();
			pstmt.close();
			
			String updateCartResult = "update cart set result=2 where cseq=?";
			pstmt = conn.prepareStatement(updateCartResult);
			pstmt.setInt(1, cartVO.getCseq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public ArrayList<OrderVO> listOrderByID(String id, String result, int oseq){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		String sql = "select * from order_view where id = ? and result like ? and oseq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+result+"%");
			pstmt.setInt(3, oseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOdseq(rs.getInt("odseq"));
				orderVO.setOseq(rs.getInt("oseq"));
				orderVO.setId(rs.getString("id"));
				orderVO.setIndate(rs.getTimestamp("indate"));
				orderVO.setMname(rs.getString("Mname"));
				orderVO.setZipNum(rs.getString("zip_num"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setPhone(rs.getString("phone"));
				orderVO.setPseq(rs.getInt("pseq"));
				orderVO.setQuantity(rs.getInt("quantity"));
				orderVO.setPname(rs.getString("pname"));
				orderVO.setPrice2(rs.getInt("price2"));
				orderVO.setResult(rs.getString("result"));
				orderList.add(orderVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}
	
	public ArrayList<Integer> SelectSeqOrdering(String id){
		ArrayList<Integer> oseqList = new ArrayList<Integer>();
		String sql = "select distinct oseq from order_view where id=? and result = '1' order by oseq desc";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				oseqList.add(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return oseqList;
	}
}
