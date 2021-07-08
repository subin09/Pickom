<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

	<!-- Bootstrap core JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
	<!-- sweetalert API --> 
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- css -->
	<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/common/main/detail-style.css" >
	 <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
	
	<title></title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="d-container">
<c:forEach items="${mo.moList}" var="m">
						<c:choose>
							<c:when test="${m.movieLinkLV == 0 && m.movieLinkType eq 'P'}">
								<c:set var="poster" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV == 0 && m.movieLinkType eq 'S'}">
								<c:set var="img0" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV == 1 && m.movieLinkType eq 'S'}">
								<c:set var="img1" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV == 2 && m.movieLinkType eq 'S'}}">
								<c:set var="img2" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==3 && m.movieLinkType eq 'S'}}">
								<c:set var="img3" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==4 && m.movieLinkType eq 'S'}}">
								<c:set var="img4" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==5 && m.movieLinkType eq 'S'}}">
								<c:set var="img5" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==6 && m.movieLinkType eq 'S'}}">
								<c:set var="img6" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==7 && m.movieLinkType eq 'S'}}">
								<c:set var="img7" value="${m.movieFileLink}"/>
							</c:when>
							<c:when test="${m.movieLinkLV ==0 && m.movieLinkType eq 'M'}}">
								<c:set var="m0" value="${m.movieFileLink}"/>
							</c:when>
						</c:choose>
					</c:forEach>


 <div class="detail-container">
        <div class="movie-info">
            <form>
                <div class="movie-info">
                  <div class="movie-poster">
                
                    <img src="${poster}">
             
                  </div>
                  <div class="movie-info-detail">
                    <h4> ${mo.movieTitleEn }</h4>
                    <hr>
                    <div class="item">
                      <span class="items">${mo.movieOpenDt}</span>
                      <span class="items">${mo.movieGenreNM }</span>
                      <span class="items">${mo.movieCountry }</span>
                    </div>
                    
                    <div class="items">${mo.movieDirector }</div>
                    <c:forEach items="${ac}" var="ac" begin="0" end="3">
                    <div class="items">
                        <span class="items actor">${ac.actorNmKo }</span>
                            
                    </div> 
                    </c:forEach>  
                    <br>
                  </div>
                </div>
                
            </form>
        </div>

        <hr class="movie-line">


        <div class="movie-detail">
            <div class="movie-story">
                <details>
    			<summary id="cusur">줄거리</summary>
    			<p>${mo.movieSummary}</p>
				</details>
            </div>
        </div>


        <hr class="movie-line">

     

        <br>
        <hr class="movie-line">

     
     
	     <!-- Swiper -->
	    <div class="swiper-container mySwiper">
	      <div class="swiper-wrapper">
	        <div class="swiper-slide">
	        	<c:if test="${!empty img0 }">
                   <img src="${img0}" class="slider-image">
                </c:if>
	        </div>
	        <div class="swiper-slide">
	        	<c:if test="${!empty img1 }">
                   <img src="${img1}" class="slider-image">
                </c:if>
	        </div>
	        <div class="swiper-slide">
	        	<c:if test="${!empty img2 }">
                   <img src="${img2}" class="slider-image">
                </c:if>
	        </div>
	       
	    
	      </div>
	      <div class="swiper-button-next"></div>
	      <div class="swiper-button-prev"></div>
	    </div>
     
          
            
            
            </div>
        </div>


<jsp:include page="../common/footer.jsp"></jsp:include>

    <!-- Swiper JS -->
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
    <script>
      var swiper = new Swiper(".mySwiper", {
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      });
    </script>



      
    </script>

</body>
</html>
</body>
</html>