package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.taeyang.dto.CartVO;

import util.DBManager;

public class CartDAO {

	private CartDAO() {
		
	}
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	//Select Max Cseq
	public  int selectMax() {
		int num = 0;
		String sql = "select Max(cseq) from cart_view";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(!rs.wasNull()) {
				rs.next();
				num = rs.getInt(1)+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		return num;
	}
	//insert cart
	public void insertCart(CartVO cVo) {
		int num = selectMax();
		String sql = "insert into cart(cseq, id, pseq, quantity) values(?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, cVo.getId());
			pstmt.setInt(3, cVo.getPseq());
			pstmt.setInt(4, cVo.getQuantity());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
	}
	
	//select list
	public ArrayList<CartVO> listCart(String userid){
		String sql = "select * from cart_view where id = ? order by cseq desc";
		ArrayList<CartVO> cartlist = new ArrayList<CartVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVO cVo = new CartVO();
				cVo.setCseq(rs.getInt(1));
				cVo.setId(rs.getString(2));
				cVo.setPseq(rs.getInt(3));
				cVo.setMname(rs.getString(4));
				cVo.setPname(rs.getString(5));
				cVo.setQuantity(rs.getInt(6));
				cVo.setIndate(rs.getTimestamp(7));
				cVo.setPrice2(rs.getInt(8));
				cartlist.add(cVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		return cartlist;
	}
	
	//delete cart
	public void deleteCart(int cseq) {
		String sql = "delete from cart where cseq=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
	}
	
	//auto_increment sort
	public void auto_increment_rsort(int cseq) {
		String sql = "update cart set cseq=cseq-1 where cseq>?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con,pstmt);
		}
	}
	
}
