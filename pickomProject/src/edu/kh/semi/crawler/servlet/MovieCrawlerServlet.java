package edu.kh.semi.crawler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
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
		HttpSession session = request.getSession();

		try {
			// 1. 영화테이블 크롤러 실행
			int movieNo = Integer.parseInt(request.getParameter("movieNo"));
			int select = service.searchMovie(movieNo);
			
			if(select == 0) {
				craw.movieTable(movieNo);			

				//  변수
				ArrayList<String> arrMovieTitleKo = craw.getArrMovieTitleKo();
				ArrayList<String> arrMovieTitleEn = craw.getArrMovieTitleEn();
				ArrayList<String> arrMovieDirector = craw.getArrMovieDirector();
				ArrayList<String> arrMovieSummary = craw.getArrMovieSummary();
				ArrayList<String> arrMovieCountry = craw.getArrMovieCountry();
				ArrayList<Date> arrMovieOpenDt =  craw.getArrMovieOpenDt();
				ArrayList<String> arrRuntime = craw.getArrRuntime();

				int result = service.insertMovie(movieNo, arrMovieTitleKo, arrMovieTitleEn, arrMovieDirector, arrMovieSummary,
						arrMovieCountry, arrMovieOpenDt, arrRuntime);



				if(result>0) {
					// 2. 영화파일

					craw.movieFileLink(movieNo);

					ArrayList<String> arrPoster = craw.getArrPoster();
					ArrayList<String> arrStillCut = craw.getArrStillCut();
					ArrayList<String> arrMedia = craw.getArrMedia();

					result = service.insertFile(movieNo, arrPoster, arrStillCut, arrMedia);

					if(result>0){
						craw.movieActor(movieNo);

						ArrayList<String> arrActorNmKo = craw.getArrActorNmKo();
						ArrayList<String> arrActorNmEn = craw.getArrActorNmEn();
						ArrayList<String> arrActorCd = craw.getArrActorCd();

						result = service.insertActor(movieNo, arrActorNmKo, arrActorNmEn, arrActorCd);
						if(result>0) {

							ArrayList<String> arrGenreCd1  = craw.getArrGenreCd1();

							service.insertActor(movieNo, arrGenreCd1);

							session.setAttribute("icon", "success");
							session.setAttribute("title", "영화 정보 삽입 성공");
							session.setAttribute("text", "추가 영화는 메소드에서 영화번호 변경 or 메소드 수정요망");
						}
					}
				}else{
					session.setAttribute("icon", "error");
					session.setAttribute("title", "영화 정보 삽입 실패");
					session.setAttribute("text", "크롤링 메소드");
					}
			} else{
				session.setAttribute("icon", "error");
				session.setAttribute("title", "영화 정보 삽입 실패");
				session.setAttribute("text", "크롤링 메소드 or 영화번호 확인 요망");
			}

			String path = request.getHeader("referer");
			response.sendRedirect(path);

		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "영화 정보 입력중 오류 발생");

			RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");

			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
