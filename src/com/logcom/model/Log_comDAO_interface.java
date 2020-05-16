package com.logcom.model;

import java.util.List;

public interface Log_comDAO_interface {
	public void insert(Log_comVO log_comvo);
	public void update(Log_comVO log_comvo);
	public void delete(String com_id);
	public Log_comVO findByPrimaryKey(String com_id);
	public List<Log_comVO> getAll();
	public List<Log_comVO> getLog_com(String log_id);
	//萬用複合查詢傳入Map 回傳List
//	public List<Log_comVO> getAll(Map<String, String[]> map);


}
