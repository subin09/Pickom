<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>09_mypage_secession</title>
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/09_secession1.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>


<body>
	<jsp:include page="../../../views/common/header.jsp" />
	<jsp:include page= "menu/mypageMenu.jsp" />


    <div class="mypagemain">
        <div> <h3>회원정보</h3> </div>
        <hr>
        <div> <h5>회원탈퇴</h5> </div>
        <hr>
        <div>
            <p id="secession-p">
              	  안내사항
            </p>
        </div>
        <hr>
    </div>

    <div class="container">
        <div class="row my-5 justify-content-center text-center">
            <form  method="POST" action="secession" class="form-horizontal" role="form">
                <div class="bg-white rounded shadow-sm container p-3 form-group my-5 mx-2">
                    <diV class="from-group my-5 mx-4">
                        <p>
                            <br><br><br><br>
                            	회원 탈퇴 신청 전에 본인 인증을 위한 현재 사용중인 비밀번호를 한 번 더 입력후 탈퇴 진행이 가능합니다.
                            <br><br><br><br>
                        </p>
                    </diV>
                    <div class="form-group mx-4">
                        <input type="password" id="secessionPw" name="secessionPw">
                    </div>
                    <div class="form-group mt-5 mx-4">
                        <button type="submit"> 탈퇴신청 </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

	<jsp:include page="../../../views/common/footer.jsp" />
	
		<c:if test="${!empty title }">
		<script>
			swal({
				"icon" : "${icon}",
				"title" : "${title}",
				"text" : "${text}"
			});
		</script>


		<c:remove var="icon" />
		<c:remove var="title" />
		<c:remove var="text" />
	</c:if>
</body>
</html>