package com.spot_report.model;

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
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Spot_reportJNDIDAO implements Spot_reportDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
		"INSERT INTO SPOT_REPORT (SPOT_REPORT_ID,S_CONTENT,MEM_NO,SPOT_ID) VALUES ('SR'||LPAD(TO_CHAR(SEQ_SPOT_REPORT_ID.NEXTVAL),4,0), ?, ?, ?)";
	private static final String GET_ALL_STML =
		"SELECT SPOT_REPORT_ID,S_CONTENT,S_STATUS,MEM_NO,SPOT_ID FROM SPOT_REPORT ORDER BY SPOT_REPORT_ID";
	private static final String GET_ONE_STMT = 
		"SELECT SPOT_REPORT_ID,S_CONTENT,S_STATUS,MEM_NO,SPOT_ID FROM SPOT_REPORT WHERE SPOT_REPORT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SPOT_REPORT WHERE SPOT_REPORT_ID = ?";
	private static final String UPDATE = 
		"UPDATE SPOT_REPORT SET S_CONTENT = ?,SPOT_ID = ? WHERE SPOT_REPORT_ID = ?";

	@Override
	public void insert(Spot_reportVO spot_reportVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO SPOT_REPORT (SPOT_REPORT_ID,S_CONTENT,S_STATUS,MEM_NO,SPOT_ID) VALUES ('SR'||LPAD(TO_CHAR(SEQ_SPOT_REPORT_ID.NEXTVAL),4,0), ?, ?, ?, ?)";
			pstmt.setString(1, spot_reportVO.getS_content());
			pstmt.setString(2, spot_reportVO.getMem_no());
			pstmt.setString(3, spot_reportVO.getSpot_id());
			
			pstmt.executeUpdate();
			
			
		}catch (SQLException sqle) {
			throw new RuntimeException("SQL語法錯了你還不找?混蛋");
		}finally {
			if (pstmt != null) {
				try{
					pstmt.close();				
				}catch(SQLException sqle){
					sqle.printStackTrace(System.err);
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
	public void update(Spot_reportVO spot_reportVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
//			"UPDATE SPOT_REPORT SET S_CONTENT = ?,SPOT_ID = ? WHERE SPOT_REPORT_ID = ?";
			pstmt.setString(1, spot_reportVO.getS_content());
			pstmt.setString(2, spot_reportVO.getSpot_id());
			pstmt.setString(3, spot_reportVO.getSpot_report_id());
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void delete(String spot_report_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, spot_report_id);

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
	public Spot_reportVO findByPrimaryKey(String spot_report_id) {
		Spot_reportVO spot_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
//			"SELECT SPOT_REPORT_ID,S_CONTENT,S_STATUS,MEM_NO,SPOT_ID FROM SPOT_REPORT WHERE SPOT_REPORT_ID = ?";
			pstmt.setString(1, spot_report_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spot_reportVO = new Spot_reportVO();
				spot_reportVO.setSpot_report_id(rs.getString("SPOT_REPORT_ID"));
				spot_reportVO.setS_content(rs.getString("S_CONTENT"));
				spot_reportVO.setS_status(rs.getInt("S_STATUS"));
				spot_reportVO.setMem_no(rs.getString("MEM_NO"));
				spot_reportVO.setSpot_id(rs.getString("SPOT_ID"));
				
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
		return spot_reportVO;
	}


	@Override
	public List<Spot_reportVO> getAll() {
		List<Spot_reportVO> list = new ArrayList<Spot_reportVO>();
		Spot_reportVO spot_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STML);
			
//			"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spot_reportVO = new Spot_reportVO();
				spot_reportVO.setSpot_report_id(rs.getString("SPOT_REPORT_ID"));
				spot_reportVO.setS_content(rs.getString("S_CONTENT"));
				spot_reportVO.setS_status(rs.getInt("S_STATUS"));
				spot_reportVO.setMem_no(rs.getString("MEM_NO"));
				spot_reportVO.setSpot_id(rs.getString("SPOT_ID"));
				list.add(spot_reportVO);
				
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


	public static void main(String[] args) throws IOException {
			Spot_reportJDBCDAO dao = new Spot_reportJDBCDAO();
			
//			 新增
//			Spot_reportVO spot_reportVO1 = new Spot_reportVO();
//			
//			spot_reportVO1.setS_content("看不到又吃不到，檢舉撿到爆");
//			spot_reportVO1.setMem_no("M0003");
//			spot_reportVO1.setSpot_id("S0005");
//
//			dao.insert(spot_reportVO1);
			
			
			// 修改
//			Spot_reportVO spot_reportVO2 = new Spot_reportVO();
//			spot_reportVO2.setS_content("看的到吃不到，我還不檢舉");
//			spot_reportVO2.setSpot_id("S0005");
//			spot_reportVO2.setSpot_report_id("SR0006");
//			dao.update(spot_reportVO2);
			
			// 刪除
//			dao.delete("SR0006");
			
			// 查詢
//			"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
//			Spot_reportVO spot_reportVO3 = dao.findByPrimaryKey("SR0006");
//			System.out.print(spot_reportVO3.getSpot_report_id() + ",");
//			System.out.print(spot_reportVO3.getS_content() + ",");
//			System.out.print(spot_reportVO3.getS_status() + ",");
//			System.out.print(spot_reportVO3.getMem_no() + ",");
//			System.out.print(spot_reportVO3.getSpot_id() + ",");
			
			
//			// 查詢
//			List<Spot_reportVO> list = dao.getAll();
//			for (Spot_reportVO srvo : list) {
//				System.out.print(srvo.getSpot_report_id() + ",");
//				System.out.print(srvo.getS_content() + ",");
//				System.out.print(srvo.getS_status() + ",");
//				System.out.print(srvo.getMem_no() + ",");
//				System.out.print(srvo.getSpot_id() + ",");
//				
//			}
			
			
	}
	


}
