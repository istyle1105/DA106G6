package com.tour.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class Tour_JDBCDAO implements TourDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO TOUR (TOUR_ID,MEM_NO,TOUR_NAME,TOUR_DETAIL,TOUR_PIC) VALUES ('R'||LPAD(TO_CHAR(SEQ_TOUR_ID.NEXTVAL),4,0), ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT TOUR_ID,MEM_NO,TOUR_NAME,TOUR_DETAIL,display FROM TOUR ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT TOUR_ID,MEM_NO,TOUR_NAME,TOUR_DETAIL,display FROM TOUR WHERE TOUR_ID = ?";
	private static final String DELETE = 
		"DELETE FROM TOUR WHERE TOUR_ID = ?";
	private static final String UPDATE = 
		"UPDATE TOUR SET TOUR_NAME=?, TOUR_DETAIL=?, TOUR_PIC=? ,display=? WHERE Tour_id=?";
	private static final String GET_ALL_MY_TOUR_STMT = 
		"SELECT TOUR_ID,MEM_NO,TOUR_NAME,TOUR_DETAIL,display FROM TOUR where MEM_NO = ? and display = 0 ORDER BY TOUR_ID";
	
	@Override
	public void insert(TourVO tourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tourVO.getMem_no());
			pstmt.setString(2, tourVO.getTour_name());
			pstmt.setString(3, tourVO.getTour_detail());
			pstmt.setBytes(4, tourVO.getTour_pic());
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
//		}catch(IOException ie){ 
//			throw new RuntimeException("A database error occured. "
//					+ ie.getMessage());			
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
	public TourVO insertGetVO(TourVO tourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = {"tour_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);

			pstmt.setString(1, tourVO.getMem_no());
			pstmt.setString(2, tourVO.getTour_name());
			pstmt.setString(3, tourVO.getTour_detail());
			pstmt.setBytes(4, tourVO.getTour_pic());
			pstmt.executeUpdate();
			String next_tour_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_tour_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_tour_id +"(剛新增成功的行程編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			tourVO.setTour_id(next_tour_id);
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		}catch(IOException ie){ 
//			throw new RuntimeException("A database error occured. "
//					+ ie.getMessage());			
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
		return tourVO;
	}

	@Override
	public void update(TourVO tourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
//			"UPDATE TOUR SET TOUR_NAME=?, TOUR_DETAIL=?, TOUR_PIC=? WHERE Tour_id = ?";
			pstmt.setString(1, tourVO.getTour_name());
			pstmt.setString(2, tourVO.getTour_detail());
			pstmt.setBytes(3, tourVO.getTour_pic());
			pstmt.setInt(4, tourVO.getDisplay());
			pstmt.setString(5, tourVO.getTour_id());
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
//		} catch(IOException ioe) {
//			System.out.println("....");
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
	public void delete(String tour_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tour_id);

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
	public TourVO findByPrimaryKey(String tour_id) {

		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tour_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				tourVO = new TourVO();
				tourVO.setTour_id(rs.getString("tour_id"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_detail(rs.getString("tour_detail"));
				tourVO.setDisplay(rs.getInt("display"));
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
		return tourVO;
	}

	@Override
	public List<TourVO> getAll() {
		List<TourVO> list = new ArrayList<TourVO>();
		TourVO tourVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tourVO = new TourVO();
				tourVO.setTour_id(rs.getString("tour_id"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_detail(rs.getString("tour_detail"));
				tourVO.setDisplay(rs.getInt("display"));
				list.add(tourVO); // Store the row in the list
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
	public List<TourVO> getAllMyTour(String mem_no) {
		List<TourVO> list = new ArrayList<TourVO>();
		TourVO tourVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MY_TOUR_STMT);
			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tourVO = new TourVO();
				tourVO.setTour_id(rs.getString("tour_id"));
				tourVO.setMem_no(rs.getString("mem_no"));
				tourVO.setTour_name(rs.getString("tour_name"));
				tourVO.setTour_detail(rs.getString("tour_detail"));
				tourVO.setDisplay(rs.getInt("display"));
				list.add(tourVO); // Store the row in the list
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

	public static void main(String[] args) throws IOException {

		Tour_JDBCDAO dao = new Tour_JDBCDAO();

//		 新增
		TourVO tourVO1 = new TourVO();
		tourVO1.setMem_no("M0005");
		tourVO1.setTour_name("東京神社5日遊");
		tourVO1.setTour_detail("東京著名神社參拜");
		TourVO tourVO = dao.insertGetVO(tourVO1);
		System.out.println(tourVO.getTour_id());

//		"UPDATE TOUR SET TOUR_NAME=?, TOUR_DETAIL=?, TOUR_PIC=? WHERE Tour_id = ?";
		
		// 修改
//		TourVO tourVO2 = new TourVO();
//		tourVO2.setTour_name("北海道雪山融水");
//		tourVO2.setTour_detail("有山有水有帝王蟹，可以粗~~~~~~~");		
////		byte[] pic = getPictureByteArray("PicFrom/團照.jpg");		
////		tourVO2.setTour_pic(pic);
//		tourVO2.setTour_id("R0005");
//		tourVO2.setDisplay(new Integer(1));
//		dao.update(tourVO2);

		
//		// 刪除
//		dao.delete("R0006");
//
//		// 查詢
//		TourVO tourVO3 = dao.findByPrimaryKey("R0005");
//		System.out.print(tourVO3.getTour_id() + ",");
//		System.out.print(tourVO3.getMem_no() + ",");
//		System.out.print(tourVO3.getTour_name() + ",");
//		System.out.print(tourVO3.getTour_detail() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<TourVO> list = dao.getAll();
//		for (TourVO aEmp : list) {
//			System.out.print(aEmp.getTour_id() + ",");
//			System.out.print(aEmp.getMem_no() + ",");
//			System.out.print(aEmp.getTour_name() + ",");
//			System.out.print(aEmp.getTour_detail() + ",");
//			System.out.print(aEmp.getDisplay() + ",");
//			System.out.println();
//		}
		
//		List<TourVO> list2 = dao.getAllMyTour("M0005");
//		for (TourVO aEmp : list2) {
//			System.out.print(aEmp.getTour_id() + ",");
//			System.out.print(aEmp.getMem_no() + ",");
//			System.out.print(aEmp.getTour_name() + ",");
//			System.out.print(aEmp.getTour_detail() + ",");
//			System.out.print(aEmp.getDisplay() + ",");
//			System.out.println();
//		}
	}
	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
}