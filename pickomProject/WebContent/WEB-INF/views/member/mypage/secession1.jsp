<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>09_mypage_secession</title>

    <link rel="stylesheet" type="text/css" href="/02_file/css/09_mypage_secession.css">
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

    fieldset#secessionreasonCheck{
        text-align: left;
    }
</style>

<body>
    <div class="mypagemain">
        <div> <h3>회원정보</h3> </div>
        <hr>
        <div> <h5>회원탈퇴</h5> </div>
        <hr>
        <div>
            <p>
                안내사항
            </p>
        </div>
        <hr>
    </div>

    <div class="container">
        <div class="row my-5 justify-content-center text-center">
            <form  metho="POST" action="" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                <div class="form-group my-5 mx-2">
                    <diV class="from-group my-5 mx-4">
                        <p>
                            <br><br><br><br>
                            회원 탈퇴 신청 전에 본인 인증을 위한 현재 사용중인 비밀번호를 한 번 더 입력후 탈퇴 진행이 가능합니다.
                            <br><br><br><br>
                        </p>
                    </diV>
                    <div class="form-group mx-4">
                        <input type="text" id="secessioninput" name="secessioninput">
                    </div>
                    <div class="form-group mt-5 mx-4">
                        <button type="submit"> 탈퇴신청 </button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</body>
</html>