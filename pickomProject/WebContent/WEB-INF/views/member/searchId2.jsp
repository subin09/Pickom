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
	  <div class="content">
        <h1>아이디 찾기</h1>
        <form method="GET" action="searchId2" class="error-form" name="searchIdForm2">
            <div class="error-area">
                <h3>고객님의</h3>
                <h3>아이디찾기가 완료되었습니다</h3><br>
                <p>아이디 :${loginId.memberId}</p>
                <button>로그인하기</button>
            </div>
        </form>
    </div>
</body>
</html>