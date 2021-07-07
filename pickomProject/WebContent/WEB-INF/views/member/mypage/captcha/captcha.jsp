<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@500&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    
</head>
<body>

	   <div class="captcha-container">
        <div class="header">Security Check</div>
        <div class="securityCode">
            <p id="code">AabIl34</p>
            <div class="icons">
                <span class="readText">
                    <i class="fas fa-headphones"></i>
                </span>
                <span class="changeText">
                    <i class="fas fa-sync-alt"></i>
                </span>
            </div>
        </div>
        <div class="userInput">
            <input type="text" placeholder="Type the text here">
            <button type="submit" class="btn">Submit</button>
        </div>

    </div>

	
	
	<script src="https://code.responsivevoice.org/responsivevoice.js"></script>

	<script>
		const changeTextBtn = document.querySelector('.changeText');
		const readTextBtn = document.querySelector('.readText');
		const code = document.querySelector('#code');
		const input = document.querySelector('.userInput input');
		const submitBtn = document.querySelector('.btn');
	
		​
	
		changeTextBtn.onclick.(function(){
			  code.textContent = createCaptcha();
		});
			window.addEventListener('load', function(){
		    code.textContent = createCaptcha();
		});
	
		​
	
		function createCaptcha() {
	
		    let letters = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		    '0','1','2','3','4','5','6','7','8','9'];
	
		​
	
		    let a = letters[Math.floor(Math.random() * letters.length)];
		    let b = letters[Math.floor(Math.random() * letters.length)];
		    let c = letters[Math.floor(Math.random() * letters.length)];
		    let d = letters[Math.floor(Math.random() * letters.length)];
		    let e = letters[Math.floor(Math.random() * letters.length)];
		    let f = letters[Math.floor(Math.random() * letters.length)];
		    let g = letters[Math.floor(Math.random() * letters.length)];
	
		    let code = a + b + c + d + e + f + g;
		    return code;
		}
	
		​
	
		function() submitBtn {
	
		    let valueText = input.value;
		    if(valueText == '') {
		        alert('보안코드를 입력하세요.');
		    } else if(valueText === code.textContent) {
		        alert('코드를 맞게 입력했습니다.');
		    } else {
		        alert('코드를 잘 못 입력했습니다.');
		    }
	
		});
	
		​
	
		readTextBtn.addEventListener('click', function(){
	
		    let txt = code.textContent;
	
		    responsiveVoice.speak(txt);
	
		});
	</script>
</body>
</html>