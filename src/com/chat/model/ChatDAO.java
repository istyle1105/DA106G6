                                    package com.chat.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;
import java.text.*;

public class ChatDAO implements ChatDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO CHAT(CHAT_ID,MEM_NO1,MEM_NO2,CHAT_CONTENT) VALUES('MC'||LPAD(to_char(SEQ_CHAT_ID.NEXTVAL), 4, '0'),?,?,?)";
	
	
	private static final String GET_ALL_STMT = 
		"SELECT CHAT_ID,MEM_NO1,MEM_NO2,CHAT_CONTENT,CHAT_TIME FROM CHAT ORDER BY CHAT_ID";
	
	private static final String GET_ONE_STMT = 
		"SELECT CHAT_ID,MEM_NO1,MEM_NO2,CHAT_CONTENT,CHAT_TIME FROM CHAT WHERE CHAT_ID=?";
	
//	private static final String DELETE = 
//		"DELETE FROM CHAT WHERE CHAT_ID=?";
	
//	 to_char(CHAT_TIME,'yyyy-mm-dd hh24:mi:ss') 

	@Override
	public void insert(ChatVO chatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, chatVO.getMem_no1());
			pstmt.setString(2, chatVO.getMem_no2());
			pstmt.setString(3, chatVO.getChat_content());

			pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (Exception e) {
            e.printStackTrace();}
		finally {
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
	public ChatVO findByPrimaryKey(String chat_id) {

		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,chat_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				chatVO =new ChatVO();
				chatVO.setChat_id(rs.getString("CHAT_ID"));
				chatVO.setMem_no1(rs.getString("MEM_NO1"));
				chatVO.setMem_no2(rs.getString("MEM_NO2"));
				chatVO.setChat_content(rs.getString("CHAT_CONTENT"));
				chatVO.setChat_time(rs.getTimestamp("CHAT_TIME"));
		
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return chatVO;
	}

	@Override
	public List<ChatVO> getAll() {
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
//			SELECT CHAT_ID,MEM_NO1,MEM_NO2,CHAT_CONTENT,CHAT_TIME FROM CHAT ORDER BY CHAT_ID

			while (rs.next()) {
				chatVO =new ChatVO();	
				chatVO.setChat_id(rs.getString("CHAT_ID"));
				chatVO.setMem_no1(rs.getString("MEM_NO1"));
				chatVO.setMem_no2(rs.getString("MEM_NO2"));
				chatVO.setChat_content(rs.getString("CHAT_CONTENT"));
				chatVO.setChat_time(rs.getTimestamp("CHAT_TIME"));

				list.add(chatVO); // Store the row in the list				
			}

			// Handle any driver errors
		}catch (SQLException se) {
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

		ChatDAO dao = new ChatDAO();

		// 新增
//		ChatVO chatVO=new ChatVO();
//		chatVO.setMem_no1("M0005");
//		chatVO.setMem_no2("M0006");
//		chatVO.setChat_content("哈囉 你好1111111111111");
//		
//		dao.insert(chatVO);

		//// CHA沒有修改
//		ChatVO chatVO2=new ChatVO();
//		chatVO2.setMem_no1("M0005");
//		chatVO2.setMem_no2("M0006");
//		chatVO2.setChat_content("哈囉 你好22222222222");
//
//		dao.update(chatVO2);
//		
		
//		// 刪除
//		dao.delete("M0007");
		


//		ChatVO chatVO3=dao.findByPrimaryKey("MC0005");
//		System.out.print(chatVO3.getChat_id()+ ",");
//		System.out.print(chatVO3.getMem_no1()+ ",");
//		System.out.print(chatVO3.getMem_no2()+ ",");	
//		System.out.print(chatVO3.getChat_content()+ ",");
//		System.out.println(chatVO3.getChat_time());
	

		
		
		List<ChatVO> list = dao.getAll();
		for (ChatVO chatVO4 : list) {
			System.out.print(chatVO4.getChat_id()+ ",");
			System.out.print(chatVO4.getMem_no1()+ ",");
			System.out.print(chatVO4.getMem_no2()+ ",");
			System.out.print(chatVO4.getChat_content()+ ",");
			
			String tsStr = "";  
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			tsStr = sdf.format(chatVO4.getChat_time()); 
			System.out.println(tsStr);
//			System.out.println(chatVO4.getChat_time());

		}
	}
}