<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>08_mypage_change_pwd</title>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/08_mypage_change_pwd.css">
    
</head>

<body>
	<jsp:include page="../../../views/common/header.jsp" />
	<jsp:include page= "menu/mypageMenu.jsp" />

    <div class="mypagemain">
        <div> <h3>회원 정보</h3> </div>
        <hr>
        <div> <h5>비밀번호 변경</h5> </div>
        <hr>
    </div>

    <div class="container">
        <div class="row my-6 justify-content-center text-center">
            <div class="col-sm-6">
                <div>
                    <form method="POST" action="changePw" onsubmit="return pwdValidate();" class="form-horizontal" role="form">
                        <div class="row my-2 mx-2">
                            <div class="col-sm-6">비밀번호 변경</div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <p>
		                                    안전한 비밀번호로 내정보를 보호하세요. <br>
		                                    다른 아이디/사이트에서 사용적 없는 비밀번호 <br>
		                                    이전에 사용한 적 없는 비밀번호 안전합니다.
                                </p>
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <input type="password" id="currentPw" name="currentPw" placeholder="현재 비밀번호">
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <input type="password" id="newPw1" name="newPw1" placeholder="새 비밀번호">
                            </div>
                            <div class="col-sm-12">
                                <input type="password" id="newPw2" name="newPw2" placeholder="새 비밀번호 확인">
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-8">자동입력방지</div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <jsp:include page="captcha/captcha.jsp"></jsp:include>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <button>변경하기</button>
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <button type="reset">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
   <jsp:include page="../../../views/common/footer.jsp" />
    
    	<script>
		// 비밀번호 유효성 검사
		function pwdValidate(){
			const regExp = /^[a-zA-Z0-9]{6,20}$/;
			
			const newPw1 = $("#newPw1").val().trim();
			const newPw2 = $("#newPw2").val().trim();
			
			// 새비밀번호가 유효하지 않거나
			// 새비밀번호, 새비밀번호 확인이 같지 않은 경우
			if( !regExp.test(newPw1)  || ( newPw1 != newPw2 )  ){
				swal({ 
					"icon" : "error",
					"title" : "비밀번호가 유효하지 않습니다"
				});
				
				return false;
			}
		}
	
	</script>
    
    
</body>
</html>