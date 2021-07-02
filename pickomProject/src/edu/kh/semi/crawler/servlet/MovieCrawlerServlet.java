package edu.kh.semi.crawler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.crawler.model.service.CrawlerService;
import edu.kh.semi.crawler.model.vo.CrawlerVo;

@WebServlet("/crawler/movie")
public class MovieCrawlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CrawlerService service = new CrawlerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		CrawlerVo craw = new CrawlerVo();
		
		try {
			// 크롤러 실행
			craw.movieTable();
			
			// 변수얻어옴
			int movieNo = craw.getMovieNo();
			ArrayList<String> arrMovieTitleKo = craw.getArrMovieTitleKo();
			ArrayList<String> arrMovieTitleEn = craw.getArrMovieTitleEn();
			ArrayList<String> arrMovieDirector = craw.getArrMovieDirector();
			ArrayList<String> arrMovieSummary = craw.getArrMovieSummary();
			ArrayList<String> arrMovieCountry = craw.getArrMovieCountry();
			ArrayList<Date> arrMovieOpenDt =  craw.getArrMovieOpenDt();
			ArrayList<String> arrRuntime = craw.getArrRuntime();
			
			int result = service.insertMovie(movieNo, arrMovieTitleKo, arrMovieTitleEn, arrMovieDirector, arrMovieSummary,
											arrMovieCountry, arrMovieOpenDt, arrRuntime);
			
			HttpSession session = request.getSession();
			
			if(result>0) {
				session.setAttribute("icon", "success");
				session.setAttribute("title", "영화 정보 삽입 성공");
				session.setAttribute("text", "추가 영화는 메소드에서 영화번호 변경 or 메소드 수정요망");
			} else{
				session.setAttribute("icon", "error");
				session.setAttribute("title", "영화 정보 삽입 실패");
				session.setAttribute("text", "크롤링 메소드 or 영화번호 확인 요망");
			}
			
			response.sendRedirect(request.getContextPath()+"/crawler");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
