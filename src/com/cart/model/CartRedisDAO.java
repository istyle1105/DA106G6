package com.cart.model;

import java.util.*;

import com.member.model.MemberVO;
import com.product.model.ProductVO;
import org.json.*;
import redis.clients.jedis.*;

public class CartRedisDAO implements CartDAO_interface {
	String host = "localhost";
	Integer port = 6379;
	String passwd = "123456";
	
	@Override
	public void insert(MemberVO memberVO,ProductVO productVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.rpush(memberVO.getMem_no() + ":2",productVO.getP_id());
			jedis.hset(memberVO.getMem_no() + ":1",productVO.getP_id(),productVO.getQuantity().toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(MemberVO memberVO,ProductVO productVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hincrBy(memberVO.getMem_no() + ":1",productVO.getP_id(),productVO.getQuantity());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void replace(MemberVO memberVO,ProductVO productVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hset(memberVO.getMem_no() + ":1",productVO.getP_id(),productVO.getQuantity().toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(MemberVO memberVO,ProductVO productVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hdel(memberVO.getMem_no() + ":1",productVO.getP_id(),productVO.getQuantity().toString());
			jedis.lrem(memberVO.getMem_no() + ":2",1,productVO.getP_id());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}
	
	@Override
	public List<String> getAllP_idByMem_no(String mem_no) {
		List<String> list = new ArrayList<String>();
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			list = jedis.lrange(mem_no + ":2",0,-1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public Integer getValueByP_id(String mem_no,String p_id) {
		Integer quantity = 0;
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);

			quantity = new Integer(jedis.hget(mem_no + ":1",p_id));

				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return quantity;
	}
	
	
	public static void main(String[] args) {

		CartRedisDAO dao = new CartRedisDAO();

		// 新增
		ProductVO productVO = new ProductVO();
		productVO.setP_id("P0007");
		productVO.setQuantity(61);	
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no("M0001");
		dao.insert(memberVO, productVO);

		// 增加//		ProductVO productVO = new ProductVO();
//		productVO.setP_id("P0012");
//		productVO.setQuantity(20);	
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMem_no("M0001");
//		dao.update(memberVO, productVO);

		//刪除//		ProductVO productVO = new ProductVO();
//		productVO.setP_id("P0016");
//		productVO.setQuantity(61);	
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMem_no("M0001");
//		dao.delete(memberVO,productVO);
		
		//查詢
		
		System.out.println(dao.getAllP_idByMem_no("M0001"));
		System.out.println(dao.getValueByP_id("M0001","P0001"));		
	}
}