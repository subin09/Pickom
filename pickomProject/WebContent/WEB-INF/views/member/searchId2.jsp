<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!--css  -->
	<link rel="stylesheet"  type="text/css" href="${contextPath}/resources/css/member/find/findId2.css" >
	<title></title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	  <div class="end-content">
        <form method="GET" action="searchId2" class="error-form" name="searchIdForm2">
        <h1>아이디 찾기</h1>
            <div class="error-area">
                <h5>고객님의 아이디찾기가 완료되었습니다</h5>
                <h3></h3><br>
                <div class="finded">
                <p>아이디 :</p>
                <span class="end"><strong>${loginId.memberId}</strong></span>
                </div>
                <button class="login-end" onclick="location.href=main.jsp">로그인하기</button>
            </div>
        </form>
    </div>
     <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>