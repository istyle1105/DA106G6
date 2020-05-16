package com.logfav.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Log_favJNDIDAO implements Log_favDAO_interface {
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
		"INSERT INTO log_fav (log_id, mem_no,fav_status) VALUES (?,?,0)";
	private static final String GET_ALL_STMT = 
		"SELECT log_id, mem_no, fav_status FROM log_fav order by log_id";
	private static final String GET_ONE_STMT = 
		"SELECT log_id, mem_no, fav_status FROM log_fav where log_id = ?";
	private static final String DELETE = 
		"DELETE FROM log_fav where log_id = ? and mem_no = ?";
	private static final String UPDATE = 
		"UPDATE log_fav set fav_status=? where log_id=? and mem_no=?  ";
	private static final String GET_MEM_FAV =
		"SELECT log_id ,mem_no, fav_status FROM log_fav where mem_no = ?";

	
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
	
	
	
	@Override
	public void insert(Log_favVO log_favvo)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, log_favvo.getLog_id());
			pstmt.setString(2, log_favvo.getMem_no());
			
			

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
	public int update(Log_favVO log_favvo) {

		int u;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, log_favvo.getFav_status());
			pstmt.setString(2, log_favvo.getLog_id());
			pstmt.setString(3, log_favvo.getMem_no());
			
			 u =pstmt.executeUpdate();

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
			return u;
	}

	@Override
	public void delete(Log_favVO log_favvo)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, log_favvo.getLog_id());
			pstmt.setString(2, log_favvo.getMem_no());
			
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
	public Log_favVO findByPrimaryKey(String com_id) {

		Log_favVO log_favvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				log_favvo = new Log_favVO();
				log_favvo.setLog_id(rs.getString("log_id"));
				log_favvo.setMem_no(rs.getString("mem_no"));
				log_favvo.setFav_status(rs.getInt("fav_status"));
				
				
				
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
		return log_favvo;
	}
	
	
	
	
	
	
	@Override
	public List<Log_favVO> getMemFav(String mem_no) {
		
		List<Log_favVO> list = new ArrayList<Log_favVO>();
		Log_favVO log_favvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_FAV);
			
			pstmt.setString(1,mem_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				log_favvo = new Log_favVO();
				log_favvo.setLog_id(rs.getString("log_id"));
				log_favvo.setMem_no(rs.getString("mem_no"));
				log_favvo.setFav_status(rs.getInt("fav_status"));
				
				list.add(log_favvo); // Store the row in the list
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
	public List<Log_favVO> getAll() {
		List<Log_favVO> list = new ArrayList<Log_favVO>();
		Log_favVO log_favvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				log_favvo = new Log_favVO();
				log_favvo.setLog_id(rs.getString("log_id"));
				log_favvo.setMem_no(rs.getString("mem_no"));
				log_favvo.setFav_status(rs.getInt("fav_status"));
				
				
				
				list.add(log_favvo); // Store the row in the list
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
//		Log_favJDBCDAO dao = new Log_favJDBCDAO();

//		//新增
//		Log_favVO log_fav01 = new Log_favVO();
//		log_fav01.setLog_id("L0002");
//		log_fav01.setMem_no("M0004");
//		
//		
//		dao.insert(log_fav01);
		
		
		
//		
//
//		// 修改
//		Log_favVO log_fav02 = new Log_favVO();
//		log_fav02.setFav_status(1);
//		log_fav02.setLog_id("L0005");
//		log_fav02.setMem_no("M0005");
//		int updateCount_update = dao.update(log_fav02);
//		System.out.println(updateCount_update);
//		
//		dao.update(log_fav02);
		
//
		// 刪除
//		dao.delete("LR0007");
//
		//查詢
//		Log_favVO log_fav03 = dao.findByPrimaryKey("L0003");
//		System.out.print(log_fav03.getLog_id() + ",");
//		System.out.print(log_fav03.getMem_no() + ",");
//		System.out.print(log_fav03.getFav_status() + ",");
//		
//		System.out.println("---------------------");
		
		
//		Log_favVO log_fav00 = dao.getMemFav("M0005");
//		System.out.print(log_fav00.getLog_id() + ",");
//		System.out.print(log_fav00.getMem_no() + ",");
//		System.out.print(log_fav00.getFav_status() + ",");
//		
		
		
		

		//查詢 all
//		List<Log_favVO> list = dao.getAll();
//		for (Log_favVO log_fav : list) {
//			System.out.print(log_fav.getLog_id() + ",");
//			System.out.print(log_fav.getMem_no() + ",");
//			System.out.print(log_fav.getFav_status() + ",");
//			
//			System.out.println("---------------------");
//		}
//	}

}