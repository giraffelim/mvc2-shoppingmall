package com.taeyang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.taeyang.dto.QnaVO;


import util.DBManager;

public class QnaDAO {

	private QnaDAO() {

	}

	private static QnaDAO instance = new QnaDAO();

	public static QnaDAO getInstance() {
		return instance;
	}

	public ArrayList<QnaVO> listQna(String id) {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		String sql = "select * from qna where id=? order by qseq desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaVO qVo = new QnaVO();
				qVo.setQseq(rs.getInt("qseq"));
				qVo.setSubject(rs.getString("subject"));
				qVo.setContent(rs.getString("content"));
				qVo.setId(rs.getString("id"));
				qVo.setIndate(rs.getTimestamp("indate"));
				qVo.setReply(rs.getString("reply"));
				qVo.setReq(rs.getString("req"));
				qnaList.add(qVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qnaList;
	}

	public QnaVO getQna(int seq) {
		QnaVO qVo = null;
		String sql = "select * from qna where qseq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qVo = new QnaVO();
				qVo.setQseq(seq);
				qVo.setSubject(rs.getString("subject"));
				qVo.setContent(rs.getString("content"));
				qVo.setId(rs.getString("id"));
				qVo.setIndate(rs.getTimestamp("indate"));
				qVo.setReply(rs.getString("reply"));
				qVo.setReq(rs.getString("req"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qVo;
	}
	
	public void insertqna(QnaVO qVo, String session_id) {
		String sql = "insert into qna(subject, content, id) values(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qVo.getSubject());
			pstmt.setString(2, qVo.getContent());
			pstmt.setString(3, session_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
