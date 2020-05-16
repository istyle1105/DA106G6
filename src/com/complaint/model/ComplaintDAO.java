package com.complaint.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class ComplaintDAO implements ComplaintDAO_interface {
	
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
		"INSERT INTO COMPLAINT( COMP_NO, MEM_NO, COMP_CONTENT) VALUES('MC'||LPAD(to_char(SEQ_COMP_NO.NEXTVAL), 4, '0'),?,?)";
	
	
	private static final String GET_ALL_STMT = 
		"SELECT * FROM COMPLAINT ORDER BY COMP_NO";
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM COMPLAINT WHERE COMP_NO=?";

	private static final String GET_ONE_MEM = 
		"SELECT * FROM COMPLAINT WHERE MEM_NO=?";	

	private static final String GET_ONE_STATUS = 
			"SELECT * FROM COMPLAINT WHERE COMP_STATUS=?";	
	
	private static final String DELETE = 
		"DELETE FROM COMPLAINT WHERE COMP_NO=?";
	
	private static final String UPDATESTATUS = 
		"UPDATE COMPLAINT SET COMP_STATUS=? WHERE COMP_NO = ?";


	

	@Override
	public void insert(ComplaintVO complaintVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, complaintVO.getMem_no());
			pstmt.setString(2, complaintVO.getComp_content());

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
	public void updateStatus(ComplaintVO complaintVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);
			
	
			pstmt.setInt(1, complaintVO.getComp_status());
			pstmt.setString(2,complaintVO.getComp_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
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
	public void delete(String comp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, comp_no);

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
	public ComplaintVO findByPrimaryKey(String comp_no) {

		ComplaintVO complaintVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,comp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				complaintVO=new ComplaintVO();
				complaintVO.setComp_no(rs.getString("comp_no"));
				complaintVO.setMem_no(rs.getString("mem_no"));
				complaintVO.setComp_content(rs.getString("comp_content"));
				complaintVO.setComp_date(rs.getTimestamp("comp_date"));
				complaintVO.setComp_status(rs.getInt("comp_status"));
			
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
		return complaintVO;
	}

	@Override
	public List<ComplaintVO> getAll() {
		List<ComplaintVO> list = new ArrayList<ComplaintVO>();
		ComplaintVO complaintVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				complaintVO=new ComplaintVO();
				complaintVO.setComp_no(rs.getString("comp_no"));
				complaintVO.setMem_no(rs.getString("mem_no"));
				complaintVO.setComp_content(rs.getString("comp_content"));
				complaintVO.setComp_date(rs.getTimestamp("comp_date"));
				complaintVO.setComp_status(rs.getInt("comp_status"));
				list.add(complaintVO); 				
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
	public List<ComplaintVO> getMemAll(String mem_no) {
		List<ComplaintVO> list = new ArrayList<ComplaintVO>();
		ComplaintVO complaintVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//					"SELECT * WHERE MEM_NO=?";	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM);
			pstmt.setString(1,mem_no);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				complaintVO=new ComplaintVO();
				complaintVO.setComp_no(rs.getString("comp_no"));
				complaintVO.setMem_no(rs.getString("mem_no"));
				complaintVO.setComp_content(rs.getString("comp_content"));
				complaintVO.setComp_date(rs.getTimestamp("comp_date"));
				complaintVO.setComp_status(rs.getInt("comp_status"));
				list.add(complaintVO); 				
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
	public List<ComplaintVO> getStatusAll(Integer comp_status) {
		List<ComplaintVO> list = new ArrayList<ComplaintVO>();
		ComplaintVO complaintVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//					"SELECT * WHERE MEM_NO=?";	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STATUS);
			pstmt.setInt(1,comp_status);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				complaintVO=new ComplaintVO();
				complaintVO.setComp_no(rs.getString("comp_no"));
				complaintVO.setMem_no(rs.getString("mem_no"));
				complaintVO.setComp_content(rs.getString("comp_content"));
				complaintVO.setComp_date(rs.getTimestamp("comp_date"));
				complaintVO.setComp_status(rs.getInt("comp_status"));
				list.add(complaintVO); 				
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