package com.employee.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.authority.model.*;

public class EmployeeDAO implements EmployeeDAO_interface{
	
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
		"INSERT INTO EMPLOYEE (EMP_NO, EMP_ID, EMP_PSW, EMP_NAME, EMP_CELLPHONE, EMP_EMAIL,EMP_PHOTO)\r\n" + 
		"VALUES ('E'||LPAD(to_char(SEQ_EMP_NO.NEXTVAL), 4, '0'), ?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT emp_no, emp_status, emp_id, emp_psw, emp_name, emp_cellphone, emp_email ,EMP_PHOTO FROM employee ORDER BY emp_no DESC";
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM employee WHERE emp_no=?";

	private static final String GET_ONE_STMT_BY_ID = 
			"SELECT emp_no, emp_status, emp_id, emp_psw, emp_name, emp_cellphone, emp_email ,EMP_PHOTO FROM employee WHERE emp_id=?";

	
	private static final String DELETE = 
		"DELETE FROM employee WHERE emp_no=?";
	
	private static final String UPDATE = 
		"UPDATE employee SET EMP_STATUS=?,EMP_PSW=?, EMP_NAME=?, EMP_CELLPHONE=?, EMP_EMAIL=? ,EMP_PHOTO=? WHERE EMP_NO=?";
	private static final String UPDATEEMP =
		"UPDATE employee SET EMP_PSW=?, EMP_NAME=?, EMP_CELLPHONE=?,EMP_EMAIL=?, EMP_PHOTO=? WHERE EMP_NO=?	";

	public void insertAutoKey(EmployeeVO employeeVO, List<AuthorityVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[]= {"EMP_NO"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
					
			pstmt.setString(1, employeeVO.getEmp_id());
			pstmt.setString(2, employeeVO.getEmp_psw());
			pstmt.setString(3,employeeVO.getEmp_name());
			pstmt.setString(4,employeeVO.getEmp_cellphone());
			pstmt.setString(5, employeeVO.getEmp_email());
			pstmt.setString(6, employeeVO.getEmp_photo());
			pstmt.executeUpdate();

			String next_empno=null;
			ResultSet rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_empno=rs.getString(1);
				System.out.println("自增主鍵值= " + next_empno +"(剛新增成功的會員編號)");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			AuthorityDAO dao=new AuthorityDAO();
			System.out.println("list.size="+list.size());
			for(AuthorityVO auth: list) {
				auth.setEmp_no(next_empno);
				System.out.println(auth.getEmp_no());
				System.out.println(auth.getFun_no());
				dao.insert2(auth, con);
			}
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增員工編號" + next_empno + "時,該員工權限" + list.size()
					+ "同時被新增");
			
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-auth");
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
					System.out.println("連線關閉");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	} 
	

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
					
			pstmt.setString(1, employeeVO.getEmp_id());
			pstmt.setString(2, employeeVO.getEmp_psw());
			pstmt.setString(3,employeeVO.getEmp_name());
			pstmt.setString(4,employeeVO.getEmp_cellphone());
			pstmt.setString(5, employeeVO.getEmp_email());
			pstmt.setString(6, employeeVO.getEmp_photo());
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
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//			"UPDATE employee SET EMP_STATUS=?,EMP_PSW=?, EMP_NAME=?, EMP_CELLPHONE=?,
//			EMP_EMAIL=? WHERE EMP_NO=?			
			pstmt.setInt(1, employeeVO.getEmp_status());				
			pstmt.setString(2, employeeVO.getEmp_psw());
			pstmt.setString(3, employeeVO.getEmp_name());
			pstmt.setString(4, employeeVO.getEmp_cellphone());
			pstmt.setString(5, employeeVO.getEmp_email());
			if(employeeVO.getEmp_photo().equals("")) {
				EmployeeDAO dao=new EmployeeDAO();
				pstmt.setString(6,dao.findByPrimaryKey(employeeVO.getEmp_no()).getEmp_photo());
			}else 
				pstmt.setString(6, employeeVO.getEmp_photo());
			pstmt.setString(7, employeeVO.getEmp_no());
			

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
	
	
//	public void updateStatus(EmployeeVO employeeVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATESTUTS);
//					
//			pstmt.setInt(1, employeeVO.getEmp_status());
//			pstmt.setString(2, employeeVO.getEmp_no());
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} 
//		finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void delete(String emp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_no);

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
	
	public EmployeeVO findByIdKey(String emp_id) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ID);
			

			pstmt.setString(1,emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				employeeVO =new EmployeeVO();				
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_cellphone(rs.getString("emp_cellphone"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_photo(rs.getString("emp_photo"));

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
		return employeeVO;		
	}

	@Override
	public EmployeeVO findByPrimaryKey(String emp_no) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			

			pstmt.setString(1,emp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				employeeVO =new EmployeeVO();				
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_cellphone(rs.getString("emp_cellphone"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_photo(rs.getString("emp_photo"));

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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO =new EmployeeVO();				
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_status(rs.getInt("emp_status"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_cellphone(rs.getString("emp_cellphone"));
				employeeVO.setEmp_email(rs.getString("emp_email"));
				employeeVO.setEmp_photo(rs.getString("emp_photo"));
				list.add(employeeVO); // Store the row in the list
							
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
	public void updateEmp(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEEMP);
//"UPDATE employee SET EMP_PSW=?, EMP_NAME=?, EMP_CELLPHONE=?,EMP_EMAIL=?, EMP_PHOTO=? WHERE EMP_NO=?	";	
				
			pstmt.setString(1, employeeVO.getEmp_psw());
			pstmt.setString(2, employeeVO.getEmp_name());
			pstmt.setString(3, employeeVO.getEmp_cellphone());
			pstmt.setString(4, employeeVO.getEmp_email());	
			
			if(employeeVO.getEmp_photo().equals("")) {
				EmployeeDAO dao=new EmployeeDAO();
				pstmt.setString(5,dao.findByPrimaryKey(employeeVO.getEmp_no()).getEmp_photo());
			}else 
				pstmt.setString(5, employeeVO.getEmp_photo());

			pstmt.setString(6, employeeVO.getEmp_no());
			

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


}
