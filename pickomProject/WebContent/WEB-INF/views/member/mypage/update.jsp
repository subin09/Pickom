<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>06_mypage_update</title>

    <link rel="stylesheet" type="text/css" href="/02_file/css/06_mypage_update.css">
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
        <div> <h5>회원 정보 수정</h5> </div>
        <hr>
    </div>
    <div class="container">
        <div class="row my-5">    
            <div class="col-sm-8">

                <div class="bg-white rounded shadow-sm container p-3">
                    <form method="POST" action="update" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                    
                        <div class="row mb-3 form-row">
                            <div class="col-md-3">
                                <h6>아이디</h6>
                            </div>
                            <div class="col-md-6">
                                <h5 id="id">  </h5>
                            </div>
                        </div>

                 
                        <div class="row mb-3 form-row">
                            <div class="col-md-3">
                                <h6>이름</h6>
                            </div>
                            <div class="col-md-6">
                                <h5 id="name"> </h5>
                            </div>
                        </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="phone1">전화번호</label>
                        </div>
                        <div class="col-md-3">
                            <select class="custom-select" id="phone1" name="phone">
                                <option>010</option>
                                <option>011</option>
                                <option>016</option>
                                <option>017</option>
                                <option>019</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <input type="number" class="form-control phone" id="phone2" name="phone" value="">
                        </div>

                        <div class="col-md-3">
                            <input type="number" class="form-control phone" id="phone3" name="phone" value="">
                        </div>
                    </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="memberEmail">Email</label>
                        </div>
                        <div class="col-md-6">
                            <input type="email" class="form-control" id="memberEmail" name="memberEmail" value="">
                        </div>
                    </div>
                    <br>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="postcodify_search_button">우편번호</label>
                        </div>
                        <div class="col-md-3">
                            <input type="text" name="address" class="form-control postcodify_postcode5" value="">
                        </div>
                        <div class="col-md-3">
                            <button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
                        </div>
                    </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="address1">도로명 주소</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control postcodify_address" name="address" id="address1"  value="">
                        </div>
                    </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="address2">상세주소</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control postcodify_details" name="address" id="address2"  value="">
                        </div>
                    </div>

                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

    <script>
  
        $(function () {
            $("#postcodify_search_button").postcodifyPopUp();
        });

    </script>

</body>
</html>