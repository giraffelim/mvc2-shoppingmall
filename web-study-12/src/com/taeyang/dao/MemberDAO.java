package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.taeyang.dto.MemberVO;

import util.DBManager;

public class MemberDAO {

	private MemberDAO() {
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	//확인
	public int confirmID(String userid) {
		int result = -1;
		String sql ="select * from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}else {
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	//회원 정보 가져오기
	public MemberVO getMember(String id) {
		MemberVO mVo = null;
		String sql="select * from member where id=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mVo = new MemberVO();
				mVo.setId(rs.getString("id"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setName(rs.getString("name"));
				mVo.setEmail(rs.getString("email"));
				mVo.setZip_num(rs.getString("zip_num"));
				mVo.setAddress(rs.getString("address"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setUseyn(rs.getString("useyn"));
				mVo.setIndate(rs.getTimestamp("indate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	//회원 가입 정보 저장
	public int insertMember(MemberVO mVo) {
		int result = 0;
		String sql = "insert into Member(id, pwd, name, zip_num, address, phone) values(?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con=DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mVo.getId());
			pstmt.setString(2, mVo.getPwd());
			pstmt.setString(3, mVo.getName());
			pstmt.setString(4, mVo.getZip_num());
			pstmt.setString(5, mVo.getAddress());
			pstmt.setString(6, mVo.getPhone());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		return result;
	}
}
