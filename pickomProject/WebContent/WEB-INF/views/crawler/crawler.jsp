<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>crawler</title>
	 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<style>
	    div{
	        border: 1px solid red;
	    }
	
	    .input-align::-webkit-input-placeholder{text-align: center;}
	    .input-align{text-align: center;}
	</style>
	
</head>
<body>
        <div class="container">
        <div class="row justify-content-center text-center">
            <div class='col-sm-4'>
                <form class="d-flex" method="GET" action="${contextPath}/crawler/movie">
                    <div class="col-sm-6">
                        <button type="submit">영화 업데이트</button>
                    </div>
                    <div class="col-sm-6">
                    <input class="input-align" type="text" id="movieNo" name="movieNo" placeholder="영화코드" size="11">
                    </div>
                </form>
            </div>
            
            <div class='col-sm-4'>
                <form method="POST" action="${contextPath}/crawler/movie">
                    <button type="submit">전체 영화 업데이트</button>
                </form>
            </div>
            
            <div class='col-sm-4'>
                <form method="POST" action="${contextPath}/crawler/genre">
                    <button type="submit">장르 업데이트</button>
                </form>
            </div>
        </div>
    </div>
        
        <c:if test="${!empty title }">
			<script>
				swal({
					"icon"  : "${icon}",
					"title" : "${title}",
					"text"  : "${text}"
				});
			
			</script>
	
	
			<c:remove var="icon" />
			<c:remove var="title" />
			<c:remove var="text" />
		</c:if>
</body>
</html>