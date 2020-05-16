package com.member.controller;

import java.io.*;
import java.sql.*;

public class WritePicIntoDB_BLOB {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//一次上傳所有圖檔到資料庫，不過要預先編輯好檔名，讓"主檔名"等同於where要指定的地點
	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA106G6";
		String passwd = "123456";


		String folderPath = "D:\\ID_PIC";	//資料夾路徑
		
		try{
			File folder = new File(folderPath);
			String[] list = folder.list();	//資料夾內所有檔名
			
			for(int i = 0; i < list.length; i++){
				try {
					String picName = list[i];
					System.out.println("檔案全名：" + list[i]);
					con = DriverManager.getConnection(url, userid, passwd);
					File pic = new File(folderPath, picName); //絕對路徑
					
					long flen = pic.length();  //檔案大小
					System.out.println("檔案大小："+ flen + " 位元組");
					
					String fileName = pic.getName();	//檔案全名
					int dotPos = fileName.indexOf('.');	//用.區分檔名及副檔名
					
					String fno = fileName.substring(0, dotPos);
					System.out.println("主檔名　：" + fno);
					
					String format = fileName.substring(dotPos + 1);
					System.out.println("副檔名　：" + format);
					
					InputStream fin = new FileInputStream(pic);  
					System.out.println("址　　　：" + fin);
					
					pstmt = con.prepareStatement("UPDATE MEMBER set ID_CARD = ? where MEM_NO = ?"); 
					pstmt.setBinaryStream(1, fin, (int)flen);
					pstmt.setString(2, fno);
					
					pstmt.executeUpdate();
					
					
					fin.close();
					pstmt.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						con.close();
						System.out.println("Try-OVER");
					} catch (SQLException e) { 
						e.printStackTrace();
					}
				}
			}
			System.out.println("For迴圈-OVER");
		} catch (Exception e){
			System.out.println("'"+folderPath+"'此資料夾不存在");
		}
	}
}
