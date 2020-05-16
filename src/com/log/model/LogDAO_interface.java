package com.log.model;

import java.util.List;
import java.util.Map;

public interface LogDAO_interface {
	
	public void insert(LogVO logvo);
	public void update(LogVO logvo);
	public void delete(String log_id);
	public LogVO findByPrimaryKey(String log_id);
	public List<LogVO> getAll();
	public List<LogVO> getSixLogFav();
	public List<LogVO> getSixLogDesc();
	public List<LogVO> getAllLogFav();
	public List<LogVO> getAllLogDesc();
	public List<LogVO> getMemAll(String mem_no);
	public List<LogVO> getLogTitle(String log_title);
	
	//萬用複合查詢傳入Map 回傳List
//	public List<LogVO> getAll(Map<String, String[]> map);

}
