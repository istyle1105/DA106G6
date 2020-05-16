package com.demand.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;
import com.wallet.model.WalletDAO;
import com.wallet.model.WalletVO;

public class DemandJNDIDAO implements DemandDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO DEMAND (DE_NO, DE_ITEM_NAME, DE_PHOTO, PRICE, DE_MEM_NO, AREA)"
			+ "VALUES(('D'||LPAD(to_char(SEQ_DE_NO.NEXTVAL), 4, '0')), ?, ?, ?, ?, ?)";
	private static final String RETURN_WALLET = "INSERT INTO WALLET (W_REC_ID, MEM_NO, W_AMOUNT, W_TXN_NOTE, W_TXN_STATUS)"
			+ "VALUES(('W'||LPAD(to_char(SEQ_DE_NO.NEXTVAL), 4, '0')), ?, ?, ?, '0')";
	private static final String GET_ALL_STMT = "SELECT * FROM DEMAND WHERE STATUS != 1 ORDER BY DE_NO";
	private static final String GET_AVAILABLE = "SELECT * FROM DEMAND WHERE STATUS = 0 ORDER BY DE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM DEMAND WHERE DE_NO = ?";
	private static final String GET_ONE_AVAILABLE_STMT = "SELECT * FROM DEMAND WHERE DE_NO = ? AND STATUS != 1";
	private static final String GET_MY_DEMAND = "SELECT * FROM DEMAND WHERE DE_MEM_NO = ? AND STATUS != 1 ORDER BY STATUS";
	private static final String GET_MY_OFFER = "SELECT * FROM DEMAND WHERE PUR_MEM_NO = ? ORDER BY STATUS";
	private static final String DELETE = "UPDATE DEMAND SET STATUS = 1 WHERE DE_NO = ?";
	private static final String UPDATE = "UPDATE DEMAND SET DE_ITEM_NAME = ?, DE_PHOTO = ?, PRICE = ?, AREA = ?"
			+ "WHERE DE_NO = ?";
	private static final String UPDATE_PURMEM = "UPDATE DEMAND SET PUR_MEM_NO = ?, STATUS = 2 WHERE DE_NO = ?";
	private static final String UPDATE_STATUS = "UPDATE DEMAND SET STATUS = ? WHERE DE_NO = ?";
	private static final String ADD_SCORE = "UPDATE DEMAND SET PUR_SCORE = ?, PUR_COMMENT = ? WHERE DE_NO = ?";
	private static final String ADD_SCORE_MEMBER = "UPDATE MEMBER SET PUR_SCORE_AMOUNT = ?, PUR_TOTAL_SCORE = ? WHERE MEM_NO = ?";
	private static final String ADD_SCORE2 = "UPDATE DEMAND SET DE_SCORE = ?, DE_COMMENT = ? WHERE DE_NO = ?";
	private static final String ADD_SCORE2_MEMBER = "UPDATE MEMBER SET DE_SCORE_AMOUNT = ?, DE_TOTAL_SCORE = ? WHERE MEM_NO = ?";

	@Override
	public void add(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, demandVO.getDe_item_name());
			pstmt.setBytes(2, Base64.getDecoder().decode(demandVO.getDe_photo().getBytes()));
			pstmt.setInt(3, demandVO.getPrice());
			pstmt.setString(4, demandVO.getDe_mem_no());
			pstmt.setString(5, demandVO.getArea());

			pstmt.executeUpdate();
		
//			WalletDAO daoW = new WalletDAO();
//			WalletVO aWallet = new WalletVO();
//			aWallet.setMem_no(demandVO.getDe_mem_no());
//			aWallet.setW_amount(demandVO.getPrice());
//			aWallet.setW_txn_note("代購預扣");
//			aWallet.setW_txn_status(1);
//			daoW.insert(aWallet);
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, demandVO.getDe_item_name());
			pstmt.setBytes(2, Base64.getDecoder().decode(demandVO.getDe_photo().getBytes()));
			pstmt.setInt(3, demandVO.getPrice());
			pstmt.setString(4, demandVO.getArea());
			pstmt.setString(5, demandVO.getDe_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateStatus(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, demandVO.getStatus());
			pstmt.setString(2, demandVO.getDe_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void addScore(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_SCORE);

			pstmt.setInt(1, demandVO.getPur_score());
			pstmt.setString(2, demandVO.getPur_comment());
			pstmt.setString(3, demandVO.getDe_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void addScoreInMem(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_SCORE_MEMBER);

			pstmt.setInt(1, memberVO.getPur_score_amount());
			pstmt.setInt(2, memberVO.getPur_total_score());
			pstmt.setString(3, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void addScore2(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_SCORE2);

			pstmt.setInt(1, demandVO.getDe_score());
			pstmt.setString(2, demandVO.getDe_comment());
			pstmt.setString(3, demandVO.getDe_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void addScore2InMem(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_SCORE2_MEMBER);

			pstmt.setInt(1, memberVO.getDe_score_amount());
			pstmt.setInt(2, memberVO.getDe_total_score());
			pstmt.setString(3, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String de_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, de_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public DemandVO findByPK(String de_no) {

		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, de_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return demandVO;
	}
	
	@Override
	public DemandVO findByAvailablePK(String de_no) {

		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AVAILABLE_STMT);

			pstmt.setString(1, de_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return demandVO;
	}
	
	@Override
	public List<DemandVO> findMyDemand(String de_mem_no) {

		List<DemandVO> deList = new ArrayList<DemandVO>();
		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_DEMAND);

			pstmt.setString(1, de_mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
				deList.add(demandVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return deList;
	}
	
	@Override
	public List<DemandVO> findMyOffer(String de_mem_no) {

		List<DemandVO> deList = new ArrayList<DemandVO>();
		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_OFFER);

			pstmt.setString(1, de_mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
				deList.add(demandVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return deList;
	}

	@Override
	public List<DemandVO> getAll() {
		List<DemandVO> deList = new ArrayList<DemandVO>();
		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
				
				deList.add(demandVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return deList;
	}

	@Override
	public void update_purmem(DemandVO demandVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PURMEM);

			pstmt.setString(1, demandVO.getPur_mem_no());
			pstmt.setString(2, demandVO.getDe_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<DemandVO> getAvailable() {
		List<DemandVO> deList = new ArrayList<DemandVO>();
		DemandVO demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AVAILABLE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				demandVO = new DemandVO();
				demandVO.setDe_no(rs.getString("De_no"));
				demandVO.setDe_item_name(rs.getString("De_item_name"));
				demandVO.setDe_photo(Base64.getEncoder().encodeToString(rs.getBytes("De_photo")));
				demandVO.setPrice(rs.getInt("Price"));
				demandVO.setDe_mem_no(rs.getString("De_mem_no"));
				demandVO.setPur_mem_no(rs.getString("Pur_mem_no"));
				demandVO.setArea(rs.getString("Area"));
				demandVO.setStatus(rs.getInt("Status"));
				demandVO.setDe_time(rs.getTimestamp("De_time"));
				demandVO.setDe_comment(rs.getString("de_comment"));
				demandVO.setDe_score(rs.getInt("de_score"));
				demandVO.setPur_comment(rs.getString("pur_comment"));
				demandVO.setPur_score(rs.getInt("pur_score"));
				deList.add(demandVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return deList;
	}
	
	@Override
	public void returnWallet(WalletVO walletVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(RETURN_WALLET);

			pstmt.setString(1, walletVO.getMem_no());
			pstmt.setInt(2, walletVO.getW_amount());
			pstmt.setString(3, walletVO.getW_txn_note());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}
