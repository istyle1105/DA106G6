package com.p_report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.demand.model.DemandVO;

public class P_ReportJNDIDAO implements P_ReportDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO P_REPORT (P_RE_NO, DE_NO, MEM_NO, P_RE_REASON)"
			+ "VALUES(('DR'||LPAD(to_char(SEQ_REPORT_NO.NEXTVAL), 4, '0')), ?, ?, ?)";
	private static final String UPDATE = "UPDATE P_REPORT SET P_RE_STATUS = ? WHERE P_RE_NO = ?";
	private static final String DELETE = "UPDATE P_REPORT SET P_RE_STATUS = 1 WHERE P_RE_NO = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM P_REPORT WHERE P_RE_NO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM P_REPORT ORDER BY P_RE_STATUS";

	@Override
	public void add(P_ReportVO p_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, p_reportVO.getDe_no());
			pstmt.setString(2, p_reportVO.getMem_no());
			pstmt.setString(3, p_reportVO.getP_re_reason());

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
	public void update(P_ReportVO p_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, p_reportVO.getP_re_status());
			pstmt.setString(2, p_reportVO.getP_re_no());

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
	public void delete(String p_re_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, p_re_no);

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
	public P_ReportVO findByPK(String p_re_no) {
		
		P_ReportVO p_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_re_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_reportVO = new P_ReportVO();
				p_reportVO.setP_re_no(rs.getString("P_re_no"));
				p_reportVO.setDe_no(rs.getString("De_no"));
				p_reportVO.setMem_no(rs.getString("Mem_no"));
				p_reportVO.setP_re_status(rs.getInt("P_re_status"));
				p_reportVO.setP_re_reason(rs.getString("P_re_reason"));
				p_reportVO.setP_re_time(rs.getTimestamp("P_re_time"));
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
		return p_reportVO;
	}

	@Override
	public List<P_ReportVO> getAll() {
		
		List<P_ReportVO> reportList = new ArrayList<P_ReportVO>();
		P_ReportVO p_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_reportVO = new P_ReportVO();
				p_reportVO.setP_re_no(rs.getString("P_re_no"));
				p_reportVO.setDe_no(rs.getString("De_no"));
				p_reportVO.setMem_no(rs.getString("Mem_no"));
				p_reportVO.setP_re_reason(rs.getString("P_re_reason"));
				p_reportVO.setP_re_status(rs.getInt("P_re_status"));
				p_reportVO.setP_re_time(rs.getTimestamp("P_re_time"));
				reportList.add(p_reportVO);
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
		return reportList;
	}

}
