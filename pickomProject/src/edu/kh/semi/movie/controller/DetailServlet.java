package edu.kh.semi.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.movie.model.service.MovieService;
import edu.kh.semi.movie.model.vo.Actor;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieLink;

@WebServlet("/movie/movieDetail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath(); 
		String command = uri.substring( (contextPath +"/movie/" ).length() ); 
		
		String path = null;
		RequestDispatcher view = null; 
		
		
		
		try {
			MovieService service = new MovieService();
			
			//int cp = request.getParameter("cp") == null ? 1: Integer.parseInt(request.getParameter("cp"));
			
			if(command.equals("movieDetail")) {
				
				int movieNo = Integer.parseInt(request.getParameter("no"));
				
				Movie mo = service.selectDetail(movieNo);
				List<Actor> ac = service.selectActor(movieNo);
				
				
				request.setAttribute("mo", mo);
				request.setAttribute("ac", ac);
				
				path = "/WEB-INF/views/movie/movieDetail.jsp";
				view =request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
		}catch(Exception e) {
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
