package com.order_master.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailVO;
import com.wallet.model.WalletDAO;
import com.wallet.model.WalletVO;

public class Order_masterDAO implements Order_masterDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO ORDER_MASTER (P_ORDER_ID,MEM_NO,OM_STATUS,OM_DLVR,OM_TPR) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(SEQ_P_ORDER_ID.NEXTVAL), 4, '0'), ?, 0, null, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT P_ORDER_ID,MEM_NO,to_char(OM_TIME,'yyyy-mm-dd HH24:MI:SS') OM_TIME,OM_STATUS,to_char(OM_DLVR,'yyyy-mm-dd HH24:MI:SS') OM_DLVR,OM_TPR FROM ORDER_MASTER ORDER BY P_ORDER_ID";
	private static final String GET_ONE_STMT = 
		"SELECT P_ORDER_ID,MEM_NO,to_char(OM_TIME,'yyyy-mm-dd HH24:MI:SS') OM_TIME,OM_STATUS,to_char(OM_DLVR,'yyyy-mm-dd HH24:MI:SS') OM_DLVR,OM_TPR FROM ORDER_MASTER WHERE P_ORDER_ID = ?";
	private static final String GET_ALL_STMT_BY_MEM_NO = 
			"SELECT P_ORDER_ID,MEM_NO,to_char(OM_TIME,'yyyy-mm-dd HH24:MI:SS') OM_TIME,OM_STATUS,to_char(OM_DLVR,'yyyy-mm-dd HH24:MI:SS') OM_DLVR,OM_TPR FROM ORDER_MASTER WHERE MEM_NO = ? ORDER BY P_ORDER_ID";
	
	private static final String UPDATE = 
		"UPDATE ORDER_MASTER SET OM_STATUS=?, OM_DLVR=? where P_ORDER_ID = ?";
	private static final String GETP_order_idByMem_no = 
		"SELECT P_ORDER_ID FROM ORDER_MASTER WHERE MEM_NO = ? ORDER BY P_ORDER_ID";
	@Override
	public void insert(Order_masterVO order_masterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, order_masterVO.getMem_no());
			pstmt.setInt(2, order_masterVO.getOm_tpr());


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
	public void update(Order_masterVO order_masterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, order_masterVO.getOm_status());
			pstmt.setTimestamp(2, order_masterVO.getOm_dlvr());
			pstmt.setString(3, order_masterVO.getP_order_id());

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
	public Order_masterVO findByPrimaryKey(String p_order_id) {

		Order_masterVO order_masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_masterVO = new Order_masterVO();
				order_masterVO.setP_order_id(rs.getString("p_order_id"));
				order_masterVO.setMem_no(rs.getString("mem_no"));
				order_masterVO.setOm_time(rs.getTimestamp("om_time"));
				order_masterVO.setOm_status(rs.getInt("om_status"));
				order_masterVO.setOm_dlvr(rs.getTimestamp("om_dlvr"));
				order_masterVO.setOm_tpr(rs.getInt("om_tpr"));
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
		return order_masterVO;
	}
	
	@Override
	public List<Order_masterVO> findByMemno(String mem_no) {
		List<Order_masterVO> list = new ArrayList<Order_masterVO>();
		Order_masterVO order_masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEM_NO);
			pstmt.setString(1, mem_no);
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
	public List<Order_masterVO> getAll() {
		List<Order_masterVO> list = new ArrayList<Order_masterVO>();
		Order_masterVO order_masterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public List<Order_masterVO> getP_order_idByMem_no(String mem_no) {
		List<Order_masterVO> list = new ArrayList<Order_masterVO>();
		Order_masterVO order_masterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
				next_p_order_id = rs.getString(1); //第1欄位
				System.out.println("自增主鍵值= " + next_p_order_id);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			Order_detailDAO dao = new Order_detailDAO();
			System.out.println("list.size()(執行前)="+list.size());
			for (Order_detailVO newOrder_detail : list) {
				newOrder_detail.setP_order_id(next_p_order_id);
				dao.insertWhenOrder_masterInsert(newOrder_detail,con);
			}
			// 再同時增加扣款
			WalletDAO daoW = new WalletDAO();
			WalletVO aWallet = new WalletVO();
			aWallet.setMem_no(order_masterVO.getMem_no());
			aWallet.setW_amount(order_masterVO.getOm_tpr());
			aWallet.setW_txn_note(next_p_order_id);
			aWallet.setW_txn_status(1);
			daoW.insert(aWallet);
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增訂單主檔編號" + next_p_order_id + " ,共有訂單明細" + list.size()
					+ "同時被新增");

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

}
