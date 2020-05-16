package com.demand.model;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PhotoWrite {
	

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
	private static final String USER = "C##DA106G6";
	private static final String PASSWORD = "123456";

    public static void main(String argv[]) {
          Connection con = null;
          PreparedStatement pstmt = null;
          String picName = "面膜.png";
        
          try {
        	Class.forName(DRIVER);
  			con = DriverManager.getConnection(URL, USER, PASSWORD);
            File pic = new File("picFrom", picName);   

            long flen = pic.length();
//            String fileName = pic.getName();
//            int dotPos = fileName.indexOf('.');
//            String fno = fileName.substring(0, dotPos);
//            String format = fileName.substring(dotPos + 1);
            InputStream fin = new FileInputStream(pic);  

            System.out.println("\n\nUpdate the database... ");
            pstmt = con.prepareStatement(
             "UPDATE DEMAND SET DE_PHOTO = ? WHERE DE_NO = ?");
            pstmt.setBinaryStream(1, fin, (int)flen);
            pstmt.setString(2, "D0005");
            int rowsUpdated = pstmt.executeUpdate();
		
            System.out.print("Changed " + rowsUpdated);

            if (1 == rowsUpdated)
                System.out.println(" row.");
            else
                System.out.println(" rows.");

            fin.close();
            pstmt.close();

          } catch (Exception e) {
                e.printStackTrace();
          } finally {
                try {
                  con.close();
                } catch (SQLException e) {
                }
         }
  }

}
