package com.p_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class P_ReportJDBCDAO implements P_ReportDAO_interface {

	final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
	private static final String USER = "C##DA106";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO P_REPORT (P_RE_NO, DE_NO, MEM_NO, P_RE_REASON, P_RE_STATUS)"
			+ "VALUES(('DR'||LPAD(to_char(SEQ_REPORT_NO.NEXTVAL), 4, '0')), ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE DEMAND SET P_RE_REASON = ?, P_RE_STATUS = ? "
			+ "WHERE P_RE_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM P_REPORT WHERE P_RE_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM P_REPORT WHERE P_RE_NO = ?";
	private static final String GET_ALL = "SELECT * FROM P_REPORT";

	@Override
	public void add(P_ReportVO p_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, p_reportVO.getDe_no());
			pstmt.setString(2, p_reportVO.getMem_no());
			pstmt.setString(3, p_reportVO.getP_re_reason());
			pstmt.setInt(4, p_reportVO.getP_re_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. ", se);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, p_reportVO.getP_re_reason());
			pstmt.setInt(2, p_reportVO.getP_re_status());
			pstmt.setString(3, p_reportVO.getP_re_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, p_re_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se);
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, p_re_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_reportVO = new P_ReportVO();
				p_reportVO.setP_re_no(rs.getString("P_re_no"));
				p_reportVO.setDe_no(rs.getString("De_no"));
				p_reportVO.setMem_no(rs.getString("Mem_no"));
				p_reportVO.setP_re_reason(rs.getString("P_re_reason"));
				p_reportVO.setP_re_time(rs.getTimestamp("P_re_time"));
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		P_ReportJDBCDAO dao = new P_ReportJDBCDAO();

//		新增
		P_ReportVO report = new P_ReportVO();
		report.setDe_no("D0002");
		report.setMem_no("M0002");
		report.setP_re_reason("不喜歡");
		report.setP_re_status(1);
		dao.add(report);

//		修改
		P_ReportVO report2 = new P_ReportVO();
		report2.setP_re_reason("就是不喜歡");
		report2.setP_re_status(2);
		report2.setP_re_no("DR0004");
		dao.update(report2);

//		刪除
		dao.delete("DR0004");

//		查詢PK
		P_ReportVO report3 = dao.findByPK("DR0002");
		System.out.print(report3.getP_re_no() + ",");
		System.out.print(report3.getDe_no() + ",");
		System.out.print(report3.getMem_no() + ",");
		System.out.print(report3.getP_re_reason() + ",");
		System.out.println(report3.getP_re_time());

//		?查詢getll()
		List<P_ReportVO> list = dao.getAll();
		for (P_ReportVO report4 : list) {
			System.out.print(report4.getP_re_no() + ",");
			System.out.print(report4.getDe_no() + ",");
			System.out.print(report4.getMem_no() + ",");
			System.out.print(report4.getP_re_status() + ",");
			System.out.println(report4.getP_re_time());
		}

	}

}
