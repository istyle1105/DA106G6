package com.product.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCT (P_ID,P_NAME,P_CAT,P_PR,P_SPEC,P_DETAIL,P_PIC,P_STATUS) VALUES ('P'||LPAD(to_char(SEQ_P_ID.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT P_ID,P_NAME,P_CAT,P_PR,P_SPEC,P_DETAIL,to_char(P_RENEW,'yyyy-mm-dd HH24:MI:SS') P_RENEW,P_PIC,P_STATUS FROM PRODUCT order by P_ID";
	private static final String GET_ONE_STMT = 
		"SELECT P_ID,P_NAME,P_CAT,P_PR,P_SPEC,P_DETAIL,to_char(P_RENEW,'yyyy-mm-dd HH24:MI:SS') P_RENEW,P_PIC,P_STATUS FROM PRODUCT where P_ID = ?";
	private static final String UPDATE = 
		"UPDATE PRODUCT set P_NAME=?,P_CAT=?,P_PR=?,P_SPEC=?,P_DETAIL=?,P_PIC=?,P_STATUS=? where P_ID= ?";
	private static final String GET_ALL_STMT_ByCat = 
		"SELECT * FROM PRODUCT where P_CAT = ? ORDER BY P_ID";
	
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getP_name());
			pstmt.setString(2, productVO.getP_cat());
			pstmt.setInt(3, productVO.getP_pr());
			pstmt.setString(4, productVO.getP_spec());
			pstmt.setString(5, productVO.getP_detail());
			pstmt.setBytes(6, productVO.getP_pic());
			pstmt.setInt(7, productVO.getP_status());

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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getP_name());
			pstmt.setString(2, productVO.getP_cat());
			pstmt.setInt(3, productVO.getP_pr());
			pstmt.setString(4, productVO.getP_spec());
			pstmt.setString(5, productVO.getP_detail());
			
			if(productVO.getP_pic().length == 0) {
				ProductDAO dao = new ProductDAO();
				pstmt.setBytes(6, dao.findByPrimaryKey(productVO.getP_id()).getP_pic());
			}else {
				pstmt.setBytes(6, productVO.getP_pic());
			}
			
			pstmt.setInt(7, productVO.getP_status());
			pstmt.setString(8, productVO.getP_id());

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
	public ProductVO findByPrimaryKey(String p_id) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				// productVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setP_id(rs.getString("p_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_cat(rs.getString("p_cat"));
				productVO.setP_pr(rs.getInt("p_pr"));
				productVO.setP_spec(rs.getString("p_spec"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_pic(rs.getBytes("P_pic"));
				productVO.setP_renew(rs.getTimestamp("p_renew"));
				productVO.setP_status(rs.getInt("p_status"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// productVO 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setP_id(rs.getString("p_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_cat(rs.getString("p_cat"));
				productVO.setP_pr(rs.getInt("p_pr"));
				productVO.setP_spec(rs.getString("p_spec"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_pic(rs.getBytes("P_pic"));
				productVO.setP_renew(rs.getTimestamp("p_renew"));
				productVO.setP_status(rs.getInt("p_status"));
				list.add(productVO); // Store the row in the list
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
	public List<ProductVO> getAllByCat(String p_cat) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_ByCat);
			pstmt.setString(1, p_cat);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setP_id(rs.getString("p_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_cat(rs.getString("p_cat"));
				productVO.setP_pr(rs.getInt("p_pr"));
				productVO.setP_spec(rs.getString("p_spec"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_pic(rs.getBytes("p_pic"));
				productVO.setP_renew(rs.getTimestamp("p_renew"));
				productVO.setP_status(rs.getInt("p_status"));
				list.add(productVO);
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
	public List<ProductVO> get_ALLByP_status() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("P_pic");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {

		ProductJDBCDAO dao = new ProductJDBCDAO();
		
        
		// 新增
//		ProductVO productVO1 = new ProductVO();
//		byte[] pic = getPictureByteArray("D:\\cover.jpg");
//		productVO1.setP_name("水壺");
//		productVO1.setP_cat("戶外用品");
//		productVO1.setP_pr(100);
//		productVO1.setP_spec("T12354A42藍");
//		productVO1.setP_detail("戶外適用");
//		productVO1.setP_pic(pic);
//		productVO1.setP_status(0);
//		dao.insert(productVO1);

		// 修改
//		ProductVO productVO2 = new ProductVO();
//		byte[] pic = getPictureByteArray("D:\\cover.jpg");
//		productVO2.setP_name("水壺");
//		productVO2.setP_cat("戶外用品");
//		productVO2.setP_pr(100);
//		productVO2.setP_spec("T12354A42藍");
//		productVO2.setP_detail("戶外");
//		productVO2.setP_pic(pic);
//		productVO2.setP_status(0);
//		productVO2.setP_id("P0012");
//		dao.update(productVO2);

		// 刪除
//		dao.delete("P0013");

		// 查詢
//		ProductVO productVO3 = dao.findByPrimaryKey("P0012");
//
//		System.out.print(productVO3.getP_id() + ",");
//		System.out.print(productVO3.getP_name() + ",");
//		System.out.print(productVO3.getP_cat() + ",");
//		System.out.print(productVO3.getP_pr() + ",");
//		System.out.print(productVO3.getP_spec() + ",");
//		System.out.print(productVO3.getP_detail() + ",");
//		System.out.print(productVO3.getP_pic() + ",");
//		System.out.print(productVO3.getP_renew() + ",");
//		System.out.println(productVO3.getP_status());
//		System.out.println("---------------------");

		// 查詢
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO  aProduct : list) {
//			System.out.print(aProduct.getP_id() + ",");
//			System.out.print(aProduct.getP_name() + ",");
//			System.out.print(aProduct.getP_cat() + ",");
//			System.out.print(aProduct.getP_pr() + ",");
//			System.out.print(aProduct.getP_spec() + ",");
//			System.out.print(aProduct.getP_detail() + ",");
//			System.out.print(aProduct.getP_pic() + ",");
//			System.out.print(aProduct.getP_renew() + ",");
//			System.out.println(aProduct.getP_status());
//			System.out.println();
//		}
		
		// 查詢類別
		List<ProductVO> list = dao.getAllByCat("backpack");
		for (ProductVO  aProduct : list) {
			System.out.print(aProduct.getP_id() + ",");
			System.out.print(aProduct.getP_name() + ",");
			System.out.print(aProduct.getP_cat() + ",");
			System.out.print(aProduct.getP_pr() + ",");
			System.out.print(aProduct.getP_spec() + ",");
			System.out.print(aProduct.getP_detail() + ",");
//			System.out.print(aProduct.getP_pic() + ",");
			System.out.print(aProduct.getP_renew() + ",");
			System.out.println(aProduct.getP_status());
			System.out.println();
		}
	}


}