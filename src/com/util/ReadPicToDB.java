package com.util;

import java.io.*;
import java.sql.*;


public class ReadPicToDB {
	private static final String TOUR_UPDATE_PIC = 
			"UPDATE TOUR SET TOUR_PIC=? WHERE Tour_id=?";
	private static final String TOUR_DETAIL_UPDATE_PIC = 
			"UPDATE TOUR_DETAIL SET ACT_PIC=? WHERE TOUR_DETAIL_ID=?";
	private static final String SPOT_UPDATE_PIC = 
			"UPDATE SPOT SET SPOT_PIC=? WHERE SPOT_ID=?";


	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA106G6";
		String pwd = "123456";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, pwd);
			for (int i = 1; i <= 6; i++) {
				StringBuilder path = new StringBuilder();
				StringBuilder pk = new StringBuilder();
				if(i<10) {
					pk.append("R000").append(i);
				}else {
					pk.append("R00").append(i);
				}
				path.append("C:\\Tour\\R").append(i).append(".jpg");
				readPicToDB(path.toString(), pk.toString(), con,TOUR_UPDATE_PIC);
			}
			for (int i = 1; i <= 28; i++) {
				StringBuilder path = new StringBuilder();
				StringBuilder pk = new StringBuilder();
				if(i<10) {
					pk.append("RD000").append(i);
				}else {
					pk.append("RD00").append(i);
				}
				path.append("C:\\TourDetail\\RD").append(i).append(".jpg");
				readPicToDB(path.toString(), pk.toString(), con,TOUR_DETAIL_UPDATE_PIC);
			}
			for (int i = 1; i <= 31; i++) {
				StringBuilder path = new StringBuilder();
				StringBuilder pk = new StringBuilder();
				if(i<10) {
					pk.append("S000").append(i);
				}else {
					pk.append("S00").append(i);
				}
				path.append("C:\\Spot\\S").append(i).append(".jpg");
				readPicToDB(path.toString(), pk.toString(), con,SPOT_UPDATE_PIC);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	public static void readPicToDB(String path,String pk,Connection con,String sql) {
		
		try {
			File pic = new File(path);
			byte[] by = new byte[(int)pic.length()];
			System.out.println(pic.length());
			InputStream in = new FileInputStream(pic); 
			in.read(by);
			
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.setBytes(1,by);
			ps.setString(2,pk);
			int num = ps.executeUpdate();
			System.out.println(num);
			in.close();
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
