<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>07_mypage_profile</title>
    
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/07_mypage_profile.css">
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>


<body>
	<jsp:include page="../../../views/common/header.jsp" />
	
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
                    <form method="POST" action="profilefn" enctype="multipart/form-data" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                        <div class="row mb-3 form-row">
                            <div class="col-sm-3 profile-outter">
                                <div class="row-sm-2 profile-inner">
                                    <label for="profilepicture">프로필 사진</label> 
                                </div>
                            </div>
                            <div class="col-sm-9 text-center">
                            	
                            	<div class="profile thubnail" id="titleImgArea">
										<img id="profileImgIn">
								</div>
                                <div id="fileArea">
									<input type="file" id="profileImg0" name="profileImg0" onchange="LoadImg(this,0)"> 
								</div>
                                
                                <div class="row mb-3 form-row justify-content-center">
                                    <div class="col-sm-2">
										<button class="btn btn-primary profileBtn" type="button">첨부</button>
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
                                <input type="text" class="form-control" id="memberNickNm" name="memberNickNm">
                            </div>
                        </div>

                        <div class="row mb-3 form-row"> <hr> </div>
                        
                        <div class="row mb-3 form-row">
                       
                            <div class="col-sm-3"></div>
                       
                            <div class="col-sm-9">
                                <div class="row text-center justify-content-center">
                                    <div class="col-sm-2">
                                        <button class="btn btn-primary" type="submit">수정</button>
                                    </div>
                                    <div class="col-sm-2">
                                        <button class="btn btn-primary profileResetBtn" type="reset">삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
	<jsp:include page="../../../views/common/footer.jsp" />
	
	<script>
	$(function() {
			$(".profile").on("click", function() {
				$("#profileImg" + 0).click();
			});
		});

    $(function() {
        $(".profileBtn").on("click", function() {
            $("#profileImg" + 0).click();
        });
    });
    
    $(function(){
        $(".profileResetBtn").on("click", function(){
            var srcReset = "";
            $("#profileImgIn").attr("src", srcReset);
        });
    });

	function LoadImg(value, num) {
			if (value.files && value.files[0]) {
				var reader = new FileReader();
	
				reader.readAsDataURL(value.files[0]);

				reader.onload = function(e) {

					$(".profile").eq(num).children("img").attr("src", e.target.result);
                    console.log(e.target.result);
				}

			}
		}
	</script>
</body>
</html>