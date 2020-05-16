package com.p_message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class P_MessageJNDIDAO implements P_MessageDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	+ "VALUES(('DM'||LPAD(to_char(SEQ_MSG_NO.NEXTVAL), 4, '0')), ?, ?, ?, TO_DATE(SYSDATE, 'MM-DD-YYYY HH24:MI'))";
	private static final String INSERT_STMT = "INSERT INTO P_MESSAGE (P_MSG_NO, DE_NO, MEM_NO, P_MSG, P_MSG_TIME)"
			+ "VALUES(('DM'||LPAD(to_char(SEQ_MSG_NO.NEXTVAL), 4, '0')), ?, ?, ?, SYSDATE)";
	private static final String UPDATE = "UPDATE P_MESSAGE SET P_MSG = ?" + "WHERE P_MSG_NO = ?";
	private static final String DELETE = "DELETE FROM P_MESSAGE WHERE P_MSG_NO = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM P_MESSAGE WHERE P_MSG_NO = ?";
	private static final String GET_ONE_BY_DE_NO = "SELECT * FROM P_MESSAGE WHERE DE_NO = ? ORDER BY P_MSG_TIME";
	private static final String GET_ALL_STMT = "SELECT * FROM P_MESSAGE ORDER BY P_MSG_NO";

	@Override
	public void add(P_MessageVO p_messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, p_messageVO.getDe_no());
			pstmt.setString(2, p_messageVO.getMem_no());
			pstmt.setString(3, p_messageVO.getP_msg());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(P_MessageVO p_messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, p_messageVO.getP_msg());
			pstmt.setString(2, p_messageVO.getP_msg_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String p_msg_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, p_msg_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public P_MessageVO findByPK(String p_msg_no) {
		P_MessageVO p_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_msg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_messageVO = new P_MessageVO();
				p_messageVO.setP_msg_no(rs.getString("P_msg_no"));
				p_messageVO.setDe_no(rs.getString("De_no"));
				p_messageVO.setMem_no(rs.getString("Mem_no"));
				p_messageVO.setP_msg(rs.getString("P_msg"));
				p_messageVO.setP_msg_time(rs.getTimestamp("P_msg_time"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return p_messageVO;
	}
	
	@Override
	public List<P_MessageVO> findByDe_no(String de_no) {
		List<P_MessageVO> msgList = new ArrayList<P_MessageVO>();
		P_MessageVO p_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_DE_NO);

			pstmt.setString(1, de_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_messageVO = new P_MessageVO();
				p_messageVO.setP_msg_no(rs.getString("P_msg_no"));
				p_messageVO.setDe_no(rs.getString("De_no"));
				p_messageVO.setMem_no(rs.getString("Mem_no"));
				p_messageVO.setP_msg(rs.getString("P_msg"));
				p_messageVO.setP_msg_time(rs.getTimestamp("P_msg_time"));
				msgList.add(p_messageVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return msgList;
	}

	@Override
	public List<P_MessageVO> getAll() {
		List<P_MessageVO> msgList = new ArrayList<P_MessageVO>();
		P_MessageVO p_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_messageVO = new P_MessageVO();
				p_messageVO.setP_msg_no(rs.getString("P_msg_no"));
				p_messageVO.setDe_no(rs.getString("De_no"));
				p_messageVO.setMem_no(rs.getString("Mem_no"));
				p_messageVO.setP_msg(rs.getString("P_msg"));
				p_messageVO.setP_msg_time(rs.getTimestamp("P_msg_time"));
				msgList.add(p_messageVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return msgList;
	}

}
