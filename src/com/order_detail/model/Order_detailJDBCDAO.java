package com.order_detail.model;

import java.util.*;
import java.sql.*;

public class Order_detailJDBCDAO implements Order_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (P_ORDER_ID,P_ID,OD_QTY,OD_PR) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT P_ORDER_ID,P_ID,OD_QTY,OD_PR FROM ORDER_DETAIL ORDER BY P_ID";
	private static final String GET_ONE_STMT = 
		"SELECT P_ORDER_ID,P_ID,OD_QTY,OD_PR FROM ORDER_DETAIL WHERE P_ORDER_ID = ? AND P_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_DETAIL SET OD_QTY = ? WHERE P_ORDER_ID = ? AND P_ID = ?";
	private static final String GET_Order_detailByP_order_id = 
		"SELECT * FROM ORDER_DETAIL WHERE P_ORDER_ID = ?";

	@Override
	public void insert(Order_detailVO order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detailVO.getP_order_id());
			pstmt.setString(2, order_detailVO.getP_id());
			pstmt.setInt(3, order_detailVO.getOd_qty());
			pstmt.setInt(4, order_detailVO.getOd_pr());

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
	public void update(Order_detailVO order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, order_detailVO.getOd_qty());
			pstmt.setString(2, order_detailVO.getP_order_id());
			pstmt.setString(3, order_detailVO.getP_id());


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
	public Order_detailVO findByPrimaryKey(String p_order_id,String p_id) {

		Order_detailVO order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_order_id);
			pstmt.setString(2, p_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				order_detailVO = new Order_detailVO();
				order_detailVO.setP_order_id(rs.getString("p_order_id"));
				order_detailVO.setP_id(rs.getString("p_id"));
				order_detailVO.setOd_qty(rs.getInt("od_qty"));
				order_detailVO.setOd_pr(rs.getInt("od_pr"));
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
		return order_detailVO;
	}

	@Override
	public List<Order_detailVO> getAll() {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		Order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				order_detailVO = new Order_detailVO();
				order_detailVO.setP_order_id(rs.getString("p_order_id"));
				order_detailVO.setP_id(rs.getString("p_id"));
				order_detailVO.setOd_qty(rs.getInt("od_qty"));
				order_detailVO.setOd_pr(rs.getInt("od_pr"));
				list.add(order_detailVO);  // Store the row in the list
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
	public void insertWhenOrder_masterInsert(Order_detailVO order_detailVO , java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detailVO.getP_order_id());
			pstmt.setString(2, order_detailVO.getP_id());
			pstmt.setInt(3, order_detailVO.getOd_qty());
			pstmt.setInt(4, order_detailVO.getOd_pr());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("訂單明細錯誤，rolled back");
					con.rollback(); 
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
	}
	@Override
	public Set<Order_detailVO> getOrder_detailByP_order_id_inSet(String p_order_id){
		Set<Order_detailVO> set = new LinkedHashSet<Order_detailVO>();
		Order_detailVO order_detailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Order_detailByP_order_id);
			pstmt.setString(1, p_order_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order_detailVO = new Order_detailVO();
				order_detailVO.setP_order_id(rs.getString("p_order_id"));
				order_detailVO.setP_id(rs.getString("p_id"));
				order_detailVO.setOd_pr(rs.getInt("od_pr"));
				order_detailVO.setOd_qty(rs.getInt("od_qty"));
				set.add(order_detailVO);
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
		return set;
	}
	
	@Override
	public List<Order_detailVO> getOrder_detailByP_order_id(String p_order_id){
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		Order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Order_detailByP_order_id);
			pstmt.setString(1, p_order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				order_detailVO = new Order_detailVO();
				order_detailVO.setP_order_id(rs.getString("p_order_id"));
				order_detailVO.setP_id(rs.getString("p_id"));
				order_detailVO.setOd_qty(rs.getInt("od_qty"));
				order_detailVO.setOd_pr(rs.getInt("od_pr"));
				list.add(order_detailVO);  // Store the row in the list
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

		Order_detailJDBCDAO dao = new Order_detailJDBCDAO();

		// 新增
//		Order_detailVO order_detailVO1 = new Order_detailVO();
//		order_detailVO1.setP_order_id("20200324-0003");
//		order_detailVO1.setP_id("P0004");
//		order_detailVO1.setOd_qty(1);
//		order_detailVO1.setOd_pr(100);
//		dao.insert(order_detailVO1);

		// 修改
//		Order_detailVO order_detailVO2 = new Order_detailVO();
//		order_detailVO2.setOd_qty(100);
//		order_detailVO2.setOd_pr(1000);
//		order_detailVO2.setP_order_id("20200324-0003");
//		order_detailVO2.setP_id("P0004");
//		dao.update(order_detailVO2);

		// 刪除
//		dao.delete("20200324-0003","P0004");

		// 查詢
//		Order_detailVO order_detailVO3 = dao.findByPrimaryKey("20200427-0006","P0001");
//		System.out.print(order_detailVO3.getP_order_id() + ",");
//		System.out.print(order_detailVO3.getP_id() + ",");
//		System.out.print(order_detailVO3.getOd_qty() + ",");
//		System.out.print(order_detailVO3.getOd_pr());
//		System.out.println("---------------------");

		// 查詢
//		List<Order_detailVO> list = dao.getAll();
//		for (Order_detailVO  aOrder_detail : list) {
//		System.out.print(aOrder_detail.getP_order_id() + ",");
//		System.out.print(aOrder_detail.getP_id() + ",");
//		System.out.print(aOrder_detail.getOd_qty() + ",");
//		System.out.print(aOrder_detail.getOd_pr());
//		System.out.println("---------------------");
//		}
		
		// 查詢
		List<Order_detailVO> list = dao.getOrder_detailByP_order_id("20200427-0006");
		for (Order_detailVO  aOrder_detail : list) {
		System.out.print(aOrder_detail.getP_order_id() + ",");
		System.out.print(aOrder_detail.getP_id() + ",");
		System.out.print(aOrder_detail.getOd_qty() + ",");
		System.out.print(aOrder_detail.getOd_pr());
		System.out.println("---------------------");
		}
	}

}