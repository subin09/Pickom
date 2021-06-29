<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>비밀번호찾기</title>
	<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/find/findPw.css" >
</head>
<body>
	 <jsp:include page="../common/header.jsp"></jsp:include>
	 <div class="content">
        <h1>비밀번호 찾기</h1>
        <form action="searchPw" method="POST" name="change-form">
            <div class="change-area">
                <label for="memberId">아이디</label><br>
                <input type="id" id="memberId" name="id" 
                placeholder="가입시 등록하신 이메일 주소를 입력해주세요" autocomplete="off" required>
            </div>

            <div class="change-area">
                <label for="email">이메일</label><br>
                <input type="email" id="email" name="name" 
                placeholder="가입시 등록하신 이메일 주소를 입력해주세요" autocomplete="off" required>
            </div>

            <button>확인</button>
        </form>
    </div>
</body>
</html>