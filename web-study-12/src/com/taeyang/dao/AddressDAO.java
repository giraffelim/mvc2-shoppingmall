package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.taeyang.dto.AddressVO;

import util.DBManager;

public class AddressDAO {

	private AddressDAO() {

	}

	private static AddressDAO instance = new AddressDAO();

	public static AddressDAO getInstance() {
		return instance;
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		String sql = "select * from address where dong like '%"+dong+"%'";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AddressVO aVo = new AddressVO();
				aVo.setZipNum(rs.getString("zip_num"));
				aVo.setSido(rs.getString("sido"));
				aVo.setGugun(rs.getString("gugun"));
				aVo.setDong(rs.getString("dong"));
				aVo.setZipCode(rs.getString("zip_code"));
				aVo.setBunji(rs.getString("bunji"));
				list.add(aVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}
