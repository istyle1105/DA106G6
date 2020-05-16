package com.logcom.model;

import java.util.List;

import com.log.model.LogVO;

public class Log_comService {
	
	private Log_comDAO_interface dao;
	
	public Log_comService() {
		dao = new Log_comJNDIDAO();
	}
	
	public Log_comVO addLog_com(String mem_no,String log_id,String log_com,String log_com_status) {
		Log_comVO log_comvo = new Log_comVO();
		
		log_comvo.setMem_no(mem_no);
		log_comvo.setLog_id(log_id);
		log_comvo.setLog_com(log_com);
		log_comvo.setLog_com_status(log_com_status);
		dao.insert(log_comvo);
		return log_comvo;
		
	}
	
	public Log_comVO updateLog_com(String mem_no,String log_id,String log_com,String log_com_status) {
		Log_comVO log_comvo = new Log_comVO();
		
		log_comvo.setMem_no(mem_no);
		log_comvo.setLog_id(log_id);
		log_comvo.setLog_com(log_com);
		log_comvo.setLog_com_status(log_com_status);
		dao.insert(log_comvo);
		return log_comvo;
		
	}
	public void deleteLog_com(String com_id) {
		dao.delete(com_id);
	}
	public Log_comVO getOneLog_com(String com_id) {
		return dao.findByPrimaryKey(com_id);
	}
	public List<Log_comVO> getAll(){
		return dao.getAll();
	}
	
	public List<Log_comVO>getLog_com(String log_id){
		return dao.getLog_com(log_id);
	}

}
