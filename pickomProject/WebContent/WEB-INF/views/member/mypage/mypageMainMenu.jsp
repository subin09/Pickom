<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>04_mypage_mainmenu</title>

    <link rel="stylesheet" type="text/css" href="/02_file/css/04_mypage_mainmenu.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">


    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>



<body>
    <div class="mypagemain">
        <div class="text-center" id="mypagemain-menu">
            <div class="d-flex bd-highlight">
                <div class="d-flex p-3 bd-highlight" id="mypagemain-menu"><a href="${contextPath}">| 회원정보 |</a></div>
                <div class="d-flex p-3 bd-highlight" id="mypagemain-menu"><a href="${contextPath}">| 작성글 관리 |</a></div>
                <div class="d-flex p-3 bd-highlight" id="mypagemain-menu"><a href="${contextPath}">| 불편사항(고객센터) |</a></div>
            </div>
        </div>
        <hr>
        <div> <h3>회원 정보</h3> </div>
        <hr>
        <div> <h5>프로필 수정</h5> </div>
        <hr>
      </div>
</body>
</html>