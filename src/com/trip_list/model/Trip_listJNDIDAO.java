package com.trip_list.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class Trip_listJNDIDAO implements Trip_listDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team6DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO TRIP_LIST (TRIP_ID, MEM_NO) VALUES(?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT TRIP_ID,MEM_NO,REG_TIME,CHECK_STATUS,RATE,COMMENT_CONTENT,COMMENT_TIME FROM TRIP_LIST";
	private static final String GET_ONE_STMT = 
		"SELECT TRIP_ID,MEM_NO,REG_TIME,CHECK_STATUS,RATE,COMMENT_CONTENT,COMMENT_TIME FROM TRIP_LIST where TRIP_ID=? and mem_no=?";
	private static final String DELETE = 
		"DELETE FROM TRIP_LIST WHERE TRIP_ID=? and mem_no=?";
	private static final String UPDATE = 
		"UPDATE TRIP_LIST SET CHECK_STATUS = ?, RATE = ?, COMMENT_CONTENT = ?, COMMENT_TIME =? WHERE TRIP_ID = ? AND MEM_NO = ?";
	private static final String GET_ALL_MY_TRIP = 
		"SELECT TRIP_ID,MEM_NO,REG_TIME,CHECK_STATUS,RATE,COMMENT_CONTENT,COMMENT_TIME FROM TRIP_LIST where mem_no=?";
	private static final String GET_ALL_MY_MEMBER = 
		"SELECT TRIP_ID,MEM_NO,REG_TIME,CHECK_STATUS,RATE,COMMENT_CONTENT,COMMENT_TIME FROM TRIP_LIST where TRIP_ID=?";

	

	@Override
	public void insert(Trip_listVO trip_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, trip_listVO.getTrip_id());
			pstmt.setString(2, trip_listVO.getMem_no());
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
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
	public void update(Trip_listVO trip_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trip_listVO.getCheck_status());
			pstmt.setInt(2, trip_listVO.getRate());
			pstmt.setString(3, trip_listVO.getComment_content());
			pstmt.setTimestamp(4, trip_listVO.getComment_time());
			pstmt.setString(5, trip_listVO.getTrip_id());
			pstmt.setString(6, trip_listVO.getMem_no());
	
			
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
	public void delete(String trip_id,String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trip_id);
			pstmt.setString(2, mem_no);

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
	public Trip_listVO findByPrimaryKey(String trip_id,String mem_no) {
		Trip_listVO trip_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, trip_id);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				trip_listVO = new Trip_listVO();
				trip_listVO.setTrip_id(rs.getString("trip_id"));
				trip_listVO.setMem_no(rs.getString("mem_no"));
				trip_listVO.setReg_time(rs.getTimestamp("reg_time"));
				trip_listVO.setCheck_status(rs.getInt("check_status"));
				trip_listVO.setRate(rs.getInt("rate"));
				trip_listVO.setComment_content(rs.getString("comment_content"));
				trip_listVO.setComment_time(rs.getTimestamp("comment_time"));				
			}

			// Handle any driver errors
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
		return trip_listVO;
	}

	@Override
	public List<Trip_listVO> getAll() {
		List<Trip_listVO> list = new ArrayList<Trip_listVO>();
		Trip_listVO trip_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				trip_listVO = new Trip_listVO();
				trip_listVO.setTrip_id(rs.getString("trip_id"));
				trip_listVO.setMem_no(rs.getString("mem_no"));
				trip_listVO.setReg_time(rs.getTimestamp("reg_time"));
				trip_listVO.setCheck_status(rs.getInt("check_status"));
				trip_listVO.setRate(rs.getInt("rate"));
				trip_listVO.setComment_content(rs.getString("comment_content"));
				trip_listVO.setComment_time(rs.getTimestamp("comment_time"));		
				list.add(trip_listVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<Trip_listVO> getAllByTrip_id(String trip_id) {
		List<Trip_listVO> list = new ArrayList<Trip_listVO>();
		Trip_listVO trip_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MY_MEMBER);
			pstmt.setString(1, trip_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				trip_listVO = new Trip_listVO();
				trip_listVO.setTrip_id(rs.getString("trip_id"));
				trip_listVO.setMem_no(rs.getString("mem_no"));
				trip_listVO.setReg_time(rs.getTimestamp("reg_time"));
				trip_listVO.setCheck_status(rs.getInt("check_status"));
				trip_listVO.setRate(rs.getInt("rate"));
				trip_listVO.setComment_content(rs.getString("comment_content"));
				trip_listVO.setComment_time(rs.getTimestamp("comment_time"));		
				list.add(trip_listVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<Trip_listVO> getAllByMem_no(String mem_no) {
		List<Trip_listVO> list = new ArrayList<Trip_listVO>();
		Trip_listVO trip_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MY_TRIP);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				trip_listVO = new Trip_listVO();
				trip_listVO.setTrip_id(rs.getString("trip_id"));
				trip_listVO.setMem_no(rs.getString("mem_no"));
				trip_listVO.setReg_time(rs.getTimestamp("reg_time"));
				trip_listVO.setCheck_status(rs.getInt("check_status"));
				trip_listVO.setRate(rs.getInt("rate"));
				trip_listVO.setComment_content(rs.getString("comment_content"));
				trip_listVO.setComment_time(rs.getTimestamp("comment_time"));		
				list.add(trip_listVO); // Store the row in the list
			}

			// Handle any driver errors
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

		Trip_listJNDIDAO dao = new Trip_listJNDIDAO();

//		// 新增
//		Trip_listVO trip_listVO = new Trip_listVO();
//		trip_listVO.setMem_no("M0001");
//		trip_listVO.setTrip_id("T0001");
//		dao.insert(trip_listVO);
//
//
//		// 修改
//		Trip_listVO trip_listVO2 = new Trip_listVO();
//		trip_listVO2.setTrip_id("T0003");
//		trip_listVO2.setMem_no("M0005");
//		trip_listVO2.setCheck_status(new Integer(1));
//		trip_listVO2.setComment_content("玩得好爽");
//		trip_listVO2.setRate(new Integer(4));
//		trip_listVO2.setComment_time(java.sql.Timestamp.valueOf("2020-03-01 12:10:10"));
//		
//		dao.update(trip_listVO2);
//
//		// 刪除
//		dao.delete("T0001");
//
		// 查詢
		Trip_listVO trip_listVO3 = dao.findByPrimaryKey("T0004","M0003");
		System.out.print(trip_listVO3.getMem_no() + ",");
		System.out.print(trip_listVO3.getComment_content()+ ",");
		System.out.print(trip_listVO3.getCheck_status()+ ",");
		System.out.print(trip_listVO3.getRate()+ ",");
		System.out.println(trip_listVO3.getReg_time());
		System.out.println("---------------------");
//
//		// 查詢
		List<Trip_listVO> list = dao.getAll();
		for (Trip_listVO trip_list : list) {
			System.out.print(trip_list.getMem_no() + ",");
			System.out.print(trip_list.getComment_content()+ ",");
			System.out.print(trip_list.getCheck_status()+ ",");
			System.out.print(trip_list.getRate()+ ",");
			System.out.println(trip_list.getReg_time());
			System.out.println("---------------------");
		}
	}

}
