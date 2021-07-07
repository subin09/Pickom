<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>mypageMenu</title>

<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/04_mypage_mainmenu.css">

</head>

<body>
    <div class="mypagemain">
        <div class="text-center" id="mypagemain-menu">
        	<hr>
            <div class="d-flex bd-highlight">
                <div class="d-flex p-3 bd-highlight" id="mypagemain-menu">
                	<a href="${contextPath}/member/mypage">
						<span id="mypageMenu">
							| 회원정보 |
						</span>
						</a>
				</div>
                <div class="d-flex p-3 bd-highlight" id="mypagemain-menu">
               		 <a href="${contextPath}">
	                	<span id="mypageMenu">
	                		| 불편사항(고객센터) |
	                	</span>
                	</a>
                </div>
            </div>
            <hr>
        </div>
      </div>
</body>
</html>