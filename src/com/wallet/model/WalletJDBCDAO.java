package com.wallet.model;

import java.util.*;
import java.sql.*;

public class WalletJDBCDAO implements WalletDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO WALLET (W_REC_ID,MEM_NO,W_AMOUNT,W_TXN_NOTE,W_TXN_STATUS) VALUES ('W'||LPAD(to_char(SEQ_WALLET.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT W_REC_ID,MEM_NO,W_AMOUNT,to_char(W_TXN_TIME,'yyyy-mm-dd HH24:MI:SS') W_TXN_TIME,W_TXN_NOTE,W_TXN_STATUS FROM WALLET order by W_REC_ID";
	private static final String GETByMem_no = 
			"SELECT * FROM WALLET WHERE MEM_NO = ?";

	@Override
	public void insert(WalletVO walletVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, walletVO.getMem_no());
			pstmt.setInt(2, walletVO.getW_amount());
			pstmt.setString(3, walletVO.getW_txn_note());
			pstmt.setInt(4, walletVO.getW_txn_status());

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
	public List<WalletVO> getAll() {
		List<WalletVO> list = new ArrayList<WalletVO>();
		WalletVO walletVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// walletVO 也稱為 Domain objects
				walletVO = new WalletVO();
				walletVO.setW_rec_id(rs.getString("w_rec_id"));
				walletVO.setMem_no(rs.getString("mem_no"));
				walletVO.setW_amount(rs.getInt("w_amount"));
				walletVO.setW_txn_time(rs.getTimestamp("w_txn_time"));
				walletVO.setW_txn_note(rs.getString("w_txn_note"));
				walletVO.setW_txn_status(rs.getInt("w_txn_status"));
				list.add(walletVO); // Store the row in the list
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
	public List<WalletVO> getByMemno(String mem_no) {
		List<WalletVO> list = new ArrayList<WalletVO>();
		WalletVO walletVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETByMem_no);
			pstmt.setString(1,mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				walletVO = new WalletVO();
				walletVO.setW_amount(rs.getInt("w_amount"));
				walletVO.setW_txn_status(rs.getInt("w_txn_status"));
				list.add(walletVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

		WalletJDBCDAO dao = new WalletJDBCDAO();

		// 新增
//		WalletVO walletVO1 = new WalletVO();
//		walletVO1.setMem_no("M0002");
//		walletVO1.setW_amount(100);
//		walletVO1.setW_txn_note("錢很多");
//		walletVO1.setW_txn_status(1);
//		dao.insert(walletVO1);
		
		// 查詢
//		List<WalletVO> list = dao.getAll();
//		for (WalletVO  aWallet : list) {
//			System.out.print(aWallet.getW_rec_id() + ",");
//			System.out.print(aWallet.getMem_no() + ",");
//			System.out.print(aWallet.getW_amount() + ",");
//			System.out.print(aWallet.getW_txn_time() + ",");
//			System.out.print(aWallet.getW_txn_note() + ",");
//			System.out.print(aWallet.getW_txn_status());
//			System.out.println();
//		}
		
		// 查詢
		List<WalletVO> list = dao.getByMemno("M0001");
		for (WalletVO  aWallet : list) {
			System.out.print(aWallet.getW_amount() + ",");
			System.out.print(aWallet.getW_txn_status() + ",");
			System.out.println();
		}
	}
}