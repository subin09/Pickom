<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>mypage_main</title>
  
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/05_mypage_main.css">
  
</head>
     
<body>

	<jsp:include page="../../../views/common/header.jsp" />

  <div class="mypageMain">
    <div> <h3>회원 정보</h3> </div>
    <hr>
    <div> <h5></h5> </div>    
    <hr>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-sm-6">
        <div class="card">
          <div class="card-body text-center mypage-menu">
            <h5 class="card-title">회원정보</h5>
              <div class = "col-sm-12">
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                data-target="#multiCollapse1" aria-expanded="false" aria-controls="multiCollapse1">더 보기</button>
              </div>
              <div class="collapse multi-collapse" id="multiCollapse1">
                <p class="card-text">
			                중요 내용을 알려드릴 때 사용하는 연락처 정보입니다. <br>
			                보다 안전한 정보 보호를 위해 등록된 연락처의 일부만 보여드립니다. <br>
			                이름 및 정확한 연락처 정보는 수정 화면에서 확인 가능합니다.
                </p>
              </div>
            <a type="GET" href="${contextPath}/member/mypage/memberUpdate" class="btn btn-primary">수정</a>
          </div>
        </div>
      </div>

      <div class="col-sm-6">
        <div class="card">
          <div class="card-body text-center mypage-menu">
            <h5 class="card-title">프로필</h5>
              <div class = "col-sm-12">
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                data-target="#multiCollapse2" aria-expanded="false" aria-controls="multiCollapse2">더 보기</button>
              </div>
            <div class="collapse multi-collapse" id="multiCollapse2">
              <p class="card-text">
             		 '나'를 표현하는 프로필 정보입니다. <br>
              		수정 화면에서 프로필 사진과 별명을 변경하세요. <br>
              </p>
            </div>
            <a type="GET" href="${contextPath}/member/mypage/profile" class="btn btn-primary">수정</a>
          </div>
        </div>
      </div>

      <div class="col-sm-6">
        <div class="card">
          <div class="card-body text-center mypage-menu">
            <h5 class="card-title\">비밀번호 변경</h5>
              <div class = "col-sm-12">
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                data-target="#multiCollapse3" aria-expanded="false" aria-controls="multiCollapse3">더 보기</button>
              </div>
            <div class="collapse multi-collapse" id="multiCollapse3">
              <p class="card-text ">
			              로그인 시 사용하는 비밀번호를 변경하거나 <br>
			              안전한 로그인을 위한 2단계 인증 기능을 설정할 수 있습니다. <br>
			              주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.
              </p>
            </div>
            <a type="GET" href="${contextPath}/member/mypage/changePw" class="btn btn-primary">수정</a>
          </div>
        </div>
      </div>

      <div class="col-sm-6">
        <div class="card">
          <div class="card-body text-center mypage-menu">
            <h5 class="card-title">회원탈퇴</h5>
              <div class = "col-sm-12">
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                data-target="#multiCollapse4" aria-expanded="false" aria-controls="multiCollapse4">더 보기</button>
              </div>
            <div class="collapse multi-collapse" id="multiCollapse4">
              <p class="card-text">
			              회원 탈퇴는 약관 동의 및 현재 비밀번화 확인 후<br>
			              탈퇴 진행이 가능합니다.
              </p>
            </div>
            <a type="GET" href="${contextPath}/member/mypage/secession" class="btn btn-primary">탈퇴하기</a>
          </div>
        </div>
      </div>
    </div>
  </div>
    <jsp:include page="../../../views/common/footer.jsp" />
</body>
</html>