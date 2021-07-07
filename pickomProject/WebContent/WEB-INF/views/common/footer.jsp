<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Footer</title>

<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common/02_footer.css">
  
</head>

<body>
	<div id="footer">
        <hr>
        <div class="d-flex bd-highlight" id="maincustomerservice">
          <div class='d-flex bd-highlight mr-sm-4'>서비스 이용약관</div>
          <div class='d-flex bd-highlight mr-sm-4'>개인정보처리방침</div>
          <div class='d-flex bd-highlight mr-sm-4'>
          <a href="${contextPath}/complainBoard/list?type=0">불편사항</a></div>
        </div>
        <hr>
        <pre>
            (04540) 서울특별시 중구 xxxx 888 xxxx
            TEL. 02)8888-8888 | FAX. 02)888-8888 | 사업자등록번호 : xxx-xx-xxxx | 기관명 : xxxxxx | 대표자 : xxx
            
            Copyright © 2021-<span id="currentYear"></span> xx xxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxx All Right Reserved
        </pre>
      </div>
	
    <script>
	    var date = new Date();
	    var year = date.getFullYear(date);
	    $(document).ready(function(){
	      $("#currentYear").text(year);
	    });
    </script>
</body>
</html>