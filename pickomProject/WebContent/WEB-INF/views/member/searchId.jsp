<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/member/find/findId.css" >
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
 <div class="id-content">
       
        <form action="searchId" method="POST" name="change-form">
       		<h1>아이디 찾기</h1>
            <div class="change-area">
                <label for="memberNm">이름</label><br>
                <input type="text" id="memberNm" name="memberNm" 
                placeholder="가입시 등록하신 이름를 입력해주세요" autocomplete="off" required>
            </div>
            <div class="change-area">
                <label for="memberEmail">이메일</label><br>
                <input type="email" id="memberEmail" name="memberEmail" 
                placeholder="가입시 등록하신 이메일를 입력해주세요" autocomplete="off" required>
            </div>

            <button>확인</button>
        </form>
    </div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
    
</body>
</html>