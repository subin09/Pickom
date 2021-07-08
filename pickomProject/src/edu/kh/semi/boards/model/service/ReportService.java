package edu.kh.semi.boards.model.service;

import edu.kh.semi.boards.model.dao.ReportDAO;
import edu.kh.semi.boards.model.vo.Report;
import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.commit;
import static edu.kh.semi.common.JDBCTemplate.getConnection;
import static edu.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

public class ReportService {
	ReportDAO dao = new ReportDAO();

	public int insertReport(Report report) throws Exception{
		int result = 0;
		Connection conn = getConnection();
		result = dao.insertReport(conn, report);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		close(conn);
		return result;
	}
	
	
}
