package com.p_report.model;

import java.sql.Timestamp;
import java.util.List;

public class P_ReportService {

	private P_ReportDAO_interface dao;

	public P_ReportService() {
		dao = new P_ReportJNDIDAO();
	}

	public P_ReportVO add(String mem_no, String de_no, String p_re_reason) {

		P_ReportVO p_reportVO = new P_ReportVO();

		p_reportVO.setDe_no(de_no);
		p_reportVO.setMem_no(mem_no);
		p_reportVO.setP_re_reason(p_re_reason);

		dao.add(p_reportVO);

		return p_reportVO;
	}

	public P_ReportVO update(String p_re_no, Integer p_re_status) {

		P_ReportVO p_reportVO = new P_ReportVO();

		p_reportVO.setP_re_no(p_re_no);
		p_reportVO.setP_re_status(p_re_status);

		dao.update(p_reportVO);

		return p_reportVO;
	}

	public void delete(String p_re_no) {
		dao.delete(p_re_no);
	}

	public P_ReportVO findByPK(String p_re_no) {
		return dao.findByPK(p_re_no);
	}

	public List<P_ReportVO> getAll() {
		return dao.getAll();
	}

}
