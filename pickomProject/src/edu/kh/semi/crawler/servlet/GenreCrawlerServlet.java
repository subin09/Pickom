package edu.kh.semi.crawler.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
		HttpSession session = request.getSession();
		
		try {
			
			craw.movieGenre();
			ArrayList<String> arrGenreNm  = craw.getArrGenreNm();
			ArrayList<String> arrGenreCd2  = craw.getArrGenreCd2();
			int check = 0;
			int result = 0;
			
			for(String checkGenreCd2: arrGenreCd2) {
				System.out.println(checkGenreCd2);
				check = service.seachGenre(checkGenreCd2);
				
				if(check == 0) {							
					result = service.insertGenre(arrGenreNm, arrGenreCd2);	
				} else{
					session.setAttribute("icon", "error");
					session.setAttribute("title", "장르 추가 실패");
				}				
				
			}
			
			if(result>0) {
				session.setAttribute("icon", "success");
				session.setAttribute("title", "장르 정보 삽입 성공");
			}
			
			String path = request.getHeader("referer");
			response.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "영화 장르 입력중 오류 발생");

			RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");

			view.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
