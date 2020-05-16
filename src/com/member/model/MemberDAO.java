package com.member.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.sql.*;

public class MemberDAO implements MemberDAO_interface {
	
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
		"INSERT INTO MEMBER(MEM_NO, MEM_ID, PSW, M_NAME, GENDER, CELLPHONE, EMAIL, ADDRESS, M_PHOTO,ID_CARD,CAPTCHA)" + 
		"VALUES('M'||LPAD(to_char(SEQ_MEM_NO.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_BY_ID = 
		"SELECT * FROM MEMBER WHERE MEM_ID=?";
			
	
	private static final String GET_ALL_STMT = 
		"SELECT * FROM MEMBER ORDER BY MEM_NO";
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MEMBER WHERE MEM_NO=?";
//	private static final String GET_ONE_STMT = 
//			"SELECT MEM_NO,MEM_ID,PSW,M_NAME,GENDER,CELLPHONE,EMAIL,M_PHOTO,ID_CARD FROM MEMBER WHERE MEM_NO=?";
	
	
	private static final String DELETE = 
		"DELETE FROM MEMBER WHERE MEM_NO=?";
	
//	private static final String UPDATE = 
//		"UPDATE MEMBER SET PSW=?, M_NAME=?, GENDER=?, CELLPHONE=?, EMAIL=?, M_PHOTO=?, ID_CARD=? WHERE MEM_NO=?";
		
//	private static final String UPDATE = 
//			"UPDATE MEMBER SET PSW=?, M_NAME=?, GENDER=?, CELLPHONE=?, EMAIL=? WHERE MEM_NO=? and MEM_ID=?";
	
	
	
	private static final String UPDATE_FRONT = 
			"UPDATE MEMBER SET " + 
			"PSW=?, M_NAME=?, GENDER=?, CELLPHONE=?, EMAIL=?, ADDRESS=? ,M_PHOTO=?, ID_CARD=? WHERE MEM_NO=?";
	private static final String UPDATE_BACK_STATUS = 
			"UPDATE MEMBER SET MEM_VERIFY=?, LEADER_VERIFY=?, PUR_VERIFY=? WHERE MEM_NO=?";
	private static final String UPDATE_MEM_VERIFY = 
			"UPDATE MEMBER SET MEM_VERIFY=? WHERE MEM_NO=?";
	private static final String UPDATE_MEM_WALLET = 
			"UPDATE MEMBER SET ACC_BALANCE=? WHERE MEM_NO=?";
	
	
	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {			
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, memberVO.getMem_id());
			pstmt.setString(2, memberVO.getPsw());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setInt(4, memberVO.getGender());
			pstmt.setString(5,memberVO.getCellphone() );
			pstmt.setString(6, memberVO.getEmail());
			pstmt.setString(7, memberVO.getAddress());
			pstmt.setString(8, memberVO.getM_photo());
			pstmt.setBytes(9, memberVO.getId_card());			
			pstmt.setString(10, memberVO.getCaptcha());			

			pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}
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
	public void update_back(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

//			String UPDATE_BACK_STATUS = 
//			"UPDATE MEMBER SET MEM_VERIFY=?, LEADER_VERIFY=?, PUR_VERIFY=? WHERE MEM_NO=?";
			pstmt = con.prepareStatement(UPDATE_BACK_STATUS);

			pstmt.setInt(1, memberVO.getMem_verify());
			pstmt.setInt(2, memberVO.getLeader_verify());
			pstmt.setInt(3, memberVO.getPur_verify());
			pstmt.setString(4, memberVO.getMem_no());

			pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
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
	public void update_front(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {		
			con = ds.getConnection();
//"UPDATE MEMBER SET PSW=?, M_NAME=?, GENDER=?, CELLPHONE=?, EMAIL=?, M_PHOTO=?, ID_CARD=? WHERE MEM_NO=?";
				pstmt = con.prepareStatement(UPDATE_FRONT);
				pstmt.setString(1, memberVO.getPsw());
				pstmt.setString(2, memberVO.getM_name());
				pstmt.setInt(3, memberVO.getGender());
				pstmt.setString(4,memberVO.getCellphone());
				pstmt.setString(5, memberVO.getEmail());
				pstmt.setString(6, memberVO.getAddress());
				
				if(memberVO.getM_photo().equals("")) {
					MemberDAO dao=new MemberDAO();
					pstmt.setString(7,dao.findByPrimaryKey(memberVO.getMem_no()).getM_photo());
				}else 
					pstmt.setString(7, memberVO.getM_photo());
				if(memberVO.getId_card().length==0) {
					MemberDAO dao=new MemberDAO();
					pstmt.setBytes(8,dao.findByPrimaryKey(memberVO.getMem_no()).getId_card());
				}else
					pstmt.setBytes(8, memberVO.getId_card());
				

				pstmt.setString(9,memberVO.getMem_no());
				pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
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
	public void delete(String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public MemberVO findPK(String mem_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ID);

			pstmt.setString(1,mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO =new MemberVO();				
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setPsw(rs.getString("psw"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setGender(rs.getInt("gender"));
				memberVO.setCellphone(rs.getString("cellphone"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setM_photo(rs.getString("m_photo"));
				memberVO.setId_card(rs.getBytes("id_card"));
				memberVO.setMem_verify(rs.getInt("mem_verify"));
				memberVO.setLeader_verify(rs.getInt("leader_verify"));
				memberVO.setPur_verify(rs.getInt("pur_verify"));
				memberVO.setReg_time(rs.getTimestamp("reg_time"));
				memberVO.setAcc_balance(rs.getInt("acc_balance"));
				memberVO.setDe_score_amount(rs.getInt("de_score_amount"));
				memberVO.setDe_total_score(rs.getInt("de_total_score"));
				memberVO.setPur_score_amount(rs.getInt("pur_score_amount"));
				memberVO.setPur_total_score(rs.getInt("pur_total_score"));
				memberVO.setLeader_score_amount(rs.getInt("leader_score_amount"));
				memberVO.setLeader_total_score(rs.getInt("leader_total_score"));
				memberVO.setCaptcha(rs.getString("captcha"));
				
			
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
		return memberVO;
	}
	

	@Override
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO =new MemberVO();				
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setPsw(rs.getString("psw"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setGender(rs.getInt("gender"));
				memberVO.setCellphone(rs.getString("cellphone"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setM_photo(rs.getString("m_photo"));
				memberVO.setId_card(rs.getBytes("id_card"));
				memberVO.setMem_verify(rs.getInt("mem_verify"));
				memberVO.setLeader_verify(rs.getInt("leader_verify"));
				memberVO.setPur_verify(rs.getInt("pur_verify"));
				memberVO.setReg_time(rs.getTimestamp("reg_time"));
				memberVO.setAcc_balance(rs.getInt("acc_balance"));
				memberVO.setDe_score_amount(rs.getInt("de_score_amount"));
				memberVO.setDe_total_score(rs.getInt("de_total_score"));
				memberVO.setPur_score_amount(rs.getInt("pur_score_amount"));
				memberVO.setPur_total_score(rs.getInt("pur_total_score"));
				memberVO.setLeader_score_amount(rs.getInt("leader_score_amount"));
				memberVO.setLeader_total_score(rs.getInt("leader_total_score"));
				memberVO.setCaptcha(rs.getString("captcha"));
				
				
			
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();			
				memberVO.setMem_no(rs.getString("mem_no"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setPsw(rs.getString("psw"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setGender(rs.getInt("gender"));
				memberVO.setCellphone(rs.getString("cellphone"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setM_photo(rs.getString("m_photo"));
				memberVO.setId_card(rs.getBytes("id_card"));
				memberVO.setMem_verify(rs.getInt("mem_verify"));
				memberVO.setLeader_verify(rs.getInt("leader_verify"));
				memberVO.setPur_verify(rs.getInt("pur_verify"));
				memberVO.setReg_time(rs.getTimestamp("reg_time"));
				memberVO.setAcc_balance(rs.getInt("acc_balance"));
				memberVO.setDe_score_amount(rs.getInt("de_score_amount"));
				memberVO.setDe_total_score(rs.getInt("de_total_score"));
				memberVO.setPur_score_amount(rs.getInt("pur_score_amount"));
				memberVO.setPur_total_score(rs.getInt("pur_total_score"));
				memberVO.setLeader_score_amount(rs.getInt("leader_score_amount"));
				memberVO.setLeader_total_score(rs.getInt("leader_total_score"));
				memberVO.setCaptcha(rs.getString("captcha"));
				list.add(memberVO); // Store the row in the list
							
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
	@Override
	public void update_mem_verify(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_MEM_VERIFY);
			pstmt.setInt(1, memberVO.getMem_verify());
			pstmt.setString(2, memberVO.getMem_no());

			pstmt.executeUpdate();


			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
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
	public void update_wallet(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
	
//UPDATE_MEM_WALLET ="UPDATE MEMBER SET ACC_BALANCE=? WHERE MEM_NO=?";
						
			pstmt = con.prepareStatement(UPDATE_MEM_WALLET);
			pstmt.setInt(1, memberVO.getAcc_balance());
			pstmt.setString(2, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
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



	
}