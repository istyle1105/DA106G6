package com.logcom.model;

import java.util.*;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.*;

public class Log_comJDBCDAO implements Log_comDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO log_com (com_id,mem_no,log_id,log_com,com_time) VALUES ('LC'||LPAD(to_char(SEQ_L_COM_ID.NEXTVAL), 4, '0'),?,?,?,sysdate)";
	private static final String GET_ALL_STMT = 
		"SELECT com_id,mem_no,log_id,log_com,com_time FROM log_com order by com_id";
	private static final String GET_ONE_STMT = 
		"SELECT com_id,mem_no,log_id,log_com,com_time FROM log_com where com_id = ? order by com_time";
	private static final String DELETE = 
		"DELETE FROM log_com where com_id = ?";
	private static final String UPDATE = 
		"UPDATE log_com set   mem_no=? ,log_id=? ,log_com=? ,com_time=?  where com_id = ?";
	private static final String GET_LOG_COM = 
		"SELECT com_id,mem_no,log_com,com_time FROM log_com WHERE log_id=?";

	
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
	
	
	
	@Override
	public void insert(Log_comVO log_comvo)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, logvo.getLog_id());
			pstmt.setString(1, log_comvo.getMem_no());
			pstmt.setString(2, log_comvo.getLog_id());
			pstmt.setString(3, log_comvo.getLog_com());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(Log_comVO log_comvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, log_comvo.getMem_no());
			pstmt.setString(2, log_comvo.getLog_id());
			pstmt.setString(3, log_comvo.getLog_com());
			pstmt.setTimestamp(4, log_comvo.getCom_time());
			pstmt.setString(5, log_comvo.getCom_id());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String com_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, com_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public Log_comVO findByPrimaryKey(String com_id) {

		Log_comVO log_comvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				log_comvo = new Log_comVO();
				log_comvo.setCom_id(rs.getString("com_id"));
				log_comvo.setMem_no(rs.getString("mem_no"));
				log_comvo.setLog_id(rs.getString("log_id"));
				log_comvo.setLog_com(rs.getString("log_com"));
				log_comvo.setCom_time(rs.getTimestamp("com_time"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return log_comvo;
	}
	
	@Override
	public List<Log_comVO> getLog_com(String log_id){
		List<Log_comVO> list = new ArrayList<Log_comVO>();
		Log_comVO log_comvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LOG_COM);
			pstmt.setString(1, log_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				log_comvo = new Log_comVO();
				log_comvo.setCom_id(rs.getString("com_id"));
				log_comvo.setMem_no(rs.getString("mem_no"));
				log_comvo.setLog_com(rs.getString("log_com"));
				log_comvo.setCom_time(rs.getTimestamp("com_time"));
//				log_comvo.setLog_com_status(rs.getString("log_com_status"));
				
				
				list.add(log_comvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		
		
		
	

	@Override
	public List<Log_comVO> getAll() {
		List<Log_comVO> list = new ArrayList<Log_comVO>();
		Log_comVO log_comvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
//			SELECT log_id,mem_no,log_title,log_con,l_time,l_status,l_favorited FROM log order by log_id";
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				log_comvo = new Log_comVO();
				log_comvo.setCom_id(rs.getString("com_id"));
				log_comvo.setMem_no(rs.getString("mem_no"));
				log_comvo.setLog_id(rs.getString("log_id"));
				log_comvo.setLog_com(rs.getString("log_com"));
				log_comvo.setCom_time(rs.getTimestamp("com_time"));
				
				
				list.add(log_comvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	

	public static void main(String[] args) throws IOException {

		Log_comJDBCDAO dao = new Log_comJDBCDAO();

		//新增
		Log_comVO log_com01 = new Log_comVO();
		log_com01.setMem_no("M0003");
		log_com01.setLog_id("L0006");
		log_com01.setLog_com("好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆好玩到爆");
		dao.insert(log_com01);
		
//		// 修改
//		Log_comVO log_comvo02 = new Log_comVO();
//		log_comvo02.setMem_no("M0005");
//		log_comvo02.setLog_id("L0003");
//		log_comvo02.setLog_com("修改一下");
//		log_comvo02.setCom_time(java.sql.Timestamp.valueOf("2020-03-25 12:12:12"));
//		log_comvo02.setCom_id("LC0004");
//		
//		dao.update(log_comvo02);
//		
//
//		// 刪除
//		dao.delete("LC0006");
//
	//	查詢
//		Log_comVO log_comvo03 = dao.findByPrimaryKey("LC0003");
//		System.out.print(log_comvo03.getCom_id() + ",");
//		System.out.print(log_comvo03.getMem_no() + ",");
//		System.out.print(log_comvo03.getLog_id() + ",");
//		System.out.print(log_comvo03.getLog_com() + ",");
//		System.out.print(log_comvo03.getCom_time() + ",");
//		
//		System.out.println("---------------------");

		
//		List<Log_comVO> list1 = dao.getLog_com("L0003");
//		for(Log_comVO log_com : list1) {
//			System.out.print(log_com.getCom_id() + ",");
//			System.out.print(log_com.getMem_no() + ",");
//			System.out.print(log_com.getLog_id() + ",");
//			System.out.print(log_com.getLog_com() + ",");
//			System.out.print(log_com.getCom_time() + ",");
//			System.out.print(log_com.getLog_com_status() + ",");
//			System.out.println("---------------------");
//			
//		}
		
		
		//查詢 all
//		List<Log_comVO> list = dao.getall();
//		for (Log_comVO log : list) {
//			System.out.print(log.getCom_id() + ",");
//			System.out.print(log.getMem_no() + ",");
//			System.out.print(log.getLog_id() + ",");
//			System.out.print(log.getLog_com() + ",");
//			System.out.print(log.getCom_time() + ",");
//			System.out.println("---------------------");
//		}
	}

}