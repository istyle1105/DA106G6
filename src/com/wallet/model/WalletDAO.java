package com.wallet.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WalletDAO implements WalletDAO_interface {


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
		"INSERT INTO WALLET (W_REC_ID,MEM_NO,W_AMOUNT,W_TXN_NOTE,W_TXN_STATUS) VALUES ('W'||LPAD(to_char(SEQ_WALLET.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT W_REC_ID,MEM_NO,W_AMOUNT,to_char(W_TXN_TIME,'yyyy-mm-dd HH24:MI:SS') W_TXN_TIME,W_TXN_NOTE,W_TXN_STATUS FROM WALLET order by W_REC_ID";
	private static final String GETByMem_no = 
			"SELECT * FROM WALLET WHERE MEM_NO = ? order by W_REC_ID";

	@Override
	public void insert(WalletVO walletVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, walletVO.getMem_no());
			pstmt.setInt(2, walletVO.getW_amount());
			pstmt.setString(3, walletVO.getW_txn_note());
			pstmt.setInt(4, walletVO.getW_txn_status());

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
	public List<WalletVO> getAll() {
		List<WalletVO> list = new ArrayList<WalletVO>();
		WalletVO walletVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Order_masterVO 也稱為 Domain objects
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETByMem_no);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				walletVO = new WalletVO();
				walletVO.setW_rec_id(rs.getString("w_rec_id"));
				walletVO.setMem_no(rs.getString("mem_no"));
				walletVO.setW_amount(rs.getInt("w_amount"));
				walletVO.setW_txn_time(rs.getTimestamp("w_txn_time"));
				walletVO.setW_txn_note(rs.getString("w_txn_note"));
				walletVO.setW_txn_status(rs.getInt("w_txn_status"));
				list.add(walletVO);
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
}
