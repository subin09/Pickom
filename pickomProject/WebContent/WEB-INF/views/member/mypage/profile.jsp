<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>07_mypage_profile</title>
    
    <link rel="stylesheet" type="text/css" href="/02_file/css/07_mypage_profile.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>


<body>

    <div class="mypagemain">
        <div> <h3>회원 정보</h3> </div>
        <hr>
        <div> <h5>프로필 수정</h5> </div>
        <hr>
    </div>

    <div class="container text-center">
        <div class="row my-6">
            <div class="col-sm-8">
                <div class="bg-white rounded shadow-sm container p-3">
                    <form method="POST" action="update" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                        <div class="row mb-3 form-row">
                            <div class="col-sm-3 profile-outter">
                                <div class="row-sm-2 profile-inner">
                                    <label for="profilepicture">프로필 사진</label> 
                                </div>
                            </div>
                            <div class="col-sm-9 text-center">
                                <div class="col-sm-3 mb-3 mt-3 profile-inner" >
                                    <a href="#profilepicture">
                                        <img class="profile-inner" src="/02_file/img/mypage.jpg" width="100px" height="auto" alt="">
                                    </a>
                                </div>
                                <div class="row mb-3 form-row justify-content-center">
                                    <div class="col-sm-2">
                                    <button class="btn btn-primary btn-lg btn-block" id="profilepicture" type="button">첨부</button>
                                    </div>
                                <div class="col-sm-2">
                                <button class="btn btn-primary btn-lg btn-block" type="submit">삭제</button>
                                </div>
                                </div>
                            </div>
                        </div>

                        <div class="row my-6 text-center form-row">
                            <div class="col-sm-3 profile-outter">
                                <div class="profile-inner">
                                    <label for="nickname">닉네임*</label>
                                </div>
                            </div>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="nickname" name="nickname" value="">
                            </div>
                        </div>
                        <div class="row mb-3 form-row"> <hr> </div>
                        
                        <div class="row form-row text-center">
                            <button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>