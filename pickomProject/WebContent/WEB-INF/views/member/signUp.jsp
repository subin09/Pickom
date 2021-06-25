<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<title>회원가입</title>
<style>
	/* number 태그 화살표 제거 */
	input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
		{
		-webkit-appearance: none;
		margin: 0;
	}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
		<!-- 
			상대경로에서 사용하는 기호
			(빈칸) : 현재 폴더
			/      : 하위 폴더 
			..     : 상위 폴더 
		 -->
	<form action="signUp" method="POST" name ="signUpForm" onsubmit="return validate();">
		<label for="id">아이디*</label>
        <input type="text" id="id" name="id">
        <div>
            <span id="checkId">&nbsp;</span>
        </div>
		<!-- <button>중복확인</button><br> -->
		
		
        <label for="pw">비밀번호*</label>
        <input type="password" id="pw1" name="pw1"><br>
        <div>
            <span id="checkPwd1">&nbsp;</span>
        </div>
    
        <label for="pw">비밀번호 확인*</label>
        <input type="password" id="pw2" name="pw2"><br>
        <div>
            <span id="checkPwd2">&nbsp;</span>
        </div>
    
        <label for="nickname">닉네임*</label>
        <input type="text" id="nickname" name="nickname"><br>
        <div>
            <span id="checkNickname">&nbsp;</span>
        </div>
    
        <label for="email">이메일*</label>
        <input type="email" id="email" name="email"><br>
        <div>
            <span id="checkEmail">&nbsp;</span>
        </div>
    
        <label for="name">이름*</label>
        <input type="text" id="name" name="name"><br>
        <div>
            <span id="checkName">&nbsp;</span>
        </div>
        
        <label for="phone">휴대폰*</label>
        <select class="custom-select" id="phone1" name="phone" required>
            <option>010</option>
            <option>011</option>
            <option>016</option>
            <option>017</option>
            <option>019</option>
        </select>
        <input type="number" name="phone" class="phone" id="phone2"><br>
        <input type="number" name="phone" class="phone"  id="phone3"><br>
        <div>
            <span id="checkPhone">&nbsp;</span>
        </div>
		
		<br>
		<div>
            <label for="postcodify_search_button">우편번호</label>
	    </div>
	    <div>
	        <input type="text" name="address" class="form-control postcodify_postcode5">
	    </div>
	    <div>
	        <button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
	    </div>
	
	    <div>
	        <label for="address1">도로명 주소</label>
	    </div>
	    <div>
	        <input type="text" class="form-control postcodify_address" name="address" id="address1">
	    </div>
	
	    <div>
	        <label for="address2">상세주소</label>
	    </div>
	    <div>
	        <input type="text" class="form-control postcodify_details" name="address" id="address2">
        </div>
        
        <hr class="mb-4">
        
		<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>

  
		<br>
		<br>
		    
	</form>

    <!--유효성 검사하는 스크립트 !! --> 
    <script src="${contextPath }/resources/js/member.js"></script>

    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
		// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
		$(function () {
			$("#postcodify_search_button").postcodifyPopUp();
		});
	</script>
	
</body>
</html>