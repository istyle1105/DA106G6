package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestWriteClob {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "david";
	private static final String PASSWORD = "123456";
	private static final String SQL = "UPDATE CLUB SET INTRO = ? WHERE ID = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. setClob
			Clob clob = con.createClob();
			String str = getLongString("items/BM.txt");
			clob.setString(1, str); // position
			pstmt.setClob(1, clob);
			pstmt.setInt(2, 1);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 2. setCharacterStream
			Reader reader = getLongStringStream("items/Bar.txt");
			pstmt.setCharacterStream(1, reader);
			pstmt.setInt(2, 2);
			pstmt.executeUpdate();
			
			// setAsciiStream使用方式同setCharacterStream
			// 差別就在於Unicode的支援
			// 若是文字為Unicode請使用setCharacterStream
			
			System.out.println("新增成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// 使用String
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

	// 使用資料流
	public static Reader getLongStringStream(String path) throws IOException {
		return new FileReader(path);

	}

}
