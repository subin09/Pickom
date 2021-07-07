<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>main</title>
<!-- sweetalert API --> 
<!-- css -->
	
<!-- swipper -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />

<!-- css -->
<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/common/main/main.css" >
</head>
<body>
	
	<!-- header.jsp 동적 include -->
	 <jsp:include page="../common/header.jsp"></jsp:include>

	
	<!-- 메인 화면  -->

	
	<!-- 메인 화면 이미지 -->

	<div class="container">
	
        <div class="swiper-container main-poster">
            <h2 class="main-title">액션</h2>
            <div class="swiper-wrapper">
            <c:forEach items="${mainList}" var="li">
            <c:if test="${li.movieGenreCode==19}">
                <div class="swiper-slide">
                  <a href="${contextPath}/movie/movieDetail?no=${li.movieNo}">
                <img alt="" src="${li.movieFileLink}">
                </a>
               
                </div>
                </c:if>
               
                </c:forEach>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>
      	</div>
	
        <div class="swiper-container main-poster">
            <h2 class="main-title">모험</h2>
            <div class="swiper-wrapper">
            <c:forEach items="${mainList}" var="li">
            <c:if test="${li.movieGenreCode==6}">
                <div class="swiper-slide">
          		<a href="${contextPath}/movie/movieDetail?no=${li.movieNo}">
                <img alt="" src="${li.movieFileLink}">
               </a>
                </div>
                </c:if>
                </c:forEach>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>
      	</div>
	
        <div class="swiper-container main-poster">
            <h2 class="main-title">스릴러</h2>
            <div class="swiper-wrapper">
            <c:forEach items="${mainList}" var="li">
            <c:if test="${li.movieGenreCode==16}">
                <div class="swiper-slide">
                 <a href="${contextPath}/movie/movieDetail?no=${li.movieNo}">
                <img alt="" src="${li.movieFileLink}">
                </a>
                </div>
                </c:if>
                </c:forEach>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>
      	</div>
	
        <div class="swiper-container main-poster">
            <h2 class="main-title">드라마</h2>
            <div class="swiper-wrapper">
            <c:forEach items="${mainList}" var="li">
            <c:if test="${li.movieGenreCode==12}">
                <div class="swiper-slide">
                 <a href="${contextPath}/movie/movieDetail?no=${li.movieNo}">
                <img alt="" src="${li.movieFileLink}">
                </a>
                </div>
                </c:if>
                </c:forEach>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>
      	</div>
	
 </div>

  
 

	<!-- footer.jsp 동적 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

    

    <script src="https://unpkg.com/swiper/swiper-bundle.js"></script>

    <script>
      var swiper = new Swiper(".main-poster", {
        slidesPerView: 4,
        spaceBetween: 10,
        slidesPerGroup: 3,
        loop: true,
        mousewheel : true,
        loopFillGroupWithBlank: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      });
      
      
     
     
    </script>
	
	
	
</body>
</html>

