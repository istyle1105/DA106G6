package com.trip.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import jdbc.util.compositeQuery.JdbcUtilCompositeTrip;



public class TripJDBCDAO implements TripDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO TRIP (TRIP_ID, MEM_NO, TOUR_ID, MEM_AMOUNT, DAYS, FIRST_DATE, LAST_DATE, REG_START, REG_DEADLINE, MEM_LIMITED, TRIP_PRICE) VALUES ('T'||lpad(to_char(seq_trip_id.nextval),4,'0'),?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT TRIP_ID,MEM_NO,TOUR_ID,MEM_AMOUNT,DAYS,to_char(FIRST_DATE,'yyyy-mm-dd')FIRST_DATE,to_char(LAST_DATE,'yyyy-mm-dd')LAST_DATE,TRIP_PRICE,to_char(REG_START,'yyyy-mm-dd')REG_START,to_char(REG_DEADLINE,'yyyy-mm-dd')REG_DEADLINE,MEM_LIMITED,CURRENT_MEM,TRIP_STATUS,TOUR_STATUS,LAST_MOD_TIME FROM trip ORDER BY TRIP_ID";
		private static final String GET_ONE_STMT = 
			"SELECT TRIP_ID,MEM_NO,TOUR_ID,MEM_AMOUNT,DAYS,to_char(FIRST_DATE,'yyyy-mm-dd')FIRST_DATE,to_char(LAST_DATE,'yyyy-mm-dd')LAST_DATE,TRIP_PRICE,to_char(REG_START,'yyyy-mm-dd')REG_START,to_char(REG_DEADLINE,'yyyy-mm-dd')REG_DEADLINE,MEM_LIMITED,CURRENT_MEM,TRIP_STATUS,TOUR_STATUS,LAST_MOD_TIME FROM trip where TRIP_ID=?";
		private static final String DELETE = 
			"DELETE FROM trip WHERE TRIP_ID=?";
		private static final String UPDATE = 
			"UPDATE TRIP SET MEM_NO = ?, TOUR_ID = ?, MEM_AMOUNT = ?, DAYS = ?, FIRST_DATE = ?, LAST_DATE =?, REG_START = ?, REG_DEADLINE = ?, MEM_LIMITED = ?, CURRENT_MEM = ?, TRIP_STATUS = ?, TOUR_STATUS = ?, LAST_MOD_TIME = ?, TRIP_PRICE =? WHERE TRIP_ID =?";
		private static final String GET_ALL_MY_TRIP_STMT = 
				"SELECT TRIP_ID,MEM_NO,TOUR_ID,MEM_AMOUNT,DAYS,to_char(FIRST_DATE,'yyyy-mm-dd')FIRST_DATE,to_char(LAST_DATE,'yyyy-mm-dd')LAST_DATE,TRIP_PRICE,to_char(REG_START,'yyyy-mm-dd')REG_START,to_char(REG_DEADLINE,'yyyy-mm-dd')REG_DEADLINE,MEM_LIMITED,CURRENT_MEM,TRIP_STATUS,TOUR_STATUS,LAST_MOD_TIME FROM trip where MEM_NO=? and TOUR_STATUS='0' ORDER BY FIRST_DATE";

	
	@Override
	public void insert(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, tripVO.getMem_no());
			pstmt.setString(2, tripVO.getTour_id());
			pstmt.setInt(3, tripVO.getMem_amount());
			pstmt.setInt(4, tripVO.getDays());
			pstmt.setDate(5, tripVO.getFirst_date());
			pstmt.setDate(6, tripVO.getLast_date());
			pstmt.setDate(7, tripVO.getReg_start());
			pstmt.setDate(8, tripVO.getReg_deadline());
			pstmt.setInt(9, tripVO.getMem_limited());
			pstmt.setInt(10, tripVO.getTrip_price());
			
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
	public void update(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tripVO.getMem_no());
			pstmt.setString(2, tripVO.getTour_id());
			pstmt.setInt(3, tripVO.getMem_amount());
			pstmt.setInt(4, tripVO.getDays());
			pstmt.setDate(5, tripVO.getFirst_date());
			pstmt.setDate(6, tripVO.getLast_date());
			pstmt.setDate(7, tripVO.getReg_start());
			pstmt.setDate(8, tripVO.getReg_deadline());
			pstmt.setInt(9, tripVO.getMem_limited());
			pstmt.setInt(10, tripVO.getCurrent_mem());
			pstmt.setInt(11, tripVO.getTrip_status());
			pstmt.setInt(12, tripVO.getTour_status());
			pstmt.setTimestamp(13, tripVO.getLast_mod_time());
			pstmt.setInt(14, tripVO.getTrip_price());
			pstmt.setString(15, tripVO.getTrip_id());
			
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
	public void delete(String trip_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, trip_id);

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
	public TripVO findByPrimaryKey(String trip_id) {
		TripVO tripVO = null;
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
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getString("trip_id"));
				tripVO.setMem_no(rs.getString("mem_no"));
				tripVO.setTour_id(rs.getString("tour_id"));
				tripVO.setMem_amount(rs.getInt("mem_amount"));
				tripVO.setDays(rs.getInt("days"));
				tripVO.setFirst_date(rs.getDate("first_date"));
				tripVO.setLast_date(rs.getDate("last_date"));
				tripVO.setReg_start(rs.getDate("reg_start"));
				tripVO.setReg_deadline(rs.getDate("reg_deadline"));
				tripVO.setMem_limited(rs.getInt("mem_limited"));
				tripVO.setCurrent_mem(rs.getInt("current_mem"));
				tripVO.setTrip_status(rs.getInt("trip_status"));
				tripVO.setTour_status(rs.getInt("tour_status"));
				tripVO.setLast_mod_time(rs.getTimestamp("last_mod_time"));
				tripVO.setTrip_price(rs.getInt("trip_price"));
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
		return tripVO;
	}

	@Override
	public List<TripVO> getAll() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getString("trip_id"));
				tripVO.setMem_no(rs.getString("mem_no"));
				tripVO.setTour_id(rs.getString("tour_id"));
				tripVO.setMem_amount(rs.getInt("mem_amount"));
				tripVO.setDays(rs.getInt("days"));
				tripVO.setFirst_date(rs.getDate("first_date"));
				tripVO.setLast_date(rs.getDate("last_date"));
				tripVO.setReg_start(rs.getDate("reg_start"));
				tripVO.setReg_deadline(rs.getDate("reg_deadline"));
				tripVO.setMem_limited(rs.getInt("mem_limited"));
				tripVO.setCurrent_mem(rs.getInt("current_mem"));
				tripVO.setTrip_status(rs.getInt("trip_status"));
				tripVO.setTour_status(rs.getInt("tour_status"));
				tripVO.setLast_mod_time(rs.getTimestamp("last_mod_time"));
				tripVO.setTrip_price(rs.getInt("trip_price"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> getAllMyTrip(String mem_no) {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MY_TRIP_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getString("trip_id"));
				tripVO.setMem_no(rs.getString("mem_no"));
				tripVO.setTour_id(rs.getString("tour_id"));
				tripVO.setMem_amount(rs.getInt("mem_amount"));
				tripVO.setDays(rs.getInt("days"));
				tripVO.setFirst_date(rs.getDate("first_date"));
				tripVO.setLast_date(rs.getDate("last_date"));
				tripVO.setReg_start(rs.getDate("reg_start"));
				tripVO.setReg_deadline(rs.getDate("reg_deadline"));
				tripVO.setMem_limited(rs.getInt("mem_limited"));
				tripVO.setCurrent_mem(rs.getInt("current_mem"));
				tripVO.setTrip_status(rs.getInt("trip_status"));
				tripVO.setTour_status(rs.getInt("tour_status"));
				tripVO.setLast_mod_time(rs.getTimestamp("last_mod_time"));
				tripVO.setTrip_price(rs.getInt("trip_price"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> getAll(Map<String, String[]> map) {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from trip_view "
		          + JdbcUtilCompositeTrip.get_WhereCondition(map)
		          + "order by first_date";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTrip_id(rs.getString("trip_id"));
				tripVO.setMem_no(rs.getString("mem_no"));
				tripVO.setTour_id(rs.getString("tour_id"));
				tripVO.setMem_amount(rs.getInt("mem_amount"));
				tripVO.setDays(rs.getInt("days"));
				tripVO.setFirst_date(rs.getDate("first_date"));
				tripVO.setLast_date(rs.getDate("last_date"));
				tripVO.setReg_start(rs.getDate("reg_start"));
				tripVO.setReg_deadline(rs.getDate("reg_deadline"));
				tripVO.setMem_limited(rs.getInt("mem_limited"));
				tripVO.setCurrent_mem(rs.getInt("current_mem"));
				tripVO.setTrip_status(rs.getInt("trip_status"));
				tripVO.setTour_status(rs.getInt("tour_status"));
				tripVO.setLast_mod_time(rs.getTimestamp("last_mod_time"));
				tripVO.setTrip_price(rs.getInt("trip_price"));
				list.add(tripVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

		TripJDBCDAO dao = new TripJDBCDAO();

		// 新增
//		TripVO tripVO1 = new TripVO();
//		tripVO1.setMem_no("M0003");
//		tripVO1.setTour_id("R0004");
//		tripVO1.setDays(new Integer(8));
//		tripVO1.setFirst_date(java.sql.Date.valueOf("2020-04-01"));
//		tripVO1.setLast_date(java.sql.Date.valueOf("2020-04-09"));
//		tripVO1.setReg_start(java.sql.Date.valueOf("2020-03-01"));
//		tripVO1.setReg_deadline(java.sql.Date.valueOf("2020-03-11"));
//		tripVO1.setMem_amount(new Integer(4));
//		tripVO1.setMem_limited(new Integer(6));
//		tripVO1.setCurrent_mem(new Integer(1));
//		tripVO1.setTrip_price(new Integer(66666));
//		dao.insert(tripVO1);
//
//
//		// 修改
//		TripVO tripVO2 = new TripVO();
//		tripVO2.setTrip_id("T0003");
//		tripVO2.setMem_no("M0005");
//		tripVO2.setTour_id("R0002");
//		tripVO2.setDays(new Integer(8));
//		tripVO2.setFirst_date(java.sql.Date.valueOf("2020-05-01"));
//		tripVO2.setLast_date(java.sql.Date.valueOf("2020-05-09"));
//		tripVO2.setReg_start(java.sql.Date.valueOf("2020-03-01"));
//		tripVO2.setReg_deadline(java.sql.Date.valueOf("2020-03-11"));
//		tripVO2.setMem_amount(new Integer(4));
//		tripVO2.setMem_limited(new Integer(6));
//		tripVO2.setCurrent_mem(new Integer(1));
//		tripVO2.setTrip_price(new Integer(88888));
//		tripVO2.setTour_status(new Integer(1));
//		tripVO2.setTrip_status(new Integer(1));
//		tripVO2.setLast_mod_time(java.sql.Timestamp.valueOf("2020-03-23 23:23:23"));
//		
//		dao.update(tripVO2);

		// 刪除
//		dao.delete("T0007");
//
//		// 查詢
//		TripVO tripVO3 = dao.findByPrimaryKey("T0005");
//		System.out.print(tripVO3.getMem_no() + ",");
//		System.out.print(tripVO3.getTour_id()+ ",");
//		System.out.print(tripVO3.getTrip_price()+ ",");
//		System.out.print(tripVO3.getReg_start()+ ",");
//		System.out.print(tripVO3.getTour_status() + ",");
//		System.out.print(tripVO3.getDays()+ ",");
//		System.out.print(tripVO3.getLast_mod_time()+ ",");
//		System.out.println(tripVO3.getReg_deadline());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<TripVO> list = dao.getAll();
//		for (TripVO aTrip : list) {
//			System.out.print(aTrip.getMem_no() + ",");
//			System.out.print(aTrip.getTour_id()+ ",");
//			System.out.print(aTrip.getTrip_price()+ ",");
//			System.out.print(aTrip.getReg_start()+ ",");
//			System.out.print(aTrip.getTour_status() + ",");
//			System.out.print(aTrip.getDays()+ ",");
//			System.out.print(aTrip.getLast_mod_time()+ ",");
//			System.out.println(aTrip.getReg_deadline());
//			System.out.println("---------------------");
//		}
		//複合查詢測試
//		Map<String,String[]>map = new TreeMap<>();
//		map.put("trip_price", new String[] { "30000" });
//		map.put("days", new String[] { "3" });
//		map.put("first_date", new String[] { "2020-02-15" });
//		map.put("last_date", new String[] { "2020-05-30" });
//		List<TripVO> tripVO = dao.getAll(map);
//		for (TripVO aTrip : tripVO) {
//			System.out.print(aTrip.getMem_no() + ",");
//			System.out.print(aTrip.getTour_id()+ ",");
//			System.out.print(aTrip.getTrip_price()+ ",");
//			System.out.print(aTrip.getReg_start()+ ",");
//			System.out.print(aTrip.getTour_status() + ",");
//			System.out.print(aTrip.getDays()+ ",");
//			System.out.print(aTrip.getLast_mod_time()+ ",");
//			System.out.println(aTrip.getReg_deadline());
//			System.out.println("---------------------");
//		}
		
		List<TripVO> list = dao.getAllMyTrip("M0003");
		for (TripVO aTrip : list) {
			System.out.print(aTrip.getMem_no() + ",");
			System.out.print(aTrip.getTour_id()+ ",");
			System.out.print(aTrip.getTrip_price()+ ",");
			System.out.print(aTrip.getReg_start()+ ",");
			System.out.print(aTrip.getTour_status() + ",");
			System.out.print(aTrip.getDays()+ ",");
			System.out.print(aTrip.getLast_mod_time()+ ",");
			System.out.println(aTrip.getReg_deadline());
			System.out.println("---------------------");
		}
		
	}
}
