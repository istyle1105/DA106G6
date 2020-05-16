package com.logreport.model;

import java.util.List;


public interface Log_reportDAO_interface {
	public void insert(Log_reportVO log_reportvo);
	public void update(Log_reportVO log_reportvo);
	public void delete(String log_reportvo);
	public Log_reportVO findByPrimaryKey(String log_reportvo);
	public List<Log_reportVO> getAll();

//	public Log_reportVO getOneReport(String log_id);
	//萬用複合查詢傳入Map 回傳List
//	public List<Log_reportVO> getAll(Map<String, String[]> map);

}
