package com.logreport.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.*;

public class Log_reportJNDIDAO implements Log_reportDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "TEST1";
//	String passwd = "TEST1";

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team6DB");
			
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	
	
	private static final String INSERT_STMT = 
		"INSERT INTO log_report (l_re_id,mem_no,log_id,l_re_reason,l_re_status) VALUES ('LR'||LPAD(to_char(SEQ_L_RE_ID.NEXTVAL), 4, '0'),?,?,?,0)";
	private static final String GET_ALL_STMT = 
		"SELECT l_re_id,mem_no,log_id,l_re_reason,l_re_status FROM log_report where l_re_status ='0' order by l_re_id desc";
	private static final String GET_ONE_STMT = 
		"SELECT l_re_id,mem_no,log_id,l_re_reason,l_re_status FROM log_report where l_re_id = ?  ";
	private static final String DELETE = 
		"DELETE FROM log_report where l_re_id = ?";
	private static final String UPDATE = 
		"UPDATE log_report set   mem_no=? ,log_id=? ,l_re_reason=? ,l_re_status=?  where l_re_id = ?";
	private static final String GET_ONE_REPORT = 
			"SELECT l_re_id,mem_no,log_id,l_re_reason,l_re_status FROM log_report where log_id = ?  ";

	
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
	
	
	
	@Override
	public void insert(Log_reportVO log_reportvo)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, log_reportvo.getMem_no());
			pstmt.setString(2, log_reportvo.getLog_id());
			pstmt.setString(3, log_reportvo.getL_re_reason());
			
			

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Log_reportVO log_reportvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, log_reportvo.getMem_no());
			pstmt.setString(2, log_reportvo.getLog_id());
			pstmt.setString(3, log_reportvo.getL_re_reason());
			pstmt.setInt(4, log_reportvo.getL_re_status());
			pstmt.setString(5, log_reportvo.getL_re_id());
			
			

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String log_reportvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, log_reportvo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Log_reportVO findByPrimaryKey(String l_re_id) {

		Log_reportVO log_reportvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, l_re_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				log_reportvo = new Log_reportVO();
				log_reportvo.setL_re_id(rs.getString("l_re_id"));
				log_reportvo.setMem_no(rs.getString("mem_no"));
				log_reportvo.setLog_id(rs.getString("log_id"));
				log_reportvo.setL_re_reason(rs.getString("l_re_reason"));
				log_reportvo.setL_re_status(rs.getInt("l_re_status"));
				
				
			}

					} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return log_reportvo;
	}
	
	
//	@Override
//	public Log_reportVO getOneReport(String log_id) {
//
//		Log_reportVO log_reportvo = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, l_re_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 銋迂� Domain objects
//				log_reportvo = new Log_reportVO();
//				log_reportvo.setL_re_id(rs.getString("l_re_id"));
//				log_reportvo.setMem_no(rs.getString("mem_no"));
//				log_reportvo.setLog_id(rs.getString("log_id"));
//				log_reportvo.setL_re_reason(rs.getString("l_re_reason"));
//				log_reportvo.setL_re_status(rs.getInt("l_re_status"));
//				
//				
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return log_reportvo;
//	}
	
	

	@Override
	public List<Log_reportVO> getAll() {
		List<Log_reportVO> list = new ArrayList<Log_reportVO>();
		Log_reportVO log_reportvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				log_reportvo = new Log_reportVO();
				log_reportvo.setL_re_id(rs.getString("l_re_id"));
				log_reportvo.setMem_no(rs.getString("mem_no"));
				log_reportvo.setLog_id(rs.getString("log_id"));
				log_reportvo.setL_re_reason(rs.getString("l_re_reason"));
				log_reportvo.setL_re_status(rs.getInt("l_re_status"));
				
				
				list.add(log_reportvo); // Store the row in the list
			}

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	

//	public static void main(String[] args) throws IOException {
//
//		Log_reportJNDIDAO dao = new Log_reportJNDIDAO();
//
//		//新增
//		Log_reportVO log_report01 = new Log_reportVO();
//		log_report01.setMem_no("M0003");
//		log_report01.setLog_id("L0042");
//		log_report01.setL_re_reason("0800");
//		
//		dao.insert(log_report01);
//		
//		
//
////		// 修改
//		Log_reportVO log_report02 = new Log_reportVO();
//		log_report02.setMem_no("M0003");
//		log_report02.setLog_id("L0003");
//		log_report02.setL_re_reason("666");
//		log_report02.setL_re_status(1);
//		log_report02.setL_re_id("LR0003");
//		
//		dao.update(log_report02);
//		
////
//		// 刪除
//		dao.delete("LR0007");
////
//		//查詢
//		Log_reportVO log_reportvo03 = dao.findByPrimaryKey("LR0003");
//		System.out.print(log_reportvo03.getL_re_id() + ",");
//		System.out.print(log_reportvo03.getMem_no() + ",");
//		System.out.print(log_reportvo03.getLog_id() + ",");
//		System.out.print(log_reportvo03.getL_re_reason() + ",");
//		System.out.print(log_reportvo03.getL_re_status() + ",");
//		
//		System.out.println("---------------------");

		//查詢 all
//		List<Log_reportVO> list = dao.getall();
//		for (Log_reportVO log_report : list) {
//			System.out.print(log_report.getL_re_id() + ",");
//			System.out.print(log_report.getMem_no() + ",");
//			System.out.print(log_report.getLog_id() + ",");
//			System.out.print(log_report.getL_re_reason() + ",");
//			System.out.print(log_report.getL_re_status() + ",");
//			System.out.println("---------------------");
//		}
//	}

}