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
        <div class="row my-6 justify-content-center text-center">
            <form  metho="POST" action="" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                <div class="form-group my-5 mx-2">
                        <fieldset class="form-group row my-5 mx-2" id="secessionreasonCheck">
                                <legend class="col-form-label col-sm-2 float-sm-left m-auto ">Radios</legend>
                            <div class="col-sm-10">
                                <div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
                                    <div>                                    
                                        <label class="form-check-label" for="gridRadios1">
                                            First radioFirst radioFirst radioFirst radioFirst radioFirst radioFirst radioFirst radioFirst radioFirst radio
                                        </label>
                                    </div>
                                    
                                </div>
                                <div class="form-check my-4">
                                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
                                    <label class="form-check-label" for="gridRadios2">
                                    Second radio
                                    </label>
                                </div>
                                <div class="form-check my-4">
                                    <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3">
                                    <label class="form-check-label" for="gridRadios3">
                                    Third disabled radio
                                    </label>
                                </div>
                            </div>
                        </fieldset>

                        <div class="form-group row mx-2">
                            <label class="col-sm-2 col-form-label" for="secessionreason">기타 사유</label>
                            <input class="form-control col-sm-10" type="text" id="secessionreason" name="secessionreason" placeholder="기타 탈퇴 사유 입력">
                        </div>
                        <div class="form-group row mx-2">
                            <p class="col-sm-12 mx-2">
                                회원 탈퇴를 원할시 다음 문구를 입력해주세요.
                            </p>
                            <label class="col-sm-12 mx-2" for="secessioninput">
                                "회원 탈퇴 신청하겠습니다."
                            </label>
                        </div>
                        <div class="form-group col-sm-12">
                            <input class="col-sm-12" type="text" id="secessioninput" name="secessioninput">
                        </div>
                        <div class="form-group col">
                            <button type="submit"> 탈퇴신청 </button>
                        </div>
                </div>
            </form>
        </div>
    </div>


</body>
</html>