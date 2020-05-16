package hw3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestReadBlob {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA106";
	private static final String PASSWORD = "123456";
	private static final String SQL = "SELECT PIC FROM CLUB WHERE ID = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. getBlob
			pstmt.setInt(1, 1);
			ResultSet rs1 = pstmt.executeQuery();
			rs1.next();
			Blob blob = rs1.getBlob(1);
			readPicture(blob);

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 2. getBytes
			pstmt.setInt(1, 2);
			ResultSet rs2 = pstmt.executeQuery();
			rs2.next();
			byte[] pic = rs2.getBytes(1);
			readPicture(pic);

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 3. setBinaryStream
			pstmt.setInt(1, 3);
			ResultSet rs3 = pstmt.executeQuery();
			rs3.next();
			InputStream is = rs3.getBinaryStream(1);
			readPicture(is);

			System.out.println("查詢成功");

			rs1.close();
			rs2.close();
			rs3.close();

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

	// Handle with blob data
	public static void readPicture(Blob blob) throws IOException, SQLException {
		InputStream is = blob.getBinaryStream();
		FileOutputStream fos = new FileOutputStream("D://1.png");
		int i;
		while ((i = is.read()) != -1) {
			fos.write(i);
		}
		fos.flush();
		fos.close();
	}

	// Handle with byte array data
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("D://2.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	// Handle with stream data
	public static void readPicture(InputStream is) throws IOException {
		FileOutputStream fos = new FileOutputStream("D://3.png");
		int i;
		while ((i = is.read()) != -1) {
			fos.write(i);
		}
		fos.flush();
		fos.close();
	}
}
