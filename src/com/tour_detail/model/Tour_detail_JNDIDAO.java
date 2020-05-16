package com.tour_detail.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;


public class Tour_detail_JNDIDAO implements Tour_detailDAO_interface{
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
		"INSERT INTO TOUR_DETAIL (TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,ACT_PIC,START_TIME,TOUR_ID,SPOT_ID) VALUES ('RD'||LPAD(TO_CHAR(SEQ_TOUR_DETAIL_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STML =
		"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL ORDER BY TOUR_DETAIL_ID";
	private static final String GET_ONE_STMT = 
		"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
	private static final String DELETE = 
		"DELETE FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
	private static final String UPDATE = 
		"UPDATE TOUR_DETAIL SET STAY_TIME = ?,ACT_DESCRIP = ?,ACT_PIC = ?,START_TIME = ? WHERE TOUR_DETAIL_ID = ?";
	private static final String UPDATE_NO_PIC = 
			"UPDATE TOUR_DETAIL SET STAY_TIME = ?,ACT_DESCRIP = ?,START_TIME = ? WHERE TOUR_DETAIL_ID = ?";
	private static final String GET_ONE_TOUR_DETAIL =
		"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_ID = ? ORDER by start_time";
	private static final String DELETE_BY_TOUR_ID = 
			"DELETE FROM TOUR_DETAIL WHERE TOUR_ID = ?";

	@Override
	public void insert(Tour_detailVO tour_detailVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
//			"INSERT INTO TOUR_DETAIL (TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,ACT_PIC,START_TIME,TOUR_ID,SPOT_ID) VALUES ('RD'||LPAD(TO_CHAR(SEQ_TOUR_DETAIL_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?)";
			pstmt.setInt(1, tour_detailVO.getStay_time());
			pstmt.setString(2, tour_detailVO.getAct_descrip());
			pstmt.setBytes(3, tour_detailVO.getAct_pic());
			pstmt.setTimestamp(4, tour_detailVO.getStart_time());
			pstmt.setString(5, tour_detailVO.getTour_id());
			pstmt.setString(6, tour_detailVO.getSpot_id());
			
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
	public void update(Tour_detailVO tour_detailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			if (tour_detailVO.getAct_pic().length != 0) {
				pstmt = con.prepareStatement(UPDATE);
//			"UPDATE TOUR_DETAIL SET STAY_TIME = ?,ACT_DESCRIP = ?,ACT_PIC = ?,START_TIME = ? WHERE TOUR_DETAIL_ID = ?";
				pstmt.setInt(1, tour_detailVO.getStay_time());
				pstmt.setString(2, tour_detailVO.getAct_descrip());
				pstmt.setBytes(3, tour_detailVO.getAct_pic());
				pstmt.setTimestamp(4, tour_detailVO.getStart_time());
				pstmt.setString(5, tour_detailVO.getTour_detail_id());
				pstmt.executeUpdate();
			} else {
				pstmt = con.prepareStatement(UPDATE_NO_PIC);
//				"UPDATE TOUR_DETAIL SET STAY_TIME = ?,ACT_DESCRIP = ?,START_TIME = ? WHERE TOUR_DETAIL_ID = ?";
				pstmt.setInt(1, tour_detailVO.getStay_time());
				pstmt.setString(2, tour_detailVO.getAct_descrip());
				pstmt.setTimestamp(3, tour_detailVO.getStart_time());
				pstmt.setString(4, tour_detailVO.getTour_detail_id());
				pstmt.executeUpdate();
			}
			
			
			
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
	public void delete(String tour_detail_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tour_detail_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public int deleteByTour_id(String tour_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_TOUR_ID);

			pstmt.setString(1, tour_id);

			return pstmt.executeUpdate();

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
	public Tour_detailVO findByPrimaryKey(String tour_detail_id) {
		Tour_detailVO tour_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
//			"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID SPOT_ID FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
			pstmt.setString(1, tour_detail_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tour_detailVO = new Tour_detailVO();
				tour_detailVO.setTour_detail_id(rs.getString("TOUR_DETAIL_ID"));
				tour_detailVO.setStay_time(rs.getInt("STAY_TIME"));
				tour_detailVO.setAct_descrip(rs.getString("ACT_DESCRIP"));
				tour_detailVO.setStart_time(rs.getTimestamp("START_TIME"));
				tour_detailVO.setTour_id(rs.getString("TOUR_ID"));
				tour_detailVO.setSpot_id(rs.getString("SPOT_ID"));
				
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
		return tour_detailVO;
	}


	@Override
	public List<Tour_detailVO> getAll() {
		List<Tour_detailVO> list = new ArrayList<Tour_detailVO>();
		Tour_detailVO tour_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STML);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tour_detailVO = new Tour_detailVO();
				tour_detailVO.setTour_detail_id(rs.getString("TOUR_DETAIL_ID"));
				tour_detailVO.setStay_time(rs.getInt("STAY_TIME"));
				tour_detailVO.setAct_descrip(rs.getString("ACT_DESCRIP"));
				tour_detailVO.setStart_time(rs.getTimestamp("START_TIME"));
				tour_detailVO.setTour_id(rs.getString("TOUR_ID"));
				tour_detailVO.setSpot_id(rs.getString("SPOT_ID"));
				list.add(tour_detailVO);
				
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
	public List<Tour_detailVO> findByTour_id(String tour_id) {
		List<Tour_detailVO> list = new ArrayList<Tour_detailVO>();
		Tour_detailVO tour_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TOUR_DETAIL);
//			"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_ID = ? ORDER by start_time"
			pstmt.setString(1, tour_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tour_detailVO = new Tour_detailVO();
				tour_detailVO.setTour_detail_id(rs.getString("TOUR_DETAIL_ID"));
				tour_detailVO.setStay_time(rs.getInt("STAY_TIME"));
				tour_detailVO.setAct_descrip(rs.getString("ACT_DESCRIP"));
				tour_detailVO.setStart_time(rs.getTimestamp("START_TIME"));
				tour_detailVO.setTour_id(rs.getString("TOUR_ID"));
				tour_detailVO.setSpot_id(rs.getString("SPOT_ID"));
				list.add(tour_detailVO);
				
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
			Tour_detail_JNDIDAO dao = new Tour_detail_JNDIDAO();
			
//			 新增
//			"INSERT INTO TOUR_DETAIL (TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,ACT_PIC,START_TIME,TOUR_ID,SPOT_ID) VALUES ('RD'||LPAD(TO_CHAR(SEQ_TOUR_DETAIL_ID.NEXTVAL),4,0), ?, ?, ?, ?, ?, ?)";
//			Tour_detailVO tour_datailVO1 = new Tour_detailVO();
//			
//			Timestamp ts1 = new Timestamp(System.currentTimeMillis());
//			String tsStr1 = "2020-05-02 08:25:00";
//			ts1 = Timestamp.valueOf(tsStr1);
//			
//			tour_datailVO1.setStay_time(300);
//			tour_datailVO1.setAct_descrip("位在大沼公園旁邊的知名甜點店，有超過百年的歷史，販賣好吃的日式糰子，共有醬油、豆沙、芝麻三種口味，來北海道旅遊必買美食，不過務必於當天食用完畢。");
//			byte[] pic = getPictureByteArray("PicFrom/丸子.jpg");
//			tour_datailVO1.setAct_pic(pic);						
//			tour_datailVO1.setStart_time(ts1);
//			tour_datailVO1.setTour_id("R0005");
//			tour_datailVO1.setSpot_id("S0005");
//
//			dao.insert(tour_datailVO1);
			
			
			// 修改
//			Tour_detailVO tour_detailVO2 = new Tour_detailVO();
//			Timestamp ts2 = new Timestamp(System.currentTimeMillis());
//			String tsStr2 = "2020-05-02 15:25:00";
//			ts2 = Timestamp.valueOf(tsStr2);
			
//			"UPDATE TOUR_DETAIL SET STAY_TIME = ?,ACT_DESCRIP = ?,ACT_PIC = ?,START_TIME = ? WHERE TOUR_DETAIL_ID = ?";
//									STAY_TIME = ?,ACT_DESCRIP = ?,ACT_PIC = ?,START_TIME = ?
//			tour_detailVO2.setStay_time(150);
//			tour_detailVO2.setAct_descrip("位在大沼公園旁邊的知名甜點店\"沼の家\"，有超過百年的歷史，販賣好吃的日式糰子，共有醬油、豆沙、芝麻三種口味，來北海道旅遊必買美食，不過務必於當天食用完畢。");
//			byte[] pic2 = getPictureByteArray("PicFrom/丸子.jpg");
//			tour_detailVO2.setAct_pic(pic2);						
//			tour_detailVO2.setStart_time(ts2);
//			tour_detailVO2.setTour_detail_id("RD0014");
//			dao.update(tour_detailVO2);
			
			// 刪除
//			dao.delete("RD0006");
			
			// 查詢
//			"SELECT TOUR_DETAIL_ID,STAY_TIME,ACT_DESCRIP,START_TIME,TOUR_ID,SPOT_ID FROM TOUR_DETAIL WHERE TOUR_DETAIL_ID = ?";
//			Tour_detailVO tour_detailVO3 = dao.findByPrimaryKey("RD0005");
//			System.out.print(tour_detailVO3.getTour_detail_id() + ",");
//			System.out.print(tour_detailVO3.getStay_time() + ",");
//			System.out.print(tour_detailVO3.getAct_descrip() + ",");
//			System.out.print(tour_detailVO3.getStart_time() + ",");
//			System.out.print(tour_detailVO3.getTour_id() + ",");
//			System.out.print(tour_detailVO3.getSpot_id() + ",");
			
			
//			// 查詢
//			List<Tour_detailVO> list = dao.getAll();
//			for (Tour_detailVO td : list) {
//				System.out.print(td.getTour_detail_id() + ",");
//				System.out.print(td.getStay_time() + ",");
//				System.out.print(td.getAct_descrip() + ",");
//				System.out.print(td.getStart_time() + ",");
//				System.out.print(td.getTour_id() + ",");
//				System.out.print(td.getSpot_id() + ",");
//				
//			}
			
			//查詢依行程編號秀出其細節以時間排序
			List<Tour_detailVO> list = dao.findByTour_id("R0001");
			for (Tour_detailVO td : list) {
				System.out.print(td.getTour_detail_id() + ",");
				System.out.print(td.getStay_time() + ",");
				System.out.print(td.getAct_descrip() + ",");
				System.out.print(td.getStart_time() + ",");
				System.out.print(td.getTour_id() + ",");
				System.out.print(td.getSpot_id() + ",");
				System.out.println();
				System.out.println("----------------------");
			}
			
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
