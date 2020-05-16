package com.log.model;

import java.sql.Timestamp;
import java.util.List;

public class LogService {
	
	private LogDAO_interface dao;
	
	public LogService() {
		dao = new LogJNDIDAO();
	}
	
	public LogVO addLog( String mem_no, String log_title, String log_con ,String log_pic) {
		
		LogVO logvo = new LogVO();
		
		
		logvo.setMem_no(mem_no);
		logvo.setLog_title(log_title);
		logvo.setLog_con(log_con);
		logvo.setLog_pic(log_pic);
		dao.insert(logvo);
		return logvo;
	}
	
	public LogVO updateLog(String log_id, String mem_no, String log_title, String log_con, Integer l_status, Integer l_favorited ,String log_pic ) {
		
		LogVO logvo = new LogVO();
		
		logvo.setLog_id(log_id);
		logvo.setMem_no(mem_no);
		logvo.setLog_title(log_title);
		logvo.setLog_con(log_con);
		logvo.setL_status(l_status);
		logvo.setL_favorited(l_favorited);
		logvo.setLog_pic(log_pic);
		
		dao.update(logvo);
		return logvo;
	}
	
	public void deleteLog(String log_id) {
		dao.delete(log_id);
	}
	
	public LogVO getOneLog(String log_id) {
		return dao.findByPrimaryKey(log_id);
	}
	
	public List<LogVO> getAll(){
		return dao.getAll();
	}
	
	public List<LogVO> getSixLogFav(){
		return dao.getSixLogFav();
	}
	
	public List<LogVO> getSixLogDesc(){
		return dao.getSixLogDesc();
	}
	
	public List<LogVO> getAllLogFav(){
		return dao.getAllLogFav();
	}
	
	public List<LogVO> getAllLogDesc(){
		return dao.getAllLogDesc();
	}
	
	
	
	
	
	
	public List<LogVO> getMemAll(String mem_no){
		return dao.getMemAll(mem_no);
	}
	
	public List<LogVO> getLogTitle(String log_title){
		return dao.getLogTitle(log_title);
	}
	
	
	
}
