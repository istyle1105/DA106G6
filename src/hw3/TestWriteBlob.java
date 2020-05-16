package hw3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestWriteBlob {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA106";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO CLUB(ID, NAME, PIC)" + "VALUES(?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. setBlob
			pstmt.setInt(1, 1);
			pstmt.setString(2, "拜仁慕尼黑");
			Blob blob = con.createBlob();
			byte[] pic2 = getPictureByteArray("D://cover.jpg");
			blob.setBytes(1, pic2);
			pstmt.setBlob(3, blob);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 2. setBytes
			pstmt.setInt(1, 2);
			pstmt.setString(2, "巴塞隆納");
			byte[] pic = getPictureByteArray("D://cover.jpg");
			pstmt.setBytes(3, pic);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();

			// 3. setBinaryStream
			pstmt.setInt(1, 3);
			pstmt.setString(2, "皇家馬德里");
			InputStream is = getPictureStream("D://cover.jpg");
			pstmt.setBinaryStream(3, is, is.available());
			pstmt.executeUpdate();

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

	// 使用InputStream資料流方式
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
