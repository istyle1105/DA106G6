package com.trip_track_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Trip_track_listJDBCDAO implements Trip_track_listDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO TRIP_TRACK_LIST(TRIP_ID, MEM_NO) VALUES(?,?)";
	private static final String GET_ALL_STMT = 
		"select TRIP_ID,MEM_NO from trip_track_list";
	private static final String GET_ONE_STMT = 
		"select TRIP_ID,MEM_NO from trip_track_list where TRIP_ID=?";
	private static final String DELETE = 
		"DELETE FROM TRIP_TRACK_LIST WHERE TRIP_ID=? and MEM_NO=?";
	private static final String UPDATE = "no need";

	@Override
	public void insert(Trip_track_listVO trip_track_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, trip_track_listVO.getTrip_id());
			pstmt.setString(2, trip_track_listVO.getMem_no());
			
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
	public void update(Trip_track_listVO trip_track_listVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trip_track_listVO findByPrimaryKey(String trip_id) {
		Trip_track_listVO trip_track_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trip_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trip_track_listVO = new Trip_track_listVO();		
				trip_track_listVO.setMem_no(rs.getString("mem_no"));
				trip_track_listVO.setTrip_id(rs.getString("trip_id"));
				
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
		return trip_track_listVO;
	}

	@Override
	public List<Trip_track_listVO> getAll() {
		List<Trip_track_listVO> list = new ArrayList<Trip_track_listVO>();
		Trip_track_listVO trip_track_listVO = null;

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
				trip_track_listVO = new Trip_track_listVO();		
				trip_track_listVO.setMem_no(rs.getString("mem_no"));
				trip_track_listVO.setTrip_id(rs.getString("trip_id"));
				list.add(trip_track_listVO); // Store the row in the list
				
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
	public void delete(String trip_id, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trip_id);
			pstmt.setString(2, mem_no);

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
	
	public static void main(String[] args) {

		Trip_track_listJDBCDAO dao = new Trip_track_listJDBCDAO();

		// 新增
//		Trip_track_listVO trip_track_listVO = new Trip_track_listVO();
//		trip_track_listVO.setMem_no("M0003");
//		trip_track_listVO.setTrip_id("T0001");
//		dao.insert(trip_track_listVO);

//		// 刪除
//		dao.delete("T0002","M0002");
//
//		// 查詢
//		Trip_track_listVO Trip_track_listVO2 = dao.findByPrimaryKey("T0004");
//		System.out.print(Trip_track_listVO2.getMem_no() + ",");
//		System.out.print(Trip_track_listVO2.getTrip_id());
//		System.out.println("---------------------");
//
		// 查詢
//		List<Trip_track_listVO> list = dao.getAll();
//		for (Trip_track_listVO trip_track_listVO3 : list) {
//			System.out.print(trip_track_listVO3.getMem_no() + ",");
//			System.out.print(trip_track_listVO3.getTrip_id());
//			System.out.println("---------------------");
//		}
	}

}
