<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 신고폼</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/report.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin/reset.css">


</head>

<body>


     <!-- header.jsp 동적 include -->
	<jsp:include page="../common/header.jsp" />

	<div class="drop-recover__body">
	<div class="drop-recover__login-section">
        <h1>신고작성</h1>
        <form action="${contextPath }/report/reportPostInsert?no=${param.no}" method="POST">
        	<div class="drop-recover__login-area">
                <label for="4">도배글</label>
                <input type="radio" name="reportType" id="4" value="4" autocomplete="off" checked>
                <label for="5">광고/홍보</label>
                <input type="radio" name="reportType" id="5" value="5" autocomplete="off">
                <label for="6">저작권법위반</label>
                <input type="radio" name="reportType" id="6" value="6" autocomplete="off">
                <label for="7">성희롱/욕설/비방</label>
                <input type="radio" name="reportType" id="7" value="7" autocomplete="off">
            </div>
            <div class="drop-recover__login-area">
                <label for="reportTitle">신고제목</label>
                <input type="text" name="reportTitle" id="reportTitle" autocomplete="off" required>
            </div>

            <div class="drop-recover__login-area">
                <label for="reportTitle">신고내용</label>
                <input type="text" name="reportContent" id="reportTitle" autocomplete="off" required>
            </div>
            
            <div class="drop-recover__btn-area">
       
                <button class="drop-recover__btn" type="button">취소</button>

                <button class="drop-recover__btn" type="submit">신고하기</button>

            </div>


        </form>
        
    </div>
    </div>
	
	
  
            <!-- footer.jsp 동적 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<%-- 로그인 실패와 같은 메세지가 서버로부터 전달되어 온 경우 출력 --%>
	<c:if test="${!empty title }">
		<script>
			swal({
				"icon" : "${icon}", 
				"title" : "${title}",
				"text" : "${text}"
			})
		</script>
		
		<%-- 특정 스코프에 있는 속성(변수)를 제거할 수 잇음 --%>
		<%-- 서버로부터 전달받은 메세지를 1회 출력 후 제거 -> 반복 출력되지 않음 --%>
		<c:remove var="icon"/>
		<c:remove var="title"/>
		<c:remove var="text"/>
	</c:if>

     <script>
        
      
     </script>



</body>

</html>