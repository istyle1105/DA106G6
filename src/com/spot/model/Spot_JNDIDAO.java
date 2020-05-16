package com.spot.model;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Spot_JNDIDAO implements SpotDAO_interface{

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
		"INSERT INTO SPOT (SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,SPOT_PIC,ADDRESS,LNG,LAT) VALUES ('S'||LPAD(TO_CHAR(SEQ_SPOT_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STML =
		"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT ORDER BY SPOT_ID";
	private static final String GET_ALL_STML_STATUS_0 =
		"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT WHERE spot_status = 0 ORDER BY SPOT_ID";
	private static final String GET_ONE_STMT = 
		"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT WHERE SPOT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SPOT WHERE SPOT_ID = ?";
	private static final String UPDATE = 
		"UPDATE SPOT SET SPOT_TYPE = ?,SPOT_NAME = ?,SPOT_INTRO = ?, SPOT_PIC = ?, ADDRESS = ?,LNG = ?,LAT = ?,SPOT_STATUS = ? WHERE SPOT_ID = ?";
	private static final String UPDATE_NO_PIC = 
			"UPDATE SPOT SET SPOT_TYPE = ?,SPOT_NAME = ?,SPOT_INTRO = ?, ADDRESS = ?,LNG = ?,LAT = ?,SPOT_STATUS = ? WHERE SPOT_ID = ?";

	@Override
	public void insert(SpotVO spotVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO SPOT (SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,SPOT_PIC,ADDRESS,LNG,LAT) VALUES ('S'||LPAD(TO_CHAR(SEQ_SPOT_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, spotVO.getSpot_type());
			pstmt.setString(2, spotVO.getSpot_name());
			pstmt.setString(3, spotVO.getSpot_intro());
			pstmt.setBytes(4, spotVO.getSpot_pic());
			pstmt.setString(5, spotVO.getAddress());
			pstmt.setString(6, spotVO.getLng());
			pstmt.setString(7, spotVO.getLat());
			
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
	public SpotVO insert2(SpotVO spotVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			String cols[] = {"spot_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
//			"INSERT INTO SPOT (SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,SPOT_PIC,ADDRESS,LNG,LAT) VALUES ('S'||LPAD(TO_CHAR(SEQ_SPOT_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, spotVO.getSpot_type());
			pstmt.setString(2, spotVO.getSpot_name());
			pstmt.setString(3, spotVO.getSpot_intro());
			pstmt.setBytes(4, spotVO.getSpot_pic());
			pstmt.setString(5, spotVO.getAddress());
			pstmt.setString(6, spotVO.getLng());
			pstmt.setString(7, spotVO.getLat());
			
			pstmt.executeUpdate();
			String next_spot_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_spot_id = rs.getString(1);
			} 
			rs.close();
			spotVO.setSpot_id(next_spot_id);
			
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
		return spotVO;
	}
	
	
	@Override
	public void update(SpotVO spotVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			if(spotVO.getSpot_pic().length==0) {
				pstmt = con.prepareStatement(UPDATE_NO_PIC);
//				"UPDATE SPOT SET SPOT_TYPE = ?,SPOT_NAME = ?,SPOT_INTRO = ?,  ADDRESS = ?,LNG = ?,LAT = ?,SPOT_STATUS = ? WHERE SPOT_ID = ?";
				pstmt.setInt(1, spotVO.getSpot_type());
				pstmt.setString(2, spotVO.getSpot_name());
				pstmt.setString(3, spotVO.getSpot_intro());
				pstmt.setString(4, spotVO.getAddress());
				pstmt.setString(5, spotVO.getLng());
				pstmt.setString(6, spotVO.getLat());
				pstmt.setInt(7, spotVO.getSpot_status());
				pstmt.setString(8, spotVO.getSpot_id());
				
			}else {
				pstmt = con.prepareStatement(UPDATE);
//				"UPDATE SPOT SET SPOT_TYPE = ?,SPOT_NAME = ?,SPOT_INTRO = ?, SPOT_PIC = ?, ADDRESS = ?,LNG = ?,LAT = ?,SPOT_STATUS = ? WHERE SPOT_ID = ?";
				pstmt.setInt(1, spotVO.getSpot_type());
				pstmt.setString(2, spotVO.getSpot_name());
				pstmt.setString(3, spotVO.getSpot_intro());
				pstmt.setBytes(4, spotVO.getSpot_pic());
				pstmt.setString(5, spotVO.getAddress());
				pstmt.setString(6, spotVO.getLng());
				pstmt.setString(7, spotVO.getLat());
				pstmt.setInt(8, spotVO.getSpot_status());
				pstmt.setString(9, spotVO.getSpot_id());
			}
			
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
	public void delete(String spot_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, spot_id);

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
	public SpotVO findByPrimaryKey(String spot_id) {
		SpotVO spotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
//			"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT WHERE SPOT_ID = ?";
			pstmt.setString(1, spot_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpot_id(rs.getString("SPOT_ID"));
				spotVO.setSpot_type(rs.getInt("SPOT_TYPE"));
				spotVO.setSpot_name(rs.getString("SPOT_NAME"));
				spotVO.setSpot_intro(rs.getString("SPOT_INTRO"));
				spotVO.setAddress(rs.getString("ADDRESS"));
				spotVO.setLng(rs.getString("LNG"));
				spotVO.setLat(rs.getString("LAT"));
				spotVO.setSpot_status(rs.getInt("SPOT_STATUS"));
				
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
		return spotVO;
	}


	@Override
	public List<SpotVO> getAll() {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STML);
			
//			"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT ORDER BY SPOT_ID";
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpot_id(rs.getString("SPOT_ID"));
				spotVO.setSpot_type(rs.getInt("SPOT_TYPE"));
				spotVO.setSpot_name(rs.getString("SPOT_NAME"));
				spotVO.setSpot_intro(rs.getString("SPOT_INTRO"));
				spotVO.setAddress(rs.getString("ADDRESS"));
				spotVO.setLng(rs.getString("LNG"));
				spotVO.setLat(rs.getString("LAT"));
				spotVO.setSpot_status(rs.getInt("SPOT_STATUS"));
				list.add(spotVO);
				
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
	
	public List<SpotVO> getAllActive() {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STML_STATUS_0);
			
//			"SELECT SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,ADDRESS,LNG,LAT,SPOT_STATUS FROM SPOT ORDER BY SPOT_ID";
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpot_id(rs.getString("SPOT_ID"));
				spotVO.setSpot_type(rs.getInt("SPOT_TYPE"));
				spotVO.setSpot_name(rs.getString("SPOT_NAME"));
				spotVO.setSpot_intro(rs.getString("SPOT_INTRO"));
				spotVO.setAddress(rs.getString("ADDRESS"));
				spotVO.setLng(rs.getString("LNG"));
				spotVO.setLat(rs.getString("LAT"));
				spotVO.setSpot_status(rs.getInt("SPOT_STATUS"));
				list.add(spotVO);
				
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
			Spot_JDBCDAO dao = new Spot_JDBCDAO();
			
//			 新增
//			"INSERT INTO SPOT (SPOT_ID,SPOT_TYPE,SPOT_NAME,SPOT_INTRO,SPOT_PIC,ADDRESS,LNG,LAT) VALUES ('S'||LPAD(TO_CHAR(SEQ_SPOT_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?, ?)";
//			SpotVO spotVO1 = new SpotVO();
//			
//			spotVO1.setSpot_type(0);
//			spotVO1.setSpot_name("福岡山王公園");
//			spotVO1.setSpot_intro("公園中有漫步小徑、櫻之丘、草坪廣場等，可盡情地觀賞。花季時期會擺設路邊攤，夜晚則有燈光照射夜櫻！");
//			byte[] pic = getPictureByteArray("PicFrom/Sakura2.jpg");			
//			spotVO1.setSpot_pic(pic);
//			spotVO1.setAddress("〒812-0015 福岡県福岡市博多区山王１丁目９");
//			spotVO1.setLng("33.582925");
//			spotVO1.setLat("130.432323");
//
//			dao.insert(spotVO1);
			
			
			// 修改
			SpotVO spotVO2 = new SpotVO();
//			"UPDATE SPOT SET SPOT_TYPE = ?,SPOT_NAME = ?,SPOT_INTRO = ?, SPOT_PIC = ?, ADDRESS = ?,LNG = ?,LAT = ?,SPOT_STATUS = ? WHERE SPOT_ID = ?";
			spotVO2.setSpot_type(0);
			spotVO2.setSpot_name("福岡山王公園");
			spotVO2.setSpot_intro("福岡\"山王公園\"中有漫步小徑、櫻之丘、草坪廣場等，可盡情地觀賞。花季時期會擺設路邊攤，夜晚則有燈光照射夜櫻！");
			byte[] pic2 = getPictureByteArray("PicFrom/山王公園.jpg");			
			spotVO2.setSpot_pic(pic2);
			spotVO2.setAddress("〒812-0015 福岡県福岡市博多区山王１丁目９");
			spotVO2.setLng("33.582925");
			spotVO2.setLat("130.432323");
			spotVO2.setSpot_status(1);
			spotVO2.setSpot_id("S0007");
			dao.update(spotVO2);
			
			// 刪除
//			dao.delete("S0006");
			
			// 查詢
//			SpotVO spotVO3 = dao.findByPrimaryKey("S0005");
//			System.out.print(spotVO3.getSpot_id() + ",");
//			System.out.print(spotVO3.getSpot_type() + ",");
//			System.out.print(spotVO3.getSpot_name() + ",");
//			System.out.print(spotVO3.getSpot_intro() + ",");
//			System.out.print(spotVO3.getAddress() + ",");
//			System.out.print(spotVO3.getLng() + ",");
//			System.out.print(spotVO3.getLat() + ",");
//			System.out.print(spotVO3.getSpot_status() + ",");
			
			
//			// 查詢
//			List<SpotVO> list = dao.getAll();
//			for (SpotVO spot : list) {
//				System.out.print(spot.getSpot_id() + ",");
//				System.out.print(spot.getSpot_type() + ",");
//				System.out.print(spot.getSpot_name() + ",");
//				System.out.print(spot.getSpot_intro() + ",");
//				System.out.print(spot.getAddress() + ",");
//				System.out.print(spot.getLng() + ",");
//				System.out.print(spot.getLat() + ",");
//				System.out.print(spot.getSpot_status() + ",");
//			}
			
			
	}

	public static byte[] getPictureByteArray(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while((i = fis.read(buffer)) != -1) {
			baos.write(buffer,0,i);
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}
}
