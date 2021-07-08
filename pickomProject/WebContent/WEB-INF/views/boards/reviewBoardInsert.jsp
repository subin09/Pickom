<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰게시판 작성</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<style>
    div{
        /*border: 1px solid red;*/
    }

    
    .boardImg{
  		cursor : pointer;
		width: 200px;
		height: 200px;
		border : 1px solid #ced4da;
		position : relative;
	}
	
	.boardImg > img{
		max-width : 100%;
		max-height : 100%;
		position: absolute;
		top: 0;
		bottom : 0;
		left : 0;
		right : 0;
		margin : auto;
	}

    div.img{
        text-align: center;
      }

      input#search{
        width: 500px;
      }

      a{
        font-weight: bold !important;
        color: black !important;
        text-align: center !important;
        width: 10em !important;
      }

      a:hover{
        color: orange !important;
      }


    #inputGroup-sizing-default{
        font-weight: bold;
    }
    #content-title{
        background-color: grey;
        color: white;
    }
    #btn-cancel{
        margin-left: 45%;
    }
    
    #fileArea {
    	display: none;
    }
    
    
        .searchForm {
            display: flex;
        }
        .movieContainer {
        	display: flex;
        	justify-content: flex-start;
        	align-items: center;
            overflow-y: auto;
            height: 100px;
            width: 50%;
            margin-left: 1rem;
        }
        .movie {
            width: 100%;
            height: 20%;
            background-color: rgb(252, 252, 252);
            border: none;
        }

        .movie:hover {
            background-color: rgb(61, 61, 105);
            color: white;
            transition: 0.1s ease-in-out;
            cursor: pointer;
        }

        .movie:focus{
            background-color: rgb(61, 61, 105);
            color: white;
        }

        .genreContainer {
            display: flex;
            margin-left: 2rem;
            height: 30%;
        }

        .genreContainer span {
            margin-left: 1rem;
            background-color: rgb(61, 61, 105);
            color: white;
            padding: 0.5rem 2rem;
            border-radius: 1rem;
 
        }

        .movieList {
            display: flex;
        }
        
        .searchContainer {
            display: flex;
            width: 100%;
            align-items : center;
			flex-direction: column;
 
        }
        
        
        .searchContainer__2{
        	display: flex;
        	justify-content: center;
        	align-items: center;
        	width: 100%;
        }
        
        .reviewUpdate__title {
        	margin: 50px 0px;
        }
        
        .searchForm span{
         	font-size: 18px;
         	
         }
         
         .searchForm input {
         	height: 40%;
         	padding: 6px;
         	border-radius: 10px;
         }
         
         .searchForm input:focus {
         	height: 40%;
         	padding: 6px;
         	border-radius: 10px;
         	outline: none;
         	border: 3px solid rgba(0,0,0,0.7);
         	transition: 0.1s ease-in-out;
         }
         

         
         .searchForm button {
         	padding: 3.5px 10px;
		    border-radius: 8px;
		    background-color: white;
		    border: 3.1px solid rgb(61, 61, 105);
		    color:rgb(61, 61, 105);
		    font-weight: bold;
         }
         
         .searchForm button:hover {
         	color: white;
         	font-weight:: bold;
         	background-color: rgb(61, 61, 105);
         	transition: 0.1s ease-in-out;
         }
        
        .searchForm {
        	width: 50%;
        	display: flex;
        	justify-content: flex-end;
        	align-items: center;
        	}
         #movieCategory {
         	width: 50%;
         	height: 40%;
         	padding: 4px;
         	border-radius: 10px;
         }
         
         #movieCategory:focus {
         	outline : none;
         }
         .submitBtn {
         	display: flex;
         	justify-content: center;
         }
         
         #content-title{
         	padding: 10px;
         }
         
         .updateform-group{
         	margin: 10px 0px 0px 0px;
         }
</style>
<body>
    <!-- header.jsp 동적 include -->
	<jsp:include page="../common/header.jsp" />
  
    <div class="container">
        <h1 class="reviewUpdate__title">리뷰 게시판 작성</h1>
        <form action="${contextPath}/reviewBoardDML/insert?type=${param.type}" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return boardValidate();">

           	<c:if test="${ !empty categoryList}"> 
					<div class="mb-2">
						<label class="input-group-addon mr-3 insert-label">카테고리</label> 
						<select	class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
						
							<%-- 관리자가 아닌 일반 회원일 때 공지 카테고리 안 뜨게 작업--%>
							<c:choose>
								<c:when test="${loginMember.memberGrade == 'G'}">
										<option value="1" selected>일반</option>
								</c:when>
								<c:otherwise>
								
									<option value="2" selected>공지</option>
									
								</c:otherwise>
							</c:choose>
						</select>
					</div>
			</c:if>
            <hr>
            
            
            
            
            
            <!-- 영화 검색 ajax 부분  -->
            <div class="searchContainer">
            
			       <div class="searchContainer__1">'
				        <div class="genreContainer">
				            <span># 영화장르</span>
				        </div>
			       </div>
			       <div class="searchContainer__2">
			       		<div class="searchForm">
	
			                <span>영화검색 : <span>
			                <input type="text" placeholder="키워드를 입력하세요" id="keyword">
			                <button type="button" onclick="showMovies();">검색</button>
	
			        	</div>
			
				        <div class="movieContainer">
				             <select name="movieNo" id="movieCategory">
				                <option disabled value="default">검색된 영화가 없습니다.</option>
				
				            </select>
				        </div>
			       
			       </div>

   			</div>
   			
   			
   			
   			
   			
   			
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
                </div>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
                id="boardTitle" name="boardTitle">
              </div>
            <hr>
            
       

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
					<div class="mr-2 boardImg" id="contentImgArea0">
						<img id="contentImg0">
					</div>
					<div class="mr-2 boardImg" id="contentImgArea1">
						<img id="contentImg1">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea2">
						<img id="contentImg2">
					</div>

					<div class="mr-2 boardImg" id="contentImgArea3">
						<img id="contentImg3">
					</div>
				</div>


				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<!--  multiple 속성
						- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 (파일 여러개 선택 가능)
					 -->
					<input type="file" id="img0" name="img0" onchange="LoadImg(this,0)"> 
					<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)"> 
					<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)"> 
					<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)">
				</div>
				
				
				
            <div class="updateform-group" id="content-title">
                <span>내용<span>
            </div>
                <textarea class="form-control exampleFormControlTextarea1" id="boardContent" rows="20" name="boardContent"></textarea>
                
             
             <div class="submitBtn">
             
	              <input class="btn btn-primary" id="" type="submit" value="등록">
             </div>
        </form>

      </div>
      <!-- footer.jsp 동적 include -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<%-- 로그인 실패와 같은 메세지가 서버로부터 전달되어 온 경우 출력 --%>
	<c:if test="${!empty title }">
		<script>
			swal({
				"icon" : "${icon}", 
				"title" : "${title}",
				"text" : "${text}"
			})
		</script>
		
		<%-- 특정 스코프에 있는 속성(변수)를 제거할 수 잇음 --%>
		<%-- 서버로부터 전달받은 메세지를 1회 출력 후 제거 -> 반복 출력되지 않음 --%>
		<c:remove var="icon"/>
		<c:remove var="title"/>
		<c:remove var="text"/>
	</c:if>
      <script>
		
		(function printToday() {
			// 오늘 날짜 출력 
			var today = new Date();
			var month = (today.getMonth() + 1);
			var date = today.getDate();

			var str = today.getFullYear() + "-"
					+ (month < 10 ? "0" + month : month) + "-"
					+ (date < 10 ? "0" + date : date);
			$("#today").html(str);
		})();

		// 유효성 검사 
		function boardValidate() {
			if ($("#boardTitle").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return false;
			}

			if ($("#boardContent").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#content").focus();
				return false;
			}
			
			if($("#movieCategory").val() == null) {
				alert("영화를 선택해주세요.");
				$("#keyword").focus();
				return false;
			}
		}

		// 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
		$(function() {
			$(".boardImg").on("click", function() {
				var index = $(".boardImg").index(this);
				$("#img" + index).click();
			});

		});

		
		// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
		function LoadImg(value, num) {
			if (value.files && value.files[0]) {
				var reader = new FileReader();
				// 자바스크립트 FileReader
				// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체

				reader.readAsDataURL(value.files[0]);
				// FileReader.readAsDataURL()
				// 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.

				// FileReader.onload
				// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
				reader.onload = function(e) {
					//console.log(e.target.result);
					// e.target.result
					// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)

					$(".boardImg").eq(num).children("img").attr("src",
							e.target.result);
				}

			}
		}
		
		function showMovies(){
			const keyword = $("#keyword").val();
			if(keyword.trim() == ""){
				swal("검색 내용 작성 후 클릭해주세요");
			} else {
				$.ajax({
					url : "${contextPath}/reviewBoard/showMovies", 
					type : "POST", 
					data : {"keyword" : keyword}, 
					dataType : "JSON",
					success : function(movieList) {

						$("#movieCategory").html(""); // 기존 정보 초기화
			 	         
			 	        var button1 = $("<option>").addClass("movie").text("검색된 영화를 확인").attr("value", "0").attr("disabled", true).attr("selected", true);
						
						console.log(button1.val());
			 	       	$("#movieCategory").append(button1);
			 	         $.each(movieList, function(index, item){
							console.log(item);
			 	            var button = $("<option>").addClass("movie").text(item.movieTitleKo).attr("value", item.movieNo);
			 	         	
			 	           $("#movieCategory").append(button);

				 	          
				 	            
			 	         });
			 	         
					}, 
					error : function() {
						swal("검색하신 영화가 존재하지 않습니다.");
					}
						
				})
			}
			
		}
		
		
		$("#movieCategory").change(function() {
		    const movieNo = $(this).val();
		    $.ajax({
				url : "${contextPath}/reviewBoard/showGenre", 
				type : "POST", 
				data : {"movieNo" : movieNo}, 
				dataType : "JSON",
				success : function(gnList) {

					$(".genreContainer").html(""); // 기존 정보 초기화
		 	         
		 	         $.each(gnList, function(index, item){


		 	            var span = $("<span>").text("# "+item.movieGenreNM);
		 	         	
		 	           $(".genreContainer").append(span);
		 	      
			 	    
			 	            
		 	         });
				}, 
				error : function() {
					swal("이 영화는 장르가 존재하지 않습니다.");
				}
					
			})
		});
		
		
		
	</script>
</body>

</html>