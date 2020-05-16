package com.authority.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AuthorityDAO implements AuthorityDAO_interface{
	
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
		"INSERT INTO AUTHORITY (EMP_NO, FUN_NO) VALUES (?,?)";
		
	private static final String GET_ALL_STMT = 
		"SELECT emp_no, fun_no, fun_status FROM authority order by emp_no";
	
	private static final String GET_ONE_STMT = 
		"SELECT emp_no, fun_no, fun_status FROM authority WHERE emp_no=?";
	
	private static final String DELETE = 
		"DELETE FROM AUTHORITY  WHERE emp_no=?";
	
	private static final String UPDATESTATUS = 
		"UPDATE AUTHORITY SET fun_status=? WHERE emp_no=? and fun_no=?";

	@Override
	public void insert2(AuthorityVO authorityVO,Connection con) {

		PreparedStatement pstmt = null;

		try {
			System.out.println(authorityVO);

			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO AUTHORITY (EMP_NO, FUN_NO) VALUES (?,?)";
			
			pstmt.setString(1, authorityVO.getEmp_no());
			pstmt.setString(2, authorityVO.getFun_no());

System.out.println("成功新增 : " + pstmt.executeUpdate());
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
	public void insert(AuthorityVO authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, authorityVO.getEmp_no());
			pstmt.setString(2, authorityVO.getFun_no());

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
	public void updateStatus(AuthorityVO authorityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);
						
	
			pstmt.setInt(1, authorityVO.getFun_status());
			pstmt.setString(2,authorityVO.getEmp_no());
			pstmt.setString(3,authorityVO.getFun_no());

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
	public void delete(String emp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
//			DELETE FROM AUTHORITY  WHERE emp_no=? and fun_no=?
			pstmt.setString(1, emp_no);
//			pstmt.setString(2, fun_no);

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
	public List<AuthorityVO> findByPrimaryKey(String emp_no) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
//"SELECT emp_no, fun_no, fun_status FROM authority WHERE emp_no=?";
			pstmt.setString(1,emp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				authorityVO=new AuthorityVO();
				authorityVO.setEmp_no(rs.getString("emp_no"));
				authorityVO.setFun_no(rs.getString("fun_no"));
				authorityVO.setFun_status(rs.getInt("fun_status"));
				list.add(authorityVO);
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
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO=new AuthorityVO();
				authorityVO.setEmp_no(rs.getString("emp_no"));
				authorityVO.setFun_no(rs.getString("fun_no"));
				authorityVO.setFun_status(rs.getInt("fun_status"));
				list.add(authorityVO); 				
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
	

	public static void main(String[] args) throws IOException {

		AuthorityDAO dao = new AuthorityDAO();
		
		// 新增
//		AuthorityVO authorityVO=new AuthorityVO();
//		authorityVO.setEmp_no("E0005");
//		authorityVO.setFun_no("EF0002");
//		dao.insert(authorityVO);

////		//// 修改
//		AuthorityVO authorityVO2=new AuthorityVO();
//		authorityVO2.setEmp_no("E0005");
//		authorityVO2.setFun_no("EF0002");
//		authorityVO2.setFun_status(1);
//		dao.updateStatus(authorityVO2);
		
//		dao.delete("E0005","EF0002");
		

//		
//		AuthorityVO authorityVO3=dao.findByPrimaryKey("E0005","EF0001");
//		System.out.print(authorityVO3.getEmp_no()+ ",");
//		System.out.print(authorityVO3.getFun_no()+ ",");		
//		System.out.println(authorityVO3.getFun_status());
//		System.out.println("---------------------");
	

//		List<AuthorityVO> list = dao.getAll();
//		for (AuthorityVO authorityVO4 : list) {
//			System.out.print(authorityVO4.getEmp_no()+ ",");
//			System.out.print(authorityVO4.getFun_no()+ ",");		
//			System.out.println(authorityVO4.getFun_status());
//
//		}
	}
}
