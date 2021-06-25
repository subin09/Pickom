<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 배포된 웹 애플리케이션의 최상위 주소를 간단히 얻어올 수 있도록 
     application 범위로 변수를 하나 생성 --%>
<c:set var="contextPath" scope="application"
	   value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Header</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 <style>
 div#mainlog,div#mainmypage{
        text-align: center;
      }
      #mainlog-div{
        margin-left: 10%;
      }
      #mainmypage-div{
        margin-right: 10%;
      }

      input#mainsearch{
        width: 500px;
      }

      #mainheader{
        background-color: beige;
      }

      #mainmenutitle{
        font-weight: bold;
        color: black;
        text-align: center;
        width: 10em;
      }

      #mainmenutitle:hover{
        color: orange;
      }

      #maincustomerservice{
          margin-left: 10%;
      }

      body {
	      padding-top: 210px;
      }


</style>
 
 

</head>

<body>
	<div class="fixed-top" id="navigator">
      <div class="d-flex bd-highlight justify-content-between" id="mainheader">  
        <div class="p-2 bd-highlight img" id="mainlog-div">
          <!-- Button trigger modal -->
            <a href="#">
            <img src="${contextPath}/resources/img/logo.png" width="200px" height="auto" id="mainlogo" alt="">
          </a>
        </div>
        
        <div class="p-2 bd-highlight align-self-end">
          <form class="form-inline">
            <div>
              <input class="form-control mr-sm-2" id="mainsearch" type="search" placeholder="Search" aria-label="Search">
            </div>
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
          </form>
        </div>
        
        <div class="p-2 bd-highlight img" id="mainmypage-div">
          <a href="#" data-toggle="modal" data-target="#exampleModal">
            <img src="${contextPath}/resources/img/mypage.jpg" width="100px" height="auto" id="mainmypage" alt="">
          </a>
        </div>
        
      </div>

      <div class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
      <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" id="mainmenutitle" href="#">메인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="mainmenutitle" href="#">일반게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="mainmenutitle" href="#">리뷰게시판</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="mainmenutitle" href="#">행사게시판</a>
            </li>
            <li class="nav-item dropdown" id="mainmenutitle">
              <a class="nav-link dropdown-toggle" href="#" id="mainmenutitle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                고객센터
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" id="mainmenutitle" href="#">FAQ</a>
                <a class="dropdown-item" id="mainmenutitle" href="#">불편신고</a>
              </div>
            </li>
          </ul>
        </div>
    
      </div>
     </div>
     
	<!-- Modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        로그인
    </button>
    
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-signin" method="POST" action="#" onsubmit="">
                        
                        <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" value="">
                        <br>
                        <input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호">
                        <br>

                        <div class="checkbox mb-3">
                            <label> 
                                <input type="checkbox" name="save" id="save" ${checked}> 아이디 저장
                            </label>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
                        
                        <a class="btn btn-lg btn-secondary btn-block" href="#">회원가입</a>
                    </form>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-secondary" href="">아이디 찾기</a>
                    <a class="btn btn-secondary" href="">비밀번호 찾기</a>
                </div>
            </div>
        </div>
    </div>
	
	<c:if test="${!empty title }">
		<script>
			swal({
				"icon"  : "${icon}",
				"title" : "${title}",
				"text"  : "${text}"
			});
			
		</script>
	
	
		<c:remove var="icon" />
		<c:remove var="title" />
		<c:remove var="text" />
	</c:if>
	
</body>

</html>
