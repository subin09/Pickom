<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<!-- swipper -->
     <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
     <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />   
	<title></title>
</head>
<body>
 <div class="container">
        <div class="movie-info">
            <form>
                <div class="movie-info">
                  <div class="movie-poster">
                    <img src="#">
                  </div>
                  <div class="movie-info-detail">
                    <h4>movie title</h4>
                    <hr>
                    <div class="item">
                      <span class="items">개봉년도</span>
                      <span class="items">장르</span>
                      <span class="items">나라</span>
                    </div>
                    
                    <div class="items">감독</div>
                    <div class="items">
                        <span class="items">배우</span>
                        <span class="items">배우</span>
                        <span class="items">배우</span>      
                    </div>   
                    <br>
                    <input class="btn btn-primary" type="button" value="찜하기">
                  </div>
                </div>
                
            </form>
        </div>

        <hr class="movie-line">


        <div class="movie-detail">
            <div class="movie-story">
                <strong>상세정보</strong>
                <p>And yes, this is the last block of representative placeholder content.
                    And yes, this is the last block of representative placeholder content.
                    And yes, this is the last block of representative placeholder content.
                </p>
            </div>
        </div>

        <hr class="movie-line">

        <div class="img-content">
            <form>
                <h4>미디어</h4>
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img src="#" class="d-block w-100" alt="...">
                      </div>
                      <div class="carousel-item">
                        <img src="#" class="d-block w-100" alt="...">
                      </div>
                      <div class="carousel-item">
                        <img src="#"  class="d-block w-100" alt="...">
                      </div>
                      <div class="carousel-item">
                        <video controls autoplay src="#" id="video"></video> 
                      </div>
                    
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                      <span class="sr-only">Next</span>
                    </a>
                  </div>
                  
            </form>
        </div>

        <br>
        <hr class="movie-line">

        <div class="movie-etc">
            <div class="review-table">
                <form>
                <div id="title">
                    <h4>리뷰</h4>
                    <a href="" id="more">더보기</a> 
                </div>
               
                <table class="table table-sm">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                      </tr>
                      <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                      </tr>
                      <tr>
                        <th scope="row">3</th>
                        <td colspan="2">Larry the Bird</td>
                        <td>@twitter</td>
                      </tr>
                    </tbody>
                  </table>
                </form>
            </div>

            <hr class="movie-line"><br>
            <div class="recommend-movie">
                <h4>비슷한 작품</h4>
                <form class="main-form">
                    <div class="swiper-container recom">
                    
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
            
            </div>
        </div>
    </div>






    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script>
        $('.carousel').carousel({
            Touch : true,
            interval:false
        })




        var swiper = new Swiper(".recom", {
        slidesPerView: 4,
        spaceBetween: 20,
        slidesPerGroup: 3,
        loop: true,
        mousewheel: false,
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
</body>
</html>