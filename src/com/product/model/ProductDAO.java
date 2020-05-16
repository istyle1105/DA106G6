package com.product.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface {


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
		"INSERT INTO PRODUCT (P_ID,P_NAME,P_CAT,P_PR,P_SPEC,P_DETAIL,P_PIC,P_STATUS) VALUES ('P'||LPAD(to_char(SEQ_P_ID.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT ORDER BY P_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PRODUCT where P_ID = ?";
	private static final String UPDATE = 
		"UPDATE PRODUCT set P_NAME=?,P_CAT=?,P_PR=?,P_SPEC=?,P_DETAIL=?,P_PIC=?,P_STATUS=? where P_ID= ?";
	private static final String GET_ALL_STMT_ByCat = 
		"SELECT * FROM (SELECT * FROM PRODUCT where P_CAT = ? ORDER BY P_ID) where P_STATUS <> 0";
	private static final String GET_ALLByP_status = 
		"SELECT * FROM (SELECT * FROM PRODUCT order by P_ID) where P_STATUS <> 0";
	
	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, productVO.getP_name());
			pstmt.setString(2, productVO.getP_cat());
			pstmt.setInt(3, productVO.getP_pr());
			pstmt.setString(4, productVO.getP_spec());
			pstmt.setString(5, productVO.getP_detail());
			pstmt.setBytes(6, productVO.getP_pic());
			pstmt.setInt(7, productVO.getP_status());

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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, p_id);

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLByP_status);
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_ByCat);
			pstmt.setString(1, p_cat);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setP_id(rs.getString("p_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_cat(rs.getString("p_cat"));
				System.out.println(rs.getString("p_cat") + "2");
				productVO.setP_pr(rs.getInt("p_pr"));
				productVO.setP_spec(rs.getString("p_spec"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_pic(rs.getBytes("p_pic"));
				productVO.setP_renew(rs.getTimestamp("p_renew"));
				productVO.setP_status(rs.getInt("p_status"));
				list.add(productVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
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

