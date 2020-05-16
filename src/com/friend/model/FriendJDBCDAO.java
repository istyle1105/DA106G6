package com.friend.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class FriendJDBCDAO implements FriendDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "DA106G6";
	
	
	
	private static final String INSERT_STMT = 
		"INSERT INTO FRIEND(MEM_NO, FR_NO) VALUES(?,?)";
		
	private static final String GET_ALL_STMT = 
		"SELECT mem_no, fr_no, fr_status FROM friend";
	
	private static final String GET_ONE_STMT = 
		"SELECT mem_no, fr_no, fr_status FROM friend WHERE mem_no=? and fr_no=?";
	
	private static final String DELETE = 
		"DELETE FROM friend WHERE mem_no=?";
	
	private static final String UPDATESTATUS = 
		"UPDATE friend SET FR_STATUS=? WHERE mem_no=? and fr_no=?";


	

	@Override
	public void insert(FriendVO friendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, friendVO.getMem_no());
			pstmt.setString(2, friendVO.getFr_no());

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
		}catch (Exception e) {
            e.printStackTrace();}
		finally {
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
	public void updateStatus(FriendVO friendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);
						
	
			pstmt.setInt(1, friendVO.getFr_status());
			pstmt.setString(2,friendVO.getMem_no());
			pstmt.setString(3,friendVO.getFr_no());

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
		} 
		finally {
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
	public FriendVO findByPrimaryKey(String mem_no, String friend_no) {

		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
//			"SELECT mem_no, fr_no, fr_status FROM friend WHERE mem_no=? and fr_no=?";

			pstmt.setString(1,mem_no);
			pstmt.setString(2,friend_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				friendVO=new FriendVO();
				friendVO.setMem_no(rs.getString("mem_no"));
				friendVO.setFr_no(rs.getString("fr_no"));
				friendVO.setFr_status(rs.getInt("fr_status"));
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
		return friendVO;
	}

	@Override
	public List<FriendVO> getAll() {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				friendVO=new FriendVO();
				friendVO.setMem_no(rs.getString("mem_no"));
				friendVO.setFr_no(rs.getString("fr_no"));
				friendVO.setFr_status(rs.getInt("fr_status"));

				list.add(friendVO); 				
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

		FriendJDBCDAO dao = new FriendJDBCDAO();
		
		// 新增
//		FriendVO friendVO=new FriendVO();
//		friendVO.setMem_no("M0002");
//		friendVO.setFr_no("M0005");
//		dao.insert(friendVO);

		//// 修改
//		
//		FriendVO friendVO2=new FriendVO();
//		friendVO2.setMem_no("M0002");
//		friendVO2.setFr_no("M0005");
//		friendVO2.setFr_status(1);		
//		dao.updateStatus(friendVO2);
		

		
		FriendVO friendVO3=dao.findByPrimaryKey("M0002","M0005");
		System.out.print(friendVO3.getMem_no()+ ",");
		System.out.print(friendVO3.getFr_no()+ ",");		
		System.out.println(friendVO3.getFr_status());
		System.out.println("---------------------");
	

		List<FriendVO> list = dao.getAll();
		for (FriendVO friendVO4 : list) {
			System.out.print(friendVO4.getMem_no()+ ",");
			System.out.print(friendVO4.getFr_no()+ ",");		
			System.out.println(friendVO4.getFr_status());

		}
	}
}