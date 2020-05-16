package com.logfav.model;

import java.util.List;

public interface Log_favDAO_interface {
	public void insert(Log_favVO log_favvo);
	public int update(Log_favVO log_favvo);
	public void delete(Log_favVO log_favvo);
	public Log_favVO findByPrimaryKey(String log_id);
	public List<Log_favVO> getAll();
	public List<Log_favVO> getMemFav(String mem_no);
	//萬用複合查詢傳入Map 回傳List
//	public List<Log_favoriteVO> getAll(Map<String, String[]> map);

}
