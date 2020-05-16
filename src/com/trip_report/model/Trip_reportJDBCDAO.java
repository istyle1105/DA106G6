package com.trip_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Trip_reportJDBCDAO implements Trip_reportDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO TRIP_REPORT (T_REP_ID, TRIP_ID, MEM_NO, T_REP_CONTENT) VALUES ('TR'||lpad(to_char(SEQ_T_REP_ID.nextval),4,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT t_rep_id,trip_id,mem_no,t_rep_content,t_rep_status,t_rep_time from trip_report";
	private static final String GET_ONE_STMT = 
		"SELECT t_rep_id,trip_id,mem_no,t_rep_content,t_rep_status,t_rep_time from trip_report WHERE t_rep_id=?";
	private static final String DELETE = "no need";
	private static final String UPDATE = 
		"UPDATE trip_report set t_rep_content=?,t_rep_status=? WHERE t_rep_id=?";
	
	@Override
	public void insert(Trip_reportVO trip_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, trip_reportVO.getTrip_id());
			pstmt.setString(2, trip_reportVO.getMem_no());
			pstmt.setString(3, trip_reportVO.getT_rep_content());
			
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
	public void update(Trip_reportVO trip_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, trip_reportVO.getT_rep_content());
			pstmt.setInt(2, trip_reportVO.getT_rep_status());
			pstmt.setString(3, trip_reportVO.getT_rep_id());
	
			
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
	public void delete(String t_rep_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trip_reportVO findByPrimaryKey(String t_rep_id) {
		Trip_reportVO trip_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, t_rep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trip_reportVO = new Trip_reportVO();		
				trip_reportVO.setMem_no(rs.getString("mem_no"));
				trip_reportVO.setTrip_id(rs.getString("trip_id"));
				trip_reportVO.setT_rep_id(rs.getString("t_rep_id"));
				trip_reportVO.setT_rep_content(rs.getString("t_rep_content"));
				trip_reportVO.setT_rep_time(rs.getTimestamp("t_rep_time"));		
				trip_reportVO.setT_rep_status(rs.getInt("t_rep_status"));				
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
		return trip_reportVO;
	}

	@Override
	public List<Trip_reportVO> getAll() {
		List<Trip_reportVO> list = new ArrayList<Trip_reportVO>();
		Trip_reportVO trip_reportVO = null;

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
				trip_reportVO = new Trip_reportVO();		
				trip_reportVO.setMem_no(rs.getString("mem_no"));
				trip_reportVO.setTrip_id(rs.getString("trip_id"));
				trip_reportVO.setT_rep_id(rs.getString("t_rep_id"));
				trip_reportVO.setT_rep_content(rs.getString("t_rep_content"));
				trip_reportVO.setT_rep_time(rs.getTimestamp("t_rep_time"));		
				trip_reportVO.setT_rep_status(rs.getInt("t_rep_status"));				
				list.add(trip_reportVO); // Store the row in the list
				
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

		Trip_reportJDBCDAO dao = new Trip_reportJDBCDAO();

		// 新增
//		Trip_reportVO trip_reportVO = new Trip_reportVO();
//		trip_reportVO.setMem_no("M0005");
//		trip_reportVO.setTrip_id("T0005");
//		trip_reportVO.setT_rep_content("團主都把團員當塑膠");
//		dao.insert(trip_reportVO);
		
//		// 修改
		Trip_reportVO trip_reportVO4 = new Trip_reportVO();
		trip_reportVO4.setT_rep_content("團長太不可靠了");
		trip_reportVO4.setT_rep_id("TR0004");
		trip_reportVO4.setT_rep_status(new Integer(1));
		dao.update(trip_reportVO4);
		
//		// 刪除
//		dao.delete("TM0008");
//
		// 查詢
//		Trip_reportVO trip_reportVO2 = dao.findByPrimaryKey("TR0006");
//		System.out.print(trip_reportVO2.getT_rep_id() + ",");
//		System.out.print(trip_reportVO2.getMem_no() + ",");
//		System.out.print(trip_reportVO2.getTrip_id() + ",");
//		System.out.print(trip_reportVO2.getT_rep_content() + ",");
//		System.out.print(trip_reportVO2.getT_rep_time());
//		System.out.print(trip_reportVO2.getT_rep_status());
//		System.out.println("---------------------");

		// 查詢
		List<Trip_reportVO> list = dao.getAll();
		for (Trip_reportVO trip_reportVO3 : list) {
			System.out.print(trip_reportVO3.getT_rep_id() + ",");
			System.out.print(trip_reportVO3.getMem_no() + ",");
			System.out.print(trip_reportVO3.getTrip_id() + ",");
			System.out.print(trip_reportVO3.getT_rep_content() + ",");
			System.out.print(trip_reportVO3.getT_rep_time());
			System.out.print(trip_reportVO3.getT_rep_status());
			System.out.println("---------------------");
		}
	}
}
