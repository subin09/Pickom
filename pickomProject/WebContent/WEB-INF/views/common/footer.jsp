<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

  <title>Footer</title>
  <style>
  	div#mainlog,div#mainmypage{
        text-align: center;
      }
      #mainlog-div{
        margin-left: 10%;
      }
      #mainmypage-div{
        margin-right: 10%;
      }

      input#mainsearch{
        width: 500px;
      }

      #mainheader{
        background-color: beige;
      }

      #mainmenutitle{
        font-weight: bold;
        color: black;
        text-align: center;
        width: 10em;
      }

      #mainmenutitle:hover{
        color: orange;
      }

      #maincustomerservice{
          margin-left: 10%;
      }

      body {
	      padding-top: 210px;
      }

  	
  </style>
</head>
<body>
	<div id="footer">
        <hr>
        <div class="d-flex bd-highlight" id="maincustomerservice">
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">서비스 이용약관</a></div>
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">개인정보처리방침</a></div>
          <div class='d-flex bd-highlight mr-sm-4'><a href="#">고객센터</a></div>
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