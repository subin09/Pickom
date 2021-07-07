package edu.kh.semi.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.movie.model.service.MovieService;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieLink;


@WebServlet("/movieMain")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			MovieService service = new MovieService();
			
			
			List<MovieLink> mainList = service.selectMain();

	       	System.out.println(mainList);
	       	 
	       	 if(mainList != null) {
	       		 request.setAttribute("mainList", mainList);
	       		 
	       		 RequestDispatcher view 
	       		 = request.getRequestDispatcher("/WEB-INF/views/movie/movieMain.jsp");
	       		 
	       		 
	       		 view.forward(request, response);
	      
	       	 }else {
				 	request.setAttribute("icon", "error"); 
					request.setAttribute("title", "불러오기 실패");
					request.setAttribute("text", "오류");		
	       	 }
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	
	}

			
			
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		
}
}