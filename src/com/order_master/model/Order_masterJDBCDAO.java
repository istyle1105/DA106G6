package com.order_master.model;

import java.util.*;
import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;

import java.sql.*;

public class Order_masterJDBCDAO implements Order_masterDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_MASTER (P_ORDER_ID,MEM_NO,OM_STATUS,OM_DLVR,OM_TPR) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(SEQ_P_ORDER_ID.NEXTVAL), 4, '0'), ?, 0, null, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT P_ORDER_ID,MEM_NO,to_char(OM_TIME,'yyyy-mm-dd HH24:MI:SS') OM_TIME,OM_STATUS,to_char(OM_DLVR,'yyyy-mm-dd HH24:MI:SS') OM_DLVR,OM_TPR FROM ORDER_MASTER ORDER BY P_ORDER_ID";
	private static final String GET_ONE_STMT = 
		"SELECT P_ORDER_ID,MEM_NO,to_char(OM_TIME,'yyyy-mm-dd HH24:MI:SS') OM_TIME,OM_STATUS,to_char(OM_DLVR,'yyyy-mm-dd HH24:MI:SS') OM_DLVR,OM_TPR FROM ORDER_MASTER WHERE P_ORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER SET OM_STATUS=? where P_ORDER_ID = ?";
	private static final String GETP_order_idByMem_no = 
		"SELECT P_ORDER_ID FROM ORDER_MASTER WHERE MEM_NO = ? ORDER BY P_ORDER_ID";
	
	@Override
	public void insert(Order_masterVO order_masterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, order_masterVO.getMem_no());
			pstmt.setInt(2, order_masterVO.getOm_status());
			pstmt.setTimestamp(3, order_masterVO.getOm_dlvr());
			pstmt.setInt(4, order_masterVO.getOm_tpr());

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
	public void update(Order_masterVO order_masterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, order_masterVO.getOm_status());
			pstmt.setString(2, order_masterVO.getP_order_id());

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
	public Order_masterVO findByPrimaryKey(String p_order_id) {

		Order_masterVO order_masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_masterVO 也稱為 Domain objects
				order_masterVO = new Order_masterVO();
				order_masterVO.setP_order_id(rs.getString("p_order_id"));
				order_masterVO.setMem_no(rs.getString("mem_no"));
				order_masterVO.setOm_time(rs.getTimestamp("om_time"));
				order_masterVO.setOm_status(rs.getInt("om_status"));
				order_masterVO.setOm_dlvr(rs.getTimestamp("om_dlvr"));
				order_masterVO.setOm_tpr(rs.getInt("om_tpr"));
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
		return order_masterVO;
	}

	@Override
	public List<Order_masterVO> getAll() {
		List<Order_masterVO> list = new ArrayList<Order_masterVO>();
		Order_masterVO order_masterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Order_masterVO 也稱為 Domain objects
				order_masterVO = new Order_masterVO();
				order_masterVO.setP_order_id(rs.getString("p_order_id"));
				order_masterVO.setMem_no(rs.getString("mem_no"));
				order_masterVO.setOm_time(rs.getTimestamp("om_time"));
				order_masterVO.setOm_status(rs.getInt("om_status"));
				order_masterVO.setOm_dlvr(rs.getTimestamp("om_dlvr"));
				order_masterVO.setOm_tpr(rs.getInt("om_tpr"));
				list.add(order_masterVO); // Store the row in the list
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
	public void insertWithOrder_detailsAndwallet(Order_masterVO order_masterVO , List<Order_detailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單主檔
			String cols[] = {"p_order_id"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, order_masterVO.getMem_no());
			pstmt.setInt(2, order_masterVO.getOm_tpr());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_p_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_p_order_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_p_order_id);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			Order_detailJDBCDAO dao = new Order_detailJDBCDAO();
			System.out.println("list.size()(執行前)="+list.size());
			for (Order_detailVO newOrder_detail : list) {
				newOrder_detail.setP_order_id(next_p_order_id);
				dao.insertWhenOrder_masterInsert(newOrder_detail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增訂單主檔編號" + next_p_order_id + " ,共有訂單明細" + list.size()
					+ "同時被新增");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("訂單主檔錯誤，rolled back");
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
	public List<Order_masterVO> getP_order_idByMem_no(String mem_no) {
		List<Order_masterVO> list = new ArrayList<Order_masterVO>();
		Order_masterVO order_masterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETP_order_idByMem_no);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Order_masterVO 也稱為 Domain objects
				order_masterVO = new Order_masterVO();
				order_masterVO.setP_order_id(rs.getString("p_order_id"));
				list.add(order_masterVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		// 新增訂單主檔，同時新增訂單明細
		Order_masterJDBCDAO dao = new Order_masterJDBCDAO();

//		Order_masterVO order_masterVO = new Order_masterVO();
//		order_masterVO.setMem_no("M0001");
//		order_masterVO.setOm_tpr(5399);
//		
//		List<Order_detailVO> testList = new ArrayList<Order_detailVO>(); // 準備置入訂單明細
//		Order_detailVO order_detail01 = new Order_detailVO();   // 訂單明細O1
//		order_detail01.setP_id("P0002");
//		order_detail01.setOd_qty(1);
//		order_detail01.setOd_pr(5100);
//
//		Order_detailVO order_detail02 = new Order_detailVO();   // 訂單明細O2
//		order_detail02.setP_id("P0002");
//		order_detail02.setOd_qty(1);
//		order_detail02.setOd_pr(5100);
//		
//		testList.add(order_detail01);
//		testList.add(order_detail02);
//		
//		dao.insertWithOrder_details(order_masterVO , testList);
		
		
		// 新增
//		Order_masterVO order_masterVO1 = new Order_masterVO();
//		order_masterVO1.setMem_no("M0002");
//		order_masterVO1.setOm_status(0);
//		order_masterVO1.setOm_dlvr(null);
//		order_masterVO1.setOm_tpr(2000);
//		dao.insert(order_masterVO1);

		// 修改
//		Order_masterVO order_masterVO2 = new Order_masterVO();
//		order_masterVO2.setOm_status(3);
//		order_masterVO2.setP_order_id("20200324-0001");
//		dao.update(order_masterVO2);

		// 刪除
//		dao.delete("20200324-0006");

		// 查詢
//		Order_masterVO order_masterVO3 = dao.findByPrimaryKey("20200324-0001");
//		System.out.print(order_masterVO3.getP_order_id() + ",");
//		System.out.print(order_masterVO3.getMem_no() + ",");
//		System.out.print(order_masterVO3.getOm_time() + ",");
//		System.out.print(order_masterVO3.getOm_status() + ",");
//		System.out.print(order_masterVO3.getOm_dlvr() + ",");
//		System.out.print(order_masterVO3.getOm_tpr() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<Order_masterVO> list = dao.getAll();
//		for (Order_masterVO aOrder_master : list) {
//			System.out.print(aOrder_master.getP_order_id() + ",");
//			System.out.print(aOrder_master.getMem_no() + ",");
//			System.out.print(aOrder_master.getOm_time() + ",");
//			System.out.print(aOrder_master.getOm_status() + ",");
//			System.out.print(aOrder_master.getOm_dlvr() + ",");
//			System.out.print(aOrder_master.getOm_tpr() + ",");
//			System.out.println();
//		}
		
		// 查詢
		List<Order_masterVO> list = dao.getP_order_idByMem_no("M0001");
		for (Order_masterVO aOrder_master : list) {
			System.out.print(aOrder_master.getP_order_id() + ",");
			System.out.println();
		}
	}

	@Override
	public List<Order_masterVO> findByMemno(String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}


}