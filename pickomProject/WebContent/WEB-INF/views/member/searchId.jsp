<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/find/findId.css" >
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
        <h1>아이디 찾기</h1>
        <form method="POST" action="searchId" class="search-form" name="searchIdForm">
            <div class="change-area">
                <label for="memberNm">이름</label><br>
                <input type="text" id="memberNm" name="memberNm" 
                placeholder="회원님의 이름을 입력해주세요" autocomplete="off" required>
            </div>

            <div class="change-area">
                <label for="memberEmail">이메일</label><br>
                <input type="email" id="memberEmail" name="memberEmail" 
                placeholder="가입시 등록하신 이메일 주소를 입력해주세요" autocomplete="off" required>
            </div>
            
     
            

            <button class="search" type="submit" onclick="">확인</button>
        </form>
        <jsp:include page="../common/footer.jsp"></jsp:include>
        
    </div>
</body>
</html>