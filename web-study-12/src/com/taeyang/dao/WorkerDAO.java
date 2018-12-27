package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class WorkerDAO {

	private WorkerDAO() {

	}

	private static WorkerDAO instance = new WorkerDAO();

	public static WorkerDAO getInstance() {
		return instance;
	}

	public int workerCheck(String userid, String userpw) {
		int result = -1;
		String sql = "select pwd from worker where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 0;
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(userpw)) {
					result = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return result;
	}

}
