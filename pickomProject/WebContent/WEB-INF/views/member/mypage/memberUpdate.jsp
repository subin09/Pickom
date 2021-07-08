<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>06_mypage_update</title>
	
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/mypage/06_mypage_update.css">
	
</head>


<body>

	<jsp:include page="../../../views/common/header.jsp" />
	<jsp:include page= "menu/mypageMenu.jsp" />
	

	<c:set var="phone" value="${fn:split( loginMember.memberPhone, '-' ) }" />

	<c:set var="addr"
		value="${fn:split( loginMember.memberAddress, ',' ) }" />

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
                    <form method="POST" action="memberUpdate" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
                    
                        <div class="row mb-3 form-row">
                            <div class="col-md-3">
                                <h6>아이디</h6>
                            </div>
                            <div class="col-md-6">
                                <h5 id="id"> ${loginMember.memberId} </h5>
                            </div>
                        </div>

                 
                        <div class="row mb-3 form-row">
                            <div class="col-md-3">
                                <h6>이름</h6>
                            </div>
                            <div class="col-md-6">
                                <h5 id="name"> ${loginMember.memberNm} </h5>
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
                            <input type="number" class="form-control phone" id="phone2" name="phone" value="${phone[1]}">
                        </div>

                        <div class="col-md-3">
                            <input type="number" class="form-control phone" id="phone3" name="phone" value="${phone[2]}">
                        </div>
                    </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="memberEmail">Email</label>
                        </div>
                        <div class="col-md-6">
                            <input type="email" class="form-control" id="email" name="email" value="${loginMember.memberEmail }">
                        </div>
                    </div>
                    <br>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="postcodify_search_button">우편번호</label>
                        </div>
                        <div class="col-md-3">
                            <input type="text" name="address" class="form-control postcodify_postcode5" value="${addr[0]}">
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
                            <input type="text" class="form-control postcodify_address" name="address" id="address1"  value="${addr[1]}">
                        </div>
                    </div>

                    <div class="row mb-3 form-row">
                        <div class="col-md-3">
                            <label for="address2">상세주소</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control postcodify_details" name="address" id="address2"  value="${addr[2]}">
                        </div>
                    </div>

                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="../../../views/common/footer.jsp" />



	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

	<script>
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});
	</script>



	<script>
		//  (function(){  })()
		// 즉시 실행 함수 : 함수가 정의 되자 마자 실행되는 함수.
		(function() {
			$("#phone1 > option").each(function(index, item) {

				if ($(item).text() == "${phone[0]}") {

					// 현재 요소에 seleted 속성을 추가
					$(item).prop("selected", true);
				}

			});
		})();

		// 회원 정보 수정 시 유효성 검사
		function memberUpdateValidate() {
			const regExp1 = /^[0-9]{3,4}$/; // 숫자 3~4글자
			const regExp2 = /^[0-9]{4}$/; // 숫자 4글자

			const ph2 = $("#phone2").val();
			const ph3 = $("#phone3").val();

			if (!regExp1.test(ph2) || !regExp2.test(ph3)) {
				swal({
					"icon" : "warning",
					"title" : "전화번호가 유효하지 않습니다.",
					"text" : "중간 자리는 3~4, 마지막 자리는 4글자로 작성해주세요."
				});

				return false;
			}

			// 이메일 유효성 검사
			const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

			const inputEmail = $("#email").val().trim();

			if (!regExp.test(inputEmail)) {
				swal({
					"icon" : "warning",
					"title" : "이메일이 유효하지 않습니다.",
					"text" : "아이디 4글자 이상의 이메일 형식으로 작성해주세요."
				});

				return false;
			}

		}
	</script>

</body>
</html>