<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
</head>
<body>
	 <h1>새비밀번호 설정</h1>
        <form action="newPw" method="post"  class="change-form">
            <div class="change-area">
                <label for="AuthUser">인증번호</label><br>
                <input type="text" id="AuthUser" name="AuthUser"  autocomplete="off" required>
            </div>
            
            <div class="change-area">
                <label for="memberPw">새 비밀번호</label><br>
                <input type="password" id="memberPw" name="memberPw"  autocomplete="off" required>
            </div>
         
           
          
            <button>비밀번호변경</button>
        </form>
</body>
</html>