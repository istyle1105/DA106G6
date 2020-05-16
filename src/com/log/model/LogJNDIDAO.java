package com.log.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.*;

public class LogJNDIDAO implements LogDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";
	
	
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
		"INSERT INTO log (log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited) VALUES ('L'||LPAD(to_char(SEQ_LOG_ID.NEXTVAL), 4, '0'),?,?,?,?,sysdate,0,0)";
	private static final String GET_ALL_STMT = 
		"SELECT  log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log order by log_id where l_status = 0";
	private static final String GET_ONE_STMT = 
		"SELECT log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where log_id = ?";
	private static final String DELETE = 
		"DELETE FROM log where log_id = ?";
	private static final String UPDATE = 
		"UPDATE log set   mem_no=? ,log_title=? ,log_con=? ,log_pic=? ,l_time=sysdate ,l_status=?,l_favorited=? where log_id = ?";
	private static final String GET_MEM_LOG =
		"SELECT log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where mem_no=? order by log_id";
	private static final String GET_LOG_TITLE =	
		"SELECT log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where l_status = 0 and log_title Like (?)  ";
	private static final String GET_SIX_LOG_FAV =
		"SELECT  log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where l_status = 0  order by l_favorited desc ";
	private static final String GET_SIX_LOG_DESC =
			"SELECT  log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where l_status = 0  order by l_time desc ";
	private static final String GET_ALL_LOG_FAV =
			"SELECT  log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where l_status = 0  order by l_favorited desc ";
	private static final String GET_ALL_LOG_DESC =
			"SELECT  log_id,mem_no,log_title,log_con,log_pic,l_time,l_status,l_favorited FROM log where l_status = 0  order by l_time desc ";
		
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	
	@Override
	public void insert(LogVO logvo)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, logvo.getLog_id());
			pstmt.setString(1, logvo.getMem_no());
			pstmt.setString(2, logvo.getLog_title());
			pstmt.setString(3, logvo.getLog_con());
			pstmt.setString(4, logvo.getLog_pic());
//			pstmt.setTimestamp(4, logvo.getL_time());
//			pstmt.setInt(5, logvo.getL_status());
//			pstmt.setInt(6, logvo.getL_favorited());
			

			pstmt.executeUpdate();

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
	public void update(LogVO logvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, logvo.getMem_no());
			pstmt.setString(2, logvo.getLog_title());
			pstmt.setString(3, logvo.getLog_con());
			pstmt.setString(4, logvo.getLog_pic());
		//	pstmt.setTimestamp(5, logvo.getL_time());
			pstmt.setInt(5, logvo.getL_status());
			pstmt.setInt(6, logvo.getL_favorited());
			pstmt.setString(7, logvo.getLog_id());
			

			pstmt.executeUpdate();

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
	public void delete(String log_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, log_id);

			pstmt.executeUpdate();

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
	public LogVO findByPrimaryKey(String log_id) {

		LogVO logvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, log_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				
			}

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
		return logvo;
	}

	@Override
	public List<LogVO> getAll() {
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
	public List<LogVO> getSixLogFav() {
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SIX_LOG_FAV);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
	public List<LogVO> getAllLogFav() {
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_LOG_FAV);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
	public List<LogVO> getSixLogDesc() {
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SIX_LOG_DESC);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
	public List<LogVO> getAllLogDesc() {
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_LOG_DESC);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
	public List<LogVO> getMemAll(String mem_no) {
		
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO logvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_LOG);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				logvo = new LogVO();
				logvo.setLog_id(rs.getString("log_id"));
				logvo.setMem_no(rs.getString("mem_no"));
				logvo.setLog_title(rs.getString("log_title"));
				logvo.setLog_con(rs.getString("log_con"));
				logvo.setLog_pic(rs.getString("log_pic"));
				logvo.setL_time(rs.getTimestamp("l_time"));
				logvo.setL_status(rs.getInt("l_status"));
				logvo.setL_favorited(rs.getInt("l_favorited"));
				
				list.add(logvo); // Store the row in the list
			}

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
		public List<LogVO> getLogTitle(String log_title){
			
			List<LogVO> list = new ArrayList<LogVO>();
			LogVO logvo = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String getlog_title = "%"+log_title+"%";

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_LOG_TITLE);
				
				pstmt.setString(1, getlog_title);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 銋迂� Domain objects
					logvo = new LogVO();
					logvo.setLog_id(rs.getString("log_id"));
					logvo.setMem_no(rs.getString("mem_no"));
					logvo.setLog_title(rs.getString("log_title"));
					logvo.setLog_con(rs.getString("log_con"));
					logvo.setLog_pic(rs.getString("log_pic"));
					logvo.setL_time(rs.getTimestamp("l_time"));
					logvo.setL_status(rs.getInt("l_status"));
					logvo.setL_favorited(rs.getInt("l_favorited"));
					
					list.add(logvo); // Store the row in the list
				}

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
//
		LogJDBCDAO dao = new LogJDBCDAO();
//
//		//新增
//		LogVO log01 = new LogVO();
//		log01.setMem_no("M0004");
//		log01.setLog_title("45645");
//		log01.setLog_con("456456");
//		
//		byte[] pic = getPictureByteArray("WebContent/images/boo1.png");
//		String encodedString = Base64.getEncoder().encodeToString(pic);
//		log01.setLog_pic(encodedString);
//		log01.setL_status(0);
//		log01.setL_favorited(0);
//		dao.insert(log01);
//		
//		

		// 修改
		LogVO logvo02 = new LogVO();
		logvo02.setMem_no("M0001");
		logvo02.setLog_title("鞋帶鬆了！正妹等燈「屁股蛋」神抖出網爆嗨 神人神出");
		logvo02.setLog_con("來試試看");
		byte[] pic = getPictureByteArray("WebContent/images/titty2.png");
		String ss = Base64.getEncoder().encodeToString(pic);
		
		logvo02.setLog_pic(ss);
		logvo02.setL_time(java.sql.Timestamp.valueOf("2020-03-25 12:12:12"));
		logvo02.setL_status(1);
		logvo02.setL_favorited(600);
		logvo02.setLog_id("L0006");
		
		dao.update(logvo02);
		
		
//		
//
//		// 刪除
//		dao.delete("L0005");

		//查詢
//		LogVO logvo03 = dao.findByPrimaryKey("L0003");
//		System.out.print(logvo03.getLog_id() + ",");
//		System.out.print(logvo03.getMem_no() + ",");
//		System.out.print(logvo03.getLog_title() + ",");
//		System.out.print(logvo03.getLog_con() + ",");
//		System.out.print(logvo03.getLog_pic() + ",");
//		System.out.print(logvo03.getL_status() + ",");
//		System.out.print(logvo03.getL_favorited() + ",");
//		System.out.println(logvo03.getL_time());
//		System.out.println("---------------------");
		
//		List<LogVO> list = dao.getmemall("M0001");
//		for(LogVO log : list) {
//		System.out.print(log.getLog_id() + ",");
//		System.out.print(log.getMem_no() + ",");
//		System.out.print(log.getLog_title() + ",");
//		System.out.print(log.getLog_con() + ",");
//		System.out.println(log.getL_favorited() + ",");
//		System.out.println(log.getL_time());
//		System.out.println("---------------------");
//		}
		
//		List<LogVO> list = dao.getlogtitle("安安");
//		for(LogVO log : list) {
//		System.out.print(log.getLog_id() + ",");
//		System.out.print(log.getMem_no() + ",");
//		System.out.print(log.getLog_title() + ",");
//		System.out.print(log.getLog_con() + ",");
//		System.out.println(log.getL_favorited() + ",");
//		System.out.println(log.getL_time());
//		System.out.println("---------------------");
//		}
		
		
		
		
//
//		//查詢 all
//		List<LogVO> list = dao.getAll();
//		for (LogVO log : list) {
//			System.out.print(log.getLog_id() + ",");
//			System.out.print(log.getMem_no() + ",");
//			System.out.print(log.getLog_title() + ",");
//			System.out.print(log.getLog_con() + ",");
//			System.out.print(log.getLog_pic() + ",");
//			System.out.print(log.getL_status() + ",");
//			System.out.println(log.getL_favorited() + ",");
//			System.out.println(log.getL_time());
//			System.out.println("---------------------");
//		}
	}

}