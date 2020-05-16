package com.trip_message.model;

import java.sql.*;
import java.util.*;


public class Trip_messageJDBCDAO implements Trip_messageDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO TRIP_MESSAGE (T_MSG_ID, MEM_NO, TRIP_ID, T_MSG_CONTENT) VALUES ('TM'||lpad(to_char(SEQ_T_MSG_ID.nextval),4,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select t_msg_id, mem_no,trip_id,t_msg_content,t_msg_time from TRIP_MESSAGE";
	private static final String GET_ONE_STMT = 
		"select t_msg_id, mem_no,trip_id,t_msg_content,t_msg_time from TRIP_MESSAGE WHERE t_msg_id=?";
	private static final String DELETE = 
		"delete from TRIP_MESSAGE WHERE t_msg_id=?";
	private static final String UPDATE = 
		"UPDATE TRIP_MESSAGE set t_msg_content=? WHERE t_msg_id=?";
	
	@Override
	public void insert(Trip_messageVO trip_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, trip_messageVO.getMem_no());
			pstmt.setString(2, trip_messageVO.getTrip_id());
			pstmt.setString(3, trip_messageVO.getT_msg_content());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void update(Trip_messageVO trip_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, trip_messageVO.getT_msg_content());
			pstmt.setString(2, trip_messageVO.getT_msg_id());
	
			
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
	public void delete(String t_msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, t_msg_id);
			
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
	public Trip_messageVO findByPrimaryKey(String t_msg_id) {
		Trip_messageVO trip_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, t_msg_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trip_messageVO = new Trip_messageVO();		
				trip_messageVO.setMem_no(rs.getString("mem_no"));
				trip_messageVO.setTrip_id(rs.getString("trip_id"));
				trip_messageVO.setT_msg_id(rs.getString("t_msg_id"));
				trip_messageVO.setT_msg_content(rs.getString("t_msg_content"));
				trip_messageVO.setT_msg_time(rs.getTimestamp("t_msg_time"));				
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
		return trip_messageVO;
	}

	@Override
	public List<Trip_messageVO> getAll() {
		List<Trip_messageVO> list = new ArrayList<Trip_messageVO>();
		Trip_messageVO trip_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trip_messageVO = new Trip_messageVO();		
				trip_messageVO.setMem_no(rs.getString("mem_no"));
				trip_messageVO.setTrip_id(rs.getString("trip_id"));
				trip_messageVO.setT_msg_id(rs.getString("t_msg_id"));
				trip_messageVO.setT_msg_content(rs.getString("t_msg_content"));
				trip_messageVO.setT_msg_time(rs.getTimestamp("t_msg_time"));
				list.add(trip_messageVO); // Store the row in the list
				
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
	
	
	public static void main(String[] args) {

		Trip_messageJDBCDAO dao = new Trip_messageJDBCDAO();

		// 新增
//		Trip_messageVO trip_messageVO = new Trip_messageVO();
//		trip_messageVO.setMem_no("M0005");
//		trip_messageVO.setTrip_id("T0005");
//		trip_messageVO.setT_msg_content("可以幫我買東京芭娜娜嗎?");;
//		dao.insert(trip_messageVO);

//		// 刪除
//		dao.delete("TM0008");
//		
//		// 修改
		Trip_messageVO trip_messageVO1 = new Trip_messageVO();
		trip_messageVO1.setT_msg_content("草莓很好吃喔");
		trip_messageVO1.setT_msg_id("TM0007");
		dao.update(trip_messageVO1);

		
		// 查詢
//		Trip_messageVO trip_messageVO2 = dao.findByPrimaryKey("TM0007");
//		System.out.print(trip_messageVO2.getT_msg_id() + ",");
//		System.out.print(trip_messageVO2.getMem_no() + ",");
//		System.out.print(trip_messageVO2.getTrip_id() + ",");
//		System.out.print(trip_messageVO2.getT_msg_content() + ",");
//		System.out.print(trip_messageVO2.getT_msg_time());
//		System.out.println("---------------------");

		// 查詢
		List<Trip_messageVO> list = dao.getAll();
		for (Trip_messageVO trip_messageVO3 : list) {
			System.out.print(trip_messageVO3.getT_msg_id() + ",");
			System.out.print(trip_messageVO3.getMem_no() + ",");
			System.out.print(trip_messageVO3.getTrip_id() + ",");
			System.out.print(trip_messageVO3.getT_msg_content() + ",");
			System.out.print(trip_messageVO3.getT_msg_time());
			System.out.println("---------------------");
		}
	}

}
