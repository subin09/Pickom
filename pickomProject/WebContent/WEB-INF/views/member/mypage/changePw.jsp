<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>08_mypage_change_pwd</title>

    <link rel="stylesheet" type="text/css" href="/02_file/css/08_mypage_change_pwd.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<style>
    div{
        border: 1px solid red;
    }

</style>

<body>
	<jsp:include page="../../../views/common/header.jsp" />

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
                    <form method="POST" action="update" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
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
                                <input type="text" placeholder="현재 비밀번호">
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <input type="text" placeholder="새 비밀번호">
                            </div>
                            <div class="col-sm-12">
                                <input type="text" placeholder="새 비밀번호 확인">
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-8">인증글자</div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-8">
                                <img src="" alt="no file">
                            </div>
                            <div class="col-sm-4">
                                <div class="col-sm-12">
                                    <button>새로고침</button>
                                </div>
                                <div class="col-sm-12">
                                    <button>음성</button>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <input type="text" placeholder="인증 글자 입력란">
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <button>확인</button>
                            </div>
                        </div>
                        <div class="row mb-2 mx-2">
                            <div class="col-sm-12">
                                <button>취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
   <jsp:include page="../../../views/common/footer.jsp" />
    
</body>
</html>