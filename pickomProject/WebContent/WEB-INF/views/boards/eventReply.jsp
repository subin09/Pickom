<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Bootstrap core JS -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
 
<!-- sweetalert API 추가 --> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>



<style>
/*댓글*/
.replyWrite>table {
	margin-top: 10px;
}

.rWriter {
	display: inline-block;
	vertical-align: top;
	font-size: 1.2em;
	font-weight: bold;
}

.rDate {
	display: inline-block;
}

.rContent, .replyBtnArea {
	height: 100%;
	width: 100%;
}

.replyBtnArea {
	text-align: right;
}

.replyUpdateContent {
	resize: none;
	width: 100%;
}

.reply-row {
	border-top: 1px solid #ccc;
	padding: 15px 0;
}

</style>

<div id="reply-area ">
	<!-- 댓글 작성 부분 -->
	<div class="replyWrite" style="text-align : center;	">
		<table align = "center">
			<tr>
				<td id="replyContentArea">
				<textArea rows="3" id="replyContent" style = "width:600px; resize: none;"></textArea>
				</td>
				<td id="replyBtnArea">
					<button class="btn btn-primary" id="addReply" onclick="addReply();">
						댓글<br>등록
					</button>
				</td>
			</tr>
		</table>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="replyList mt-5 pt-2" id="commentList">
		<ul id="replyListArea">
			<c:forEach items="${rList}" var="reply">
			
				<li class="shadow p-3 mb-5 bg-white rounded">
					<div>
						<p class="rWriter">${reply.memberNm}</p>

						<br>

						[<p class="rDate">
							작성일 :
							<fmt:formatDate value="${reply.eventReplyCreateDt}"
								pattern="yyyy년 MM월 dd일 HH:mm" />
						</p>]


					</div>

					<p class="rContent">${reply.eventReplyContent}</p>
					<c:if test="${reply.memberNo == loginMember.memberNo || loginMember.memberNo==1}">
						<div class="replyBtnArea">
							<button class="btn btn-primary btn-sm ml-1" id="updateReply"
								onclick="showUpdateReply(${reply.eventReplyNo}, this)">수정</button>
							<button class="btn btn-primary btn-sm ml-1" id="deleteReply"
								onclick="deleteReply(${reply.eventReplyNo})">삭제</button>
						</div>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

<script>

// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
const loginMemberNo = "${loginMember.memberNo}";

// 현재 게시글 번호
const boardNo = ${board.eventBodNo};

// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
let beforeReplyRow;


//-----------------------------------------------------------------------------------------
// 댓글 등록
function addReply()	{
	
	// 작성된 댓글 내용 얻어오기
	const replyContent = $("#replyContent").val();
	
	// 로그인이 되어있지 않은 경우
	if(loginMemberNo == ""){
		swal("로그인 후 이용해 주세요.")
	}else{
		
		if(replyContent.trim() == ""){ // 작성된 댓글이 없을 경우
			swal("댓글 작성 후 클릭해주세요.");
		}else{
			// 로그인 O, 댓글 작성 O

			$.ajax({
				url : "${contextPath}/EventReply/insertReply", // 필수 속성
				type : "POST", 
				data : {"memberNo" : loginMemberNo,
						"boardNo" : boardNo,
						"replyContent" : replyContent},
						
				success : function(result){
					if(result >0){ // 댓글 삽입 성공
						swal({"icon" : "success" , "title" : "댓글 등록 성공"});
						$("#replyContent").val(""); // 댓글 작성 내용 삭제

						selectReplyList(); // 비동기로 댓글 목록 갱신
					}
				},
				error : function(){
					console.log("댓글 삽입 실패");	
					
				}
				
			});
			
		}
	}
	
}	
	


//-----------------------------------------------------------------------------------------
//해당 게시글 댓글 목록 조회
function selectReplyList(){
 $.ajax({
	 url : "${contextPath}/EventReply/list",
	 data : {"boardNo" : boardNo},
	 type : "POST",
	 dataType : "JSON", // 응답되는 데이터의 형식이 JSON임을 알려줌 -> 자바스크립트 객체로 변환됨
	 success : function(rList){
			 console.log(rList);
			 
	          $("#replyListArea").html(""); // 기존 정보 초기화 
	         // 왜? 새로 읽어온 댓글 목록으로 다시 만들어서 출력하려고!!
	         
	         
	         $.each(rList, function(index, item){
	        	 // $.each() : jQuery의 반복문
	        	 // rList : ajax 결과로 받은 댓글이 담겨있는 객체 배열
	        	 // index : 순차 접근 시 현재 인덱스
	        	 // item : 순차 접근 시 현재 접근한 배열 요소(댓글 객체 하나)
	        	 
	        	 
	        	 
	            
	            var li = $("<li>").addClass("reply-row");
	         
	            // 작성자, 작성일, 수정일 영역 
	            var div = $("<div>");
	            var rWriter = $("<p>").addClass("rWriter").text(item.memberNm);
	            var rDate = $("<p>").addClass("rDate").text("작성일 : " + item.eventReplyCreateDt);
	            div.append(rWriter).append(rDate)
	            
	            
	            // 댓글 내용
	            var rContent = $("<p>").addClass("rContent").html(item.eventReplyContent);
	            
	            
	            // 대댓글, 수정, 삭제 버튼 영역
	            var replyBtnArea = $("<div>").addClass("replyBtnArea");
	            
	            // 현재 댓글의 작성자와 로그인한 멤버의 아이디가 같을 때 버튼 추가
	            if(item.memberNo == loginMemberNo){
	               
	               // ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
	               var showUpdate = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("수정").attr("onclick", "showUpdateReply("+item.eventReplyNo+", this)");
	               var deleteReply = $("<button>").addClass("btn btn-primary btn-sm ml-1").text("삭제").attr("onclick", "deleteReply("+item.eventReplyNo+")");
	               
	               replyBtnArea.append(showUpdate).append(deleteReply);
	            }
	            
	            
	            // 댓글 요소 하나로 합치기
	            li.append(div).append(rContent).append(replyBtnArea);
	            
	            
	            // 합쳐진 댓글을 화면에 배치
	            $("#replyListArea").append(li);
	         });
			 
			 
			 
			 
		 },
		 error : function(){
			 console.log("댓글 목록 조회 실패");
		 }
	 
 });
}


// -----------------------------------------------------------------------------------------
// 일정 시간마다 댓글 목록 갱신
/* const replyInterval = setInteval(function(){
	selectReplyList();
}, 5000); */


// -----------------------------------------------------------------------------------------
// 댓글 수정 폼

function showUpdateReply(replyNo, el){
	// el : 수정 버튼 클릭 이벤트가 발생한 요소

	
	// 이미 열려있는 댓글 수정 창이 있을 경우 닫아주기
	   if($(".replyUpdateContent").length > 0){
	      $(".replyUpdateContent").eq(0).parent().html(beforeReplyRow);
	   }
	      
	   
	   // 댓글 수정화면 출력 전 요소를 저장해둠.
	   beforeReplyRow = $(el).parent().parent().html();
	
	   
	   
	   
	   // 작성되어있던 내용(수정 전 댓글 내용) 
	   var beforeContent = $(el).parent().prev().html();
	   
	   
	   
	   
	   
	   
	   // 이전 댓글 내용의 크로스사이트 스크립트 처리 해제, 개행문자 변경
	   // -> 자바스크립트에는 replaceAll() 메소드가 없으므로 정규 표현식을 이용하여 변경
	   beforeContent = beforeContent.replace(/&amp;/g, "&");   
	   beforeContent = beforeContent.replace(/&lt;/g, "<");   
	   beforeContent = beforeContent.replace(/&gt;/g, ">");   
	   beforeContent = beforeContent.replace(/&quot;/g, "\"");   
	   
	   beforeContent = beforeContent.replace(/<br>/g, "\n");   
	   
	   
	   // 기존 댓글 영역을 삭제하고 textarea를 추가 
	   $(el).parent().prev().remove();
	   var textarea = $("<textarea>").addClass("replyUpdateContent").attr("rows", "3").val(beforeContent);
	   $(el).parent().before(textarea);
	   
	   
	   // 수정 버튼
	   var updateReply = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("댓글 수정").attr("onclick", "updateReply(" + replyNo + ", this)");
	   
	   // 취소 버튼
	   var cancelBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1 mb-4").text("취소").attr("onclick", "updateCancel(this)");
	   
	   var replyBtnArea = $(el).parent();
	   
	   $(replyBtnArea).empty(); 
	   $(replyBtnArea).append(updateReply); 
	   $(replyBtnArea).append(cancelBtn); 
	   
	   
	   
}

//-----------------------------------------------------------------------------------------
//댓글 수정 취소 시 원래대로 돌아가기
function updateCancel(el){
	$(el).parent().parent().html(beforeReplyRow);
}


//-----------------------------------------------------------------------------------------
// 댓글 수정
function updateReply(replyNo, el){
	
	// 수정된 댓글 내용
	const replyContent = $(el).parent().prev().val();
	
	$.ajax({
		url : "${contextPath}/EventReply/updateReply",
		type : "POST",
		data : {"replyNo" : replyNo,
				"replyContent" : replyContent},
		success : function(result){
		if(result>0){
			swal({"icon" : "success" , "title" : "댓글 수정 성공"});
			selectReplyList();
		}
			
		},
		
		error : function(){
			console.log("댓글 수정 실패");
		}
	});
	
	
}


//-----------------------------------------------------------------------------------------
//댓글 삭제
function deleteReply(replyNo){

	   if(confirm("정말로 삭제하시겠습니까?")){
		      var url = "${contextPath}/EventReply/deleteReply";
		      
		      $.ajax({
		         url : url,
		         data : {"replyNo" : replyNo},
		         success : function(result){
		            if(result > 0){
		               selectReplyList(boardNo);
		               
		               swal({"icon" : "success" , "title" : "댓글 삭제 성공"});
		            }
		            
		         }, error : function(){
		            console.log("ajax 통신 실패");
		         }
		      });
		   }
	
	
}


</script>