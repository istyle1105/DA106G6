package com.p_report.model;
import java.util.List;

public interface P_ReportDAO_interface {
	
	void add(P_ReportVO p_reportVO);
	void update(P_ReportVO p_reportVO);
	void delete(String p_re_no);
	P_ReportVO findByPK(String p_re_no);
	List<P_ReportVO> getAll();

}
