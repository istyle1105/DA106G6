package com.logreport.model;

import java.util.List;

import com.logcom.model.Log_comVO;

public class Log_reportService {
	
	private Log_reportDAO_interface dao;
	
	public Log_reportService() {
		dao = new Log_reportJNDIDAO();
	}
	
	public Log_reportVO addLog_report( String mem_no, String log_id,String l_re_reason) {
		Log_reportVO log_reportvo = new Log_reportVO();
		
//		log_reportvo.setL_re_id(l_re_id);
		log_reportvo.setMem_no(mem_no);
		log_reportvo.setLog_id(log_id);
		log_reportvo.setL_re_reason(l_re_reason);
		dao.insert(log_reportvo);
		return log_reportvo;
	}
	
	public Log_reportVO updateLog_report(String l_re_id, String mem_no, String log_id,String l_re_reason, Integer l_re_status) {
		Log_reportVO log_reportvo = new Log_reportVO();
		
		log_reportvo.setL_re_id(l_re_id);
		log_reportvo.setMem_no(mem_no);
		log_reportvo.setLog_id(log_id);
		log_reportvo.setL_re_reason(l_re_reason);
		log_reportvo.setL_re_status(l_re_status);
		dao.update(log_reportvo);
		return log_reportvo;
		
	}
	
	public void deleteLog_report(String l_re_id) {
		dao.delete(l_re_id);
	}
	
	public Log_reportVO getOneLog_report(String l_re_id) {
		return dao.findByPrimaryKey(l_re_id);
	}
	
	public List<Log_reportVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		
		Log_reportService logresvc = new Log_reportService();
		Log_reportVO logre = new Log_reportVO();
		
		logresvc.dao.findByPrimaryKey("M0004");
		
		
		
	}

}
