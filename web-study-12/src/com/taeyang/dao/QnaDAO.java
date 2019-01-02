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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 관리자모드에서 필요한 메소드

	public ArrayList<QnaVO> listAllQna() {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();

		// 게시판의 데이터를 가지고 오는 쿼리 rep:1 게시물 2: 답변
		String sql = "select * from qna order by indate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setQseq(rs.getInt("qseq"));
				qnaVO.setSubject(rs.getString("subject"));
				qnaVO.setContent(rs.getString("content"));
				qnaVO.setId(rs.getString("id"));
				qnaVO.setIndate(rs.getTimestamp("indate"));
				qnaVO.setReply(rs.getString("reply"));
				qnaVO.setReq(rs.getString("req"));
				qnaList.add(qnaVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qnaList;
	}
	
	public void updateQna(QnaVO qnaVO) {
		String sql = "update qna set reply=?, req='2' where qseq=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		 
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,qnaVO.getReply());
			pstmt.setInt(2, qnaVO.getQseq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
