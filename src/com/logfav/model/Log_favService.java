package com.logfav.model;

import java.util.List;

import com.log.model.LogVO;

public class Log_favService {
	
	private Log_favDAO_interface dao;
	
	public Log_favService() {
		dao = new Log_favJNDIDAO();
	}
	
	public Log_favVO addLog_fav(String log_id,String mem_no) {
		Log_favVO log_favvo = new Log_favVO();
		
		log_favvo.setLog_id(log_id);
		log_favvo.setMem_no(mem_no);
		dao.insert(log_favvo);
		return log_favvo;
		
	}
	
	public List<Log_favVO> getMemFav(String mem_no){
		return dao.getMemFav(mem_no);
	}
	
	
	
	public void delete(String log_id , String mem_no) {
		Log_favVO log_favvo = new Log_favVO();
		log_favvo.setLog_id(log_id);
		log_favvo.setMem_no(mem_no);
		
		dao.delete(log_favvo);
	}
	
//	public static void main(String[] args) {
//		Log_favService logfavsvc = new Log_favService();
//		Log_favVO logfav = new Log_favVO();
//		
//		logfavsvc.dao.getMemFav("M0005");
//		
//		
//	}
	

}
