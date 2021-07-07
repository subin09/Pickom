<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>09_mypage_secession</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<style>
    fieldset#secessionreasonCheck{
        text-align: left;
    }
</style>

<body>

	<jsp:include page="../../../views/common/header.jsp" />
	<jsp:include page= "menu/mypageMenu.jsp" />

	
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
            <form  method="POST" action="secession2" onsubmit="return secessionValidate();" class="form-horizontal" role="form">
                <div class="form-group my-5 mx-2">
                       
                        <fieldset class="form-group row my-5 mx-2" id="secessionreasonCheck">
                                <legend class="col-form-label col-sm-2 float-sm-left m-auto ">탈퇴 사유</legend>
                            <div class="col-sm-10">
                                
                                <div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="secessionreason" id="secessionreason1" value="option1" checked>
                                    <div>                                    
                                        <label class="form-check-label" for="secessionreason1">
                                            	커뮤니티 이용 어려움
                                        </label>
                                    </div>
                                </div>
                                
                                <div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="secessionreason" id="secessionreason2" value="option2">
                                    <div>                                    
                                        <label class="form-check-label" for="secessionreason2">
                                           		커뮤니티 이용 어려움1
                                        </label>
                                    </div>
                                </div>
                                
                                <div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="secessionreason" id="secessionreason3" value="option3">
                                    <div>                                    
                                        <label class="form-check-label" for="secessionreason3">
                                           	커뮤니티 이용 어려움2
                                        </label>
                                    </div>
                                </div>
                                
                                <div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="secessionreason" id="secessionreason4" value="option4">
                                    <div>                                    
                                        <label class="form-check-label" for="secessionreason4">
                                            	커뮤니티 이용 어려움3
                                        </label>
                                    </div>
                                </div>
                           		
                           		<div class="form-check my-4">
                                        <input class="form-check-input" type="radio" name="secessionreason" id="secessionreason5" value="input">
                                    <div>                                    
                                        <input class="form-check-label">
                                            	커뮤니티 이용 어려움3
                                    </div>
                                </div>
                            </div>
                        </fieldset>

                        
                        <div class="form-group row mx-2">
                            <p class="col-sm-12 mx-2">
                               	 회원 탈퇴를 원할시 다음 문구를 입력해주세요.
                            </p>
                            <label class="col-sm-12 mx-2" for="secessionAgree">
                                "회원 탈퇴 신청하겠습니다."
                            </label>
                        </div>
                        <div class="form-group col-sm-12">
                            <input class="col-sm-12" type="text" id="secessionAgree" name="secessionAgree">
                        </div>
                        
                        <hr>
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="form-group pull-left">
									<label class="control-label"> 회원 탈퇴 약관 </label>
									<div class="col-xs-12">
										<textarea class="form-control" readonly rows="10" cols="100">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
</textarea>
									</div>
									<div class="checkbox pull-right">
										<div class="custom-checkbox">
											<div class="form-check">
												<input type="checkbox" name="agree" id="agree"
													class="form-check-input custom-control-input"> <br>
												<label class="form-check-label custom-control-label"
													for="agree">위 약관에 동의합니다.</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<hr class="mb-4">
                        <div class="form-group col">
                            <button type="submit"> 탈퇴신청 </button>
                        </div>
                </div>
            </form>
        </div>
    </div>

	<jsp:include page="../../../views/common/footer.jsp" />

	<c:if test="${!empty title }">
		<script>
			swal({
				"icon" : "${icon}",
				"title" : "${title}",
				"text" : "${text}"
			});
		</script>


		<c:remove var="icon" />
		<c:remove var="title" />
		<c:remove var="text" />
	</c:if>
	<script>
		// 약관 동의가 체크 되었을 때에만 탈퇴 진행
		function secessionValidate(){
			if( $("#agree:checked").length == 0){
				swal({"icon" : "info", "title" : "약관 동의를 체크해주세요."})
				return false;
			}
			if( !$("#agree").prop("checked") ){
				swal({"icon" : "info", "title" : "약관 동의를 체크해주세요."})
				return false;
			}
			
			
		}
	</script>
</body>
</html>