<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
	WebContent 폴더 : 프로젝트의 웹 배포 시 최상위 폴더(ContextPath, root, contextRoot)
					보통 주소상에서 프로젝트명으로 나타남
					http://localhost:8080/semi/ 이 주소에서     /semi를 나타냄.
					WebContent 하위에 존재하는 폴더/파일은 주소를 이용한 직접 요청이 가능하지만
					WEB-INF 폴더는 직접 요청이 불가능하다.
	WEB-INF 폴더 : 외부(클라이언트)로 부터 직접적인 파일 접근 요청을 차단하는 폴더
			   (파일 보호 -> 보안성 상승)
			   Servlet을 이용한 간접 접근만 가능함.
				
				
				
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>html문서 제목</title>
<!-- sweetalert API --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- css -->
<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/common/header/header.css" >
	
<!-- swipper -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />   

<!-- css -->
<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/common/main/main.css" >
</head>
<body>
	
	<!-- header.jsp 동적 include -->
	<jsp:include page="common/header.jsp" />

	
	<!-- 메인 화면  -->

	
	<!-- 메인 화면 이미지 -->
	 <form class="main-form">
        <div class="swiper-container main-poster">
            <h2>박스오피스</h2>
            <div class="swiper-wrapper">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>
       
            
      </div>

    </form>

    <form class="main-form">
        <div class="swiper-container main-poster">
            <h2>추천영화</h2>
            <div class="swiper-wrapper ">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>

            
       
      </div>

    </form>


    <form class="main-form">
        <div class="swiper-container main-poster">
            <h2>추천영화</h2>
            <div class="swiper-wrapper">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>

              
      </div>

    </form>




    <form class="main-form">
        <div class="swiper-container main-poster">
            <h2>추천영화</h2>
            <div class="swiper-wrapper">
                <div class="swiper-slide">Slide 1</div>
                <div class="swiper-slide">Slide 2</div>
                <div class="swiper-slide">Slide 3</div>
                <div class="swiper-slide">Slide 4</div>
                <div class="swiper-slide">Slide 5</div>
                <div class="swiper-slide">Slide 6</div>
                <div class="swiper-slide">Slide 7</div>
                <div class="swiper-slide">Slide 8</div>
                <div class="swiper-slide">Slide 9</div>
              </div>
              <div class="swiper-button-next"></div>
              <div class="swiper-button-prev"></div>
              <div class="swiper-pagination"></div>

             
       
      </div>

    </form>

	<!-- footer.jsp 동적 include -->
	<jsp:include page="common/footer.jsp"></jsp:include>

    

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <script>
      var swiper = new Swiper(".main-poster", {
        slidesPerView: 4,
        spaceBetween: 30,
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

