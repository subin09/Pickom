<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" scope="application" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Header</title>



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common/01_header.css">

</head>

<body>
	<div id="header-body">
	<div class="fixed-top" id="navigator">
		<div class="d-flex bd-highlight justify-content-around"
			id="mainheader">
			<div class="p-2 bd-highlight img" id="mainlog-div">



				<%--mian logo 연결 --%>
				<a href="${contextPath}"> <img id="logoimage"
					src="${contextPath}/resources/img/logo.jpg"
					alt="">
				</a>
			</div>

			<div class="p-2 bd-highlight align-self-end">


					<form class="form-inline"
						action="${contextPath}/reviewBoard/list?type=100&cp=1"
						method="post" role="form" onsubmit="return searchValidate();">
						
						<div class="mb-2">
							<input name="searchType" value="1" type = hidden>
							<input class="form-control mr-sm-2" type="search"
							placeholder="Search" aria-label="Search" size="60" name="searchValue">
						</div>
						
						<div class="input-group-append mb-2">
                        <button class="btn btn-outline-secondary" type="submit"
                        	 id="button-addon2">검색</button>
                        </div>
					</form>
			</div>

			<%-- loginModal --%>
			<div class="d-flex p-2 bd-highlight img" id="mainmypage-div">
				<c:choose>

					<c:when test="${empty loginMember}">
						<a href="#" data-toggle="modal" data-target="#loginModal">
							<img id="loginimage"
							src="${contextPath}/resources/img/login-logo.jpg" alt="">
						</a>
					</c:when>
					<c:otherwise>
						
						<div class="p-2 bd-highlight img">
							<c:choose>
								<c:when test="${loginMember.memberGrade == 'A'}">
									<a href="${contextPath}/admin/adminMain">
										<img id="loginimage" 
										src="${contextPath}/${filePath}${fileName}"
										 alt="">
									 </a>
								 </c:when>
								 <c:otherwise>
								 	<a href="${contextPath}/member/mypage">
									 <img id="loginimage" 
										src="${contextPath}/${filePath}${fileName}"
										 alt="">
									</a>
								 </c:otherwise>
							</c:choose>
						</div>
							
						<div class="p-2 bd-highlight">
							<ul class="navbar-nav ml-auto">
								<li class="nav-item active fixed">
								
								<%-- 마이페이지 관리자/회원 분류 --%>
								<c:choose>
									<c:when test="${loginMember.memberGrade == 'A'}">
										<a class="btn" id="mypageList"
											href="${contextPath}/admin/adminMain"> 관리페이지 </a>
                  </c:when>
									<c:otherwise>
										<a class="btn" id="mypageList"
											href="${contextPath}/member/mypage"> 마이페이지 </a>
									</c:otherwise>
								</c:choose>
								
							
								</li>
								<li class="nav-item active">
									<a class="btn" id="mypageList"
										href="${contextPath}/member/logout"> 로그아웃 </a>
								</li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>


		</div>

		<%-- 전체 메뉴 toggle 기능 --%>
		<div class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-center"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" id="mainmenutitle"
						href="${contextPath}">메인</a></li>
					<li class="nav-item"><a class="nav-link" id="mainmenutitle"
						href="${contextPath}/reviewBoard/list?type=0">리뷰</a></li>
					<li class="nav-item"><a class="nav-link" id="mainmenutitle"
						href="${contextPath}/eventBoard/list">행사</a></li>
					<li class="nav-item"><a class="nav-link" id="mainmenutitle"
						href="${contextPath}/FAQBoard/list">공지</a></li>
					<li class="nav-item"><a class="nav-link" id="mainmenutitle"
						href="${contextPath}/complainBoard/list?type=0">불편사항</a></li>
				</ul>
			</div>

		</div>
	</div>

	<!-- LoginModal window -->
	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel">로그인</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<form class="form-signin" method="POST" action="${contextPath}/member/login" onsubmit="return loginValidate();">
						<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" value="${cookie.saveId.value}">
						<br>
						<input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호">
						<br>
						
						
						<%-- checkbox 아이디 저장 기능 --%>
						<c:if test="${!empty cookie.saveId.value}">
							<c:set var="ch" value="checked"/>
						</c:if>
						<div class="checkbox mb-3">
							<label>
								<input type="checkbox" name="save" id="save" ${ch}> 아이디 저장
							</label>
						</div>


						<c:choose>
							<c:when test="${empty loginMember}">
								<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
							</c:when>
						</c:choose>

						<a class="btn btn-lg btn-secondary btn-block"
							href="${contextPath}/member/signUp">회원가입</a>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-secondary" href="${contextPath}/member/searchId">
						아이디 찾기
					</a>
					<a class="btn btn-secondary"
						href="${contextPath}/member/searchPw">비밀번호 찾기</a>
				</div>
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
	
	<script>
		function loginValidate(){
			if(  $("#memberId").val().trim().length == 0  ){
				swal({
					"icon" : "warning",
					"title" : "아이디를 입력해주세요"
				}).then(function(){
					$("#memberId").focus();
				});
				return false;  
			}
			
			if(  $("#memberPw").val().trim().length == 0  ){
				swal({
					"icon" : "warning",
					"title" : "비밀번호를 입력해주세요"
				}).then(function(){
					// 아이디 입력창으로 포커스 이동
					$("#memberPw").focus();
				});
				return false;  
			}
		}
	</script>
	
	
</body>

</html>
