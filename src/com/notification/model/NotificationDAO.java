package com.notification.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class NotificationDAO implements NotificationDAO_interface {
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
		"INSERT INTO NOTIFICATION( NOTE_NO, MEM_NO, NOTE_CONTENT) VALUES('MN'||LPAD(to_char(SEQ_NOTE_NO.NEXTVAL), 4, '0'),?,?)";
		
	private static final String GET_ALL_STMT = 
		"SELECT * FROM notification ORDER BY note_no DESC";

	private static final String GET_ONE_MEM = 
		"SELECT * FROM notification WHERE mem_no=? ORDER BY note_no DESC";	
	
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM notification WHERE note_no=?";
	
	private static final String DELETE = 
		"DELETE FROM notification WHERE note_no=?";
	
	private static final String UPDATESTATUS = 
		"UPDATE notification SET NOTE_STATUS=? WHERE note_no = ?";


	

	@Override
	public void insert(NotificationVO notificationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			
			pstmt.setString(1, notificationVO.getMem_no());
			pstmt.setString(2, notificationVO.getNote_content());

			pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (Exception e) {
            e.printStackTrace();}
		finally {
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
	public void updateStatus(NotificationVO notificationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);
			
	
			pstmt.setInt(1, notificationVO.getNote_status());
			pstmt.setString(2,notificationVO.getNote_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
	public void delete(String note_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, note_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public NotificationVO findByPrimaryKey(String note_no) {

		NotificationVO notificationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,note_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				notificationVO=new NotificationVO();
				notificationVO.setNote_no(rs.getString("note_no"));
				notificationVO.setMem_no(rs.getString("mem_no"));
				notificationVO.setNote_content(rs.getString("note_content"));
				notificationVO.setNote_date(rs.getTimestamp("note_date"));
				notificationVO.setNote_status(rs.getInt("note_status"));
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return notificationVO;
	}
	
	
	@Override
	public List<NotificationVO> getOneAll(String mem_no) {
		
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//SELECT * FROM notification WHERE mem_no=? ORDER BY note_no

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM);
			pstmt.setString(1,mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				notificationVO=new NotificationVO();
				notificationVO.setNote_no(rs.getString("note_no"));
				notificationVO.setMem_no(rs.getString("mem_no"));
				notificationVO.setNote_content(rs.getString("note_content"));
				notificationVO.setNote_date(rs.getTimestamp("note_date"));
				notificationVO.setNote_status(rs.getInt("note_status"));

				list.add(notificationVO); 				
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	public List<NotificationVO> getAll() {
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				notificationVO=new NotificationVO();
				notificationVO.setNote_no(rs.getString("note_no"));
				notificationVO.setMem_no(rs.getString("mem_no"));
				notificationVO.setNote_content(rs.getString("note_content"));
				notificationVO.setNote_date(rs.getTimestamp("note_date"));
				notificationVO.setNote_status(rs.getInt("note_status"));

				list.add(notificationVO); 				
			}

			// Handle any driver errors
		}catch (SQLException se) {
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