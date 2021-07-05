package edu.kh.semi.crawler.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.crawler.model.service.CrawlerService;
import edu.kh.semi.crawler.model.vo.CrawlerVo;

@WebServlet("/crawler/genre")
public class GenreCrawlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CrawlerService service = new CrawlerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		CrawlerVo craw = new CrawlerVo();
		
		try {
			craw.movieGenre();
			
			ArrayList<String> arrGenreNm  = craw.getArrGenreNm();
			ArrayList<String> arrGenreCd2  = craw.getArrGenreCd2();
			
			int result = service.insertGenre(arrGenreNm, arrGenreCd2);
			
			HttpSession session = request.getSession();
			
			if(result>0) {
				session.setAttribute("icon", "success");
				session.setAttribute("title", "장르 정보 삽입 성공");
			} else{
				session.setAttribute("icon", "error");
				session.setAttribute("title", "장르 추가 실패");
			}
			
			String path = request.getHeader("referer");
			response.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
